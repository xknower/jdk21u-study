package jdk.internal.classfile.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import jdk.internal.classfile.ClassSignature;
import jdk.internal.classfile.MethodSignature;
import jdk.internal.classfile.Signature;
import jdk.internal.classfile.Signature.*;

public final class SignaturesImpl {

    public SignaturesImpl() {
    }

    private String sig;
    private int sigp;

    public ClassSignature parseClassSignature(String signature) {
        this.sig = signature;
        sigp = 0;
        List<TypeParam> typeParamTypes = parseParamTypes();
        RefTypeSig superclass = referenceTypeSig();
        ArrayList<RefTypeSig> superinterfaces = null;
        while (sigp < sig.length()) {
            if (superinterfaces == null)
                superinterfaces = new ArrayList<>();
            superinterfaces.add(referenceTypeSig());
        }
        return new ClassSignatureImpl(typeParamTypes, superclass, null2Empty(superinterfaces));
    }

    public MethodSignature parseMethodSignature(String signature) {
        this.sig = signature;
        sigp = 0;
        List<TypeParam> typeParamTypes = parseParamTypes();
        assert sig.charAt(sigp) == '(';
        sigp++;
        ArrayList<Signature> paramTypes = null;
        while (sig.charAt(sigp) != ')') {
            if (paramTypes == null)
                 paramTypes = new ArrayList<>();
            paramTypes.add(typeSig());
        }
        sigp++;
        Signature returnType = typeSig();
        ArrayList<ThrowableSig> throwsTypes = null;
        while (sigp < sig.length() && sig.charAt(sigp) == '^') {
            sigp++;
            if (throwsTypes == null)
                throwsTypes = new ArrayList<>();
            var t = typeSig();
            if (t instanceof ThrowableSig ts)
                throwsTypes.add(ts);
            else
                throw new IllegalArgumentException("not a valid type signature: " + sig);
        }
        return new MethodSignatureImpl(typeParamTypes, null2Empty(throwsTypes), returnType, null2Empty(paramTypes));
    }

    public Signature parseSignature(String signature) {
        this.sig = signature;
        sigp = 0;
        return typeSig();
    }

    private List<TypeParam> parseParamTypes() {
        ArrayList<TypeParam> typeParamTypes = null;
        if (sig.charAt(sigp) == '<') {
            sigp++;
            typeParamTypes = new ArrayList<>();
            while (sig.charAt(sigp) != '>') {
                int sep = sig.indexOf(":", sigp);
                String name = sig.substring(sigp, sep);
                RefTypeSig classBound = null;
                ArrayList<RefTypeSig> interfaceBounds = null;
                sigp = sep + 1;
                if (sig.charAt(sigp) != ':')
                    classBound = referenceTypeSig();
                while (sig.charAt(sigp) == ':') {
                    sigp++;
                    if (interfaceBounds == null)
                        interfaceBounds = new ArrayList<>();
                    interfaceBounds.add(referenceTypeSig());
                }
                typeParamTypes.add(new TypeParamImpl(name, Optional.ofNullable(classBound), null2Empty(interfaceBounds)));
            }
            sigp++;
        }
        return null2Empty(typeParamTypes);
    }

    private Signature typeSig() {
        char c = sig.charAt(sigp++);
        switch (c) {
            case 'B','C','D','F','I','J','V','S','Z': return Signature.BaseTypeSig.of(c);
            default:
                sigp--;
                return referenceTypeSig();
        }
    }

    private RefTypeSig referenceTypeSig() {
        char c = sig.charAt(sigp++);
        switch (c) {
            case 'L':
                StringBuilder sb = new StringBuilder();
                ArrayList<TypeArg> argTypes = null;
                Signature.ClassTypeSig t = null;
                char sigch ;
                do {
                    switch  (sigch = sig.charAt(sigp++)) {
                        case '<' -> {
                            argTypes = new ArrayList<>();
                            while (sig.charAt(sigp) != '>')
                                argTypes.add(typeArg());
                            sigp++;
                        }
                        case '.',';' -> {
                            t = new ClassTypeSigImpl(Optional.ofNullable(t), sb.toString(), null2Empty(argTypes));
                            sb.setLength(0);
                            argTypes = null;
                        }
                        default -> sb.append(sigch);
                    }
                } while (sigch != ';');
                return t;
            case 'T':
                int sep = sig.indexOf(';', sigp);
                var ty = Signature.TypeVarSig.of(sig.substring(sigp, sep));
                sigp = sep + 1;
                return ty;
            case '[': return ArrayTypeSig.of(typeSig());
        }
        throw new IllegalArgumentException("not a valid type signature: " + sig);
    }

