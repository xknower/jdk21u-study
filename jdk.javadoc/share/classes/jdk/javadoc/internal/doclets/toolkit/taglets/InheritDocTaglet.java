package jdk.javadoc.internal.doclets.toolkit.taglets;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;

import com.sun.source.doctree.DocTree;
import jdk.javadoc.doclet.Taglet.Location;
import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.Messages;
import jdk.javadoc.internal.doclets.toolkit.util.CommentHelper;
import jdk.javadoc.internal.doclets.toolkit.util.DocFinder;
import jdk.javadoc.internal.doclets.toolkit.util.DocFinder.Result;
import jdk.javadoc.internal.doclets.toolkit.util.Utils;

/**
 * A taglet that represents the {@code {@inheritDoc}} tag.
 */
public class InheritDocTaglet extends BaseTaglet {

    /**
     * Construct a new InheritDocTaglet.
     */
    public InheritDocTaglet() {
        super(DocTree.Kind.INHERIT_DOC, true, EnumSet.of(Location.METHOD));
    }

    /**
     * Given an element and {@code @inheritDoc} tag in that element's doc comment,
     * returns the (recursive) expansion of that tag.
     *
     * <p>This method does not expand all {@code {@inheritDoc}} tags in the given
     * element's doc comment. To do this, the method must be called for every
     * such tag.</p>
     *
     * @param writer the writer that is writing the output.
     * @param method the method that we are documenting.
     * @param inheritDoc the {@code {@inheritDoc}} tag
     * @param isFirstSentence true if we only want to inherit the first sentence
     */
    private Content retrieveInheritedDocumentation(TagletWriter writer,
                                                   ExecutableElement method,
                                                   DocTree inheritDoc,
                                                   boolean isFirstSentence) {
        Content replacement = writer.getOutputInstance();
        BaseConfiguration configuration = writer.configuration();
        Messages messages = configuration.getMessages();
        Utils utils = configuration.utils;
        CommentHelper ch = utils.getCommentHelper(method);
        var path = ch.getDocTreePath(inheritDoc).getParentPath();
        DocTree holderTag = path.getLeaf();
        if (holderTag.getKind() == DocTree.Kind.DOC_COMMENT) {
            try {
                var docFinder = utils.docFinder();
                Optional<Documentation> r = docFinder.trySearch(method,
                        m -> Result.fromOptional(extractMainDescription(m, isFirstSentence, utils))).toOptional();
                if (r.isPresent()) {
                    replacement = writer.commentTagsToOutput(r.get().method, null,
                            r.get().mainDescription, isFirstSentence);
                }
            } catch (DocFinder.NoOverriddenMethodsFound e) {
                String signature = utils.getSimpleName(method)
                        + utils.flatSignature(method, writer.getCurrentPageElement());
                messages.warning(method, "doclet.noInheritedDoc", signature);
            }
            return replacement;
        }

        Taglet taglet = configuration.tagletManager.getTaglet(ch.getTagName(holderTag));
        if (taglet != null && !(taglet instanceof InheritableTaglet)) {
            // This tag does not support inheritance.
            messages.warning(path, "doclet.inheritDocWithinInappropriateTag");
            return replacement;
        }

        InheritableTaglet.Output inheritedDoc = ((InheritableTaglet) taglet).inherit(method, holderTag, isFirstSentence, configuration);
        if (inheritedDoc.isValidInheritDocTag()) {
            if (!inheritedDoc.inlineTags().isEmpty()) {
                replacement = writer.commentTagsToOutput(inheritedDoc.holder(), inheritedDoc.holderTag(),
                        inheritedDoc.inlineTags(), isFirstSentence);
            }
        } else {
            String signature = utils.getSimpleName(method)
                    + utils.flatSignature(method, writer.getCurrentPageElement());
            messages.warning(method, "doclet.noInheritedDoc", signature);
        }
        return replacement;
    }

    private record Documentation(List<? extends DocTree> mainDescription, ExecutableElement method) { }

    private static Optional<Documentation> extractMainDescription(ExecutableElement m,
                                                                boolean extractFirstSentenceOnly,
                                                                Utils utils) {
        List<? extends DocTree> docTrees = extractFirstSentenceOnly
                ? utils.getFirstSentenceTrees(m)
                : utils.getFullBody(m);
        return docTrees.isEmpty() ? Optional.empty() : Optional.of(new Documentation(docTrees, m));
    }

    @Override
    public Content getInlineTagOutput(Element e, DocTree inheritDoc, TagletWriter tagletWriter) {
        if (e.getKind() != ElementKind.METHOD) {
            return tagletWriter.getOutputInstance();
        }
        return retrieveInheritedDocumentation(tagletWriter, (ExecutableElement) e, inheritDoc, tagletWriter.isFirstSentence);
    }
}
