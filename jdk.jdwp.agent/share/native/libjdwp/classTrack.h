#ifndef JDWP_CLASSTRACK_H
#define JDWP_CLASSTRACK_H

/*
 * Called after class unloads have occurred.
 * The signatures of classes which were unloaded are returned.
 */
struct bag *
classTrack_processUnloads(JNIEnv *env);

/*
 * Initialize class tracking.
 */
void
classTrack_initialize(JNIEnv *env);

/*
 * Activates class tracking.
 */
void
classTrack_activate(JNIEnv *env);

/*
 * Reset class tracking.
 */
void
classTrack_reset(void);

#endif
