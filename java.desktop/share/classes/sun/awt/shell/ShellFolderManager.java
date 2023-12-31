package sun.awt.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.Callable;
import java.util.stream.Stream;


/**
 * @author Michael Martak
 * @since 1.4
 */

class ShellFolderManager {
    /**
     * Create a shell folder from a file.
     * Override to return machine-dependent behavior.
     */
    public ShellFolder createShellFolder(File file) throws FileNotFoundException {
        return new DefaultShellFolder(null, file);
    }

    /**
     * @param key a {@code String}
     *  "fileChooserDefaultFolder":
     *    Returns a {@code File} - the default shellfolder for a new filechooser
     *  "roots":
     *    Returns a {@code File[]} - containing the root(s) of the displayable hierarchy
     *  "fileChooserComboBoxFolders":
     *    Returns a {@code File[]} - an array of shellfolders representing the list to
     *    show by default in the file chooser's combobox
     *   "fileChooserShortcutPanelFolders":
     *    Returns a {@code File[]} - an array of shellfolders representing well-known
     *    folders, such as Desktop, Documents, History, Network, Home, etc.
     *    This is used in the shortcut panel of the filechooser on Windows 2000
     *    and Windows Me.
     *  "fileChooserIcon <icon>":
     *    Returns an {@code Image} - icon can be ListView, DetailsView, UpFolder, NewFolder or
     *    ViewMenu (Windows only).
     *
     * @return An Object matching the key string.
     */
    public Object get(String key) {
        if (key.equals("fileChooserDefaultFolder")) {
            // Return the default shellfolder for a new filechooser
            File homeDir = new File(System.getProperty("user.home"));
            try {
                return checkFile(createShellFolder(homeDir));
            } catch (FileNotFoundException e) {
                return checkFile(homeDir);
            }
        } else if (key.equals("roots")) {
            // The root(s) of the displayable hierarchy
            return checkFiles(File.listRoots());
        } else if (key.equals("fileChooserComboBoxFolders")) {
            // Return an array of ShellFolders representing the list to
            // show by default in the file chooser's combobox
            return get("roots");
        } else if (key.equals("fileChooserShortcutPanelFolders")) {
            // Return an array of ShellFolders representing well-known
            // folders, such as Desktop, Documents, History, Network, Home, etc.
            // This is used in the shortcut panel of the filechooser on Windows 2000
            // and Windows Me
            return checkFiles(new File[] { (File)get("fileChooserDefaultFolder") });
        }

        return null;
    }

    private static File checkFile(File f) {
        @SuppressWarnings("removal")
        SecurityManager sm = System.getSecurityManager();
        return (sm == null || f == null) ? f : checkFile(f, sm);
    }

    private static File checkFile(File f, @SuppressWarnings("removal") SecurityManager sm) {
        try {
            sm.checkRead(f.getPath());
            if (f instanceof ShellFolder) {
                ShellFolder sf = (ShellFolder)f;
                if (sf.isLink()) {
                    sm.checkRead(sf.getLinkLocation().getPath());
                }
            }
            return f;
        } catch (SecurityException | FileNotFoundException e) {
            return null;
        }
    }

    private static File[] checkFiles(File[] fs) {
        @SuppressWarnings("removal")
        SecurityManager sm = System.getSecurityManager();
        return (sm == null || fs == null) ? fs : checkFiles(Stream.of(fs), sm);
    }

    private static File[] checkFiles(Stream<File> fs, @SuppressWarnings("removal") SecurityManager sm) {
        return fs.filter(f -> f != null && checkFile(f, sm) != null)
                 .toArray(File[]::new);
    }

    /**
     * Does {@code dir} represent a "computer" such as a node on the network, or
     * "My Computer" on the desktop.
     */
    public boolean isComputerNode(File dir) {
        return false;
    }

    public boolean isFileSystemRoot(File dir) {
        if (dir instanceof ShellFolder && !((ShellFolder) dir).isFileSystem()) {
            return false;
        }
        return (dir.getParentFile() == null);
    }

    protected ShellFolder.Invoker createInvoker() {
        return new DirectInvoker();
    }

    private static class DirectInvoker implements ShellFolder.Invoker {
        public <T> T invoke(Callable<T> task) throws Exception {
            return task.call();
        }
    }
}
