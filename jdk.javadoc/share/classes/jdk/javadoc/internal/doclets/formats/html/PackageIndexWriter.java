package jdk.javadoc.internal.doclets.formats.html;

import java.util.*;

import javax.lang.model.element.PackageElement;

import jdk.javadoc.internal.doclets.formats.html.markup.ContentBuilder;
import jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle;
import jdk.javadoc.internal.doclets.formats.html.markup.Text;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.util.DocFileIOException;
import jdk.javadoc.internal.doclets.toolkit.util.DocPath;
import jdk.javadoc.internal.doclets.toolkit.util.DocPaths;
import jdk.javadoc.internal.doclets.toolkit.util.Group;

/**
 * Generate the package index page "index.html".
 */
public class PackageIndexWriter extends AbstractOverviewIndexWriter {

    /**
     * A Set of Packages to be documented.
     */
    protected SortedSet<PackageElement> packages;

    /**
     * Construct the PackageIndexWriter. Also constructs the grouping
     * information as provided on the command line by "-group" option. Stores
     * the order of groups specified by the user.
     *
     * @param configuration the configuration for this doclet
     * @param filename the path of the page to be generated
     * @see Group
     */
    public PackageIndexWriter(HtmlConfiguration configuration, DocPath filename) {
        super(configuration, filename);
        packages = configuration.packages;
    }

    /**
     * Generate the package index page.
     *
     * @param configuration the current configuration of the doclet.
     * @throws DocFileIOException if there is a problem generating the package index page
     */
    public static void generate(HtmlConfiguration configuration) throws DocFileIOException {
        DocPath filename = DocPaths.INDEX;
        PackageIndexWriter packgen = new PackageIndexWriter(configuration, filename);
        packgen.buildOverviewIndexFile("doclet.Window_Overview_Summary", "package index");
    }

    /**
     * Adds the packages list to the documentation tree.
     *
     * @param target the content to which the packages list will be added
     */
    @Override
    protected void addIndex(Content target) {
        Map<String, SortedSet<PackageElement>> groupPackageMap
                = configuration.group.groupPackages(packages);

        if (!groupPackageMap.keySet().isEmpty()) {
            var table = new Table<PackageElement>(HtmlStyle.summaryTable)
                    .setHeader(getPackageTableHeader())
                    .setColumnStyles(HtmlStyle.colFirst, HtmlStyle.colLast)
                    .setId(HtmlIds.ALL_PACKAGES_TABLE)
                    .setDefaultTab(contents.getContent("doclet.All_Packages"));

            // add the tabs in command-line order
            for (String groupName : configuration.group.getGroupList()) {
                Set<PackageElement> groupPackages = groupPackageMap.get(groupName);
                if (groupPackages != null) {
                    table.addTab(Text.of(groupName), groupPackages::contains);
                }
            }

            for (PackageElement pkg : configuration.packages) {
                if (!(options.noDeprecated() && utils.isDeprecated(pkg))) {
                    Content packageLinkContent = getPackageLink(pkg, getLocalizedPackageName(pkg));
                    Content summaryContent = new ContentBuilder();
                    addSummaryComment(pkg, summaryContent);
                    table.addRow(pkg, packageLinkContent, summaryContent);
                }
            }

            target.add(table);
        }
    }
}
