package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.IFLT;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @author Morten Jorgensen
 * @LastModified: Oct 2017
 */
final class ContainsCall extends FunctionCall {

    private Expression _base = null;
    private Expression _token = null;

    /**
     * Create a contains() call - two arguments, both strings
     */
    public ContainsCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
    }

    /**
     * This XPath function returns true/false values
     */
    public boolean isBoolean() {
        return true;
    }

    /**
     * Type check the two parameters for this function
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {

        // Check that the function was passed exactly two arguments
        if (argumentCount() != 2) {
            throw new TypeCheckError(ErrorMsg.ILLEGAL_ARG_ERR, getName(), this);
        }

        // The first argument must be a String, or cast to a String
        _base = argument(0);
        Type baseType = _base.typeCheck(stable);
        if (baseType != Type.String)
            _base = new CastExpr(_base, Type.String);

        // The second argument must also be a String, or cast to a String
        _token = argument(1);
        Type tokenType = _token.typeCheck(stable);
        if (tokenType != Type.String)
            _token = new CastExpr(_token, Type.String);

        return _type = Type.Boolean;
    }

    /**
     * Compile the expression - leave boolean expression on stack
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        translateDesynthesized(classGen, methodGen);
        synthesize(classGen, methodGen);
    }

    /**
     * Compile expression and update true/false-lists
     */
    public void translateDesynthesized(ClassGenerator classGen,
                                       MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        _base.translate(classGen, methodGen);
        _token.translate(classGen, methodGen);
        il.append(new INVOKEVIRTUAL(cpg.addMethodref(STRING_CLASS,
                                                     "indexOf",
                                                     "("+STRING_SIG+")I")));
        _falseList.add(il.append(new IFLT(null)));
    }
}
