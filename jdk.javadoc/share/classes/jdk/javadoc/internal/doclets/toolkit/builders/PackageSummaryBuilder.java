package jdk.javadoc.internal.doclets.toolkit.builders;

import javax.lang.model.element.PackageElement;

import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.DocFilesHandler;
import jdk.javadoc.internal.doclets.toolkit.DocletException;
import jdk.javadoc.internal.doclets.toolkit.PackageSummaryWriter;


/**
 * Builds the summary for a given package.
 */
public class PackageSummaryBuilder extends AbstractBuilder {

    /**
     * The package being documented.
     */
    private final PackageElement packageElement;

    /**
     * The doclet specific writer that will output the result.
     */
    private final PackageSummaryWriter packageWriter;

    /**
     * Construct a new PackageSummaryBuilder.
     *
     * @param context  the build context.
     * @param pkg the package being documented.
     * @param packageWriter the doclet specific writer that will output the
     *        result.
     */
    private PackageSummaryBuilder(Context context,
            PackageElement pkg,
            PackageSummaryWriter packageWriter) {
        super(context);
        this.packageElement = pkg;
        this.packageWriter = packageWriter;
    }

    /**
     * Construct a new PackageSummaryBuilder.
     *
     * @param context  the build context.
     * @param pkg the package being documented.
     * @param packageWriter the doclet specific writer that will output the
     *        result.
     *
     * @return an instance of a PackageSummaryBuilder.
     */
    public static PackageSummaryBuilder getInstance(Context context,
            PackageElement pkg, PackageSummaryWriter packageWriter) {
        return new PackageSummaryBuilder(context, pkg, packageWriter);
    }

    /**
     * Build the package summary.
     *
     * @throws DocletException if there is a problem while building the documentation
     */
    @Override
    public void build() throws DocletException {
        if (packageWriter == null) {
            //Doclet does not support this output.
            return;
        }
        buildPackageDoc();
    }

    /**
     * Build the package documentation.
     *
     * @throws DocletException if there is a problem while building the documentation
     */
    protected void buildPackageDoc() throws DocletException {
        Content content = packageWriter.getPackageHeader();

        buildContent();

        packageWriter.addPackageFooter();
        packageWriter.printDocument(content);
        DocFilesHandler docFilesHandler = configuration
                .getWriterFactory()
                .getDocFilesHandler(packageElement);
        docFilesHandler.copyDocFiles();
    }

    /**
     * Build the content for the package.
     *
     * @throws DocletException if there is a problem while building the documentation
     */
    protected void buildContent() throws DocletException {
        Content packageContent = packageWriter.getContentHeader();

        packageWriter.addPackageSignature(packageContent);
        buildPackageDescription(packageContent);
        buildPackageTags(packageContent);
        buildSummary(packageContent);

        packageWriter.addPackageContent(packageContent);
    }

    /**
     * Builds the list of summaries for the different kinds of types in this package.
     *
     * @param packageContent the package content to which the summaries will
     *                       be added
     * @throws DocletException if there is a problem while building the documentation
     */
    protected void buildSummary(Content packageContent) throws DocletException {
        Content summariesList = packageWriter.getSummariesList();

        buildRelatedPackagesSummary(summariesList);
        buildAllClassesAndInterfacesSummary(summariesList);

        packageContent.add(packageWriter.getPackageSummary(summariesList));
    }

    /**
     * Builds a list of "nearby" packages (subpackages, superpackages, and sibling packages).
     *
     * @param summariesList the list of summaries to which the summary will be added
     */
    protected void buildRelatedPackagesSummary(Content summariesList) {
        packageWriter.addRelatedPackagesSummary(summariesList);
    }

    /**
     * Builds the summary for all classes and interfaces in this package.
     *
     * @param summariesList the list of summaries to which the summary will be added
     */
    protected void buildAllClassesAndInterfacesSummary(Content summariesList) {
        packageWriter.addAllClassesAndInterfacesSummary(summariesList);
    }


    /**
     * Build the description of the summary.
     *
     * @param packageContent the content to which the package description will
     *                       be added
     */
    protected void buildPackageDescription(Content packageContent) {
        if (options.noComment()) {
            return;
        }
        packageWriter.addPackageDescription(packageContent);
    }

    /**
     * Build the tags of the summary.
     *
     * @param packageContent the content to which the package tags will be added
     */
    protected void buildPackageTags(Content packageContent) {
        if (options.noComment()) {
            return;
        }
        packageWriter.addPackageTags(packageContent);
    }
}
