package sun.nio.fs;

import java.io.IOException;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Arrays;
import java.util.List;

/**
 * Linux implementation of FileStore
 */

class LinuxFileStore
    extends UnixFileStore
{
    // used when checking if extended attributes are enabled or not
    private volatile boolean xattrChecked;
    private volatile boolean xattrEnabled;

    LinuxFileStore(UnixPath file) throws IOException {
        super(file);
    }

    LinuxFileStore(UnixFileSystem fs, UnixMountEntry entry) throws IOException {
        super(fs, entry);
    }

    /**
     * Finds, and returns, the mount entry for the file system where the file
     * resides.
     */
    @Override
    UnixMountEntry findMountEntry() throws IOException {
        LinuxFileSystem fs = (LinuxFileSystem)file().getFileSystem();

        // step 1: get realpath
        UnixPath path = null;
        try {
            byte[] rp = UnixNativeDispatcher.realpath(file());
            path = new UnixPath(fs, rp);
        } catch (UnixException x) {
            x.rethrowAsIOException(file());
        }

        // step 2: find mount point
        List<UnixMountEntry> procMountsEntries =
            fs.getMountEntries("/proc/mounts");
        UnixPath parent = path.getParent();
        while (parent != null) {
            UnixFileAttributes attrs = null;
            try {
                attrs = UnixFileAttributes.get(parent, true);
            } catch (UnixException x) {
                x.rethrowAsIOException(parent);
            }
            if (attrs.dev() != dev()) {
                // step 3: lookup mounted file systems (use /proc/mounts to
                // ensure we find the file system even when not in /etc/mtab)
                byte[] dir = path.asByteArray();
                for (UnixMountEntry entry : procMountsEntries) {
                    if (Arrays.equals(dir, entry.dir()))
                        return entry;
                }
            }
            path = parent;
            parent = parent.getParent();
        }

        // step 3: lookup mounted file systems (use /proc/mounts to
        // ensure we find the file system even when not in /etc/mtab)
        byte[] dir = path.asByteArray();
        for (UnixMountEntry entry : procMountsEntries) {
            if (Arrays.equals(dir, entry.dir()))
                return entry;
        }

        throw new IOException("Mount point not found");
    }

    @Override
    public boolean supportsFileAttributeView(Class<? extends FileAttributeView> type) {
        // support DosFileAttributeView and UserDefinedAttributeView if extended
        // attributes enabled
        if (type == DosFileAttributeView.class ||
            type == UserDefinedFileAttributeView.class)
        {
            // lookup fstypes.properties
            FeatureStatus status = checkIfFeaturePresent("user_xattr");
            if (status == FeatureStatus.PRESENT)
                return true;
            if (status == FeatureStatus.NOT_PRESENT)
                return false;

            // if file system is mounted with user_xattr option then assume
            // extended attributes are enabled
            if ((entry().hasOption("user_xattr")))
                return true;

            // check for explicit disabling of extended attributes
            if (entry().hasOption("nouser_xattr")) {
                return false;
            }

            // user_xattr option not present but we special-case ext4 as we
            // know that extended attributes are enabled by default
            if (entry().fstype().equals("ext4")) {
                return true;
            }

            // not ext4 so probe mount point
            if (!xattrChecked) {
                UnixPath dir = new UnixPath(file().getFileSystem(), entry().dir());
                xattrEnabled = isExtendedAttributesEnabled(dir);
                xattrChecked = true;
            }
            return xattrEnabled;
        }
        // POSIX attributes not supported on FAT
        if (type == PosixFileAttributeView.class && entry().fstype().equals("vfat"))
            return false;
        return super.supportsFileAttributeView(type);
    }

    @Override
    public boolean supportsFileAttributeView(String name) {
        if (name.equals("dos"))
            return supportsFileAttributeView(DosFileAttributeView.class);
        if (name.equals("user"))
            return supportsFileAttributeView(UserDefinedFileAttributeView.class);
        return super.supportsFileAttributeView(name);
    }
}
