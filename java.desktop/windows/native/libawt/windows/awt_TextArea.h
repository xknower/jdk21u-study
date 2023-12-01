#ifndef AWT_TEXTAREA_H
#define AWT_TEXTAREA_H

#include "awt_TextComponent.h"

#include "java_awt_TextArea.h"
#include "sun_awt_windows_WTextAreaPeer.h"

#include <ole2.h>
#include <richedit.h>
#include <richole.h>

/************************************************************************
 * AwtTextArea class
 */

class AwtTextArea : public AwtTextComponent {

public:

    /* java.awt.TextArea fields ids */
    static jfieldID scrollbarVisibilityID;

    AwtTextArea();
    virtual ~AwtTextArea();

    virtual void Dispose();

    static AwtTextArea* Create(jobject self, jobject parent);

    static size_t CountNewLines(JNIEnv *env, jstring jStr, size_t maxlen);
    static size_t GetALength(JNIEnv* env, jstring jStr, size_t maxlen);

    LRESULT WindowProc(UINT message, WPARAM wParam, LPARAM lParam);

    MsgRouting WmEnable(BOOL fEnabled);
    MsgRouting WmNcHitTest(UINT x, UINT y, LRESULT &retVal);
    MsgRouting HandleEvent(MSG *msg, BOOL synthetic);

    virtual BOOL InheritsNativeMouseWheelBehavior();
    virtual void Reshape(int x, int y, int w, int h);

    virtual LONG getJavaSelPos(LONG orgPos);
    virtual LONG getWin32SelPos(LONG orgPos);
    virtual void SetSelRange(LONG start, LONG end);

    // called on Toolkit thread from JNI
    static void _ReplaceText(void *param);

protected:

    void EditSetSel(CHARRANGE &cr);
  private:
    LONG    m_lHDeltaAccum;
    LONG    m_lVDeltaAccum;


};

#endif /* AWT_TEXTAREA_H */
