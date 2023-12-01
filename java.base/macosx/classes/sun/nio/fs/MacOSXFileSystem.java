package sun.nio.fs;

import java.util.regex.Pattern;
import sun.security.action.GetPropertyAction;

import static sun.nio.fs.MacOSXNativeDispatcher.*;

/**
 * MacOS implementation of FileSystem
 */

class MacOSXFileSystem extends BsdFileSystem {

    private static final String PROPERTY_NORMALIZE_FILE_PATHS =
        "jdk.nio.path.useNormalizationFormD";

    private static final boolean NORMALIZE_FILE_PATHS;

    static {
        final String name = PROPERTY_NORMALIZE_FILE_PATHS;
        String value = GetPropertyAction.privilegedGetProperty(name);
        NORMALIZE_FILE_PATHS = (value != null)
            && ("".equals(value) || Boolean.parseBoolean(value));
    }

    MacOSXFileSystem(UnixFileSystemProvider provider, String dir) {
        super(provider, dir);
    }

    boolean isCaseInsensitiveAndPreserving() {
        return true;
    }

    // match in unicode canon_eq
    Pattern compilePathMatchPattern(String expr) {
        return Pattern.compile(expr, Pattern.CANON_EQ) ;
    }

    @Override
    String normalizeNativePath(String path) {
        if (NORMALIZE_FILE_PATHS) {
            for (int i = 0; i < path.length(); i++) {
                char c = path.charAt(i);
                if (c > 0x80)
                    return new String(normalizepath(path.toCharArray(),
                                  kCFStringNormalizationFormD));
            }
        }
        return path;
    }

    @Override
    String normalizeJavaPath(String path) {
        if (NORMALIZE_FILE_PATHS) {
            for (int i = 0; i < path.length(); i++) {
                if (path.charAt(i) > 0x80)
                    return new String(normalizepath(path.toCharArray(),
                                      kCFStringNormalizationFormC));
            }
        }
        return path;
    }

}
