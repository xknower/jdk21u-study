package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.util.List;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @LastModified: Oct 2017
 */
final class NotCall extends FunctionCall {
    public NotCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        argument().translate(classGen, methodGen);
        il.append(ICONST_1);
        il.append(IXOR);
    }

    public void translateDesynthesized(ClassGenerator classGen,
                                       MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        final Expression exp = argument();
        exp.translateDesynthesized(classGen, methodGen);
        final BranchHandle gotoh = il.append(new GOTO(null));
        _trueList = exp._falseList;     // swap flow lists
        _falseList = exp._trueList;
        _falseList.add(gotoh);
    }
}
