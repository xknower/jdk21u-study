// in nss.h:
// extern PRBool NSS_VersionCheck(const char *importedVersion);
// extern SECStatus NSS_Initialize(const char *configdir,
//     const char *certPrefix, const char *keyPrefix,
//     const char *secmodName, PRUint32 flags);

typedef int (*FPTR_VersionCheck)(const char *importedVersion);
typedef int (*FPTR_Initialize)(const char *configdir,
        const char *certPrefix, const char *keyPrefix,
        const char *secmodName, unsigned int flags);

#ifdef SECMOD_DEBUG
typedef int (*FPTR_GetError)(void);
#endif //SECMOD_DEBUG

// in secmod.h
//extern SECMODModule *SECMOD_LoadModule(char *moduleSpec,SECMODModule *parent,
//                                                      PRBool recurse);
//char **SECMOD_GetModuleSpecList(SECMODModule *module);
//extern SECMODModuleList *SECMOD_GetDBModuleList(void);

typedef void *(*FPTR_LoadModule)(char *moduleSpec, void *parent, int recurse);
typedef char **(*FPTR_GetModuleSpecList)(void *module);
typedef void *(*FPTR_GetDBModuleList)(void);
