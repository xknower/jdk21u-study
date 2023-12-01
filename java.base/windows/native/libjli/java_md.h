#ifndef JAVA_MD_H
#define JAVA_MD_H

#include <jni.h>
#include <windows.h>
#include <io.h>
#include "manifest_info.h"
#include "jli_util.h"

#define PATH_SEPARATOR  ';'
#define FILESEP         "\\"
#define FILE_SEPARATOR  '\\'
#define IS_FILE_SEPARATOR(c) ((c) == '\\' || (c) == '/')
#define MAXPATHLEN      MAX_PATH
#define MAXNAMELEN      MAX_PATH

#define JLONG_FORMAT_SPECIFIER "%lld"

/*
 * Function prototypes.
 */

int UnsetEnv(char *name);

#endif /* JAVA_MD_H */
