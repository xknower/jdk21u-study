/* There is a known incompatibility for CK_GCM_PARAMS structure.
 * PKCS#11 v2.40 standard mechanisms specification specifies
 * CK_GCM_PARAMS as
 *     typedef struct CK_GCM_PARAMS {
 *         CK_BYTE_PTR       pIv;
 *         CK_ULONG          ulIvLen;
 *         CK_BYTE_PTR       pAAD;
 *         CK_ULONG          ulAADLen;
 *         CK_ULONG          ulTagBits;
 *     } CK_GCM_PARAMS;
 * However, the official header file of PKCS#11 v2.40 defines the
 * CK_GCM_PARAMS with an extra "ulIvBits" field (type CK_ULONG).
 * NSS uses the spec version while Solaris and SoftHSM2 use the header
 * version. In order to work with both sides, SunPKCS11 provider defines
 * the spec version of CK_GCM_PARAMS as CK_GCM_PARAMS_NO_IVBITS (as in this
 * file) and uses it first before failing over to the header version.
 */
#ifndef _PKCS11GCM2_H_
#define _PKCS11GCM2_H_ 1

/* include the platform dependent part of the header */
typedef struct CK_GCM_PARAMS_NO_IVBITS {
    CK_BYTE_PTR       pIv;
    CK_ULONG          ulIvLen;
    CK_BYTE_PTR       pAAD;
    CK_ULONG          ulAADLen;
    CK_ULONG          ulTagBits;
} CK_GCM_PARAMS_NO_IVBITS;

typedef CK_GCM_PARAMS_NO_IVBITS CK_PTR CK_GCM_PARAMS_NO_IVBITS_PTR;

#endif /* _PKCS11GCM2_H_ */
