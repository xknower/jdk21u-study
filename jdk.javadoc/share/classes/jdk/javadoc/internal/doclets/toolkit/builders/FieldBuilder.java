package jdk.javadoc.internal.doclets.toolkit.builders;

import java.util.*;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import jdk.javadoc.internal.doclets.toolkit.BaseOptions;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.DocletException;
import jdk.javadoc.internal.doclets.toolkit.FieldWriter;

import static jdk.javadoc.internal.doclets.toolkit.util.VisibleMemberTable.Kind.*;

/**
 * Builds documentation for a field.
 */
public class FieldBuilder extends AbstractMemberBuilder {

    /**
     * The writer to output the field documentation.
     */
    private final FieldWriter writer;

    /**
     * The list of fields being documented.
     */
    private final List<? extends Element> fields;

    /**
     * The index of the current field that is being documented at this point
     * in time.
     */
    private VariableElement currentElement;

    /**
     * Construct a new FieldBuilder.
     *
     * @param context  the build context.
     * @param typeElement the class whose members are being documented.
     * @param writer the doclet specific writer.
     */
    private FieldBuilder(Context context,
                         TypeElement typeElement,
                         FieldWriter writer) {
        super(context, typeElement);
        this.writer = Objects.requireNonNull(writer);
        fields = getVisibleMembers(FIELDS);
    }

    /**
     * Construct a new FieldBuilder.
     *
     * @param context  the build context.
     * @param typeElement the class whose members are being documented.
     * @param writer the doclet specific writer.
     * @return the new FieldBuilder
     */
    public static FieldBuilder getInstance(Context context,
            TypeElement typeElement,
            FieldWriter writer) {
        return new FieldBuilder(context, typeElement, writer);
    }

    /**
     * Returns whether or not there are members to document.
     *
     * @return whether or not there are members to document
     */
    @Override
    public boolean hasMembersToDocument() {
        return !fields.isEmpty();
    }

    @Override
    public void build(Content target) throws DocletException {
        buildFieldDoc(target);
    }

    /**
     * Build the field documentation.
     *
     * @param target the content to which the documentation will be added
     * @throws DocletException if there is a problem while building the documentation
     */
    protected void buildFieldDoc(Content target) throws DocletException {
        if (!fields.isEmpty()) {
            Content fieldDetailsHeader = writer.getFieldDetailsHeader(target);
            Content memberList = writer.getMemberList();

            for (Element element : fields) {
                currentElement = (VariableElement)element;
                Content fieldContent = writer.getFieldHeaderContent(currentElement);

                buildSignature(fieldContent);
                buildDeprecationInfo(fieldContent);
                buildPreviewInfo(fieldContent);
                buildFieldComments(fieldContent);
                buildTagInfo(fieldContent);

                memberList.add(writer.getMemberListItem(fieldContent));
            }
            Content fieldDetails = writer.getFieldDetails(fieldDetailsHeader, memberList);
            target.add(fieldDetails);
        }
    }

    /**
     * Build the signature.
     *
     * @param fieldContent the content to which the documentation will be added
     */
    protected void buildSignature(Content fieldContent) {
        fieldContent.add(writer.getSignature(currentElement));
    }

    /**
     * Build the deprecation information.
     *
     * @param fieldContent the content to which the documentation will be added
     */
    protected void buildDeprecationInfo(Content fieldContent) {
        writer.addDeprecated(currentElement, fieldContent);
    }

    /**
     * Build the preview information.
     *
     * @param fieldContent the content to which the documentation will be added
     */
    protected void buildPreviewInfo(Content fieldContent) {
        writer.addPreview(currentElement, fieldContent);
    }

    /**
     * Build the comments for the field.  Do nothing if
     * {@link BaseOptions#noComment()} is set to true.
     *
     * @param fieldContent the content to which the documentation will be added
     */
    protected void buildFieldComments(Content fieldContent) {
        if (!options.noComment()) {
            writer.addComments(currentElement, fieldContent);
        }
    }

    /**
     * Build the tag information.
     *
     * @param fieldContent the content to which the documentation will be added
     */
    protected void buildTagInfo(Content fieldContent) {
        writer.addTags(currentElement, fieldContent);
    }

    /**
     * Return the field writer for this builder.
     *
     * @return the field writer for this builder.
     */
    public FieldWriter getWriter() {
        return writer;
    }
}
