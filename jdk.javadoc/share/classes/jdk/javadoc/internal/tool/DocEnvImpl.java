package jdk.javadoc.internal.tool;

import java.util.Set;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject.Kind;

import com.sun.source.util.DocTrees;
import com.sun.tools.javac.code.Source;
import jdk.javadoc.doclet.DocletEnvironment;

/**
 * This class holds the information from one run of javadoc.
 * Particularly the packages, classes and options specified
 * by the user.
 */
public class DocEnvImpl implements DocletEnvironment {

    public final ElementsTable etable;

    public final ToolEnvironment toolEnv;

    /**
     * Construct a doclet environment.
     *
     * @param toolEnv the tool environment
     * @param etable the includes table, providing all the information
     * with respect to specified, included/selected elements.
     */
    public DocEnvImpl(ToolEnvironment toolEnv, ElementsTable etable) {
        this.toolEnv = toolEnv;
        this.etable = etable;
    }
   @Override
    public Set<? extends Element> getSpecifiedElements() {
        return etable.getSpecifiedElements();
    }

    @Override
    public Set<? extends Element> getIncludedElements() {
        return etable.getIncludedElements();
    }

    @Override
    public boolean isIncluded(Element e) {
        return etable.isIncluded(e);
    }

    @Override
    public DocTrees getDocTrees() {
        return toolEnv.docTrees;
    }

    @Override
    public Elements getElementUtils() {
        return toolEnv.elements;
    }

    @Override
    public Types getTypeUtils() {
        return toolEnv.typeutils;
    }

    @Override
    public JavaFileManager getJavaFileManager() {
        return toolEnv.fileManager;
    }

    @Override
    public SourceVersion getSourceVersion() {
        return Source.toSourceVersion(toolEnv.source);
    }

    @Override
    public ModuleMode getModuleMode() {
        return etable.getModuleMode();
    }

    @Override
    public Kind getFileKind(TypeElement type) {
        return toolEnv.getFileKind(type);
    }

    @Override
    public boolean isSelected(Element e) {
        return etable.isSelected(e);
    }
}
