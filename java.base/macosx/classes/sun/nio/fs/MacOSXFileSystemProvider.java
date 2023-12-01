package sun.nio.fs;

import java.nio.file.Path;
import java.nio.file.spi.FileTypeDetector;
import jdk.internal.util.StaticProperty;
import sun.security.action.GetPropertyAction;

/**
 * MacOSX implementation of FileSystemProvider
 */

class MacOSXFileSystemProvider extends BsdFileSystemProvider {
    public MacOSXFileSystemProvider() {
        super();
    }

    @Override
    MacOSXFileSystem newFileSystem(String dir) {
        return new MacOSXFileSystem(this, dir);
    }

    @Override
    FileTypeDetector getFileTypeDetector() {
        Path userMimeTypes = Path.of(StaticProperty.userHome(), ".mime.types");

        return chain(new MimeTypesFileTypeDetector(userMimeTypes),
                     new UTIFileTypeDetector());
    }
}
