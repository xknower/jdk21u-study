#ifndef _JLI_UTIL_H
#define _JLI_UTIL_H

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#ifndef NO_JNI
  #include <jni.h>
#else
  #define jboolean int
  #define JNI_TRUE  1
  #define JNI_FALSE 0
#endif

#define JLDEBUG_ENV_ENTRY "_JAVA_LAUNCHER_DEBUG"

JNIEXPORT void * JNICALL
JLI_MemAlloc(size_t size);

void *JLI_MemRealloc(void *ptr, size_t size);

JNIEXPORT char * JNICALL
JLI_StringDup(const char *s1);

JNIEXPORT void JNICALL
JLI_MemFree(void *ptr);

int   JLI_StrCCmp(const char *s1, const char *s2);
jboolean   JLI_HasSuffix(const char *s1, const char *s2);

typedef struct {
    char *arg;
    jboolean has_wildcard;
} StdArg;

JNIEXPORT StdArg * JNICALL
JLI_GetStdArgs();

JNIEXPORT int JNICALL
JLI_GetStdArgc();

JNIEXPORT int JNICALL
JLI_GetAppArgIndex();

#define JLI_StrLen(p1)          strlen((p1))
#define JLI_StrChr(p1, p2)      strchr((p1), (p2))
#define JLI_StrRChr(p1, p2)     strrchr((p1), (p2))
#define JLI_StrCmp(p1, p2)      strcmp((p1), (p2))
#define JLI_StrNCmp(p1, p2, p3) strncmp((p1), (p2), (p3))
#define JLI_StrCat(p1, p2)      strcat((p1), (p2))
#define JLI_StrCpy(p1, p2)      strcpy((p1), (p2))
#define JLI_StrNCpy(p1, p2, p3) strncpy((p1), (p2), (p3))
#define JLI_StrStr(p1, p2)      strstr((p1), (p2))
#define JLI_StrSpn(p1, p2)      strspn((p1), (p2))
#define JLI_StrCSpn(p1, p2)     strcspn((p1), (p2))
#define JLI_StrPBrk(p1, p2)     strpbrk((p1), (p2))

#define JLI_Snprintf            snprintf

/* On Windows lseek() is in io.h rather than the location dictated by POSIX. */
#ifdef _WIN32
#include <windows.h>
#include <io.h>
#include <process.h>
#define JLI_StrCaseCmp(p1, p2)          stricmp((p1), (p2))
#define JLI_StrNCaseCmp(p1, p2, p3)     strnicmp((p1), (p2), (p3))
int JLI_Open(const char* name, int flags);
JNIEXPORT void JNICALL
JLI_CmdToArgs(char *cmdline);
#define JLI_Lseek                       _lseeki64
#else  /* NIXES */
#include <unistd.h>
#include <strings.h>
#define JLI_StrCaseCmp(p1, p2)          strcasecmp((p1), (p2))
#define JLI_StrNCaseCmp(p1, p2, p3)     strncasecmp((p1), (p2), (p3))
#define JLI_Open                        open
#ifdef __linux__
#define _LARGFILE64_SOURCE
#define JLI_Lseek                       lseek64
#endif
#ifdef MACOSX
#define JLI_Lseek                       lseek
#endif
#ifdef _AIX
#define JLI_Lseek                       lseek
#endif
#endif /* _WIN32 */

/*
 * Make launcher spit debug output.
 */
void     JLI_TraceLauncher(const char* fmt, ...);

JNIEXPORT void JNICALL
JLI_SetTraceLauncher();

jboolean JLI_IsTraceLauncher();

/*
 * JLI_List - a dynamic list of char*
 */
struct JLI_List_
{
    char **elements;
    size_t size;
    size_t capacity;
};
typedef struct JLI_List_ *JLI_List;

JNIEXPORT JLI_List JNICALL
JLI_List_new(size_t capacity);

void JLI_List_free(JLI_List l);
void JLI_List_ensureCapacity(JLI_List l, size_t capacity);

/* e must be JLI_MemFree-able */
JNIEXPORT void JNICALL
JLI_List_add(JLI_List l, char *e);

/* a copy is made out of beg */
void JLI_List_addSubstring(JLI_List l, const char *beg, size_t len);
char *JLI_List_combine(JLI_List sl);
char *JLI_List_join(JLI_List l, char sep);
JLI_List JLI_List_split(const char *str, char sep);

JNIEXPORT void JNICALL
JLI_InitArgProcessing(jboolean hasJavaArgs, jboolean disableArgFile);

JNIEXPORT JLI_List JNICALL
JLI_PreprocessArg(const char *arg, jboolean expandSourceOpt);

JNIEXPORT jboolean JNICALL
JLI_AddArgsFromEnvVar(JLI_List args, const char *var_name);

#endif  /* _JLI_UTIL_H */
