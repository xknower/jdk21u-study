package jdk.javadoc.internal.doclets.toolkit.taglets;

import java.util.EnumSet;
import javax.lang.model.element.Element;

import com.sun.source.doctree.DocTree;
import jdk.javadoc.doclet.Taglet.Location;
import jdk.javadoc.internal.doclets.toolkit.Content;

/**
 * An inline taglet representing {@code {@docRoot}}.  This taglet is
 * used to get the relative path to the document's root output
 * directory.
 */
public class DocRootTaglet extends BaseTaglet {

    /**
     * Construct a new DocRootTaglet.
     */
    public DocRootTaglet() {
        super(DocTree.Kind.DOC_ROOT, true, EnumSet.allOf(Location.class));
    }

    @Override
    public Content getInlineTagOutput(Element holder, DocTree tag, TagletWriter writer) {
        return writer.getDocRootOutput();
    }
}
