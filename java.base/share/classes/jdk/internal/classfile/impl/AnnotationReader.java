package jdk.internal.classfile.impl;

import jdk.internal.classfile.Annotation;
import jdk.internal.classfile.AnnotationElement;
import jdk.internal.classfile.AnnotationValue;
import jdk.internal.classfile.ClassReader;
import jdk.internal.classfile.constantpool.*;
import jdk.internal.classfile.TypeAnnotation;
import static jdk.internal.classfile.Classfile.*;
import static jdk.internal.classfile.TypeAnnotation.TargetInfo.*;

import java.util.List;
import jdk.internal.classfile.Label;
import jdk.internal.classfile.constantpool.Utf8Entry;
import jdk.internal.access.SharedSecrets;

class AnnotationReader {
    private AnnotationReader() { }

    public static List<Annotation> readAnnotations(ClassReader classReader, int p) {
        int pos = p;
        int numAnnotations = classReader.readU2(pos);
        var annos = new Object[numAnnotations];
        pos += 2;
        for (int i = 0; i < numAnnotations; ++i) {
            annos[i] = readAnnotation(classReader, pos);
            pos = skipAnnotation(classReader, pos);
        }
        return SharedSecrets.getJavaUtilCollectionAccess().listFromTrustedArrayNullsAllowed(annos);
    }

    public static AnnotationValue readElementValue(ClassReader classReader, int p) {
        char tag = (char) classReader.readU1(p);
        ++p;
        return switch (tag) {
            case 'B' -> new AnnotationImpl.OfByteImpl((IntegerEntry)classReader.readEntry(p));
            case 'C' -> new AnnotationImpl.OfCharacterImpl((IntegerEntry)classReader.readEntry(p));
            case 'D' -> new AnnotationImpl.OfDoubleImpl((DoubleEntry)classReader.readEntry(p));
            case 'F' -> new AnnotationImpl.OfFloatImpl((FloatEntry)classReader.readEntry(p));
            case 'I' -> new AnnotationImpl.OfIntegerImpl((IntegerEntry)classReader.readEntry(p));
            case 'J' -> new AnnotationImpl.OfLongImpl((LongEntry)classReader.readEntry(p));
            case 'S' -> new AnnotationImpl.OfShortImpl((IntegerEntry)classReader.readEntry(p));
            case 'Z' -> new AnnotationImpl.OfBooleanImpl((IntegerEntry)classReader.readEntry(p));
            case 's' -> new AnnotationImpl.OfStringImpl(classReader.readUtf8Entry(p));
            case 'e' -> new AnnotationImpl.OfEnumImpl(classReader.readUtf8Entry(p), classReader.readUtf8Entry(p + 2));
            case 'c' -> new AnnotationImpl.OfClassImpl(classReader.readUtf8Entry(p));
            case '@' -> new AnnotationImpl.OfAnnotationImpl(readAnnotation(classReader, p));
            case '[' -> {
                int numValues = classReader.readU2(p);
                p += 2;
                var values = new Object[numValues];
                for (int i = 0; i < numValues; ++i) {
                    values[i] = readElementValue(classReader, p);
                    p = skipElementValue(classReader, p);
                }
                yield new AnnotationImpl.OfArrayImpl(SharedSecrets.getJavaUtilCollectionAccess().listFromTrustedArrayNullsAllowed(values));
            }
            default -> throw new IllegalArgumentException(
                    "Unexpected tag '%s' in AnnotationValue, pos = %d".formatted(tag, p - 1));
        };
    }

    public static List<TypeAnnotation> readTypeAnnotations(ClassReader classReader, int p, LabelContext lc) {
        int numTypeAnnotations = classReader.readU2(p);
        p += 2;
        var annotations = new Object[numTypeAnnotations];
        for (int i = 0; i < numTypeAnnotations; ++i) {
            annotations[i] = readTypeAnnotation(classReader, p, lc);
            p = skipTypeAnnotation(classReader, p);
        }
        return SharedSecrets.getJavaUtilCollectionAccess().listFromTrustedArrayNullsAllowed(annotations);
    }

    public static List<List<Annotation>> readParameterAnnotations(ClassReader classReader, int p) {
        int cnt = classReader.readU1(p++);
        var pas = new Object[cnt];
        for (int i = 0; i < cnt; ++i) {
            pas[i] = readAnnotations(classReader, p);
            p = skipAnnotations(classReader, p);
        }
        return SharedSecrets.getJavaUtilCollectionAccess().listFromTrustedArrayNullsAllowed(pas);
    }

