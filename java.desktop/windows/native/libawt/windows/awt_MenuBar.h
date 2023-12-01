#ifndef AWT_MENUBAR_H
#define AWT_MENUBAR_H

#include "awt.h"
#include "awt_Menu.h"
#include <java_awt_MenuBar.h>
#include <sun_awt_windows_WMenuBarPeer.h>
#include <sun_awt_windows_WFramePeer.h>


class AwtFrame;


/************************************************************************
 * AwtMenuBar class
 */

class AwtMenuBar : public AwtMenu {
public:

    /* java.awt.MenuBar method ids */
    static jmethodID getMenuCountMID;
    static jmethodID getMenuMID;

    AwtMenuBar();
    virtual ~AwtMenuBar();

    virtual void Dispose();

    virtual LPCTSTR GetClassName();

    /* Create a new AwtMenuBar.  This must be run on the main thread. */
    static AwtMenuBar* Create(jobject self, jobject framePeer);

    virtual AwtMenuBar* GetMenuBar() { return this; }
    INLINE AwtFrame* GetFrame() { return m_frame; }
    INLINE void SetFrame(AwtFrame* frame) {
        m_frame = frame;
    }

    virtual HWND GetOwnerHWnd();
    virtual void RedrawMenuBar();

    AwtMenuItem* GetItem(jobject target, long index);
    int CountItem(jobject menuBar);

    void DrawItem(DRAWITEMSTRUCT& drawInfo);
    void MeasureItem(HDC hDC, MEASUREITEMSTRUCT& measureInfo);

    void AddItem(AwtMenuItem* item);
    void DeleteItem(UINT index);

    // called on Toolkit thread
    static void _AddMenu(void *param);
    static void _DelItem(void *param);
protected:
    AwtFrame* m_frame;
};

#endif /* AWT_MENUBAR_H */
