package com.sun.tools.javac.jvm;

import com.sun.tools.javac.util.ByteBuffer;
import com.sun.tools.javac.util.ByteBuffer.UnderflowException;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.Name.NameMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.tools.JavaFileObject;

import static com.sun.tools.javac.jvm.ClassFile.*;


/**
 * Stripped down ClassReader, just sufficient to read module names from module-info.class files
 * while analyzing the module path.
 *
 * <p>
 * <b>This is NOT part of any supported API. If you write code that depends on this, you do so at
 * your own risk. This code and its internal interfaces are subject to change or deletion without
 * notice.</b>
 */
public class ModuleNameReader {
    public static class BadClassFile extends Exception {
        private static final long serialVersionUID = 0;
        BadClassFile(String msg) {
            super(msg);
        }
    }

    private static final int INITIAL_BUFFER_SIZE = 0x0fff0;

    /** The buffer containing the currently read class file.
     */
    private ByteBuffer buf = new ByteBuffer(INITIAL_BUFFER_SIZE);

    /** The current input pointer.
     */
    private int bp;

    /** The constant pool reader.
     */
    private PoolReader reader;

    public ModuleNameReader() {
    }

    public String readModuleName(Path p) throws IOException, BadClassFile {
        try (InputStream in = Files.newInputStream(p)) {
            return readModuleName(in);
        }
    }

    public String readModuleName(JavaFileObject jfo) throws IOException, BadClassFile {
        try (InputStream in = jfo.openInputStream()) {
            return readModuleName(in);
        }
    }

    public String readModuleName(InputStream in) throws IOException, BadClassFile {
        bp = 0;
        buf.reset();
        buf.appendStream(in);

        int magic = nextInt();
        if (magic != JAVA_MAGIC)
            throw new BadClassFile("illegal.start.of.class.file");

        int minorVersion = nextChar();
        int majorVersion = nextChar();
        if (majorVersion < 53)
            throw new BadClassFile("bad major version number for module: " + majorVersion);

        reader = new PoolReader(buf);
        bp = reader.readPool(buf, bp);

        int access_flags = nextChar();
        if (access_flags != 0x8000)
            throw new BadClassFile("invalid access flags for module: 0x" + Integer.toHexString(access_flags));

        int this_class = nextChar();
        // could, should, check this_class == CONSTANT_Class("module-info")
        checkZero(nextChar(), "super_class");
        checkZero(nextChar(), "interface_count");
        checkZero(nextChar(), "fields_count");
        checkZero(nextChar(), "methods_count");
        int attributes_count = nextChar();
        for (int i = 0; i < attributes_count; i++) {
            int attr_name = nextChar();
            int attr_length = nextInt();
            if (reader.peekName(attr_name, utf8Mapper(false)).equals("Module") && attr_length > 2) {
                return reader.peekModuleName(nextChar(), utf8Mapper(true));
            } else {
                // skip over unknown attributes
                bp += attr_length;
            }
        }
        throw new BadClassFile("no Module attribute");
    }

    void checkZero(int count, String name) throws BadClassFile {
        if (count != 0)
            throw new BadClassFile("invalid " + name + " for module: " + count);
    }

    /** Read a character.
     */
    char nextChar() throws BadClassFile {
        char res;
        try {
            res = buf.getChar(bp);
        } catch (UnderflowException e) {
            throw new BadClassFile("class file truncated at offset " + e.getLength());
        }
        bp += 2;
        return res;
    }

    /** Read an integer.
     */
    int nextInt() throws BadClassFile {
        int res;
        try {
            res = buf.getInt(bp);
        } catch (UnderflowException e) {
            throw new BadClassFile("class file truncated at offset " + e.getLength());
        }
        bp += 4;
        return res;
    }

    PoolReader.Utf8Mapper<String> utf8Mapper(boolean internalize) {
        return internalize ?
            (buf, offset, len) -> {
                buf = ClassFile.internalize(buf, offset, len);
                return Convert.utf2string(buf, 0, buf.length, Convert.Validation.STRICT);
            } :
            (buf, offset, len) -> Convert.utf2string(buf, offset, len, Convert.Validation.STRICT);
    }

}
