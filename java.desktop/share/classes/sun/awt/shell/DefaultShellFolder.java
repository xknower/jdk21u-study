package sun.awt.shell;

import java.io.File;
import java.io.Serial;

/**
 * @author Michael Martak
 * @since 1.4
 */
@SuppressWarnings("serial") // JDK-implementation class
class DefaultShellFolder extends ShellFolder {

    /**
     * Create a file system shell folder from a file
     */
    DefaultShellFolder(ShellFolder parent, File f) {
        super(parent, f.getAbsolutePath());
    }

    /**
     * This method is implemented to make sure that no instances
     * of {@code ShellFolder} are ever serialized. An instance of
     * this default implementation can always be represented with a
     * {@code java.io.File} object instead.
     *
     * @return a java.io.File replacement object.
     */
    @Serial
    protected Object writeReplace() throws java.io.ObjectStreamException {
        return new File(getPath());
    }

    /**
     * @return An array of shell folders that are children of this shell folder
     * object, null if this shell folder is empty.
     */
    public File[] listFiles() {
        File[] files = super.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                files[i] = new DefaultShellFolder(this, files[i]);
            }
        }
        return files;
    }

    /**
     * @return Whether this shell folder is a link
     */
    public boolean isLink() {
        return false; // Not supported by default
    }

    /**
     * @return Whether this shell folder is marked as hidden
     */
    public boolean isHidden() {
        String fileName = getName();
        if (fileName.length() > 0) {
            return (fileName.charAt(0) == '.');
        }
        return false;
    }

    /**
     * @return The shell folder linked to by this shell folder, or null
     * if this shell folder is not a link
     */
    public ShellFolder getLinkLocation() {
        return null; // Not supported by default
    }

    /**
     * @return The name used to display this shell folder
     */
    public String getDisplayName() {
        return getName();
    }

    /**
     * @return The type of shell folder as a string
     */
    public String getFolderType() {
        if (isDirectory()) {
            return "File Folder"; // TODO : LOCALIZE THIS STRING!!!
        } else {
            return "File"; // TODO : LOCALIZE THIS STRING!!!
        }
    }

    /**
     * @return The executable type as a string
     */
    public String getExecutableType() {
        return null; // Not supported by default
    }
}
