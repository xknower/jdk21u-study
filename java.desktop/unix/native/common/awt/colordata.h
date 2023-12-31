#ifndef _COLORDATA_H_
#define _COLORDATA_H_

#include "img_globals.h"

typedef struct ColorEntry {
        unsigned char r, g, b;
        unsigned char flags;
} ColorEntry;

typedef struct _ColorData {
    ColorEntry  *awt_Colors;
    int         awt_numICMcolors;
    int         *awt_icmLUT;
    unsigned char *awt_icmLUT2Colors;
    unsigned char *img_grays;
    unsigned char *img_clr_tbl;
    signed char* img_oda_red;
    signed char* img_oda_green;
    signed char* img_oda_blue;
    int *pGrayInverseLutData;
    int screendata;
    int representsPrimaries;
} ColorData;


#define CANFREE(pData) (pData  && (pData->screendata == 0))

#endif           /* _COLORDATA_H_ */
