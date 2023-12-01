/*
 * pkcs11wrapper.h
 * 18.05.2001
 *
 * declaration of all functions used by pkcs11wrapper.c
 *
 * @author Karl Scheibelhofer <Karl.Scheibelhofer@iaik.at>
 */

/* defines for UNIX platforms *************************************************/

#ifndef _P11_MD_H
#define _P11_MD_H 1

#define CK_PTR *
#define CK_DEFINE_FUNCTION(returnType, name) returnType name
#define CK_DECLARE_FUNCTION(returnType, name) returnType name
#define CK_DECLARE_FUNCTION_POINTER(returnType, name) returnType (* name)
#define CK_CALLBACK_FUNCTION(returnType, name) returnType (* name)
#ifndef NULL_PTR
#define NULL_PTR 0
#endif

#include "pkcs11.h"
#include "pkcs11gcm2.h"

#include "jni.h"

/* A data structure to hold required information about a PKCS#11 module. */
struct ModuleData {

    /* the module (DLL or shared library) handle */
    void *hModule;

    /* The pointers to the PKCS#11 functions of this module. */
    CK_FUNCTION_LIST_PTR ckFunctionListPtr;
    CK_FUNCTION_LIST_3_0_PTR ckFunctionList30Ptr;

    /* Reference to the object to use for mutex handling. NULL, if not used. */
    jobject applicationMutexHandler;

};
typedef struct ModuleData ModuleData;

#endif  /* _P11_MD_H */
