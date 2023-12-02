package jdk.javadoc.internal.doclets.toolkit.taglets;

import java.util.EnumSet;
import javax.lang.model.element.Element;

import com.sun.source.doctree.DocTree;
import jdk.javadoc.doclet.Taglet.Location;
import com.sun.source.doctree.SummaryTree;
import jdk.javadoc.internal.doclets.toolkit.Content;

/**
 * A taglet that represents the {@code {@summary}} tag.
 */
public class SummaryTaglet extends BaseTaglet {

    public SummaryTaglet() {
        super(DocTree.Kind.SUMMARY, true, EnumSet.allOf(Location.class));
    }

    @Override
    public Content getInlineTagOutput(Element holder, DocTree tag, TagletWriter writer) {
        return writer.commentTagsToOutput(holder, tag, ((SummaryTree)tag).getSummary(),
                writer.isFirstSentence);
    }
}
