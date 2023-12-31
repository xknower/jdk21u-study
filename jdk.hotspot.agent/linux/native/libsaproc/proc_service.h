#ifndef _PROC_SERVICE_H_
#define _PROC_SERVICE_H_

#include <stdio.h>
#include <sys/procfs.h>
#include "jni.h"
#include "libproc.h"


// copied from Solaris "proc_service.h"
typedef enum {
        PS_OK,          /* generic "call succeeded" */
        PS_ERR,         /* generic error */
        PS_BADPID,      /* bad process handle */
        PS_BADLID,      /* bad lwp identifier */
        PS_BADADDR,     /* bad address */
        PS_NOSYM,       /* p_lookup() could not find given symbol */
        PS_NOFREGS      /* FPU register set not available for given lwp */
} ps_err_e;

#ifdef __cplusplus
extern "C" {
#endif

// ps_getpid() is only defined on Linux to return a thread's process ID
JNIEXPORT pid_t JNICALL
ps_getpid(struct ps_prochandle *ph);

// ps_pglobal_lookup() looks up the symbol sym_name in the symbol table
// of the load object object_name in the target process identified by ph.
// It returns the symbol's value as an address in the target process in
// *sym_addr.

JNIEXPORT ps_err_e JNICALL
ps_pglobal_lookup(struct ps_prochandle *ph, const char *object_name,
                    const char *sym_name, psaddr_t *sym_addr);

// read "size" bytes of data from debuggee at address "addr"
JNIEXPORT ps_err_e JNICALL
ps_pdread(struct ps_prochandle *ph, psaddr_t  addr,
                   void *buf, size_t size);

// write "size" bytes of data to debuggee at address "addr"
JNIEXPORT ps_err_e JNICALL
ps_pdwrite(struct ps_prochandle *ph, psaddr_t addr,
                    const void *buf, size_t size);

JNIEXPORT ps_err_e JNICALL
ps_lsetfpregs(struct ps_prochandle *ph, lwpid_t lid, const prfpregset_t *fpregs);

JNIEXPORT ps_err_e JNICALL
ps_lsetregs(struct ps_prochandle *ph, lwpid_t lid, const prgregset_t gregset);

JNIEXPORT ps_err_e JNICALL
ps_lgetfpregs(struct ps_prochandle *ph, lwpid_t lid, prfpregset_t *fpregs);

JNIEXPORT ps_err_e JNICALL
ps_lgetregs(struct ps_prochandle *ph, lwpid_t lid, prgregset_t gregset);

#ifdef __cplusplus
}
#endif

#endif /* _PROC_SERVICE_H_ */
