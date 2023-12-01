#ifndef SYSINFO_H
#define SYSINFO_H

#include "tstrings.h"


//
// This namespace provides information about environment in which
// the current application runs.
// It is for general purpose use.
// Functions in this namespaces are just queries about the environment.
// Functions that change the existing environment like file or directory
// creation should not be added to this namespace.
//
namespace SysInfo {
    /**
     * Returns temp dir (for the current user).
     */
    tstring getTempDir();

    /**
     * Returns absolute path to the process executable.
     */
    tstring getProcessModulePath();

    /**
     * Returns absolute path to the current executable module.
     */
    tstring getCurrentModulePath();

    enum CommandArgProgramNameMode {
        IncludeProgramName,
        ExcludeProgramName
    };
    /**
     * Retrieves the command-line arguments for the current process.
     * With IncludeProgramName option returns result similar to argv/argc.
     * With ExcludeProgramName option program name
     *  (the 1st element of command line)
     * is excluded.
     */
    tstring_array getCommandArgs(
            CommandArgProgramNameMode progNameMode = ExcludeProgramName);

    /**
     * Returns value of environment variable with the given name.
     * Throws exception if variable is not set or any other error occurred
     * reading the value.
     */
    tstring getEnvVariable(const tstring& name);

    /**
     * Returns value of environment variable with the given name.
     * Returns value of 'defValue' parameter if variable is not set or any
     * other error occurred reading the value.
     */
    tstring getEnvVariable(const std::nothrow_t&, const tstring& name,
            const tstring& defValue=tstring());

    /**
     * Sets the value of environment variable with the given name to the given value.
     */
    void setEnvVariable(const tstring& name, const tstring& value);

    /**
     * Returns 'true' if environment variable with the given name is set.
     */
    bool isEnvVariableSet(const tstring& name);

}

#endif // SYSINFO_H
