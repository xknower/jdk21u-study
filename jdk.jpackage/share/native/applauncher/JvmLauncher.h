
#ifndef JvmLauncher_h
#define JvmLauncher_h


#include "jni.h" /* JNIEXPORT */
#ifdef _WIN32
#include <windows.h>
#include <tchar.h>
#endif


#ifdef __cplusplus
extern "C" {
#endif

#ifndef _WIN32
typedef char TCHAR;
#endif

typedef struct {
    const char* jliLibPath;
    int jliLaunchArgc;
    int envVarCount;
    char** jliLaunchArgv;
    TCHAR** envVarNames;
    TCHAR** envVarValues;
} JvmlLauncherData;

typedef void* JvmlLauncherHandle;

typedef void (*JvmlLauncherAPI_CloseHandleFunc)(JvmlLauncherHandle);
typedef int (*JvmlLauncherAPI_GetJvmlLauncherDataSizeFunc)(JvmlLauncherHandle);
typedef JvmlLauncherData* (*JvmlLauncherAPI_InitJvmlLauncherDataFunc)(JvmlLauncherHandle, void*, int);

typedef struct {
    JvmlLauncherAPI_CloseHandleFunc closeHandle;
    JvmlLauncherAPI_GetJvmlLauncherDataSizeFunc getJvmlLauncherDataSize;
    JvmlLauncherAPI_InitJvmlLauncherDataFunc initJvmlLauncherData;
} JvmlLauncherAPI;

typedef JvmlLauncherAPI* (*JvmlLauncherAPI_GetAPIFunc)(void);

JNIEXPORT JvmlLauncherAPI* jvmLauncherGetAPI(void);

static inline void jvmLauncherCloseHandle(JvmlLauncherAPI* api, JvmlLauncherHandle h) {
    (*api->closeHandle)(h);
}

static inline int jvmLauncherGetJvmlLauncherDataSize(JvmlLauncherAPI* api,
                                                        JvmlLauncherHandle h) {
    return (*api->getJvmlLauncherDataSize)(h);
}

static inline JvmlLauncherData* jvmLauncherInitJvmlLauncherData(JvmlLauncherAPI* api,
                            JvmlLauncherHandle h, void* ptr, int bufferSize) {
    return (*api->initJvmlLauncherData)(h, ptr, bufferSize);
}

JvmlLauncherData* jvmLauncherCreateJvmlLauncherData(JvmlLauncherAPI* api,
                                            JvmlLauncherHandle h, int* size);
int jvmLauncherStartJvm(JvmlLauncherData* jvmArgs, void* JLI_Launch);

void jvmLauncherLog(const char* format, ...);

#define JP_LOG_ERRMSG(msg) do { jvmLauncherLog((msg)); } while (0)
#define JP_LOG_ERRNO JP_LOG_ERRMSG(strerror(errno))
#define JP_LOG_TRACE jvmLauncherLog


#ifdef __cplusplus
}
#endif


#ifdef __cplusplus

#include "tstrings.h"

class CfgFile;


class Jvm {
public:
    Jvm();
    ~Jvm();

    Jvm& initFromConfigFile(const CfgFile& cfgFile);

    Jvm& addArgument(const tstring& value) {
        args.push_back(value);
        return *this;
    }

    Jvm& addEnvVariable(const tstring& name, const tstring& value) {
        envVarNames.push_back(name);
        envVarValues.push_back(value);
        return *this;
    }

    Jvm& setPath(const tstring& v) {
        jvmPath = v;
        return *this;
    }

    tstring getPath() const {
        return jvmPath;
    }

    bool isWithSplash() const;

    void launch();

    void setEnvVariables();

    JvmlLauncherHandle exportLauncher() const;

private:
    tstring jvmPath;
    tstring_array args;
    tstring_array envVarNames;
    tstring_array envVarValues;
};

#endif // #ifdef __cplusplus

#endif // JvmLauncher_h
