#ifndef Guid_h
#define Guid_h

#include <windows.h>
#include "tstrings.h"


class Guid {
public:
    Guid(const std::string& str);
    Guid(const std::wstring& str);
    Guid(const GUID& v);
    Guid();

    // Comparison for equality is the only comparison operation that make
    // sense for GUIDs. However in order to use STL algorithms with
    // Guid class need to define less operator.
    bool operator < (const Guid& other) const;
    bool operator == (const Guid& other) const;
    bool operator != (const Guid& other) const {
        return ! (*this == other);
    }

    enum StringifyFlags {
        WithCurlyBrackets = 0x0,
        WithDashes = 0x0,
        UpperCase = 0x0,
        StringifyDefaults = WithCurlyBrackets | UpperCase | WithDashes,
        NoCurlyBrackets = 0x1,
        NoDashes = 0x2,
        LowerCase = 0x4,
    };

    tstring toString(int flags=StringifyDefaults) const;

    /**
     * Returns string GUID representation of this instance compatible with
     * Windows MSI API.
     */
    tstring toMsiString() const {
        return toString(UpperCase | WithCurlyBrackets | WithDashes);
    }

    static Guid generate();

private:
    GUID value;
};

#endif // #ifndef Guid_h
