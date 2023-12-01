package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.util.List;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @LastModified: Oct 2017
 */
final class FloorCall extends FunctionCall {
    public FloorCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        argument().translate(classGen, methodGen);
        methodGen.getInstructionList()
            .append(new INVOKESTATIC(classGen.getConstantPool()
                                     .addMethodref(MATH_CLASS,
                                                   "floor", "(D)D")));
    }
}
