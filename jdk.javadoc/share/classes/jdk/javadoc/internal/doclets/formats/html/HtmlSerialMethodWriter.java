package jdk.javadoc.internal.doclets.formats.html;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle;
import jdk.javadoc.internal.doclets.formats.html.markup.TagName;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlTree;
import jdk.javadoc.internal.doclets.formats.html.markup.Text;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.SerializedFormWriter;
import jdk.javadoc.internal.doclets.toolkit.taglets.TagletManager;


/**
 * Generate serialized form for Serializable/Externalizable methods.
 * Documentation denoted by the <code>serialData</code> tag is processed.
 */
public class HtmlSerialMethodWriter extends MethodWriterImpl implements
        SerializedFormWriter.SerialMethodWriter {

    public HtmlSerialMethodWriter(SubWriterHolderWriter writer, TypeElement  typeElement) {
        super(writer, typeElement);
    }

    @Override
    public Content getSerializableMethodsHeader() {
        return HtmlTree.UL(HtmlStyle.blockList);
    }

    @Override
    public Content getMethodsContentHeader(boolean isLastContent) {
        return new HtmlTree(TagName.LI);
    }

    /**
     * Add serializable methods.
     *
     * @param heading the heading for the section
     * @param source the content to be added to the serializable methods
     *        content
     * @return a content for the serializable methods content
     */
    @Override
    public Content getSerializableMethods(String heading, Content source) {
        Content headingContent = Text.of(heading);
        var serialHeading = HtmlTree.HEADING(Headings.SerializedForm.CLASS_SUBHEADING, headingContent);
        var section = HtmlTree.SECTION(HtmlStyle.detail, serialHeading);
        section.add(source);
        return HtmlTree.LI(section);
    }

    /**
     * Return the no customization message.
     *
     * @param msg the message to be displayed
     * @return no customization message content
     */
    @Override
    public Content getNoCustomizationMsg(String msg) {
        return Text.of(msg);
    }

    /**
     * Add the member header.
     *
     * @param member the method document to be listed
     * @param methodsContent the content to which the member header will be added
     */
    @Override
    public void addMemberHeader(ExecutableElement member, Content methodsContent) {
        Content memberContent = Text.of(name(member));
        var heading = HtmlTree.HEADING(Headings.SerializedForm.MEMBER_HEADING, memberContent);
        methodsContent.add(heading);
        methodsContent.add(getSignature(member));
    }

    /**
     * Add the deprecated information for this member.
     *
     * @param member the method to document.
     * @param methodsContent the content to which the deprecated info will be added
     */
    @Override
    public void addDeprecatedMemberInfo(ExecutableElement member, Content methodsContent) {
        addDeprecatedInfo(member, methodsContent);
    }

    /**
     * Add the description text for this member.
     *
     * @param member the method to document.
     * @param methodsContent the content to which the deprecated info will be added
     */
    @Override
    public void addMemberDescription(ExecutableElement member, Content methodsContent) {
        addComment(member, methodsContent);
    }

    /**
     * Add the tag information for this member.
     *
     * @param member the method to document.
     * @param methodsContent the content to which the member tags info will be added
     */
    @Override
    public void addMemberTags(ExecutableElement member, Content methodsContent) {
        TagletManager tagletManager = configuration.tagletManager;
        Content tagContent = writer.getBlockTagOutput(member, tagletManager.getSerializedFormTaglets());
        var dl = HtmlTree.DL(HtmlStyle.notes);
        dl.add(tagContent);
        methodsContent.add(dl);
        if (name(member).equals("writeExternal")
                && utils.getSerialDataTrees(member).isEmpty()) {
            serialWarning(member, "doclet.MissingSerialDataTag",
                utils.getFullyQualifiedName(member.getEnclosingElement()), name(member));
        }
    }
}