    private static int skipElementValue(ClassReader classReader, int p) {
        char tag = (char) classReader.readU1(p);
        ++p;
        return switch (tag) {
            case 'B', 'C', 'D', 'F', 'I', 'J', 'S', 'Z', 's', 'c' -> p + 2;
            case 'e' -> p + 4;
            case '@' -> skipAnnotation(classReader, p);
            case '[' -> {
                int numValues = classReader.readU2(p);
                p += 2;
                for (int i = 0; i < numValues; ++i) {
                    p = skipElementValue(classReader, p);
                }
                yield p;
            }
            default -> throw new IllegalArgumentException(
                    "Unexpected tag '%s' in AnnotationValue, pos = %d".formatted(tag, p - 1));
        };
    }

    private static Annotation readAnnotation(ClassReader classReader, int p) {
        Utf8Entry annotationClass = classReader.utf8EntryByIndex(classReader.readU2(p));
        p += 2;
        List<AnnotationElement> elems = readAnnotationElementValuePairs(classReader, p);
        return new AnnotationImpl(annotationClass, elems);
    }

    private static int skipAnnotations(ClassReader classReader, int p) {
        int numAnnotations = classReader.readU2(p);
        p += 2;
        for (int i = 0; i < numAnnotations; ++i)
            p = skipAnnotation(classReader, p);
        return p;
    }

    private static int skipAnnotation(ClassReader classReader, int p) {
        return skipElementValuePairs(classReader, p + 2);
    }

    private static List<AnnotationElement> readAnnotationElementValuePairs(ClassReader classReader, int p) {
        int numElementValuePairs = classReader.readU2(p);
        p += 2;
        var annotationElements = new Object[numElementValuePairs];
        for (int i = 0; i < numElementValuePairs; ++i) {
            Utf8Entry elementName = classReader.readUtf8Entry(p);
            p += 2;
            AnnotationValue value = readElementValue(classReader, p);
            annotationElements[i] = new AnnotationImpl.AnnotationElementImpl(elementName, value);
            p = skipElementValue(classReader, p);
        }
        return SharedSecrets.getJavaUtilCollectionAccess().listFromTrustedArrayNullsAllowed(annotationElements);
    }

    private static int skipElementValuePairs(ClassReader classReader, int p) {
        int numElementValuePairs = classReader.readU2(p);
        p += 2;
        for (int i = 0; i < numElementValuePairs; ++i) {
            p = skipElementValue(classReader, p + 2);
        }
        return p;
    }

    private static Label getLabel(LabelContext lc, int bciOffset, int targetType, int p) {
        //helper method to avoid NPE
        if (lc == null) throw new IllegalArgumentException("Unexpected targetType '%d' in TypeAnnotation outside of Code attribute, pos = %d".formatted(targetType, p - 1));
        return lc.getLabel(bciOffset);
    }

