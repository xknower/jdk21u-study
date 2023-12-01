#include "systemScale.h"
#include <stdlib.h>

static int getScale(const char *name) {
    char *uiScale = getenv(name);
    if (uiScale != NULL) {
        double scale = strtod(uiScale, NULL);
        if (scale < 1) {
            return -1;
        }
        return (int) scale;
    }
    return -1;
}

double getNativeScaleFactor() {
    static int scale = -2.0;

    if (scale == -2) {
        scale = getScale("J2D_UISCALE");
    }

    if (scale > 0) {
        return scale;
    }

    return getScale("GDK_SCALE");
}
