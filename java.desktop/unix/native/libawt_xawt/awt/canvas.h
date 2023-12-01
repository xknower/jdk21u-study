#ifdef HEADLESS
    #error This file should not be included in headless library
#endif

#ifndef _CANVAS_H_
#define _CANVAS_H_

KeySym awt_getX11KeySym(jint awtKey);

#endif           /* _CANVAS_H_ */
