#include <algorithm>
#include <fstream>
#include "ResourceEditor.h"
#include "WinErrorHandling.h"
#include "Log.h"


ResourceEditor::FileLock::FileLock(const std::wstring& binaryPath) {
    h = BeginUpdateResource(binaryPath.c_str(), FALSE);
    if (NULL == h) {
        JP_THROW(SysError(tstrings::any() << "BeginUpdateResource("
                    << binaryPath << ") failed", BeginUpdateResource));
    }

    ownHandle(true);
    discard(false);
}


ResourceEditor::FileLock::FileLock(HANDLE h): h(h) {
    ownHandle(false);
    discard(false);
}


ResourceEditor::FileLock::~FileLock() {
    if (theOwnHandle && !EndUpdateResource(h, theDiscard)) {
        JP_NO_THROW(JP_THROW(SysError(tstrings::any()
            << "EndUpdateResource(" << h << ") failed.", EndUpdateResource)));
    }
}


ResourceEditor::ResourceEditor() {
    language(MAKELANGID(LANG_NEUTRAL, SUBLANG_NEUTRAL)).type(unsigned(0)).id(unsigned(0));
}


ResourceEditor& ResourceEditor::type(unsigned v) {
    return type(MAKEINTRESOURCE(v));
}


ResourceEditor& ResourceEditor::type(LPCWSTR v) {
    if (IS_INTRESOURCE(v)) {
        std::wostringstream printer;
        printer << L"#" << reinterpret_cast<size_t>(v);
        theType = printer.str();
        theTypePtr = MAKEINTRESOURCE(static_cast<DWORD>(reinterpret_cast<DWORD_PTR>(v)));
    } else {
        theType = v;
        theTypePtr = theType.c_str();
    }
    return *this;
}


ResourceEditor& ResourceEditor::id(unsigned v) {
    return id(MAKEINTRESOURCE(v));
}


ResourceEditor& ResourceEditor::id(LPCWSTR v) {
    if (IS_INTRESOURCE(v)) {
        std::wostringstream printer;
        printer << L"#" << reinterpret_cast<size_t>(v);
        theId = printer.str();
    } else {
        theId = v;
    }
    theIdPtr = v;
    return *this;
}


ResourceEditor& ResourceEditor::apply(const FileLock& dstBinary,
                            std::istream& srcStream, std::streamsize size) {

    typedef std::vector<BYTE> ByteArray;
    ByteArray buf;
    if (size <= 0) {
        // Read the entire stream.
        buf = ByteArray((std::istreambuf_iterator<char>(srcStream)),
                                            std::istreambuf_iterator<char>());
    } else {
        buf.resize(size_t(size));
        srcStream.read(reinterpret_cast<char*>(buf.data()), size);
    }

    auto reply = UpdateResource(dstBinary.get(), theTypePtr, theIdPtr, lang,
                                buf.data(), static_cast<DWORD>(buf.size()));
    if (reply == FALSE) {
        JP_THROW(SysError("UpdateResource() failed", UpdateResource));
    }

    return *this;
}


ResourceEditor& ResourceEditor::apply(const FileLock& dstBinary,
                                                const std::wstring& srcFile) {
    std::ifstream input(srcFile, std::ios_base::binary);
    input.exceptions(std::ios::failbit | std::ios::badbit);
    return apply(dstBinary, input);
}
