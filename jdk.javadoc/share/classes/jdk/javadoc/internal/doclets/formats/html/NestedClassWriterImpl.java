package jdk.javadoc.internal.doclets.formats.html;

import java.util.Arrays;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import jdk.javadoc.internal.doclets.formats.html.markup.ContentBuilder;
import jdk.javadoc.internal.doclets.formats.html.markup.Entity;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlTree;
import jdk.javadoc.internal.doclets.formats.html.markup.Text;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.MemberSummaryWriter;

/**
 * Writes nested class documentation in HTML format.
 */
public class NestedClassWriterImpl extends AbstractMemberWriter
    implements MemberSummaryWriter {

    public NestedClassWriterImpl(SubWriterHolderWriter writer, TypeElement typeElement) {
        super(writer, typeElement);
    }

    public NestedClassWriterImpl(SubWriterHolderWriter writer) {
        super(writer);
    }

    @Override
    public Content getMemberSummaryHeader(TypeElement typeElement,
            Content content) {
        content.add(MarkerComments.START_OF_NESTED_CLASS_SUMMARY);
        Content memberContent = new ContentBuilder();
        writer.addSummaryHeader(this, memberContent);
        return memberContent;
    }

    @Override
    public void addSummary(Content summariesList, Content content) {
        writer.addSummary(HtmlStyle.nestedClassSummary,
                HtmlIds.NESTED_CLASS_SUMMARY, summariesList, content);
    }

    @Override
    public void addSummaryLabel(Content content) {
        var label = HtmlTree.HEADING(Headings.TypeDeclaration.SUMMARY_HEADING,
                contents.nestedClassSummary);
        content.add(label);
    }

    @Override
    public TableHeader getSummaryTableHeader(Element member) {
        Content label = utils.isPlainInterface(member) ?
                contents.interfaceLabel : contents.classLabel;

        return new TableHeader(contents.modifierAndTypeLabel, label, contents.descriptionLabel);
    }

    @Override
    protected Table<Element> createSummaryTable() {
        List<HtmlStyle> bodyRowStyles = Arrays.asList(HtmlStyle.colFirst, HtmlStyle.colSecond,
                HtmlStyle.colLast);

        return new Table<Element>(HtmlStyle.summaryTable)
                .setCaption(contents.getContent("doclet.Nested_Classes"))
                .setHeader(getSummaryTableHeader(typeElement))
                .setColumnStyles(bodyRowStyles);
    }

    @Override
    public void addInheritedSummaryLabel(TypeElement typeElement, Content content) {
        Content classLink = writer.getPreQualifiedClassLink(HtmlLinkInfo.Kind.PLAIN, typeElement);
        Content label;
        if (options.summarizeOverriddenMethods()) {
            label = Text.of(utils.isPlainInterface(typeElement)
                    ? resources.getText("doclet.Nested_Classes_Interfaces_Declared_In_Interface")
                    : resources.getText("doclet.Nested_Classes_Interfaces_Declared_In_Class"));
        } else {
            label = Text.of(utils.isPlainInterface(typeElement)
                    ? resources.getText("doclet.Nested_Classes_Interfaces_Inherited_From_Interface")
                    : resources.getText("doclet.Nested_Classes_Interfaces_Inherited_From_Class"));
        }
        var labelHeading = HtmlTree.HEADING(Headings.TypeDeclaration.SUMMARY_HEADING, label);
        labelHeading.setId(htmlIds.forInheritedClasses(typeElement));
        labelHeading.add(Entity.NO_BREAK_SPACE);
        labelHeading.add(classLink);
        content.add(labelHeading);
    }

    @Override
    protected void addSummaryLink(HtmlLinkInfo.Kind context, TypeElement typeElement, Element member,
                                  Content content) {
        Content memberLink = writer.getLink(new HtmlLinkInfo(configuration, context, (TypeElement)member)
                .style(HtmlStyle.typeNameLink));
        var code = HtmlTree.CODE(memberLink);
        content.add(code);
    }

    @Override
    protected void addInheritedSummaryLink(TypeElement typeElement, Element member, Content target) {
        target.add(
                writer.getLink(new HtmlLinkInfo(configuration, HtmlLinkInfo.Kind.LINK_TYPE_PARAMS_AND_BOUNDS,
                        (TypeElement)member)));
    }

    @Override
    protected void addSummaryType(Element member, Content content) {
        addModifiersAndType(member, null, content);
    }

    @Override
    protected void addSummaryLink(TypeElement typeElement, Element member, Content content) {
        addSummaryLink(HtmlLinkInfo.Kind.LINK_TYPE_PARAMS_AND_BOUNDS, typeElement, member, content);
    }

    @Override
    protected Content getSummaryLink(Element member) {
        return writer.getQualifiedClassLink(HtmlLinkInfo.Kind.SHOW_PREVIEW, member);
    }
}
