#ifndef _AWT_PRINT_CONTROL_H_
#define _AWT_PRINT_CONTROL_H_

#include "stdhdrs.h"
#include <commdlg.h>

/************************************************************************
 * AwtPrintControl class
 */

class AwtPrintControl {
public:

    /* sun.awt.windows.WPrinterJob methods & fields */

    static jfieldID  dialogOwnerPeerID;
    static jfieldID  driverDoesMultipleCopiesID;
    static jfieldID  driverDoesCollationID;
    static jmethodID getPrintDCID;
    static jmethodID setPrintDCID;
    static jmethodID getDevmodeID;
    static jmethodID setDevmodeID;
    static jmethodID getDevnamesID;
    static jmethodID setDevnamesID;
    static jmethodID getParentWindowID;
    static jmethodID getWin32MediaID;
    static jmethodID setWin32MediaID;
    static jmethodID getWin32MediaTrayID;
    static jmethodID setWin32MediaTrayID;
    static jmethodID getColorID;
    static jmethodID getCopiesID;
    static jmethodID getSelectID;
    static jmethodID getDestID;
    static jmethodID getDialogID;
    static jmethodID getFromPageID;
    static jmethodID getMaxPageID;
    static jmethodID getMinPageID;
    static jmethodID getCollateID;
    static jmethodID getOrientID;
    static jmethodID getQualityID;
    static jmethodID getPrintToFileEnabledID;
    static jmethodID getPrinterID;
    static jmethodID setPrinterID;
    static jmethodID getResID;
    static jmethodID getSidesID;
    static jmethodID getToPageID;
    static jmethodID setToPageID;
    static jmethodID setNativeAttID;
    static jmethodID setRangeCopiesID;
    static jmethodID setResID;
    static jmethodID setJobAttributesID;

    static void initIDs(JNIEnv *env, jclass cls);
    static BOOL FindPrinter(jstring printerName, LPBYTE pPrinterEnum,
                            LPDWORD pcbBuf, LPTSTR * foundPrinter,
                            LPTSTR * foundPORT);
    // This function determines whether the printer driver
    // for the passed printer handle supports PRINTER_INFO
    // structure of level dwLevel.
    static BOOL IsSupportedLevel(HANDLE hPrinter, DWORD dwLevel);
    static BOOL CreateDevModeAndDevNames(PRINTDLG *ppd,
                                               LPTSTR pPrinterName,
                                               LPTSTR pPortName);
    static BOOL InitPrintDialog(JNIEnv *env,
                                      jobject printCtrl, PRINTDLG &pd);
    static BOOL UpdateAttributes(JNIEnv *env,
                                      jobject printCtrl, PRINTDLG &pd);
    static WORD getNearestMatchingPaper(LPTSTR printer, LPTSTR port,
                                      double origWid, double origHgt,
                                      double* newWid, double *newHgt);

    static BOOL getDevmode(HANDLE hPrinter,
                                 LPTSTR pPrinterName,
                                 LPDEVMODE *pDevMode);

    inline static HWND getParentID(JNIEnv *env, jobject self) {
      return (HWND)env->CallLongMethod(self, getParentWindowID);
    }

    inline static  HDC getPrintDC(JNIEnv *env, jobject self) {
      return (HDC)env->CallLongMethod(self, getPrintDCID);
    }

    inline static void setPrintDC(JNIEnv *env, jobject self, HDC printDC) {
      env->CallVoidMethod(self, setPrintDCID, (jlong)printDC);
    }

    inline static HGLOBAL getPrintHDMode(JNIEnv *env, jobject self) {
      return (HGLOBAL) env->CallLongMethod(self, getDevmodeID);
    }

    inline static void setPrintHDMode(JNIEnv *env, jobject self,
                                      HGLOBAL hGlobal) {
      env->CallVoidMethod(self, setDevmodeID,
                          reinterpret_cast<jlong>(hGlobal));
    }

    inline static HGLOBAL getPrintHDName(JNIEnv *env, jobject self) {
      return (HGLOBAL) env->CallLongMethod(self, getDevnamesID);
    }

    inline static void setPrintHDName(JNIEnv *env, jobject self,
                                      HGLOBAL hGlobal) {
      env->CallVoidMethod(self, setDevnamesID,
                          reinterpret_cast<jlong>(hGlobal));
    }

};

#endif
