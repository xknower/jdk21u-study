#ifndef AWT_TRAY_ICON_H
#define AWT_TRAY_ICON_H

#include "awt_Object.h"
#include "awt_Component.h"

#include "java_awt_TrayIcon.h"
#include "sun_awt_windows_WTrayIconPeer.h"
#include "java_awt_event_ActionEvent.h"

#define TRAY_ICON_X_HOTSPOT 0
#define TRAY_ICON_Y_HOTSPOT 0

#define TRAY_ICON_TOOLTIP_MAX_SIZE 128

#define TRAY_ICON_BALLOON_TITLE_MAX_SIZE 64
#define TRAY_ICON_BALLOON_INFO_MAX_SIZE  256

/************************************************************************
 * AwtTrayIcon class
 */

class AwtTrayIcon: public AwtObject {
public:
    AwtTrayIcon();
    virtual ~AwtTrayIcon();

    virtual void Dispose();

    void LinkObjects(JNIEnv *env, jobject peer);
    void UnlinkObjects();

    void InitNID(UINT uID);

    void InitMessage(MSG* msg, UINT message, WPARAM wParam, LPARAM lParam,
                     int x = 0, int y = 0);

    void SendMouseEvent(jint id, jlong when, jint x, jint y, jint modifiers, jint clickCount,
                        jboolean popupTrigger, jint button = 0, MSG *pMsg = NULL);
    void SendActionEvent(jint id, jlong when, jint modifiers, MSG *pMsg = NULL);

    virtual MsgRouting WmAwtTrayNotify(WPARAM wParam, LPARAM lParam);
    virtual MsgRouting WmMouseDown(UINT flags, int x, int y, int button);
    virtual MsgRouting WmMouseUp(UINT flags, int x, int y, int button);
    virtual MsgRouting WmMouseMove(UINT flags, int x, int y);
    virtual MsgRouting WmBalloonUserClick(UINT flags, int x, int y);
    virtual MsgRouting WmKeySelect(UINT flags, int x, int y);
    virtual MsgRouting WmSelect(UINT flags, int x, int y);
    virtual MsgRouting WmContextMenu(UINT flags, int x, int y);
    static MsgRouting WmTaskbarCreated();

    INLINE void SetID(int ID) { m_nid.uID = ID; }
    INLINE int GetID() { return m_nid.uID; }

    void SetToolTip(LPCTSTR tooltip);
    INLINE LPTSTR GetToolTip() { return m_nid.szTip; }

    void SetIcon(HICON hIcon);
    INLINE HICON GetIcon() { return m_nid.hIcon; }

    void DisplayMessage(LPCTSTR caption, LPCTSTR text, LPCTSTR msgType);

    void UpdateImage();

    // Adds to the head of the list
    INLINE void AddTrayIconItem(UINT id) {
        TrayIconListItem* item = new TrayIconListItem(id, this);
        item->m_next = sm_trayIconList;
        sm_trayIconList = item;
    }

    static AwtTrayIcon* SearchTrayIconItem(UINT id);
    static void RemoveTrayIconItem(UINT id);

    static LPCTSTR GetClassName();
    static void FillClassInfo(WNDCLASS *lpwc);
    static void RegisterClass();
    static void UnregisterClass();

    static LRESULT CALLBACK TrayWindowProc(HWND hWnd, UINT uMsg, WPARAM wParam, LPARAM lParam);

    static AwtTrayIcon* Create(jobject self, jobject parent);

    static HWND CreateMessageWindow();
    static void DestroyMessageWindow();

    static HBITMAP CreateBMP(HWND hW,int* imageData,int nSS, int nW, int nH);

    // methods called on Toolkit thread
    static void _SetToolTip(void *param);
    static void _SetIcon(void *param);
    static void _UpdateIcon(void *param);
    static void _DisplayMessage(void *param);

    /*
     * java.awt.TrayIcon fields
     */
    static jfieldID idID;
    static jfieldID actionCommandID;
    static jmethodID updateImageID;

    // ************************

    static HWND sm_msgWindow;
    static int sm_instCount;

private:
    NOTIFYICONDATA m_nid;

    /* A bitmask keeps the button's numbers as MK_LBUTTON, MK_MBUTTON, MK_RBUTTON
     * which are allowed to
     * generate the CLICK event after the RELEASE has happened.
     * There are conditions that must be true for that sending CLICK event:
     * 1) button was initially PRESSED
     * 2) no movement or drag has happened until RELEASE
    */
    UINT m_mouseButtonClickAllowed;

    class TrayIconListItem {
      public:
        TrayIconListItem(UINT id, AwtTrayIcon* trayIcon) {
            m_ID = id;
            m_trayIcon = trayIcon;
            m_next = NULL;
        }
        UINT m_ID;
        AwtTrayIcon* m_trayIcon;
        TrayIconListItem* m_next;
    };

    BOOL SendTrayMessage(DWORD dwMessage);

    INLINE void AddTrayIcon() {
        BOOL result = SendTrayMessage(NIM_ADD);
        // 6270114: Instructs the taskbar to behave according to the Shell version 5.0
        if (result) {
            SendTrayMessage(NIM_SETVERSION);
        }
    }

    INLINE void ModifyTrayIcon() {
        SendTrayMessage(NIM_MODIFY);
    }

    static bool m_bDPIChanged;

public:
    static TrayIconListItem* sm_trayIconList;
};

#endif /* AWT_TRAY_ICON_H */
