#ifndef _COLORDATA_H_
#define _COLORDATA_H_

#include "img_globals.h"

typedef struct _ColorData {
    signed char* img_oda_red;
    signed char* img_oda_green;
    signed char* img_oda_blue;
    unsigned char* img_clr_tbl;
    int *pGrayInverseLutData;
    int representsPrimaries;
} ColorData;

#define CANFREE(pData) (pData)

#endif
