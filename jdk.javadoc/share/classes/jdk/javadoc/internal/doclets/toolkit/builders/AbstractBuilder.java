package jdk.javadoc.internal.doclets.toolkit.builders;

import java.util.*;

import javax.lang.model.element.PackageElement;

import jdk.javadoc.internal.doclets.toolkit.BaseConfiguration;
import jdk.javadoc.internal.doclets.toolkit.BaseOptions;
import jdk.javadoc.internal.doclets.toolkit.DocletException;
import jdk.javadoc.internal.doclets.toolkit.Messages;
import jdk.javadoc.internal.doclets.toolkit.Resources;
import jdk.javadoc.internal.doclets.toolkit.util.Utils;


/**
 * The superclass for all builders.  A builder is a class that provides
 * the structure and content of API documentation.  A builder is completely
 * doclet independent which means that any doclet can use builders to
 * construct documentation, as long as it implements the appropriate
 * writer interfaces.  For example, if a doclet wanted to use
 * {@link ConstantsSummaryBuilder} to build a constant summary, all it has to
 * do is implement the ConstantsSummaryWriter interface and pass it to the
 * builder using a WriterFactory.
 */
public abstract class AbstractBuilder {
    public static class Context {
        /**
         * The configuration used in this run of the doclet.
         */
        final BaseConfiguration configuration;

        /**
         * Keep track of which packages we have seen for
         * efficiency purposes.  We don't want to copy the
         * doc files multiple times for a single package.
         */
        final Set<PackageElement> containingPackagesSeen;

        Context(BaseConfiguration configuration, Set<PackageElement> containingPackagesSeen) {
            this.configuration = configuration;
            this.containingPackagesSeen = containingPackagesSeen;
        }
    }

    /**
     * The configuration used in this run of the doclet.
     */
    protected final BaseConfiguration configuration;
    protected final BaseOptions options;

    protected final BuilderFactory builderFactory;
    protected final Messages messages;
    protected final Resources resources;
    protected final Utils utils;

    /**
     * Keep track of which packages we have seen for
     * efficiency purposes.  We don't want to copy the
     * doc files multiple times for a single package.
     */
    protected final Set<PackageElement> containingPackagesSeen;

    /**
     * Construct a Builder.
     * @param c a context providing information used in this run of the doclet
     */
    public AbstractBuilder(Context c) {
        this.configuration = c.configuration;
        this.options = configuration.getOptions();
        this.builderFactory = configuration.getBuilderFactory();
        this.messages = configuration.getMessages();
        this.resources = configuration.getDocResources();
        this.utils = configuration.utils;
        this.containingPackagesSeen = c.containingPackagesSeen;
    }

    /**
     * Build the documentation.
     *
     * @throws DocletException if there is a problem building the documentation
     */
    public abstract void build() throws DocletException;
}
