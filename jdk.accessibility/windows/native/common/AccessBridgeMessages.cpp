/*
 * Common AccessBridge IPC message definitions
 */

#include "AccessBridgeMessages.h"


// unique broadcast msg. IDs gotten dymanically

// wParam == sourceHwnc; lParam = *vmID
UINT theFromJavaHelloMsgID;
// wParam == sourceHwnc; lParam unused
UINT theFromWindowsHelloMsgID;


BOOL initBroadcastMessageIDs() {
        theFromJavaHelloMsgID = RegisterWindowMessage("AccessBridge-FromJava-Hello");
        theFromWindowsHelloMsgID = RegisterWindowMessage("AccessBridge-FromWindows-Hello");

        if (theFromJavaHelloMsgID == 0 || theFromWindowsHelloMsgID) {
                return FALSE;
        }
        return TRUE;
}