    private TypeArg typeArg() {
        char c = sig.charAt(sigp++);
        switch (c) {
            case '*': return TypeArg.unbounded();
            case '+': return TypeArg.extendsOf(referenceTypeSig());
            case '-': return TypeArg.superOf(referenceTypeSig());
            default:
                sigp--;
                return TypeArg.of(referenceTypeSig());
        }
    }

    public static record BaseTypeSigImpl(char baseType) implements Signature.BaseTypeSig {

        @Override
        public String signatureString() {
            return "" + baseType;
        }
    }

    public static record TypeVarSigImpl(String identifier) implements Signature.TypeVarSig {

        @Override
        public String signatureString() {
            return "T" + identifier + ';';
        }
    }

    public static record ArrayTypeSigImpl(int arrayDepth, Signature elemType) implements Signature.ArrayTypeSig {

        @Override
        public Signature componentSignature() {
            return arrayDepth > 1 ? new ArrayTypeSigImpl(arrayDepth - 1, elemType) : elemType;
        }

        @Override
        public String signatureString() {
            return "[".repeat(arrayDepth) + elemType.signatureString();
        }
    }

    public static record ClassTypeSigImpl(Optional<ClassTypeSig> outerType, String className, List<Signature.TypeArg> typeArgs)
            implements Signature.ClassTypeSig {

        @Override
        public String signatureString() {
            String prefix = "L";
            if (outerType.isPresent()) {
                prefix = outerType.get().signatureString();
                assert prefix.charAt(prefix.length() - 1) == ';';
                prefix = prefix.substring(0, prefix.length() - 1) + '.';
            }
            String suffix = ";";
            if (!typeArgs.isEmpty()) {
                var sb = new StringBuilder();
                sb.append('<');
                for (var ta : typeArgs)
                    sb.append(((TypeArgImpl)ta).signatureString());
                suffix = sb.append(">;").toString();
            }
            return prefix + className + suffix;
        }
    }

    public static record TypeArgImpl(WildcardIndicator wildcardIndicator, Optional<RefTypeSig> boundType) implements Signature.TypeArg {

        public String signatureString() {
            return switch (wildcardIndicator) {
                case DEFAULT -> boundType.get().signatureString();
                case EXTENDS -> "+" + boundType.get().signatureString();
                case SUPER -> "-" + boundType.get().signatureString();
                case UNBOUNDED -> "*";
            };
        }
    }

    public static record TypeParamImpl(String identifier, Optional<RefTypeSig> classBound, List<RefTypeSig> interfaceBounds)
            implements TypeParam {
    }

    private static StringBuilder printTypeParameters(List<TypeParam> typeParameters) {
        var sb = new StringBuilder();
        if (typeParameters != null && !typeParameters.isEmpty()) {
            sb.append('<');
            for (var tp : typeParameters) {
                sb.append(tp.identifier()).append(':');
                if (tp.classBound().isPresent())
                    sb.append(tp.classBound().get().signatureString());
                if (tp.interfaceBounds() != null) for (var is : tp.interfaceBounds())
                    sb.append(':').append(is.signatureString());
            }
            sb.append('>');
        }
        return sb;
    }

    public static record ClassSignatureImpl(List<TypeParam> typeParameters, RefTypeSig superclassSignature,
            List<RefTypeSig> superinterfaceSignatures) implements ClassSignature {

        @Override
        public String signatureString() {
            var sb = printTypeParameters(typeParameters);
            sb.append(superclassSignature.signatureString());
            if (superinterfaceSignatures != null) for (var in : superinterfaceSignatures)
                sb.append(in.signatureString());
            return sb.toString();
        }
    }

    public static record MethodSignatureImpl(
            List<TypeParam> typeParameters,
            List<ThrowableSig> throwableSignatures,
            Signature result,
            List<Signature> arguments) implements MethodSignature {

        @Override
        public String signatureString() {
            var sb = printTypeParameters(typeParameters);
            sb.append('(');
            for (var a : arguments)
                sb.append(a.signatureString());
            sb.append(')').append(result.signatureString());
            if (!throwableSignatures.isEmpty())
                for (var t : throwableSignatures)
                    sb.append('^').append(t.signatureString());
            return sb.toString();
        }
    }

    private static <T> List<T> null2Empty(ArrayList<T> l) {
        return l == null ? List.of() : Collections.unmodifiableList(l);
    }
}
