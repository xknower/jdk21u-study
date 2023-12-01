#ifndef MTLSurfaceData_h_Included
#define MTLSurfaceData_h_Included

#import "MTLSurfaceDataBase.h"
#import "MTLGraphicsConfig.h"
#import "AWTWindow.h"
#import "MTLLayer.h"

/**
 * The MTLSDOps structure contains the MTL-specific information for a given
 * MTLSurfaceData.  It is referenced by the native MTLSDOps structure.
 */
typedef struct _MTLSDOps {
    AWTView               *peerData;
    MTLLayer              *layer;
    jint              argb[4]; // background clear color
    MTLGraphicsConfigInfo *configInfo;
} MTLSDOps;

// debug-method
NSString * getSurfaceDescription(const BMTLSDOps * bmtlsdOps);

#endif /* MTLSurfaceData_h_Included */
