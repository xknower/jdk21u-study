package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @LastModified: Oct 2017
 */
final class UnaryOpExpr extends Expression {
    private Expression _left;

    public UnaryOpExpr(Expression left) {
        (_left = left).setParent(this);
    }

    /**
     * Returns true if this expressions contains a call to position(). This is
     * needed for context changes in node steps containing multiple predicates.
     */
    public boolean hasPositionCall() {
        return(_left.hasPositionCall());
    }

    /**
     * Returns true if this expressions contains a call to last()
     */
    public boolean hasLastCall() {
            return(_left.hasLastCall());
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        _left.setParser(parser);
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        final Type tleft = _left.typeCheck(stable);
        final MethodType ptype = lookupPrimop(stable, "u-",
                                              new MethodType(Type.Void,
                                                             tleft));

        if (ptype != null) {
            final Type arg1 = ptype.argsType().get(0);
            if (!arg1.identicalTo(tleft)) {
                _left = new CastExpr(_left, arg1);
            }
            return _type = ptype.resultType();
        }

        throw new TypeCheckError(this);
    }

    public String toString() {
        return "u-" + '(' + _left + ')';
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        InstructionList il = methodGen.getInstructionList();
        _left.translate(classGen, methodGen);
        il.append(_type.NEG());
    }
}
