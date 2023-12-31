package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Stylesheet;

/**
 * This class implements auxiliary classes needed to compile
 * patterns in <tt>xsl:number</tt>. These classes inherit from
 * {Any,Single,Multiple}NodeCounter and override the
 * <tt>matchFrom</tt> and <tt>matchCount</tt> methods.
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 */
public final class NodeCounterGenerator extends ClassGenerator {
    private Instruction _aloadTranslet;

    public NodeCounterGenerator(String className,
                                String superClassName,
                                String fileName,
                                int accessFlags,
                                String[] interfaces,
                                Stylesheet stylesheet) {
        super(className, superClassName, fileName,
              accessFlags, interfaces, stylesheet);
    }

    /**
     * Set the index of the register where "this" (the pointer to
     * the translet) is stored.
     */
    public void setTransletIndex(int index) {
        _aloadTranslet = new ALOAD(index);
    }

    /**
     * The index of the translet pointer within the execution of
     * matchFrom or matchCount.
     * Overridden from ClassGenerator.
     */
    public Instruction loadTranslet() {
        return _aloadTranslet;
    }

    /**
     * Returns <tt>true</tt> since this class is external to the
     * translet.
     */
    public boolean isExternal() {
        return true;
    }
}
