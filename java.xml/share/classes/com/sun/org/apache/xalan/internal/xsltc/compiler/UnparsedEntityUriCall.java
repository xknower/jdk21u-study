package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @author Morten Jorgensen
 * @LastModified: Oct 2017
 */
final class UnparsedEntityUriCall extends FunctionCall {
    private Expression _entity;

    public UnparsedEntityUriCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
        _entity = argument();
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        final Type entity = _entity.typeCheck(stable);
        if (entity instanceof StringType == false) {
            _entity = new CastExpr(_entity, Type.String);
        }
        return _type = Type.String;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        // Feck the this pointer on the stack...
        il.append(methodGen.loadDOM());
        // ...then the entity name...
        _entity.translate(classGen, methodGen);
        // ...to get the URI from the DOM object.
        il.append(new INVOKEINTERFACE(
                         cpg.addInterfaceMethodref(DOM_INTF,
                                                   GET_UNPARSED_ENTITY_URI,
                                                   GET_UNPARSED_ENTITY_URI_SIG),
                         2));
    }
}
