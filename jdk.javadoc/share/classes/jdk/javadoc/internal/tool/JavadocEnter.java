package jdk.javadoc.internal.tool;

import javax.tools.JavaFileObject;

import com.sun.source.util.TreePath;
import com.sun.tools.javac.code.Symbol.*;
import com.sun.tools.javac.comp.Enter;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition;
import com.sun.tools.javac.util.List;

import static com.sun.tools.javac.code.Kinds.Kind.*;
import com.sun.tools.javac.main.JavaCompiler;

/**
 * Javadoc's own enter phase does a few things above and beyond that
 * done by javac.
 */
public class JavadocEnter extends Enter {
    public static JavadocEnter instance(Context context) {
        Enter instance = context.get(enterKey);
        if (instance == null)
            instance = new JavadocEnter(context);
        return (JavadocEnter)instance;
    }

    public static void preRegister(Context context) {
        context.put(enterKey, (Context.Factory<Enter>)JavadocEnter::new);
    }

    protected JavadocEnter(Context context) {
        super(context);
        log = JavadocLog.instance0(context);
        toolEnv = ToolEnvironment.instance(context);
        compiler = JavaCompiler.instance(context);
    }

    final JavadocLog log;
    final ToolEnvironment toolEnv;
    final JavaCompiler compiler;

    @Override
    public void main(List<JCCompilationUnit> trees) {
        // cache the error count if we need to convert Enter errors as warnings.
        int nerrors = log.nerrors;
        super.main(trees);
        compiler.enterDone();
        if (toolEnv.ignoreSourceErrors) {
            log.nwarnings += (log.nerrors - nerrors);
            log.nerrors = nerrors;
        }
    }

    @Override
    public void visitTopLevel(JCCompilationUnit tree) {
        super.visitTopLevel(tree);
        if (tree.sourcefile.isNameCompatible("package-info", JavaFileObject.Kind.SOURCE)) {
            JCPackageDecl pd = tree.getPackage();
            TreePath tp = pd == null ? toolEnv.getTreePath(tree) : toolEnv.getTreePath(tree, pd);
            toolEnv.setElementToTreePath(tree.packge, tp);
        }
    }

    @Override
    public void visitClassDef(JCClassDecl tree) {
        super.visitClassDef(tree);
        if (tree.sym == null) return;
        if (tree.sym.kind == TYP || tree.sym.kind == ERR) {
            ClassSymbol c = tree.sym;
            toolEnv.setElementToTreePath(c, toolEnv.getTreePath(env.toplevel, tree));
            c.classfile = env.toplevel.sourcefile;
        }
    }

    /** Don't complain about a duplicate class. */
    @Override
    protected void duplicateClass(DiagnosticPosition pos, ClassSymbol c) {}

}
