#import "QuartzSurfaceData.h"

struct _PrintSDOps
{
    QuartzSDOps                qsdo; // must be the first entry!

    NSGraphicsContext        *nsRef;

    jint                    width;
    jint                    height;
};
typedef struct _PrintSDOps PrintSDOps;
