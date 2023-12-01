package sun.security.smartcardio;

import java.io.File;
import java.io.IOException;

import java.security.AccessController;
import java.security.PrivilegedAction;

import sun.security.util.Debug;

/**
 * Platform specific code and functions for Unix / MUSCLE based PC/SC
 * implementations.
 *
 * @since   1.6
 * @author  Andreas Sterbenz
 */
class PlatformPCSC {

    static final Debug debug = Debug.getInstance("pcsc");

    private static final String PROP_NAME = "sun.security.smartcardio.library";

    private static final String LIB1 = "/usr/$LIBISA/libpcsclite.so";
    private static final String LIB2 = "/usr/local/$LIBISA/libpcsclite.so";
    private static final String PCSC_FRAMEWORK = "/System/Library/Frameworks/PCSC.framework/Versions/Current/PCSC";

    PlatformPCSC() {
        // empty
    }

    @SuppressWarnings("removal")
    static final Throwable initException
            = AccessController.doPrivileged(new PrivilegedAction<Throwable>() {
        public Throwable run() {
            try {
                System.loadLibrary("j2pcsc");
                String library = getLibraryName();
                if (debug != null) {
                    debug.println("Using PC/SC library: " + library);
                }
                initialize(library);
                return null;
            } catch (Throwable e) {
                return e;
            }
        }
    });

    // expand $LIBISA to the system specific directory name for libraries
    private static String expand(String lib) {
        int k = lib.indexOf("$LIBISA");
        if (k == -1) {
            return lib;
        }
        String s1 = lib.substring(0, k);
        String s2 = lib.substring(k + 7);
        String libDir;
        if ("64".equals(System.getProperty("sun.arch.data.model"))) {
            // assume Linux convention
            libDir = "lib64";
        } else {
            // must be 32-bit
            libDir = "lib";
        }
        String s = s1 + libDir + s2;
        return s;
    }

    private static String getLibraryName() throws IOException {
        // if system property is set, use that library
        String lib = expand(System.getProperty(PROP_NAME, "").trim());
        if (lib.length() != 0) {
            return lib;
        }
        lib = expand(LIB1);
        if (new File(lib).isFile()) {
            // if LIB1 exists, use that
            return lib;
        }
        lib = expand(LIB2);
        if (new File(lib).isFile()) {
            // if LIB2 exists, use that
            return lib;
        }

        // As of macos 11, framework libraries have been removed from the file
        // system, but in such a way that they can still be dlopen()ed, even
        // though they can no longer be open()ed.
        //
        // https://developer.apple.com/documentation/macos-release-notes/macos-big-sur-11_0_1-release-notes
        //
        // """New in macOS Big Sur 11.0.1, the system ships with a built-in
        // dynamic linker cache of all system-provided libraries. As part of
        // this change, copies of dynamic libraries are no longer present on
        // the filesystem. Code that attempts to check for dynamic library
        // presence by looking for a file at a path or enumerating a directory
        // will fail. Instead, check for library presence by attempting to
        // dlopen() the path, which will correctly check for the library in the
        // cache."""
        //
        // The directory structure remains otherwise intact, so check for
        // existence of the containing directory instead of the file.
        lib = PCSC_FRAMEWORK;
        if (new File(lib).getParentFile().isDirectory()) {
            // if PCSC.framework exists, use that
            return lib;
        }
        throw new IOException("No PC/SC library found on this system");
    }

    private static native void initialize(String libraryName);

    // PCSC constants defined differently under Windows and MUSCLE
    // MUSCLE version
    static final int SCARD_PROTOCOL_T0     =  0x0001;
    static final int SCARD_PROTOCOL_T1     =  0x0002;
    static final int SCARD_PROTOCOL_RAW    =  0x0004;

    static final int SCARD_UNKNOWN         =  0x0001;
    static final int SCARD_ABSENT          =  0x0002;
    static final int SCARD_PRESENT         =  0x0004;
    static final int SCARD_SWALLOWED       =  0x0008;
    static final int SCARD_POWERED         =  0x0010;
    static final int SCARD_NEGOTIABLE      =  0x0020;
    static final int SCARD_SPECIFIC        =  0x0040;

}
