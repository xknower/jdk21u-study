#ifndef PACKAGE_H
#define PACKAGE_H


#include "tstrings.h"


class AppLauncher;


class Package {
public:
    Package();

    std::string name() const {
        return theName;
    }

    void initAppLauncher(AppLauncher& appLauncher) const;

    static Package findOwnerOfFile(const std::string& path);

private:
    enum Type { Unknown, RPM, DEB };

    Type type;
    std::string theName;
};

#endif // #ifndef PACKAGE_H
