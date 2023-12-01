#include <stdio.h>
#include <stdlib.h>
#include "Executor.h"
#include "Log.h"
#include "ErrorHandling.h"


int executeCommandLineAndReadStdout(const std::string& cmd,
        CommandOutputConsumer& consumer) {
    FILE * stream = popen(cmd.c_str(), "r");
    if (!stream) {
        JP_THROW(tstrings::any() << "popen(" << cmd
                << ") failed. Error: " << lastCRTError());
    }

    LOG_TRACE(tstrings::any() << "Reading output of [" << cmd << "] command");

    try {
        bool useConsumer = true;
        std::string buf;
        for (;;) {
            const int c = fgetc(stream);
            if(c == EOF) {
                if (useConsumer && !buf.empty()) {
                    LOG_TRACE(tstrings::any() << "Next line: [" << buf << "]");
                    consumer.accept(buf);
                }
                break;
            }

            if (c == '\n' && useConsumer) {
                LOG_TRACE(tstrings::any() << "Next line: [" << buf << "]");
                useConsumer = !consumer.accept(buf);
                buf.clear();
            } else {
                buf.push_back(static_cast<char>(c));
            }
        }
        return pclose(stream);
    } catch (...) {
        if (stream) {
            pclose(stream);
        }
        throw;
    }
}
