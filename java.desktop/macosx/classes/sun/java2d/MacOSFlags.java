package sun.java2d;

import java.security.PrivilegedAction;
import sun.java2d.metal.MTLGraphicsConfig;
import sun.java2d.opengl.CGLGraphicsConfig;


public class MacOSFlags {

    /**
     * Description of command-line flags.  All flags with [true|false]
     * values
     *      metalEnabled: usage: "-Dsun.java2d.metal=[true|false]"
     */

    private static boolean oglEnabled;
    private static boolean oglVerbose;
    private static boolean metalEnabled;
    private static boolean metalVerbose;

    private enum PropertyState {ENABLED, DISABLED, UNSPECIFIED}

    static {
        initJavaFlags();
    }

    private static PropertyState getBooleanProp(String p, PropertyState defaultVal) {
        String propString = System.getProperty(p);
        PropertyState returnVal = defaultVal;
        if (propString != null) {
            if (propString.equals("true") ||
                propString.equals("t") ||
                propString.equals("True") ||
                propString.equals("T") ||
                propString.equals("")) // having the prop name alone
            {                          // is equivalent to true
                returnVal = PropertyState.ENABLED;
            } else if (propString.equals("false") ||
                       propString.equals("f") ||
                       propString.equals("False") ||
                       propString.equals("F"))
            {
                returnVal = PropertyState.DISABLED;
            }
        }
        return returnVal;
    }

    private static boolean isBooleanPropTrueVerbose(String p) {
        String propString = System.getProperty(p);
        if (propString != null) {
            if (propString.equals("True") ||
                propString.equals("T"))
            {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("removal")
    private static void initJavaFlags() {
        java.security.AccessController.doPrivileged(
                (PrivilegedAction<Object>) () -> {
                    PropertyState oglState = getBooleanProp("sun.java2d.opengl", PropertyState.UNSPECIFIED);
                    PropertyState metalState = getBooleanProp("sun.java2d.metal", PropertyState.UNSPECIFIED);

                    // Handle invalid combinations to use the default rendering pipeline
                    // The default rendering pipeline is Metal
                    if ((oglState == PropertyState.UNSPECIFIED && metalState == PropertyState.UNSPECIFIED) ||
                        (oglState == PropertyState.DISABLED && metalState == PropertyState.DISABLED) ||
                        (oglState == PropertyState.ENABLED && metalState == PropertyState.ENABLED)) {
                        metalState = PropertyState.ENABLED; // Enable default pipeline
                        oglState = PropertyState.DISABLED; // Disable non-default pipeline
                    }

                    if (metalState == PropertyState.UNSPECIFIED) {
                        if (oglState == PropertyState.DISABLED) {
                            oglEnabled = false;
                            metalEnabled = true;
                        } else {
                            oglEnabled = true;
                            metalEnabled = false;
                        }
                    } else if (metalState == PropertyState.ENABLED) {
                        oglEnabled = false;
                        metalEnabled = true;
                    } else if (metalState == PropertyState.DISABLED) {
                        oglEnabled = true;
                        metalEnabled = false;
                    }

                    oglVerbose = isBooleanPropTrueVerbose("sun.java2d.opengl");
                    metalVerbose = isBooleanPropTrueVerbose("sun.java2d.metal");

                    if (oglEnabled && !metalEnabled) {
                        // Check whether OGL is available
                        if (!CGLGraphicsConfig.isCGLAvailable()) {
                            if (oglVerbose) {
                                System.out.println("Could not enable OpenGL pipeline (CGL not available)");
                            }
                            oglEnabled = false;
                            metalEnabled = MTLGraphicsConfig.isMetalAvailable();
                        }
                    } else if (metalEnabled && !oglEnabled) {
                        // Check whether Metal framework is available
                        if (!MTLGraphicsConfig.isMetalAvailable()) {
                            if (metalVerbose) {
                                System.out.println("Could not enable Metal pipeline (Metal framework not available)");
                            }
                            metalEnabled = false;
                            oglEnabled = CGLGraphicsConfig.isCGLAvailable();
                        }
                    }

                    // At this point one of the rendering pipeline must be enabled.
                    if (!metalEnabled && !oglEnabled) {
                        throw new InternalError("Error - unable to initialize any rendering pipeline.");
                    }

                    return null;
                });
    }

    public static boolean isMetalEnabled() {
        return metalEnabled;
    }

    public static boolean isMetalVerbose() {
        return metalVerbose;
    }

    public static boolean isOGLEnabled() {
        return oglEnabled;
    }

    public static boolean isOGLVerbose() {
        return oglVerbose;
    }
}
