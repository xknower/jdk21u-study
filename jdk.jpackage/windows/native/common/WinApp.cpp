#include <memory>
#include "WinApp.h"
#include "Log.h"
#include "SysInfo.h"
#include "FileUtils.h"
#include "ErrorHandling.h"


// MessageBox - Requires linking with user32


namespace {

class LastErrorGuiLogAppender : public LogAppender {
public:
    virtual void append(const LogEvent& v) {
        JP_TRY;

        const std::wstring msg = (tstrings::any()
                << app::lastErrorMsg()).wstr();
        MessageBox(0, msg.c_str(),
            FileUtils::basename(SysInfo::getProcessModulePath()).c_str(),
            MB_ICONERROR | MB_OK);

        JP_CATCH_ALL;
    }
};


class Console {
public:
    Console() {
        if (!AttachConsole(ATTACH_PARENT_PROCESS)) {
            // Failed to connect to parent's console. Create our own.
            if (!AllocConsole()) {
                // We already have a console, no need to redirect std I/O.
                return;
            }
        }

        stdoutChannel = std::unique_ptr<Channel>(new Channel(stdout));
        stderrChannel = std::unique_ptr<Channel>(new Channel(stderr));
    }

    struct FileCloser {
        typedef FILE* pointer;

        void operator()(pointer h) {
            ::fclose(h);
        }
    };

    typedef std::unique_ptr<
        FileCloser::pointer,
        FileCloser
    > UniqueFILEHandle;

private:
    class Channel {
    public:
        Channel(FILE* stdFILEHandle): stdFILEHandle(stdFILEHandle) {
            const char* stdFileName = "CONOUT$";
            const char* openMode = "w";
            if (stdFILEHandle == stdin) {
                stdFileName = "CONIN$";
                openMode = "r";
            }

            FILE* fp = 0;
            freopen_s(&fp, stdFileName, openMode, stdFILEHandle);

            fileHandle = UniqueFILEHandle(fp);

            std::ios_base::sync_with_stdio();
        }

        virtual ~Channel() {
            JP_TRY;

            FILE* fp = 0;
            fileHandle = UniqueFILEHandle(fp);
            std::ios_base::sync_with_stdio();

            JP_CATCH_ALL;
        }

    private:
        UniqueFILEHandle fileHandle;
        FILE *stdFILEHandle;
    };

    std::unique_ptr<Channel> stdoutChannel;
    std::unique_ptr<Channel> stderrChannel;
};

} // namespace


namespace app {
int wlaunch(const std::nothrow_t&, LauncherFunc func) {
    std::unique_ptr<Console> console;
    JP_TRY;
    if (app::isWithLogging()) {
        console = std::unique_ptr<Console>(new Console());
    }
    JP_CATCH_ALL;

    LastErrorGuiLogAppender lastErrorLogAppender;
    TeeLogAppender logAppender(&app::defaultLastErrorLogAppender(),
            &lastErrorLogAppender);
    return app::launch(std::nothrow, func, &logAppender);
}
} // namespace app
