/*
 * Machine Dependent implementation of the dynamic linking support
 * for java.  This routine is Unix specific.
 */

#include <stdio.h>
#include <dlfcn.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

#include "util.h"
#include "path_md.h"

#ifdef __APPLE__
#define LIB_SUFFIX "dylib"
#else
#define LIB_SUFFIX "so"
#endif

static void dll_build_name(char* buffer, size_t buflen,
                           const char* paths, const char* fname) {
    char *path, *paths_copy, *next_token;
    *buffer = '\0';

    paths_copy = jvmtiAllocate((int)strlen(paths) + 1);
    if (paths_copy == NULL) {
        return;
    }

    strcpy(paths_copy, paths);

    next_token = NULL;
    path = strtok_r(paths_copy, PATH_SEPARATOR, &next_token);

    while (path != NULL) {
        size_t result_len = (size_t)snprintf(buffer, buflen, "%s/lib%s." LIB_SUFFIX, path, fname);
        if (result_len >= buflen) {
            EXIT_ERROR(JVMTI_ERROR_INVALID_LOCATION, "One or more of the library paths supplied to jdwp, "
                                                     "likely by sun.boot.library.path, is too long.");
        } else if (access(buffer, F_OK) == 0) {
            break;
        }
        *buffer = '\0';
        path = strtok_r(NULL, PATH_SEPARATOR, &next_token);
    }

    jvmtiDeallocate(paths_copy);
}

/*
 * create a string for the JNI native function name by adding the
 * appropriate decorations.
 */
int
dbgsysBuildFunName(char *name, int nameLen, int args_size, int encodingIndex)
{
    // On Unix, there is only one encoding method.
    if (encodingIndex == 0)
        return 1;
    return 0;
}

/*
 * create a string for the dynamic lib open call by adding the
 * appropriate pre and extensions to a filename and the path
 */
void
dbgsysBuildLibName(char *holder, int holderlen, const char *pname,
                   const char *fname)
{
    const int pnamelen = pname ? strlen(pname) : 0;

    if (pnamelen == 0) {
        if (pnamelen + (int)strlen(fname) + 10 > holderlen) {
            EXIT_ERROR(JVMTI_ERROR_INVALID_LOCATION, "One or more of the library paths supplied to jdwp, "
                                                     "likely by sun.boot.library.path, is too long.");
        }
        (void)snprintf(holder, holderlen, "lib%s." LIB_SUFFIX, fname);
    } else {
      dll_build_name(holder, holderlen, pname, fname);
    }
}

void *
dbgsysLoadLibrary(const char *name, char *err_buf, int err_buflen)
{
    void * result;
    result = dlopen(name, RTLD_LAZY);
    if (result == NULL) {
        (void)strncpy(err_buf, dlerror(), err_buflen-2);
        err_buf[err_buflen-1] = '\0';
    }
    return result;
}

void dbgsysUnloadLibrary(void *handle)
{
    (void)dlclose(handle);
}

void * dbgsysFindLibraryEntry(void *handle, const char *name)
{
    return dlsym(handle, name);
}