    private static TypeAnnotation readTypeAnnotation(ClassReader classReader, int p, LabelContext lc) {
        int targetType = classReader.readU1(p++);
        var targetInfo = switch (targetType) {
            case TAT_CLASS_TYPE_PARAMETER ->
                ofClassTypeParameter(classReader.readU1(p));
            case TAT_METHOD_TYPE_PARAMETER ->
                ofMethodTypeParameter(classReader.readU1(p));
            case TAT_CLASS_EXTENDS ->
                ofClassExtends(classReader.readU2(p));
            case TAT_CLASS_TYPE_PARAMETER_BOUND ->
                ofClassTypeParameterBound(classReader.readU1(p), classReader.readU1(p + 1));
            case TAT_METHOD_TYPE_PARAMETER_BOUND ->
                ofMethodTypeParameterBound(classReader.readU1(p), classReader.readU1(p + 1));
            case TAT_FIELD ->
                ofField();
            case TAT_METHOD_RETURN ->
                ofMethodReturn();
            case TAT_METHOD_RECEIVER ->
                ofMethodReceiver();
            case TAT_METHOD_FORMAL_PARAMETER ->
                ofMethodFormalParameter(classReader.readU1(p));
            case TAT_THROWS ->
                ofThrows(classReader.readU2(p));
            case TAT_LOCAL_VARIABLE ->
                ofLocalVariable(readLocalVarEntries(classReader, p, lc, targetType));
            case TAT_RESOURCE_VARIABLE ->
                ofResourceVariable(readLocalVarEntries(classReader, p, lc, targetType));
            case TAT_EXCEPTION_PARAMETER ->
                ofExceptionParameter(classReader.readU2(p));
            case TAT_INSTANCEOF ->
                ofInstanceofExpr(getLabel(lc, classReader.readU2(p), targetType, p));
            case TAT_NEW ->
                ofNewExpr(getLabel(lc, classReader.readU2(p), targetType, p));
            case TAT_CONSTRUCTOR_REFERENCE ->
                ofConstructorReference(getLabel(lc, classReader.readU2(p), targetType, p));
            case TAT_METHOD_REFERENCE ->
                ofMethodReference(getLabel(lc, classReader.readU2(p), targetType, p));
            case TAT_CAST ->
                ofCastExpr(getLabel(lc, classReader.readU2(p), targetType, p), classReader.readU1(p + 2));
            case TAT_CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT ->
                ofConstructorInvocationTypeArgument(getLabel(lc, classReader.readU2(p), targetType, p), classReader.readU1(p + 2));
            case TAT_METHOD_INVOCATION_TYPE_ARGUMENT ->
                ofMethodInvocationTypeArgument(getLabel(lc, classReader.readU2(p), targetType, p), classReader.readU1(p + 2));
            case TAT_CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT ->
                ofConstructorReferenceTypeArgument(getLabel(lc, classReader.readU2(p), targetType, p), classReader.readU1(p + 2));
            case TAT_METHOD_REFERENCE_TYPE_ARGUMENT ->
                ofMethodReferenceTypeArgument(getLabel(lc, classReader.readU2(p), targetType, p), classReader.readU1(p + 2));
            default ->
                throw new IllegalArgumentException("Unexpected targetType '%d' in TypeAnnotation, pos = %d".formatted(targetType, p - 1));
        };
        p += targetInfo.size();
        int pathLength = classReader.readU1(p++);
        TypeAnnotation.TypePathComponent[] typePath = new TypeAnnotation.TypePathComponent[pathLength];
        for (int i = 0; i < pathLength; ++i) {
            int typePathKindTag = classReader.readU1(p++);
            int typeArgumentIndex = classReader.readU1(p++);
            typePath[i] = switch (typePathKindTag) {
                case 0 -> TypeAnnotation.TypePathComponent.ARRAY;
                case 1 -> TypeAnnotation.TypePathComponent.INNER_TYPE;
                case 2 -> TypeAnnotation.TypePathComponent.WILDCARD;
                case 3 -> new UnboundAttribute.TypePathComponentImpl(TypeAnnotation.TypePathComponent.Kind.TYPE_ARGUMENT, typeArgumentIndex);
                default -> throw new IllegalArgumentException("Unknown type annotation path component kind: " + typePathKindTag);
            };
        }
        // the annotation info for this annotation
        Utf8Entry type = classReader.readUtf8Entry(p);
        p += 2;
        return TypeAnnotation.of(targetInfo, List.of(typePath), type,
                                 readAnnotationElementValuePairs(classReader, p));
    }

    private static List<TypeAnnotation.LocalVarTargetInfo> readLocalVarEntries(ClassReader classReader, int p, LabelContext lc, int targetType) {
        int tableLength = classReader.readU2(p);
        p += 2;
        var entries = new Object[tableLength];
        for (int i = 0; i < tableLength; ++i) {
            int startPc = classReader.readU2(p);
            entries[i] = TypeAnnotation.LocalVarTargetInfo.of(
                    getLabel(lc, startPc, targetType, p),
                    getLabel(lc, startPc + classReader.readU2(p + 2), targetType, p - 2),
                    classReader.readU2(p + 4));
            p += 6;
        }
        return SharedSecrets.getJavaUtilCollectionAccess().listFromTrustedArrayNullsAllowed(entries);
    }

    private static int skipTypeAnnotation(ClassReader classReader, int p) {
        int targetType = classReader.readU1(p++);
        p += switch (targetType) {
            case 0x13, 0x14, 0x15 -> 0;
            case 0x00, 0x01, 0x16 -> 1;
            case 0x10, 0x11, 0x12, 0x17, 0x42, 0x43, 0x44, 0x45, 0x46 -> 2;
            case 0x47, 0x48, 0x49, 0x4A, 0x4B -> 3;
            case 0x40, 0x41 -> 2 + classReader.readU2(p) * 6;
            default -> throw new IllegalArgumentException(
                    "Unexpected targetType '%d' in TypeAnnotation, pos = %d".formatted(targetType, p - 1));
        };
        int pathLength = classReader.readU1(p++);
        p += pathLength * 2;

        // the annotation info for this annotation
        p += 2;
        p = skipElementValuePairs(classReader, p);
        return p;
    }
}
