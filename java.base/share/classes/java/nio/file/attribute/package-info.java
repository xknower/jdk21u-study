/**
 * Interfaces and classes providing access to file and file system attributes.
 *
 * <table class="striped" style="padding-left:2em; text-align:left">
 *     <caption style="display:none">Attribute views</caption>
 * <thead>
 * <tr><th scope="col">Attribute views</th>
 *     <th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row"><i>{@link java.nio.file.attribute.AttributeView}</i></th>
 *     <td>Can read or update non-opaque values associated with objects in a file system</td></tr>
 * <tr><th scope="row">
 *     <span style="padding-left:1em"><i>{@link java.nio.file.attribute.FileAttributeView}</i></span></th>
 *     <td>Can read or update file attributes</td></tr>
 * <tr><th scope="row">
 *     <span style="padding-left:2em">
 *     <i>{@link java.nio.file.attribute.BasicFileAttributeView}</i></span></th>
 *     <td>Can read or update a basic set of file attributes</td></tr>
 * <tr><th scope="row">
 *     <span style="padding-left:3em">
 *     <i>{@link java.nio.file.attribute.PosixFileAttributeView}</i></span></th>
 *     <td>Can read or update POSIX defined file attributes</td></tr>
 * <tr><th scope="row">
 *     <span style="padding-left:3em">
 *     <i>{@link java.nio.file.attribute.DosFileAttributeView}</i></span></th>
 *     <td>Can read or update FAT file attributes</td></tr>
 * <tr><th scope="row">
 *     <span style="padding-left:2em">
 *     <i>{@link java.nio.file.attribute.FileOwnerAttributeView}</i></span></th>
 *     <td>Can read or update the owner of a file</td></tr>
 * <tr><th scope="row">
 *     <span style="padding-left:3em">
 *     <i>{@link java.nio.file.attribute.AclFileAttributeView}</i></span></th>
 *     <td>Can read or update Access Control Lists</td></tr>
 * <tr><th scope="row">
 *     <span style="padding-left:2em">
 *     <i>{@link java.nio.file.attribute.UserDefinedFileAttributeView}</i></span></th>
 *     <td>Can read or update user-defined file attributes</td></tr>
 * <tr><th scope="row">
 *     <span style="padding-left:1em"><i>{@link java.nio.file.attribute.FileStoreAttributeView}</i></span></th>
 *     <td>Can read or update file system attributes</td></tr>
 * </tbody>
 * </table>
 *
 * <p> An attribute view provides a read-only or updatable view of the non-opaque
 * values, or <em>metadata</em>, associated with objects in a file system.
 * The {@link java.nio.file.attribute.FileAttributeView} interface is
 * extended by several other interfaces that provide views to specific sets of file
 * attributes. {@code FileAttributeViews} are selected by invoking the {@link
 * java.nio.file.Files#getFileAttributeView} method with a
 * <em>type-token</em> to identify the required view. Views can also be identified
 * by name. The {@link java.nio.file.attribute.FileStoreAttributeView} interface
 * provides access to file store attributes. A {@code FileStoreAttributeView} of
 * a given type is obtained by invoking the {@link
 * java.nio.file.FileStore#getFileStoreAttributeView} method.
 *
 * <p> The {@link java.nio.file.attribute.BasicFileAttributeView}
 * class defines methods to read and update a <em>basic</em> set of file
 * attributes that are common to many file systems.
 *
 * <p> The {@link java.nio.file.attribute.PosixFileAttributeView}
 * interface extends {@code BasicFileAttributeView} by defining methods
 * to access the file attributes commonly used by file systems and operating systems
 * that implement the Portable Operating System Interface (POSIX) family of
 * standards.
 *
 * <p> The {@link java.nio.file.attribute.DosFileAttributeView}
 * class extends {@code BasicFileAttributeView} by defining methods to
 * access the legacy "DOS" file attributes supported on file systems such as File
 * Allocation Table (FAT), commonly used in consumer devices.
 *
 * <p> The {@link java.nio.file.attribute.AclFileAttributeView}
 * class defines methods to read and write the Access Control List (ACL)
 * file attribute. The ACL model used by this file attribute view is based
 * on the model defined by <a href="http://www.ietf.org/rfc/rfc3530.txt">
 * <i>RFC&nbsp;3530: Network File System (NFS) version 4 Protocol</i></a>.
 *
 * <p> In addition to attribute views, this package also defines classes and
 * interfaces that are used when accessing attributes:
 *
 * <ul>
 *
 *   <li> The {@link java.nio.file.attribute.UserPrincipal} and
 *   {@link java.nio.file.attribute.GroupPrincipal} interfaces represent an
 *   identity or group identity. </li>
 *
 *   <li> The {@link java.nio.file.attribute.UserPrincipalLookupService}
 *   interface defines methods to lookup user or group principals. </li>
 *
 *   <li> The {@link java.nio.file.attribute.FileAttribute} interface
 *   represents the value of an attribute for cases where the attribute value is
 *   required to be set atomically when creating an object in the file system. </li>
 *
 * </ul>
 *
 *
 * <p> Unless otherwise noted, passing a {@code null} argument to a constructor
 * or method in any class or interface in this package will cause a {@link
 * java.lang.NullPointerException NullPointerException} to be thrown.
 *
 * @spec https://www.rfc-editor.org/info/rfc3530
 *      RFC 3530: Network File System (NFS) version 4 Protocol
 * @since 1.7
 */

package java.nio.file.attribute;
