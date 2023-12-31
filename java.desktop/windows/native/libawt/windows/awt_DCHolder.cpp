#include "awt_ole.h"
#include "awt_DCHolder.h"       // main symbols


////////////////////////
// struct DCHolder

DCHolder::DCHolder()
: m_hMemoryDC(NULL),
    m_iWidth(0),
    m_iHeight(0),
    m_bForImage(FALSE),
    m_hBitmap(NULL),
    m_hOldBitmap(NULL),
    m_pPoints(NULL)
{}

void DCHolder::Create(
    HDC hRelDC,
    int iWidth,
    int iHeght,
    BOOL bForImage
){
    OLE_DECL
    m_iWidth = iWidth;
    m_iHeight = iHeght;
    m_bForImage = bForImage;
    m_hMemoryDC = ::CreateCompatibleDC(hRelDC);
    //NB: can not throw an error in non-safe stack!!! Just conversion and logging!
    //OLE_WINERROR2HR just set OLE_HR without any throw!
    if (!m_hMemoryDC) {
        OLE_THROW_LASTERROR(_T("CreateCompatibleDC"))
    }
    m_hBitmap = m_bForImage
        ? CreateJavaContextBitmap(hRelDC, m_iWidth, m_iHeight, &m_pPoints)
        : ::CreateCompatibleBitmap(hRelDC, m_iWidth, m_iHeight);
    if (!m_hBitmap) {
        OLE_THROW_LASTERROR(_T("CreateCompatibleBitmap"))
    }
    m_hOldBitmap = (HBITMAP)::SelectObject(m_hMemoryDC, m_hBitmap);
    if (!m_hOldBitmap) {
        OLE_THROW_LASTERROR(_T("SelectBMObject"))
    }
}

DCHolder::~DCHolder(){
    if (m_hOldBitmap) {
        ::SelectObject(m_hMemoryDC, m_hOldBitmap);
    }
    if (m_hBitmap) {
        ::DeleteObject(m_hBitmap);
    }
    if (m_hMemoryDC) {
        ::DeleteDC(m_hMemoryDC);
    }
}


HBITMAP DCHolder::CreateJavaContextBitmap(
    HDC hdc,
    int iWidth,
    int iHeight,
    void **ppPoints)
{
    BITMAPINFO    bitmapInfo = {0};
    bitmapInfo.bmiHeader.biWidth = iWidth;
    bitmapInfo.bmiHeader.biHeight = -iHeight;
    bitmapInfo.bmiHeader.biPlanes = 1;
    bitmapInfo.bmiHeader.biBitCount = 32;
    bitmapInfo.bmiHeader.biSize = sizeof(BITMAPINFOHEADER);
    bitmapInfo.bmiHeader.biCompression = BI_RGB;

    return ::CreateDIBSection(
        hdc,
        (BITMAPINFO *)&bitmapInfo,
        DIB_RGB_COLORS,
        (void **)ppPoints,
        NULL,
        0
    );
}
