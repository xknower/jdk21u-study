package jdk.nio.zipfs;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.util.Objects;

/**
 * @author Xueming Shen, Rajendra Gutupalli, Jaya Hangal
 */
final class ZipFileStore extends FileStore {

    private final ZipFileSystem zfs;

    ZipFileStore(ZipPath zpath) {
        this.zfs = zpath.getFileSystem();
    }

    @Override
    public String name() {
        return zfs.toString() + "/";
    }

    @Override
    public String type() {
        return "zipfs";
    }

    @Override
    public boolean isReadOnly() {
        return zfs.isReadOnly();
    }

    @Override
    public boolean supportsFileAttributeView(Class<? extends FileAttributeView> type) {
        Objects.requireNonNull(type);
        return (type == BasicFileAttributeView.class ||
                type == ZipFileAttributeView.class ||
                ((type == FileOwnerAttributeView.class ||
                  type == PosixFileAttributeView.class) && zfs.supportPosix));
    }

    @Override
    public boolean supportsFileAttributeView(String name) {
        Objects.requireNonNull(name);
        return "basic".equals(name) || "zip".equals(name) ||
               (("owner".equals(name) || "posix".equals(name)) && zfs.supportPosix);
    }

    @Override
    public <V extends FileStoreAttributeView> V getFileStoreAttributeView(Class<V> type) {
        Objects.requireNonNull(type);
        return null;
    }

    @Override
    public long getTotalSpace() throws IOException {
         return new ZipFileStoreAttributes(this).totalSpace();
    }

    @Override
    public long getUsableSpace() throws IOException {
         return new ZipFileStoreAttributes(this).usableSpace();
    }

    @Override
    public long getUnallocatedSpace() throws IOException {
         return new ZipFileStoreAttributes(this).unallocatedSpace();
    }

    @Override
    public Object getAttribute(String attribute) throws IOException {
        Objects.requireNonNull(attribute);
         if (attribute.equals("totalSpace"))
               return getTotalSpace();
         if (attribute.equals("usableSpace"))
               return getUsableSpace();
         if (attribute.equals("unallocatedSpace"))
               return getUnallocatedSpace();
        throw new UnsupportedOperationException("does not support the given attribute: " + attribute);
    }

    private static final class ZipFileStoreAttributes {
        final FileStore fstore;
        final long size;

        ZipFileStoreAttributes(ZipFileStore fileStore)
            throws IOException
        {
            Path path = FileSystems.getDefault().getPath(fileStore.name());
            this.size = Files.size(path);
            this.fstore = Files.getFileStore(path);
        }

        long totalSpace() {
            return size;
        }

        long usableSpace() throws IOException {
            if (!fstore.isReadOnly())
                return fstore.getUsableSpace();
            return 0;
        }

        long unallocatedSpace()  throws IOException {
            if (!fstore.isReadOnly())
                return fstore.getUnallocatedSpace();
            return 0;
        }
    }
}
