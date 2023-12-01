#ifndef app_h
#define app_h

#include "tstrings.h"

class LogAppender;

namespace app {

LogAppender& defaultLastErrorLogAppender();

bool isWithLogging();

typedef void (*LauncherFunc) ();

int launch(const std::nothrow_t&, LauncherFunc func,
        LogAppender* lastErrorLogAppender = 0);

std::string lastErrorMsg();

} // namespace app

#endif // app_h
