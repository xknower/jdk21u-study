package jdk.javadoc.internal.doclets.toolkit.util;

import java.util.*;

import javax.lang.model.element.Element;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;
import jdk.javadoc.internal.doclets.toolkit.BaseOptions;
import jdk.javadoc.internal.doclets.toolkit.Resources;

/**
 * Provides methods for creating an array of class, method and
 * field names to be included as meta keywords in the HTML header
 * of class pages.  These keywords improve search results
 * on browsers that look for keywords.
 */
public class MetaKeywords {

    private final BaseOptions options;
    private final Resources resources;
    private final Utils utils;

    /**
     * Constructor
     */
    public MetaKeywords(BaseConfiguration configuration) {
        options = configuration.getOptions();
        resources = configuration.getDocResources();
        utils = configuration.utils;
    }

    /**
     * Returns an array of strings where each element
     * is a class, method or field name.  This array is
     * used to create one meta keyword tag for each element.
     * Method parameter lists are converted to "()" and
     * overloads are combined.
     *
     * Constructors are not included because they have the same
     * name as the class, which is already included.
     * Nested class members are not included because their
     * definitions are on separate pages.
     */
    public List<String> getMetaKeywords(TypeElement typeElement) {
        var results = new ArrayList<String>();

        // Add field and method keywords only if -keywords option is used
        if (options.keywords()) {
            results.addAll(getClassKeyword(typeElement));
            results.addAll(getMemberKeywords(utils.getFields(typeElement)));
            results.addAll(getMemberKeywords(utils.getMethods(typeElement)));
        }
        results.trimToSize();
        return results;
    }

    /**
     * Get the current class for a meta tag keyword, as a singleton list.
     */
    protected List<String> getClassKeyword(TypeElement typeElement) {
        String cltypelower = utils.isPlainInterface(typeElement) ? "interface" : "class";
        return List.of(utils.getFullyQualifiedName(typeElement) + " " + cltypelower);
    }

    /**
     * Get the package keywords.
     */
    public List<String> getMetaKeywords(PackageElement packageElement) {
        if (options.keywords()) {
            return List.of(utils.getPackageName(packageElement) + " " + "package");
        } else {
            return List.of();
        }
    }

    /**
     * Get the module keywords.
     *
     * @param mdle the module being documented
     */
    public List<String> getMetaKeywordsForModule(ModuleElement mdle) {
        if (options.keywords()) {
            return List.of(mdle.getQualifiedName() + " " + "module");
        } else {
            return List.of();
        }
    }

    /**
     * Get the overview keywords.
     */
    public List<String> getOverviewMetaKeywords(String title, String docTitle) {
        if (options.keywords()) {
            String windowOverview = resources.getText(title);
            if (docTitle.length() > 0) {
                return List.of(windowOverview + ", " + docTitle);
            } else {
                return List.of(windowOverview);
            }
        } else {
            return List.of();
        }
    }

    /**
     * Get members for meta tag keywords as an array,
     * where each member name is a string element of the array.
     * The parameter lists are not included in the keywords;
     * therefore all overloaded methods are combined.<br>
     * Example: getValue(Object) is returned in array as getValue()
     *
     * @param members  array of members to be added to keywords
     */
    protected List<String> getMemberKeywords(List<? extends Element> members) {
        var results = new ArrayList<String>();
        for (Element member : members) {
            String memberName = utils.isMethod(member)
                    ? utils.getSimpleName(member) + "()"
                    : utils.getSimpleName(member);
            if (!results.contains(memberName)) {
                results.add(memberName);
            }
        }
        results.trimToSize();
        return results;
    }
}
