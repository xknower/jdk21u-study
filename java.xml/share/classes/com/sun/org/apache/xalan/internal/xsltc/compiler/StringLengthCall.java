package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.util.List;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @LastModified: Oct 2017
 */
final class StringLengthCall extends FunctionCall {
    public StringLengthCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (argumentCount() > 0) {
            argument().translate(classGen, methodGen);
        }
        else {
            il.append(methodGen.loadContextNode());
            Type.Node.translateTo(classGen, methodGen, Type.String);
        }
        il.append(new INVOKESTATIC(cpg.addMethodref(BASIS_LIBRARY_CLASS,
                                                     "getStringLength",
                                                     "(Ljava/lang/String;)I")));
    }
}
