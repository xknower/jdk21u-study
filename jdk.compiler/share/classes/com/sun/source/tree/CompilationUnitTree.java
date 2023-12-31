package com.sun.source.tree;

import java.util.List;
import javax.tools.JavaFileObject;

/**
 * Represents the abstract syntax tree for ordinary compilation units
 * and modular compilation units.
 *
 * @jls 7.3 Compilation Units
 * @jls 7.4 Package Declarations
 * @jls 7.7 Module Declarations
 *
 * @author Peter von der Ah&eacute;
 * @since 1.6
 */
public interface CompilationUnitTree extends Tree {

    /**
     * Returns the module tree associated with this compilation unit,
     * or {@code null} if there is no module declaration.
     * @return the module tree
     * @implSpec This implementation throws {@code UnsupportedOperationException}
     * @since 17
     */
     default ModuleTree getModule() {
         throw new UnsupportedOperationException();
     }

    /**
     * Returns the annotations listed on any package declaration
     * at the head of this compilation unit, or {@code null} if there
     * is no package declaration.
     * @return the package annotations
     */
    List<? extends AnnotationTree> getPackageAnnotations();

    /**
     * Returns the name contained in any package declaration
     * at the head of this compilation unit, or {@code null} if there
     * is no package declaration.
     * @return the package name
     */
    ExpressionTree getPackageName();

    /**
     * Returns the package tree associated with this compilation unit,
     * or {@code null} if there is no package declaration.
     * @return the package tree
     * @since 9
     */
    PackageTree getPackage();

    /**
     * Returns the import declarations appearing in this compilation unit,
     * or an empty list if there are no import declarations.
     * @return the import declarations
     */
    List<? extends ImportTree> getImports();

    /**
     * Returns the type declarations appearing in this compilation unit,
     * or an empty list if there are no type declarations.
     * The list may also include empty statements resulting from
     * extraneous semicolons.
     * A modular compilation unit does not contain any type declarations.
     * @return the type declarations
     */
    List<? extends Tree> getTypeDecls();

    /**
     * Returns the file object containing the source for this compilation unit.
     * @return the file object
     */
    JavaFileObject getSourceFile();

    /**
     * Returns the line map for this compilation unit, if available,
     * or {@code null} if the line map is not available.
     * @return the line map for this compilation unit
     */
    LineMap getLineMap();
}
