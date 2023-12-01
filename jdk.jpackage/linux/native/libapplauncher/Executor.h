#ifndef EXECUTOR_H
#define EXECUTOR_H

#include "tstrings.h"


class CommandOutputConsumer {
public:
    virtual ~CommandOutputConsumer() {}

    virtual bool accept(const std::string& line) {
        return true;
    };
};

int executeCommandLineAndReadStdout(const std::string& cmd,
        CommandOutputConsumer& consumer);

#endif // #ifndef EXECUTOR_H
