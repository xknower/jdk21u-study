#ifndef AWT_CLIPBOARD_H
#define AWT_CLIPBOARD_H

#include "awt.h"


/************************************************************************
 * AwtClipboard class
 */

class AwtClipboard {
private:
    static BOOL isGettingOwnership;
    static volatile BOOL isClipboardViewerRegistered;
    static volatile jmethodID handleContentsChangedMID;

public:
    static jmethodID lostSelectionOwnershipMID;
    static jobject theCurrentClipboard;

    INLINE static void GetOwnership() {
        AwtClipboard::isGettingOwnership = TRUE;
        VERIFY(EmptyClipboard());
        AwtClipboard::isGettingOwnership = FALSE;
    }

    INLINE static BOOL IsGettingOwnership() {
        return isGettingOwnership;
    }

    static void LostOwnership(JNIEnv *env);
    static void WmClipboardUpdate(JNIEnv *env);
    static void RegisterClipboardViewer(JNIEnv *env, jobject jclipboard);
    static void UnregisterClipboardViewer(JNIEnv *env);
};

#endif /* AWT_CLIPBOARD_H */
