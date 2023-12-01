#import "AWT_debug.h"

bool ShouldPrintVerboseDebugging() {
    static int debug = -1;
    if (debug == -1) {
        debug = (int)(getenv("JAVA_AWT_VERBOSE") != NULL);
    }
    return (bool)debug;
}
