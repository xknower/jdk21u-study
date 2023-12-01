#include <algorithm>

#define NOMINMAX
#include "Version.h"



namespace VersionDetails {

size_t Parser::operator ()(const tstring& str, int& buffer,
                                                    size_t& bufferSize) const {
    if (bufferSize < 1) {
        JP_THROW(tstrings::any() << "Destination buffer can't be empty");
    }

    tstring_array strComponents;

    tstrings::split(strComponents, str, _T("."));

    // Temporary storage. Needed to preserve destination buffer from
    // partial update if parsing fails.
    std::vector<int> recognizedComponents;

    tstring_array::const_iterator it = strComponents.begin();
    tstring_array::const_iterator end =
                            it + std::min(strComponents.size(), bufferSize);

    // Number of successfully parsed characters in 'str'.
    size_t cursor = 0;

    while (it != end) {
        const tstring& strComponent(*it);

        try {
            recognizedComponents.push_back(parseComponent(strComponent));
        } catch (const std::exception&) {
            // error parsing version component
            break;
        }

        cursor += strComponent.size();
        if (++it != end) {
            ++cursor;
        }
    }

    if (str.size() < cursor) {
        // Should never happen.
        JP_THROW(tstrings::any()
                        << "[" << cursor << " < " << str.size() << "] failed");
    }

    // Publish results only after successful parse.
    bufferSize = recognizedComponents.size();
    if (bufferSize) {
        memcpy(&buffer, &*recognizedComponents.begin(),
                                                bufferSize * sizeof(buffer));
    }

    if (!strComponents.empty() && strComponents.back().size() == 0
                                                    && str.size() == cursor) {
        // Input string ends with dot character (.). Mark it as unrecognized.
        --cursor;
    }

    return (str.size() - cursor);
}


int parseComponent (const tstring& str) {
    tistringstream input(str);

    do {
        if (str.empty() || !isdigit(str[0])) {
            break;
        }

        int reply;
        input >> reply;

        if (!input.eof() || input.fail()) {
            break;
        }

        return reply;
    } while (false);

    JP_THROW(tstrings::any()
            << "Failed to recognize version component in [" << str << "]");
}

} // namespace VersionDetails
