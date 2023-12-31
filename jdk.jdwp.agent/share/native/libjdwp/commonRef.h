#ifndef JDWP_COMMONREF_H
#define JDWP_COMMONREF_H

void commonRef_initialize(void);
void commonRef_reset(JNIEnv *env);

jlong commonRef_refToID(JNIEnv *env, jobject ref);
jobject commonRef_idToRef(JNIEnv *env, jlong id);
void commonRef_idToRef_delete(JNIEnv *env, jobject ref);
jvmtiError commonRef_pin(jlong id);
jvmtiError commonRef_unpin(jlong id);
void commonRef_pinAll();
void commonRef_unpinAll();
void commonRef_releaseMultiple(JNIEnv *env, jlong id, jint refCount);
void commonRef_release(JNIEnv *env, jlong id);
void commonRef_compact(void);

void commonRef_lock(void);
void commonRef_unlock(void);

#endif
