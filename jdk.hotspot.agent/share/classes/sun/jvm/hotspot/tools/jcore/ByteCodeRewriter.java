package sun.jvm.hotspot.tools.jcore;

import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.interpreter.*;
import sun.jvm.hotspot.utilities.*;
import sun.jvm.hotspot.runtime.*;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class ByteCodeRewriter
{
    private Method method;
    private ConstantPool cpool;
    private ConstantPoolCache cpCache;
    private byte[] code;
    private Bytes  bytes;

    private static final int jintSize = 4;
    public static final boolean DEBUG;

    static {
        @SuppressWarnings("removal")
        String debug = AccessController.doPrivileged(
            new PrivilegedAction<>() {
                public String run() {
                    return System.getProperty("sun.jvm.hotspot.tools.jcore.ByteCodeRewriter.DEBUG");
                }
            }
        );
        DEBUG = (debug != null ? debug.equalsIgnoreCase("true") : false);
    }


    protected void debugMessage(String message) {
        System.out.println(message);
    }

    public ByteCodeRewriter(Method method, ConstantPool cpool, byte[] code) {
        this.method = method;
        this.cpool = cpool;
        this.cpCache = cpool.getCache();
        this.code = code;
        this.bytes = VM.getVM().getBytes();

    }

    protected short getConstantPoolIndexFromRefMap(int rawcode, int bci) {
        int refIndex;
        String fmt = Bytecodes.format(rawcode);
        switch (fmt.length()) {
            case 2: refIndex = 0xFF & method.getBytecodeByteArg(bci); break;
            case 3: refIndex = 0xFFFF & bytes.swapShort(method.getBytecodeShortArg(bci)); break;
            default: throw new IllegalArgumentException();
        }

        return (short)cpool.objectToCPIndex(refIndex);
     }

    protected short getConstantPoolIndex(int rawcode, int bci) {
       // get ConstantPool index from ConstantPoolCacheIndex at given bci
       String fmt = Bytecodes.format(rawcode);
       int cpCacheIndex;
       switch (fmt.length()) {
       case 2: cpCacheIndex = method.getBytecodeByteArg(bci); break;
       case 3: cpCacheIndex = method.getBytecodeShortArg(bci); break;
       case 5:
           if (fmt.contains("__"))
               cpCacheIndex = method.getBytecodeShortArg(bci);
           else
               cpCacheIndex = method.getBytecodeIntArg(bci);
           break;
       default: throw new IllegalArgumentException();
       }

       if (cpCache == null) {
          return (short) cpCacheIndex;
       } else if (fmt.contains("JJJJ")) {
          // Invokedynamic require special handling
          cpCacheIndex = ~cpCacheIndex;
          cpCacheIndex = bytes.swapInt(cpCacheIndex);
          return (short) cpCache.getEntryAt(cpCacheIndex).getConstantPoolIndex();
       } else if (fmt.contains("JJ")) {
          // change byte-ordering and go via cache
          return (short) cpCache.getEntryAt((int) (0xFFFF & bytes.swapShort((short)cpCacheIndex))).getConstantPoolIndex();
       } else if (fmt.contains("j")) {
          // go via cache
          return (short) cpCache.getEntryAt((int) (0xFF & cpCacheIndex)).getConstantPoolIndex();
       } else {
          return (short) cpCacheIndex;
       }
    }

    private static void writeShort(byte[] buf, int index, short value) {
        buf[index] = (byte) ((value >> 8) & 0x00FF);
        buf[index + 1] = (byte) (value & 0x00FF);
    }

    public void rewrite() {
        int bytecode = Bytecodes._illegal;
        int hotspotcode = Bytecodes._illegal;
        int len = 0;

        if (DEBUG) {
            String msg = method.getMethodHolder().getName().asString() + "." +
                         method.getName().asString() +
                         method.getSignature().asString();
            debugMessage(msg);
        }
        for (int bci = 0; bci < code.length;) {
            hotspotcode = Bytecodes.codeAt(method, bci);
            bytecode = Bytecodes.javaCode(hotspotcode);

            if (Assert.ASSERTS_ENABLED) {
                int code_from_buffer = 0xFF & code[bci];
                Assert.that(code_from_buffer == hotspotcode
                          || code_from_buffer == Bytecodes._breakpoint,
                          "Unexpected bytecode found in method bytecode buffer!");
            }

            // update the code buffer hotspot specific bytecode with the jvm bytecode
            code[bci] = (byte) (0xFF & bytecode);

            short cpoolIndex = 0;
            switch (bytecode) {
                // bytecodes with ConstantPoolCache index
                case Bytecodes._getstatic:
                case Bytecodes._putstatic:
                case Bytecodes._getfield:
                case Bytecodes._putfield:
                case Bytecodes._invokevirtual:
                case Bytecodes._invokespecial:
                case Bytecodes._invokestatic:
                case Bytecodes._invokeinterface: {
                    cpoolIndex = getConstantPoolIndex(hotspotcode, bci + 1);
                    writeShort(code, bci + 1, cpoolIndex);
                    break;
                }

                case Bytecodes._invokedynamic:
                    cpoolIndex = getConstantPoolIndex(hotspotcode, bci + 1);
                    writeShort(code, bci + 1, cpoolIndex);
                    writeShort(code, bci + 3, (short)0);  // clear out trailing bytes
                    break;

                case Bytecodes._ldc_w:
                    if (hotspotcode != bytecode) {
                        // fast_aldc_w puts constant in reference map
                        cpoolIndex = getConstantPoolIndexFromRefMap(hotspotcode, bci + 1);
                        writeShort(code, bci + 1, cpoolIndex);
                    }
                    break;
                case Bytecodes._ldc:
                    if (hotspotcode != bytecode) {
                        // fast_aldc puts constant in reference map
                        cpoolIndex = getConstantPoolIndexFromRefMap(hotspotcode, bci + 1);
                        code[bci + 1] = (byte)(cpoolIndex);
                    }
                    break;
            }

            len = Bytecodes.lengthFor(bytecode);
            if (len <= 0) len = Bytecodes.lengthAt(method, bci);

            if (DEBUG) {
                String operand = "";
                switch (len) {
                   case 2:
                        operand += code[bci + 1];
                        break;
                   case 3:
                        operand += (cpoolIndex != 0)? cpoolIndex :
                                            method.getBytecodeShortArg(bci + 1);
                        break;
                   case 5:
                        operand += method.getBytecodeIntArg(bci + 1);
                        break;
                }

                // the operand following # is not quite like javap output.
                // in particular, for goto & goto_w, the operand is PC relative
                // offset for jump. Javap adds relative offset with current PC
                // to give absolute bci to jump to.

                String message = "\t\t" + bci + " " + Bytecodes.name(bytecode);
                if (hotspotcode != bytecode)
                    message += " [" + Bytecodes.name(hotspotcode) + "]";
                if (operand != "")
                    message += " #" + operand;

                if (DEBUG) debugMessage(message);
            }

            bci += len;
        }
    }
}
