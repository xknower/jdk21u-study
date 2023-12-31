#include <jni.h>
#include "jni_util.h"
#include "com_sun_security_auth_module_UnixSystem.h"
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

#include <pwd.h>

/*
 * Declare library specific JNI_Onload entry if static build
 */
DEF_STATIC_JNI_OnLoad

JNIEXPORT void JNICALL
Java_com_sun_security_auth_module_UnixSystem_getUnixInfo
                                                (JNIEnv *env, jobject obj) {

    int i;
    char pwd_buf[1024];
    struct passwd *pwd = NULL;
    struct passwd resbuf;
    jfieldID userNameID;
    jfieldID userID;
    jfieldID groupID;
    jfieldID supplementaryGroupID;

    jstring jstr;
    jlongArray jgroups;
    jlong *jgroupsAsArray;
    jsize numSuppGroups;
    gid_t *groups;
    jclass cls;

    numSuppGroups = getgroups(0, NULL);
    if (numSuppGroups == -1) {
        return;
    }
    groups = (gid_t *)calloc(numSuppGroups, sizeof(gid_t));
    if (groups == NULL) {
        jclass cls = (*env)->FindClass(env,"java/lang/OutOfMemoryError");
        if (cls != NULL) {
            (*env)->ThrowNew(env, cls, NULL);
        }
        return;
    }

    cls = (*env)->GetObjectClass(env, obj);

    supplementaryGroupID = (*env)->GetFieldID(env, cls, "groups", "[J");
    if (supplementaryGroupID == 0) {
        goto cleanUpAndReturn;
    }

    if (getgroups(numSuppGroups, groups) != -1) {
        jgroups = (*env)->NewLongArray(env, numSuppGroups);
        if (jgroups == NULL) {
            goto cleanUpAndReturn;
        }
        jgroupsAsArray = (*env)->GetLongArrayElements(env, jgroups, 0);
        if (jgroupsAsArray == NULL) {
            goto cleanUpAndReturn;
        }
        for (i = 0; i < numSuppGroups; i++) {
            jgroupsAsArray[i] = groups[i];
        }
        (*env)->ReleaseLongArrayElements(env, jgroups, jgroupsAsArray, 0);
        (*env)->SetObjectField(env, obj, supplementaryGroupID, jgroups);
    }

    userNameID = (*env)->GetFieldID(env, cls, "username", "Ljava/lang/String;");
    if (userNameID == 0) {
        goto cleanUpAndReturn;
    }

    userID = (*env)->GetFieldID(env, cls, "uid", "J");
    if (userID == 0) {
        goto cleanUpAndReturn;
    }

    groupID = (*env)->GetFieldID(env, cls, "gid", "J");
    if (groupID == 0) {
        goto cleanUpAndReturn;
    }

    memset(pwd_buf, 0, sizeof(pwd_buf));
    if (getpwuid_r(getuid(), &resbuf, pwd_buf, sizeof(pwd_buf), &pwd) == 0 &&
            pwd != NULL) {
        (*env)->SetLongField(env, obj, userID, pwd->pw_uid);
        (*env)->SetLongField(env, obj, groupID, pwd->pw_gid);
        jstr = (*env)->NewStringUTF(env, pwd->pw_name);
        if (jstr == NULL) {
            goto cleanUpAndReturn;
        }
        (*env)->SetObjectField(env, obj, userNameID, jstr);
    } else {
        (*env)->SetLongField(env, obj, userID, getuid());
        (*env)->SetLongField(env, obj, groupID, getgid());
    }
cleanUpAndReturn:
    free(groups);
    return;
}
