#ifndef AWT_LABEL_H
#define AWT_LABEL_H

#include "awt_Component.h"

#include "java_awt_Label.h"
#include "sun_awt_windows_WLabelPeer.h"


/************************************************************************
 * AwtLabel class
 */

class AwtLabel : public AwtComponent {
public:
    /*
     * java.awt.Label fields
     */
    static jfieldID textID;
    static jfieldID alignmentID;

    AwtLabel();

    virtual LPCTSTR GetClassName();

    static AwtLabel* Create(jobject label, jobject parent);

    /*
     * Windows message handler functions
     */
    virtual MsgRouting WmPaint(HDC hDC);
    virtual MsgRouting WmPrintClient(HDC hDC, LPARAM flags);
    virtual MsgRouting WmEraseBkgnd(HDC hDC, BOOL& didErase);

    /*
     * if WM_PAINT was received when we cannot paint
     * then setup m_needPaint and when can paint call this function
     */
    void LazyPaint();
     /*
      * Enable/disable component
      */
    virtual void Enable(BOOL bEnable);

    // some methods called on Toolkit thread
    static void _SetText(void *param);
    static void _SetAlignment(void *param);
    static void _LazyPaint(void *param);

private:
    BOOL m_needPaint; // flags for lazy paint of Label

    void DoPaint(HDC hDC, RECT& r);
};

#endif /* AWT_LABEL_H */
