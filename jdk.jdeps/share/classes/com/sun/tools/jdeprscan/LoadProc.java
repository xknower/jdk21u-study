package com.sun.tools.jdeprscan;

import java.lang.annotation.IncompleteAnnotationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;

import javax.tools.Diagnostic;

/**
 * Annotation processor for the Deprecation Scanner tool.
 * Examines APIs for deprecated elements and records information
 *
 */
@SupportedAnnotationTypes("java.lang.Deprecated")
public class LoadProc extends AbstractProcessor {
    Elements elements;
    Messager messager;
    final List<DeprData> deprList = new ArrayList<>();

    public LoadProc() {
    }

    @Override
    public void init(ProcessingEnvironment pe) {
        super.init(pe);
        elements = pe.getElementUtils();
        messager = pe.getMessager();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }

        // Assume annotations contains only @Deprecated.
        // Note: no way to get deprecated packages, since
        // @Deprecated is ignored in package-info.java files.

        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(Deprecated.class);
        for (Element e : set) {
            ElementKind kind = e.getKind();
            Deprecated depr = e.getAnnotation(Deprecated.class);
            switch (kind) {
                case CLASS:
                case INTERFACE:
                case ENUM:
                case ANNOTATION_TYPE:
                    addType(kind, (TypeElement)e, depr);
                    break;
                case CONSTRUCTOR:
                case ENUM_CONSTANT:
                case FIELD:
                case METHOD:
                    Element encl = e.getEnclosingElement();
                    ElementKind enclKind = encl.getKind();
                    switch (enclKind) {
                        case CLASS:
                        case INTERFACE:
                        case ENUM:
                        case ANNOTATION_TYPE:
                            String detail = getDetail(e);
                            addMember(kind, (TypeElement)encl, detail, depr);
                            break;
                        default:
                            messager.printMessage(Diagnostic.Kind.WARNING,
                                "element " + e +
                                " within unknown enclosing element " + encl +
                                " of kind " + enclKind, e);
                            break;
                    }
                    break;
                default:
                    messager.printMessage(Diagnostic.Kind.WARNING,
                        "unknown element " + e +
                        " of kind " + kind +
                        " within " + e.getEnclosingElement(), e);
                    break;
            }
        }
        return true;
    }

    public List<DeprData> getDeprecations() {
        return deprList;
    }

    String getDetail(Element e) {
        if (e.getKind().isField()) {
            return e.getSimpleName().toString();
        } else {
            // method or constructor
            ExecutableElement ee = (ExecutableElement) e;
            String ret;
            ret = desc(ee.getReturnType());
            List<? extends TypeMirror> parameterTypes = ((ExecutableType)ee.asType()).getParameterTypes();
            String parms = parameterTypes.stream()
                                .map(this::desc)
                                .collect(Collectors.joining());
            return ee.getSimpleName().toString() + "(" + parms + ")" + ret;
        }
    }

    String desc(TypeMirror tm) {
        switch (tm.getKind()) {
            case BOOLEAN:
                return "Z";
            case BYTE:
                return "B";
            case SHORT:
                return "S";
            case CHAR:
                return "C";
            case INT:
                return "I";
            case LONG:
                return "J";
            case FLOAT:
                return "F";
            case DOUBLE:
                return "D";
            case VOID:
                return "V";
            case DECLARED:
                String s =
                    ((TypeElement)((DeclaredType)tm).asElement()).getQualifiedName().toString();
                s = s.replace('.', '/');
                return "L" + s + ";";
            case ARRAY:
                return "[" + desc(((ArrayType)tm).getComponentType());
            default:
                return tm.getKind().toString();
        }
    }

    void addType(ElementKind kind, TypeElement type, Deprecated dep) {
        addData(kind, type, "", dep);
    }

    void addMember(ElementKind kind, TypeElement type, String nameSig, Deprecated dep) {
        addData(kind, type, nameSig, dep);
    }

    void addData(ElementKind kind, TypeElement type, String nameSig, Deprecated dep) {
        String typeName = elements.getBinaryName(type).toString().replace('.', '/');

        String since = "";
        try {
            since = dep.since();
        } catch (IncompleteAnnotationException ignore) { }

        boolean forRemoval = false;
        try {
            forRemoval = dep.forRemoval();
        } catch (IncompleteAnnotationException ignore) { }

        deprList.add(new DeprData(kind, type, typeName, nameSig, since, forRemoval));
    }
}
