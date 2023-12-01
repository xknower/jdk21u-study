#include "jni.h"
#include "jni_util.h"

/*
 * Macros to use the right data type for file descriptors
 */
#define FD jlong

/*
 * Prototypes for functions in io_util_md.c called from io_util.c,
 * FileDescriptor.c, FileInputStream.c, FileOutputStream.c,
 * ProcessImpl_md.c
 */
WCHAR* pathToNTPath(JNIEnv *env, jstring path, jboolean throwFNFE);
WCHAR* fileToNTPath(JNIEnv *env, jobject file, jfieldID id);
JNIEXPORT WCHAR* getPrefixed(const WCHAR* path, int pathlen);
WCHAR* currentDir(int di);
int currentDirLength(const WCHAR* path, int pathlen);
int handleAvailable(FD fd, jlong *pbytes);
int handleSync(FD fd);
jint handleSetLength(FD fd, jlong length);
jlong handleGetLength(FD fd);
JNIEXPORT jint handleRead(FD fd, void *buf, jint len);
jint handleWrite(FD fd, const void *buf, jint len);
jint handleAppend(FD fd, const void *buf, jint len);
void fileDescriptorClose(JNIEnv *env, jobject this);
JNIEXPORT jlong JNICALL
handleLseek(FD fd, jlong offset, jint whence);

/*
 * Returns an opaque handle to file named by "path".  If an error occurs,
 * returns -1 and an exception is pending.
 */
FD winFileHandleOpen(JNIEnv *env, jstring path, int flags);

/*
 * Function to get fd from the java.io.FileDescriptor field of an
 * object.  These functions rely on having an appropriately
 * defined object with a FileDescriptor object at the fid offset.
 * If the FD object is null, return -1 to avoid crashing VM.
 */
FD getFD(JNIEnv *env, jobject cur, jfieldID fid);

/*
 * Macros to set/get fd when inside java.io.FileDescriptor
 */
#define THIS_FD(obj) (*env)->GetLongField(env, obj, IO_handle_fdID)

/*
 * Route the routines away from VM
 */
#define IO_Append handleAppend
#define IO_Write handleWrite
#define IO_Sync handleSync
#define IO_Read handleRead
#define IO_Lseek handleLseek
#define IO_Available handleAvailable
#define IO_SetLength handleSetLength
#define IO_GetLength handleGetLength

/*
 * Setting the handle field in Java_java_io_FileDescriptor_set for
 * standard handles stdIn, stdOut, stdErr
 */
#define SET_HANDLE(fd) \
if (fd == 0) { \
    return (jlong)GetStdHandle(STD_INPUT_HANDLE); \
} else if (fd == 1) { \
    return (jlong)GetStdHandle(STD_OUTPUT_HANDLE); \
} else if (fd == 2) { \
    return (jlong)GetStdHandle(STD_ERROR_HANDLE); \
} else { \
    return (jlong)-1; \
} \

/* INVALID_FILE_ATTRIBUTES is not defined in VC++6.0's header files but
 * in later release. Keep here just in case someone is still using VC++6.0
 */
#ifndef INVALID_FILE_ATTRIBUTES
#define INVALID_FILE_ATTRIBUTES ((DWORD)-1)
#endif
