package java.nio.file;

import java.nio.file.attribute.*;
import java.io.InputStream;
import java.io.IOException;

/**
 * Helper class to support copying or moving files when the source and target
 * are associated with different providers.
 */

class CopyMoveHelper {
    private CopyMoveHelper() { }

    /**
     * Parses the arguments for a file copy operation.
     */
    private static class CopyOptions {
        boolean replaceExisting = false;
        boolean copyAttributes = false;
        boolean followLinks = true;

        private CopyOptions() { }

        static CopyOptions parse(CopyOption... options) {
            CopyOptions result = new CopyOptions();
            for (CopyOption option: options) {
                if (option == StandardCopyOption.REPLACE_EXISTING) {
                    result.replaceExisting = true;
                    continue;
                }
                if (option == LinkOption.NOFOLLOW_LINKS) {
                    result.followLinks = false;
                    continue;
                }
                if (option == StandardCopyOption.COPY_ATTRIBUTES) {
                    result.copyAttributes = true;
                    continue;
                }
                if (option == null)
                    throw new NullPointerException();
                throw new UnsupportedOperationException("'" + option +
                    "' is not a recognized copy option");
            }
            return result;
        }
    }

    /**
     * Converts the given array of options for moving a file to options suitable
     * for copying the file when a move is implemented as copy + delete.
     */
    private static CopyOption[] convertMoveToCopyOptions(CopyOption... options)
        throws AtomicMoveNotSupportedException
    {
        int len = options.length;
        CopyOption[] newOptions = new CopyOption[len+2];
        for (int i=0; i<len; i++) {
            CopyOption option = options[i];
            if (option == StandardCopyOption.ATOMIC_MOVE) {
                throw new AtomicMoveNotSupportedException(null, null,
                    "Atomic move between providers is not supported");
            }
            newOptions[i] = option;
        }
        newOptions[len] = LinkOption.NOFOLLOW_LINKS;
        newOptions[len+1] = StandardCopyOption.COPY_ATTRIBUTES;
        return newOptions;
    }

    /**
     * Simple copy for use when source and target are associated with different
     * providers
     */
    static void copyToForeignTarget(Path source, Path target,
                                    CopyOption... options)
        throws IOException
    {
        CopyOptions opts = CopyOptions.parse(options);
        LinkOption[] linkOptions = (opts.followLinks) ? new LinkOption[0] :
            new LinkOption[] { LinkOption.NOFOLLOW_LINKS };

        // retrieve source posix view, null if unsupported
        final PosixFileAttributeView sourcePosixView =
            Files.getFileAttributeView(source, PosixFileAttributeView.class);

        // attributes of source file
        BasicFileAttributes sourceAttrs = null;
        if (sourcePosixView != null) {
            try {
                sourceAttrs = Files.readAttributes(source,
                                                   PosixFileAttributes.class,
                                                   linkOptions);
            } catch (SecurityException ignored) {
                // okay to continue if RuntimePermission("accessUserInformation") not granted
            }
        }
        if (sourceAttrs == null)
            sourceAttrs = Files.readAttributes(source,
                                               BasicFileAttributes.class,
                                               linkOptions);
        assert sourceAttrs != null;

        if (sourceAttrs.isSymbolicLink())
            throw new IOException("Copying of symbolic links not supported");

        // delete target if it exists and REPLACE_EXISTING is specified
        if (opts.replaceExisting) {
            Files.deleteIfExists(target);
        } else if (Files.exists(target))
            throw new FileAlreadyExistsException(target.toString());

        // create directory or copy file
        if (sourceAttrs.isDirectory()) {
            Files.createDirectory(target);
        } else {
            try (InputStream in = Files.newInputStream(source)) {
                Files.copy(in, target);
            }
        }

        // copy basic and, if supported, POSIX attributes to target
        if (opts.copyAttributes) {
            BasicFileAttributeView targetView = null;
            if (sourcePosixView != null) {
                targetView = Files.getFileAttributeView(target,
                                                     PosixFileAttributeView.class);
            }

            // target might not support posix even if source does
            if (targetView == null) {
                targetView = Files.getFileAttributeView(target,
                                                     BasicFileAttributeView.class);
            }

            try {
                targetView.setTimes(sourceAttrs.lastModifiedTime(),
                                 sourceAttrs.lastAccessTime(),
                                 sourceAttrs.creationTime());

                if (sourceAttrs instanceof PosixFileAttributes sourcePosixAttrs &&
                    targetView instanceof PosixFileAttributeView targetPosixView) {
                    try {
                        targetPosixView.setPermissions(sourcePosixAttrs.permissions());
                    } catch (SecurityException ignored) {
                        // okay to continue if RuntimePermission("accessUserInformation") not granted
                    }
                }
            } catch (Throwable x) {
                // rollback
                try {
                    Files.delete(target);
                } catch (Throwable suppressed) {
                    x.addSuppressed(suppressed);
                }
                throw x;
            }
        }
    }

    /**
     * Simple move implements as copy+delete for use when source and target are
     * associated with different providers
     */
    static void moveToForeignTarget(Path source, Path target,
                                    CopyOption... options) throws IOException
    {
        copyToForeignTarget(source, target, convertMoveToCopyOptions(options));
        Files.delete(source);
    }
}
