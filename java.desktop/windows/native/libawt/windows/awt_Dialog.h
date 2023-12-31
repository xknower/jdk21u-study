#ifndef AWT_DIALOG_H
#define AWT_DIALOG_H

#include "awt_Frame.h"

#include "java_awt_Dialog.h"
#include "sun_awt_windows_WDialogPeer.h"


/************************************************************************
 * AwtDialog class
 */
// unification with AwtComponent
#define AWT_DIALOG_WINDOW_CLASS_NAME TEXT("SunAwtDialog")

class AwtDialog : public AwtFrame {
public:

    /* java.awt.Dialog field ids */
    static jfieldID titleID;

    /* boolean undecorated field for java.awt.Dialog */
    static jfieldID undecoratedID;

    AwtDialog();
    virtual ~AwtDialog();

    virtual void Dispose();

    virtual LPCTSTR GetClassName();
    virtual void  FillClassInfo(WNDCLASSEX *lpwc);
    virtual void SetResizable(BOOL isResizable);

    void Show();

    virtual void DoUpdateIcon();
    virtual HICON GetEffectiveIcon(int iconType);

    /* Create a new AwtDialog.  This must be run on the main thread. */
    static AwtDialog* Create(jobject peer, jobject hParent);
    virtual MsgRouting WmShowModal();
    virtual MsgRouting WmEndModal();
    virtual MsgRouting WmStyleChanged(int wStyleType, LPSTYLESTRUCT lpss);
    virtual MsgRouting WmSize(UINT type, int w, int h);
    MsgRouting WmNcMouseDown(WPARAM hitTest, int x, int y, int button);
    virtual LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);

    /*
     * The check is performed before the dialog is shown.
     * The focused window can't be blocked at the time it's focused.
     * Thus we don't have to perform any transitive (a blocker of a blocker) checks.
     */
    INLINE virtual BOOL IsFocusedWindowModalBlocker() {
        return (AwtComponent::GetFocusedWindow() != NULL) && (GetModalBlocker(AwtComponent::GetFocusedWindow()) == GetHWnd());
    }

    // finds and activates some window after the modal dialog is hidden
    static void ModalActivateNextWindow(HWND dialogHWnd,
                                        jobject dialogTarget, jobject dialogPeer);

    // some methods called on Toolkit thread
    static void _ShowModal(void *param);
    static void _EndModal(void *param);
    static void _SetIMMOption(void *param);

    static BOOL IsModalExcluded(HWND hwnd);

    static void CheckInstallModalHook();
    static void CheckUninstallModalHook();

private:

    void UpdateSystemMenu();

    HWND m_modalWnd;

    // checks if the given window can be activated after a modal dialog is hidden
    inline static BOOL ModalCanBeActivated(HWND hwnd) {
        return ::IsWindow(hwnd) &&
               ::IsWindowVisible(hwnd) &&
               ::IsWindowEnabled(hwnd) &&
              !::IsWindow(AwtWindow::GetModalBlocker(hwnd));
    }
    /*
     * Activates the given window
     * If the window is an embedded frame, it is activated from Java code.
     *   See WEmbeddedFrame.activateEmbeddingTopLevel() for details.
     */
    static void ModalPerformActivation(HWND hWnd);

    static void PopupBlockers(HWND blocker, BOOL isModalHook, HWND prevFGWindow, BOOL onTaskbar);
    static void PopupBlocker(HWND blocker, HWND nextBlocker, BOOL isModalHook, HWND prevFGWindow, BOOL onTaskbar);

public:

    // WH_CBT hook procedure used in modality, prevents modal
    // blocked windows from being activated
    static LRESULT CALLBACK ModalFilterProc(int code,
                                            WPARAM wParam, LPARAM lParam);
    // WM_MOUSE hook procedure used in modality, filters some
    // mouse events for blocked windows and brings blocker
    // dialog to front
    static LRESULT CALLBACK MouseHookProc(int code,
                                          WPARAM wParam, LPARAM lParam);
    // WM_MOUSE hook procedure used in modality, similar to
    // MouseHookProc but installed on non-toolkit threads, for
    // example on browser's thread when running in Java Plugin
    static LRESULT CALLBACK MouseHookProc_NonTT(int code,
                                                WPARAM wParam, LPARAM lParam);

    static void AnimateModalBlocker(HWND window);
};

#endif /* AWT_DIALOG_H */
