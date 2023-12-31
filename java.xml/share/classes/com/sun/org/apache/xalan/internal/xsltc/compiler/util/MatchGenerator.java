package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.Type;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 */
public final class MatchGenerator extends MethodGenerator {
    private static int CURRENT_INDEX = 1;

    private int _iteratorIndex = INVALID_INDEX;

    private final Instruction _iloadCurrent;
    private final Instruction _istoreCurrent;
    private Instruction _aloadDom;

    public MatchGenerator(int access_flags, Type return_type,
                          Type[] arg_types, String[] arg_names,
                          String method_name, String class_name,
                          InstructionList il, ConstantPoolGen cp) {
        super(access_flags, return_type, arg_types, arg_names, method_name,
              class_name, il, cp);

        _iloadCurrent = new ILOAD(CURRENT_INDEX);
        _istoreCurrent = new ISTORE(CURRENT_INDEX);
    }

    public Instruction loadCurrentNode() {
        return _iloadCurrent;
    }

    public Instruction storeCurrentNode() {
        return _istoreCurrent;
    }

    public int getHandlerIndex() {
        return INVALID_INDEX;           // not available
    }

    /**
     * Get index of the register where the DOM is stored.
     */
    public Instruction loadDOM() {
        return _aloadDom;
    }

    /**
     * Set index where the reference to the DOM is stored.
     */
    public void setDomIndex(int domIndex) {
        _aloadDom = new ALOAD(domIndex);
    }

    /**
     * Get index of the register where the current iterator is stored.
     */
    public int getIteratorIndex() {
        return _iteratorIndex;
    }

    /**
     * Set index of the register where the current iterator is stored.
     */
    public void setIteratorIndex(int iteratorIndex) {
        _iteratorIndex = iteratorIndex;
    }

    public int getLocalIndex(String name) {
        if (name.equals("current")) {
            return CURRENT_INDEX;
        }
        return super.getLocalIndex(name);
    }
}
