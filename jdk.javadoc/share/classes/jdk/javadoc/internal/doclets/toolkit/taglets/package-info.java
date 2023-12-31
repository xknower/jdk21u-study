/**
 * This package has classes used to generate output for Javadoc tags.
 *
 * <p>Doclets no longer have to implement their own version of standard tags
 * such as &#64;param and &#64;throws.  Individual taglets provide
 * common processing, independent of the output format.
 * Each doclet must have a taglet writer that takes a taglet
 * as input and writes doclet-dependent output. The taglet itself will
 * do the tag processing. For example, suppose we are outputting
 * &#64;throws tags. The taglet would:
 * <ul>
 *     <li> Retrieve the list of throws tags to be documented.
 *     <li> Replace {&#64;inheritDoc} with the appropriate documentation.
 *     <li> Add throws documentation for exceptions that are declared in
 *          the signature of the method but not documented with the throws tags.
 * </ul>
 * After doing the steps above, the taglet would pass the information to
 * the taglet writer for writing. The taglets are essentially builders for
 * tags.
 */
package jdk.javadoc.internal.doclets.toolkit.taglets;
