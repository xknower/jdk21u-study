package com.sun.tools.javac.api;

import java.util.function.Predicate;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import com.sun.tools.javac.code.Kinds.Kind;
import com.sun.tools.javac.code.Scope.CompoundScope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.util.DefinedBy;
import com.sun.tools.javac.util.DefinedBy.Api;
import com.sun.tools.javac.util.Assert;

/**
 * Provides an implementation of Scope.
 *
 * <p><b>This is NOT part of any supported API.
 * If you write code that depends on this, you do so at your own
 * risk.  This code and its internal interfaces are subject to change
 * or deletion without notice.</b></p>
 *
 * @author Jonathan Gibbons;
 */
public class JavacScope implements com.sun.source.tree.Scope {

    private static final Predicate<Symbol> VALIDATOR = sym -> {
        sym.apiComplete();
        return sym.kind != Kind.ERR;
    };

    static JavacScope create(Env<AttrContext> env) {
        if (env.outer == null || env.outer == env) {
            //the "top-level" scope needs to return both imported and defined elements
            //see test CheckLocalElements
            return new JavacScope(env) {
                @Override @DefinedBy(Api.COMPILER_TREE)
                public Iterable<? extends Element> getLocalElements() {
                    CompoundScope result = new CompoundScope(env.toplevel.packge);
                    result.prependSubScope(env.toplevel.toplevelScope);
                    result.prependSubScope(env.toplevel.namedImportScope);
                    return result.getSymbols(VALIDATOR);
                }
            };
        } else {
            return new JavacScope(env);
        }
    }

    protected final Env<AttrContext> env;

    private JavacScope(Env<AttrContext> env) {
        this.env = Assert.checkNonNull(env);
    }

    @DefinedBy(Api.COMPILER_TREE)
    public JavacScope getEnclosingScope() {
        if (env.outer != null && env.outer != env) {
            return create(env.outer);
        } else {
            // synthesize an outermost "star-import" scope
            return new JavacScope(env) {
                public boolean isStarImportScope() {
                    return true;
                }
                @DefinedBy(Api.COMPILER_TREE)
                public JavacScope getEnclosingScope() {
                    return null;
                }
                @DefinedBy(Api.COMPILER_TREE)
                public Iterable<? extends Element> getLocalElements() {
                    return env.toplevel.starImportScope.getSymbols(VALIDATOR);
                }
            };
        }
    }

    @DefinedBy(Api.COMPILER_TREE)
    public TypeElement getEnclosingClass() {
        // hide the dummy class that javac uses to enclose the top level declarations
        return (env.outer == null || env.outer == env ? null : env.enclClass.sym);
    }

    @DefinedBy(Api.COMPILER_TREE)
    public ExecutableElement getEnclosingMethod() {
        return (env.enclMethod == null ? null : env.enclMethod.sym);
    }

    @DefinedBy(Api.COMPILER_TREE)
    public Iterable<? extends Element> getLocalElements() {
        return env.info.getLocalElements();
    }

    public Env<AttrContext> getEnv() {
        return env;
    }

    public boolean isStarImportScope() {
        return false;
    }

    public boolean equals(Object other) {
        return other instanceof JavacScope javacScope
                && env.equals(javacScope.env)
                && isStarImportScope() == javacScope.isStarImportScope();
    }

    public int hashCode() {
        return env.hashCode() + (isStarImportScope() ? 1 : 0);
    }

    public String toString() {
        return "JavacScope[env=" + env + ",starImport=" + isStarImportScope() + "]";
    }
}
