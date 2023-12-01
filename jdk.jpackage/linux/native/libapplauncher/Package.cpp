#include "Package.h"
#include "Executor.h"
#include "AppLauncher.h"
#include "ErrorHandling.h"


Package::Package(): type(Unknown) {
}


namespace {
class FirstLineConsumer : public CommandOutputConsumer {
public:
    FirstLineConsumer(): processed(false) {
    }

    virtual bool accept(const std::string& line) {
        if (!processed) {
            value = line;
            processed = true;
        }
        return processed;
    };

    std::string getValue() const {
        if (!processed) {
            JP_THROW("No output captured");
        }
        return value;
    }

private:
    bool processed;
    std::string value;
};


std::string findOwnerOfFile(const std::nothrow_t&, const std::string& cmdline,
        const std::string& path) {
    try {
        FirstLineConsumer consumer;
        int exitCode = executeCommandLineAndReadStdout(
                cmdline + " \'" + path + "\' 2>/dev/null", consumer);
        if (exitCode == 0) {
            return consumer.getValue();
        }
    } catch (...) {
    }
    return "";
}

} // namespace

Package Package::findOwnerOfFile(const std::string& path) {
    Package result;
    result.theName = ::findOwnerOfFile(std::nothrow,
            "rpm --queryformat '%{NAME}' -qf", path);
    if (!result.theName.empty()) {
        result.type = RPM;
    } else {
        tstring_array components = tstrings::split(::findOwnerOfFile(
                std::nothrow, "dpkg -S", path), ":");
        if (!components.empty()) {
            result.theName = components.front();
            if (!result.theName.empty()) {
                result.type = DEB;
            }
        }
    }

    return result;
}


namespace {
class AppLauncherInitializer : public CommandOutputConsumer {
public:
    AppLauncherInitializer() {
    }

    virtual bool accept(const std::string& line) {
        if (appDir.empty()) {
            if (tstrings::endsWith(line, "/app")) {
                appDir = line;
            }
        }

        if (runtimeDir.empty()) {
            if (tstrings::endsWith(line, "/runtime")) {
                runtimeDir = line;
            }
        }

        return !appDir.empty() && !runtimeDir.empty();
    };

    void apply(AppLauncher& launcher) {
        launcher.setDefaultRuntimePath(runtimeDir);
        launcher.setAppDir(appDir);
    }

private:
    std::string appDir;
    std::string runtimeDir;
};

} // namespace

void Package::initAppLauncher(AppLauncher& appLauncher) const {
    AppLauncherInitializer consumer;
    int exitCode = -1;
    if (type == RPM) {
        exitCode = executeCommandLineAndReadStdout(
                "rpm -ql \'" + theName + "\'", consumer);
    } else if (type == DEB) {
        exitCode = executeCommandLineAndReadStdout(
                "dpkg -L \'" + theName + "\'", consumer);
    }

    if (exitCode == 0) {
        consumer.apply(appLauncher);
    }
}
