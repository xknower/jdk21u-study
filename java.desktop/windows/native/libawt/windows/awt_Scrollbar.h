#ifndef AWT_SCROLLBAR_H
#define AWT_SCROLLBAR_H

#include "awt_Component.h"

#include "java_awt_Scrollbar.h"
#include "sun_awt_windows_WScrollbarPeer.h"


#define Java_java_awt_Scrollbar_HORIZONTAL    0
#define Java_java_awt_Scrollbar_VERTICAL      1


/************************************************************************
 * AwtScrollbar class
 */

class AwtScrollbar : public AwtComponent {
public:

    /* java.awt.Scrollbar fields */
    static jfieldID lineIncrementID;
    static jfieldID pageIncrementID;
    static jfieldID orientationID;

    AwtScrollbar();
    virtual ~AwtScrollbar();

    virtual void Dispose();

    virtual LPCTSTR GetClassName();

    static AwtScrollbar* Create(jobject self, jobject parent);

    void SetValue(int value);
    void SetLineIncrement(int value) { m_lineIncr = value; }
    void SetPageIncrement(int value) { m_pageIncr = value; }

    virtual LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);

    /*
     * Windows message handler functions
     */
    virtual MsgRouting WmHScroll(UINT scrollCode, UINT pos, HWND hScrollBar);
    virtual MsgRouting WmVScroll(UINT scrollCode, UINT pos, HWND hScrollBar);

    // Prevent KB Q102552 race.
    virtual MsgRouting WmMouseDown(UINT flags, int x, int y, int button);
    virtual MsgRouting WmNcHitTest(UINT x, UINT y, LRESULT& retVal);

    virtual MsgRouting HandleEvent(MSG *msg, BOOL synthetic);

    INLINE virtual BOOL IsScrollbar() { return TRUE; }

    static void _SetLineIncrement(void *param);
    static void _SetPageIncrement(void *param);
    // invoked on Toolkit thread
    static void _SetValues(void *param);

private:
    UINT          m_orientation; /* SB_HORZ or SB_VERT */

    int           m_lineIncr;
    int           m_pageIncr;

    // Work around KB Q73839 bug.
    void UpdateFocusIndicator();

    // Don't do redundant callbacks.
    const char *m_prevCallback;
    int m_prevCallbackPos;

    static const char * const SbNlineDown;
    static const char * const SbNlineUp;
    static const char * const SbNpageDown;
    static const char * const SbNpageUp;
    static const char * const SbNdrag;
    static const char * const SbNdragEnd;
    static const char * const SbNwarp;

    static int ms_instanceCounter;
    static HHOOK ms_hMouseFilter;
    static BOOL ms_isInsideMouseFilter;
    static LRESULT CALLBACK MouseFilter(int nCode, WPARAM wParam,
                                        LPARAM lParam);

    void DoScrollCallbackCoalesce(const char* methodName, int newPos);
};

#endif /* AWT_SCROLLBAR_H */
