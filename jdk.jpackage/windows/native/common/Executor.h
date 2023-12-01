#ifndef EXECUTOR_H
#define EXECUTOR_H

#include "tstrings.h"
#include "UniqueHandle.h"


class Executor {
public:
    explicit Executor(const std::wstring& appPath=std::wstring()) {
        app(appPath).visible(false).suspended(false).withJobObject(NULL).inherit(false);
    }

    /**
     * Returns command line configured with arg() calls so far.
     */
    std::wstring args() const;

    /**
     * Set path to application to execute.
     */
    Executor& app(const std::wstring& v) {
        appPath = v;
        return *this;
    }

    /**
     * Adds another command line argument.
     */
    Executor& arg(const std::wstring& v) {
        argsArray.push_back(v);
        return *this;
    }

    /**
     * Controls if application window should be visible.
     */
    Executor& visible(bool v) {
        theVisible = v;
        return *this;
    }

    /**
     * Controls if the process should inherit handles.
     */
    Executor& inherit(bool v) {
        theInherit = v;
        return *this;
    }

    /**
     * Controls if the process should be started suspended.
     */
    Executor& suspended(bool v) {
        theSuspended = v;
        return *this;
    }

    /**
     * Use the given job object with started process.
     */
    Executor& withJobObject(HANDLE v) {
        jobHandle = v;
        return *this;
    }

    /**
     * Starts application process and blocks waiting when the started
     * process terminates.
     * Returns process exit code.
     * Throws exception if process start failed.
     */
    int execAndWaitForExit() const;

private:
    UniqueHandle startProcess(UniqueHandle* threadHandle=0) const;

    bool theVisible;
    bool theInherit;
    bool theSuspended;
    HANDLE jobHandle;
    tstring_array argsArray;
    std::wstring appPath;
};

#endif // #ifndef EXECUTOR_H
