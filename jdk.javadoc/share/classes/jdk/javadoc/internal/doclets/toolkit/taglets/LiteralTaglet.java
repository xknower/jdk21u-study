package jdk.javadoc.internal.doclets.toolkit.taglets;

import java.util.EnumSet;
import javax.lang.model.element.Element;

import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.LiteralTree;
import jdk.javadoc.doclet.Taglet.Location;
import jdk.javadoc.internal.doclets.toolkit.Content;

/**
 * An inline taglet used to denote literal text.
 * For example, the text:
 * <blockquote>  {@code {@literal a<B>c}}  </blockquote>
 * displays as:
 * <blockquote>  {@literal a<B>c}  </blockquote>
 */
public class LiteralTaglet extends BaseTaglet {

    LiteralTaglet() {
        super(DocTree.Kind.LITERAL, true, EnumSet.allOf(Location.class));
    }

    @Override
    public Content getInlineTagOutput(Element e, DocTree tag, TagletWriter writer) {
        return writer.literalTagOutput(e, (LiteralTree) tag);
    }
}
