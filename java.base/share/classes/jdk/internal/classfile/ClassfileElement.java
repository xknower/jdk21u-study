package jdk.internal.classfile;

/**
 * Immutable model for a portion of (or the entirety of) a classfile.  Elements
 * that model parts of the classfile that have attributes will implement {@link
 * AttributedElement}; elements that model complex parts of the classfile that
 * themselves contain their own child elements will implement {@link
 * CompoundElement}.  Elements specific to various locations in the classfile
 * will implement {@link ClassElement}, {@link MethodElement}, etc.
 */
public sealed interface ClassfileElement
        permits AttributedElement, CompoundElement, WritableElement,
                ClassElement, CodeElement, FieldElement, MethodElement {
}
