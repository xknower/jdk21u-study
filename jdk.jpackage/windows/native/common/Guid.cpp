#include <algorithm>
#include <Objbase.h>

#include "Guid.h"
#include "ErrorHandling.h"


// Code in this file requires linking with ole32


Guid::Guid(const std::string& str) {
    *this = Guid(std::wstring(str.begin(), str.end()));
}


namespace {

void initGuid(const std::wstring& str, GUID& v) {
    if (S_OK != IIDFromString(str.c_str(), &v)) {
        JP_THROW(tstrings::any() << "IIDFromString(" << str << ") failed");
    }
}

} //namespace

Guid::Guid(const std::wstring& str) {
    enum { StdGuildLength = 38 };

    if ((str.size() == StdGuildLength && str.front() == L'{' && str.back() == L'}')) {
        initGuid(str, value);
        return;
    }

    enum { BracketCount = 2 };
    enum { DashCount = 4 };

    std::wstring buf(str);

    if (str.size() >= (StdGuildLength - (BracketCount + DashCount))) {
        if (str.front() != L'{' && str.back() != L'}') {
            buf = L"{" + str + L"}";
        }

        if (str.find(L'-') == std::wstring::npos) {
            const size_t positions[] = { 9, 14, 19, 24 };
            for (int i = 0; i < DashCount; ++i) {
                buf.insert(positions[i], 1, L'-');
            }
        }

        if (buf.size() != StdGuildLength) {
            // Still no good, drop all tweaks.
            // Let parsing function fail on the original string.
            buf = str;
        }
    }
    initGuid(buf, value);
}


Guid::Guid(const GUID& v): value(v) {
}


Guid::Guid() {
    memset(&value, 0, sizeof(value));
}


bool Guid::operator < (const Guid& other) const {
    return toString() < other.toString();
}


bool Guid::operator == (const Guid& other) const {
    return IsEqualGUID(value, other.value) != FALSE;
}


tstring Guid::toString(int flags) const {
    wchar_t buffer[128];
    const int chars = StringFromGUID2(value, buffer, _countof(buffer));
    if (chars < 3 /* strlen("{}") + 1 */) {
        JP_THROW("StringFromGUID2() failed");
    }

    tstring reply(tstrings::fromUtf16(buffer));

    if (flags & NoCurlyBrackets) {
        reply = reply.substr(1, reply.size() - 2);
    }

    if (flags & NoDashes) {
        // Drop all '-'.
        reply = tstring(reply.begin(), std::remove(reply.begin(), reply.end(), _T('-')));
    }

    if (flags & LowerCase) {
        reply = tstrings::toLower(reply);
    }

    return reply;
}


Guid Guid::generate() {
    GUID guid = { 0 };
    if (S_OK != CoCreateGuid(&guid)) {
        JP_THROW("CoCreateGuid() failed");
    }
    return Guid(guid);
}
