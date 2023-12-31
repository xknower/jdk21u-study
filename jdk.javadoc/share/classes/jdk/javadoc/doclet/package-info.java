/**
 * The Doclet API provides an environment which, in conjunction with
 * the Language Model API and Compiler Tree API, allows clients
 * to inspect the source-level structures of programs and
 * libraries, including API comments embedded in the source.
 *
 * <p>
 * The {@link StandardDoclet standard doclet} can be used to
 * generate HTML-formatted documentation. It supports user-defined
 * {@link Taglet taglets}, which can be used to generate customized
 * output for user-defined tags in documentation comments.
 *
 * <p style="font-style: italic">
 * <b>Note:</b> The declarations in this package supersede those
 * in the older package {@code com.sun.javadoc}. For details on the
 * mapping of old types to new types, see the
 * <a href="#migration">Migration Guide</a>.
 * </p>
 *
 * <p>
 * Doclets are invoked by javadoc and this API can be used to write out
 * program information to files.  For example, the standard doclet is
 * invoked by default, to generate HTML documentation.
 * <p>

 * The invocation is defined by the interface {@link jdk.javadoc.doclet.Doclet}
 * -- the {@link jdk.javadoc.doclet.Doclet#run(DocletEnvironment) run} interface
 * method, defines the entry point.
 * {@snippet id="entry-point" lang=java :
 *    public boolean run(DocletEnvironment environment) // @highlight substring="run"
 * }
 * The {@link jdk.javadoc.doclet.DocletEnvironment} instance holds the
 * environment that the doclet will be initialized with. From this environment
 * all other information can be extracted, in the form of
 * {@link javax.lang.model.element.Element elements}. One can further use the APIs and utilities
 * described by {@link javax.lang.model Language Model API} to query Elements and Types.
 * <p>
 *
 * <a id="terminology"></a>
 * <h2>Terminology</h2>
 *
 * <dl>
 *   <dt><a id="selected"></a>Selected</dt>
 *     <dd>An element is considered to be <em>selected</em>, if the
 *         <em>selection controls</em> <a href="#options">allow</a> it
 *         to be documented. (Note that synthetic elements are never
 *         selected.)
 *    </dd>
 *
 *   <dt><a id="specified"></a>Specified</dt>
 *   <dd>The set of elements specified by the user are considered to be <em>specified
 *       elements</em>. Specified elements provide the starting points
 *       for determining the <em>included elements</em> to be documented.
 *   </dd>
 *
 *   <dt><a id="included"></a>Included</dt>
 *   <dd>An element is considered to be <em>included</em>, if it is <em>selected</em> and any of the following is true:
 *     <ul>
 *       <li>the element is <em>specified</em>, or
 *       <li>the element contains a <em>specified</em> element, or
 *       <li>the element is enclosed in a <em>specified</em> element.
 *     </ul>
 *     Included elements will be documented.
 *   </dd>
 *
 * </dl>
 * <p>
 * <a id="options"></a>
 * <h2>Options</h2>
 * Javadoc <em>selection control</em> can be specified with these options
 * as follows:
 * <ul>
 *   <li>{@code --show-members:value} and {@code --show-types:value} can
 *       be used to filter the members, with the following values:
 *   <ul>
 *     <li> public    -- considers only public elements
 *     <li> protected -- considers public and protected elements
 *     <li> package   -- considers public, protected and package private elements
 *     <li> private   -- considers all elements
 *   </ul>
 *
 *   <li>{@code --show-packages:value} "exported" or "all" can be used
 *       to consider only exported packages or all packages within a module.
 *
 *   <li>{@code --show-module-contents:value} can be used to specify the level at
 *       module declarations could be documented. A value of "api" indicates API
 *       level documentation, and "all" indicates detailed documentation.
 * </ul>
 * The following options can be used to specify the elements to be documented:
 * <ul>
 *   <li>{@code --module} documents the specified modules.
 *
 *   <li>{@code --expand-requires:value} expand the set of modules to be documented
 *        by including some or all of the modules dependencies. The value may be
 *        one of:
 *   <ul>
 *     <li> transitive -- each module specified explicitly on the command line is
 *          expanded to include the closure of its transitive dependencies
 *     <li> all    -- each module specified explicitly on the command line
 *          is expanded to include the closure of its transitive dependencies,
 *          and also all of its direct dependencies
 *   </ul>
 *   By default, only the specified modules will be considered, without expansion
 *   of the module dependencies.
 *
 *   <li>{@code packagenames} can be used to specify packages.
 *   <li>{@code -subpackages} can be used to recursively load packages.
 *   <li>{@code -exclude} can be used exclude package directories.
 *   <li>{@code sourcefilenames} can be used to specify source file names.
 * </ul>
 * <p>
 * <a id="legacy-interactions"></a>
 * <h3>Interactions with older options.</h3>
 *
 * The new {@code --show-*} options provide a more detailed replacement
 * for the older options {@code -public}, {@code -protected}, {@code -package}, {@code -private}.
 * Alternatively, the older options can continue to be used as shorter
 * forms for combinations of the new options, as described below:
 * <table class="striped">
 *   <caption>Short form options mapping</caption>
 *   <thead>
 *       <tr>   <th rowspan="2" scope="col" style="vertical-align:top">
 *                      Older option
 *              <th colspan="5" scope="col" style="border-bottom: 1px solid black">
 *                      Equivalent to these values with the new option
 *       <tr>   <th scope="col">{@code --show-members}
 *              <th scope="col">{@code --show-types}
 *              <th scope="col">{@code --show-packages}
 *              <th scope="col">{@code --show-module-contents}
 *   </thead>
 *   <tbody>
 *       <tr>   <th scope="row">{@code -public}
 *              <td>public
 *              <td>public
 *              <td>exported
 *              <td>api
 *       <tr>   <th scope="row">{@code -protected}
 *              <td>protected
 *              <td>protected
 *              <td>exported
 *              <td>api
 *       <tr>   <th scope="row">{@code -package}
 *              <td>package
 *              <td>package
 *              <td>all
 *              <td>all
 *       <tr>   <th scope="row">{@code -private}
 *              <td>private
 *              <td>private
 *              <td>all
 *              <td>all
 *   </tbody>
 * </table>
 * <p>
 * <a id="qualified"></a>
 * A <em>qualified</em> element name is one that has its package
 * name prepended to it, such as {@code java.lang.String}.  A non-qualified
 * name has no package name, such as {@code String}.
 * <p>
 *
 * <a id="example"></a>
 * <h2>Example</h2>
 *
 * The following is an example doclet that displays information of a class
 * and its members, supporting an option.
 *
 * {@snippet lang=java id="Example.java" :
 * // @replace region=imports replacement=" // Note: imports deleted for clarity"
 * import com.sun.source.doctree.DocCommentTree;
 * import com.sun.source.util.DocTrees;
 * import jdk.javadoc.doclet.Doclet;
 * import jdk.javadoc.doclet.DocletEnvironment;
 * import jdk.javadoc.doclet.Reporter;
 *
 * import javax.lang.model.SourceVersion;
 * import javax.lang.model.element.Element;
 * import javax.lang.model.element.TypeElement;
 * import javax.lang.model.util.ElementFilter;
 * import javax.tools.Diagnostic.Kind;
 * import java.io.IOException;
 * import java.io.PrintWriter;
 * import java.util.List;
 * import java.util.Locale;
 * import java.util.Set;
 * // @end
 *
 *
 * public class Example implements Doclet {
 *     private Reporter reporter;
 *     private PrintWriter stdout;
 *
 *     @Override
 *     public void init(Locale locale, Reporter reporter) {
 *         reporter.print(Kind.NOTE, "Doclet using locale: " + locale);
 *         this.reporter = reporter;
 *         stdout = reporter.getStandardWriter();
 *     }
 *
 *     public void printElement(DocTrees trees, Element e) {
 *         DocCommentTree docCommentTree = trees.getDocCommentTree(e);
 *         if (docCommentTree != null) {
 *             stdout.println("Element (" + e.getKind() + ": "
 *                     + e + ") has the following comments:");
 *             stdout.println("Entire body: " + docCommentTree.getFullBody());
 *             stdout.println("Block tags: " + docCommentTree.getBlockTags());
 *         }
 *     }
 *
 *     @Override
 *     public boolean run(DocletEnvironment docEnv) {
 *         reporter.print(Kind.NOTE, "overviewFile: " + overviewFile);
 *
 *         // get the DocTrees utility class to access document comments
 *         DocTrees docTrees = docEnv.getDocTrees();
 *
 *         // location of an element in the same directory as overview.html
 *         try {
 *             Element e = ElementFilter.typesIn(docEnv.getSpecifiedElements()).iterator().next();
 *             DocCommentTree docCommentTree
 *                     = docTrees.getDocCommentTree(e, overviewFile);
 *             if (docCommentTree != null) {
 *                 stdout.println("Overview html: " + docCommentTree.getFullBody());
 *             }
 *         } catch (IOException missing) {
 *             reporter.print(Kind.ERROR, "No overview.html found.");
 *         }
 *
 *         for (TypeElement t : ElementFilter.typesIn(docEnv.getIncludedElements())) {
 *             stdout.println(t.getKind() + ":" + t);
 *             for (Element e : t.getEnclosedElements()) {
 *                 printElement(docTrees, e);
 *             }
 *         }
 *         return true;
 *     }
 *
 *     @Override
 *     public String getName() {
 *         return "Example";
 *     }
 *
 *     private String overviewFile;
 *
 *     @Override
 *     public Set<? extends Option> getSupportedOptions() {
 *         Option[] options = {
 *             new Option() {
 *                 private final List<String> someOption = List.of(
 *                         "--overview-file",
 *                         "-overviewfile",
 *                         "-o"
 *                 );
 *
 *                 @Override
 *                 public int getArgumentCount() {
 *                     return 1;
 *                 }
 *
 *                 @Override
 *                 public String getDescription() {
 *                     return "an option with aliases";
 *                 }
 *
 *                 @Override
 *                 public Option.Kind getKind() {
 *                     return Option.Kind.STANDARD;
 *                 }
 *
 *                 @Override
 *                 public List<String> getNames() {
 *                     return someOption;
 *                 }
 *
 *                 @Override
 *                 public String getParameters() {
 *                     return "file";
 *                 }
 *
 *                 @Override
 *                 public boolean process(String opt, List<String> arguments) {
 *                     overviewFile = arguments.get(0);
 *                     return true;
 *                 }
 *             }
 *         };
 *
 *         return Set.of(options);
 *     }
 *
 *     @Override
 *     public SourceVersion getSupportedSourceVersion() {
 *         // support the latest release
 *         return SourceVersion.latest();
 *     }
 * }
 * }
 *
 * <p>
 * This doclet can be invoked with a command line, such as:
 * {@snippet id="run-doclet":
 *     javadoc -docletpath doclet-classes \     // @highlight substring="doclet-classes " type=italic
 *       -doclet Example \
 *       --overview-file overview.html \
 *       --source-path source-location \        // @highlight region substring="source-location" type=italic
 *       source-location/Example.java           // @end
 *     }
 *
 * <h2><a id="migration">Migration Guide</a></h2>
 *
 * <p>Many of the types in the old {@code com.sun.javadoc} API do not have equivalents in this
 * package. Instead, types in the {@code javax.lang.model} and {@code com.sun.source} APIs
 * are used instead.
 *
 * <p>The following table gives a guide to the mapping from old types to their replacements.
 * In some cases, there is no direct equivalent.
 *
 * <table class="striped">
 *   <caption>Guide for mapping old types to new types</caption>
 *   <thead>
 *     <tr><th scope="col">Old Type<th scope="col">New Type
 *   </thead>
 *   <tbody style="text-align:left">
 *     <tr><th scope="row">{@code AnnotatedType}            <td>{@link javax.lang.model.type.TypeMirror javax.lang.model.type.TypeMirror}
 *     <tr><th scope="row">{@code AnnotationDesc}           <td>{@link javax.lang.model.element.AnnotationMirror javax.lang.model.element.AnnotationMirror}
 *     <tr><th scope="row">{@code AnnotationDesc.ElementValuePair}<td>{@link javax.lang.model.element.AnnotationValue javax.lang.model.element.AnnotationValue}
 *     <tr><th scope="row">{@code AnnotationTypeDoc}        <td>{@link javax.lang.model.element.TypeElement javax.lang.model.element.TypeElement}
 *     <tr><th scope="row">{@code AnnotationTypeElementDoc} <td>{@link javax.lang.model.element.ExecutableElement javax.lang.model.element.ExecutableElement}
 *     <tr><th scope="row">{@code AnnotationValue}          <td>{@link javax.lang.model.element.AnnotationValue javax.lang.model.element.AnnotationValue}
 *     <tr><th scope="row">{@code ClassDoc}                 <td>{@link javax.lang.model.element.TypeElement javax.lang.model.element.TypeElement}
 *     <tr><th scope="row">{@code ConstructorDoc}           <td>{@link javax.lang.model.element.ExecutableElement javax.lang.model.element.ExecutableElement}
 *     <tr><th scope="row">{@code Doc}                      <td>{@link javax.lang.model.element.Element javax.lang.model.element.Element}
 *     <tr><th scope="row">{@code DocErrorReporter}         <td>{@link jdk.javadoc.doclet.Reporter jdk.javadoc.doclet.Reporter}
 *     <tr><th scope="row">{@code Doclet}                   <td>{@link jdk.javadoc.doclet.Doclet jdk.javadoc.doclet.Doclet}
 *     <tr><th scope="row">{@code ExecutableMemberDoc}      <td>{@link javax.lang.model.element.ExecutableElement javax.lang.model.element.ExecutableElement}
 *     <tr><th scope="row">{@code FieldDoc}                 <td>{@link javax.lang.model.element.VariableElement javax.lang.model.element.VariableElement}
 *     <tr><th scope="row">{@code LanguageVersion}          <td>{@link javax.lang.model.SourceVersion javax.lang.model.SourceVersion}
 *     <tr><th scope="row">{@code MemberDoc}                <td>{@link javax.lang.model.element.Element javax.lang.model.element.Element}
 *     <tr><th scope="row">{@code MethodDoc}                <td>{@link javax.lang.model.element.ExecutableElement javax.lang.model.element.ExecutableElement}
 *     <tr><th scope="row">{@code PackageDoc}               <td>{@link javax.lang.model.element.PackageElement javax.lang.model.element.PackageElement}
 *     <tr><th scope="row">{@code Parameter}                <td>{@link javax.lang.model.element.VariableElement javax.lang.model.element.VariableElement}
 *     <tr><th scope="row">{@code ParameterizedType}        <td>{@link javax.lang.model.type.DeclaredType javax.lang.model.type.DeclaredType}
 *     <tr><th scope="row">{@code ParamTag}                 <td>{@link com.sun.source.doctree.ParamTree com.sun.source.doctree.ParamTree}
 *     <tr><th scope="row">{@code ProgramElementDoc}        <td>{@link javax.lang.model.element.Element javax.lang.model.element.Element}
 *     <tr><th scope="row">{@code RootDoc}                  <td>{@link jdk.javadoc.doclet.DocletEnvironment jdk.javadoc.doclet.DocletEnvironment}
 *     <tr><th scope="row">{@code SeeTag}                   <td>{@link com.sun.source.doctree.LinkTree com.sun.source.doctree.LinkTree}<br>
 *                                                              {@link com.sun.source.doctree.SeeTree com.sun.source.doctree.SeeTree}
 *     <tr><th scope="row">{@code SerialFieldTag}           <td>{@link com.sun.source.doctree.SerialFieldTree com.sun.source.doctree.SerialFieldTree}
 *     <tr><th scope="row">{@code SourcePosition}           <td>{@link com.sun.source.util.SourcePositions com.sun.source.util.SourcePositions}
 *     <tr><th scope="row">{@code Tag}                      <td>{@link com.sun.source.doctree.DocTree com.sun.source.doctree.DocTree}
 *     <tr><th scope="row">{@code ThrowsTag}                <td>{@link com.sun.source.doctree.ThrowsTree com.sun.source.doctree.ThrowsTree}
 *     <tr><th scope="row">{@code Type}                     <td>{@link javax.lang.model.type.TypeMirror javax.lang.model.type.TypeMirror}
 *     <tr><th scope="row">{@code TypeVariable}             <td>{@link javax.lang.model.type.TypeVariable javax.lang.model.type.TypeVariable}
 *     <tr><th scope="row">{@code WildcardType}             <td>{@link javax.lang.model.type.WildcardType javax.lang.model.type.WildcardType}
 *   </tbody>
 * </table>
 *
 * @see jdk.javadoc.doclet.Doclet
 * @see jdk.javadoc.doclet.DocletEnvironment
 * @since 9
 */
package jdk.javadoc.doclet;
