/* This is a special library that should be loaded before libc &
 * libthread to interpose the signal handler installation functions:
 * sigaction(), signal(), sigset().
 * Used for signal-chaining. See RFE 4381843.
 * Use of signal() and sigset() is now deprecated as these old API's should
 * not be used - sigaction is the only truly supported API.
 */

#include "jni.h"

#include <dlfcn.h>
#include <errno.h>
#include <pthread.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#if (__STDC_VERSION__ >= 199901L)
  #include <stdbool.h>
#else
  #define bool int
  #define true 1
  #define false 0
#endif

#define MAX_SIGNALS NSIG

static struct sigaction sact[MAX_SIGNALS]; /* saved signal handlers */

static sigset_t jvmsigs; /* Signals used by jvm. */

#ifdef MACOSX
static __thread bool reentry = false; /* prevent reentry deadlock (per-thread) */
#endif

/* Used to synchronize the installation of signal handlers. */
static pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
static pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
static pthread_t tid;

typedef void (*sa_handler_t)(int);
typedef void (*sa_sigaction_t)(int, siginfo_t *, void *);
typedef sa_handler_t (*signal_function_t)(int, sa_handler_t);
typedef int (*sigaction_t)(int, const struct sigaction *, struct sigaction *);

static signal_function_t os_signal = 0; /* os's version of signal()/sigset() */
static sigaction_t os_sigaction = 0; /* os's version of sigaction() */

static bool jvm_signal_installing = false;
static bool jvm_signal_installed = false;


static void signal_lock() {
  pthread_mutex_lock(&mutex);
  /* When the jvm is installing its set of signal handlers, threads
   * other than the jvm thread should wait. */
  if (jvm_signal_installing) {
    /* tid is not initialized until jvm_signal_installing is set to true. */
    if (pthread_equal(tid, pthread_self()) == 0) {
      do {
        pthread_cond_wait(&cond, &mutex);
      } while (jvm_signal_installing);
    }
  }
}

static void signal_unlock() {
  pthread_mutex_unlock(&mutex);
}

static sa_handler_t call_os_signal(int sig, sa_handler_t disp,
                                   bool is_sigset) {
  sa_handler_t res;

  if (os_signal == NULL) {
    // Deprecation warning first time through
    printf(HOTSPOT_VM_DISTRO " VM warning: the use of signal() and sigset() "
           "for signal chaining was deprecated in version 16.0 and will "
           "be removed in a future release. Use sigaction() instead.\n");
    if (!is_sigset) {
      os_signal = (signal_function_t)dlsym(RTLD_NEXT, "signal");
    } else {
      os_signal = (signal_function_t)dlsym(RTLD_NEXT, "sigset");
    }
    if (os_signal == NULL) {
      printf("%s\n", dlerror());
      exit(0);
    }
  }

#ifdef MACOSX
  /* On macosx, the OS implementation of signal calls sigaction.
   * Make sure we do not deadlock with ourself. (See JDK-8072147). */
  reentry = true;
#endif

  res = (*os_signal)(sig, disp);

#ifdef MACOSX
  reentry = false;
#endif

  return res;
}

static void save_signal_handler(int sig, sa_handler_t disp, bool is_sigset) {
  sigset_t set;

  sact[sig].sa_handler = disp;
  sigemptyset(&set);
  sact[sig].sa_mask = set;
  sact[sig].sa_flags = 0;
}

static sa_handler_t set_signal(int sig, sa_handler_t disp, bool is_sigset) {
  sa_handler_t oldhandler;
  bool sigused;
  bool sigblocked;

  signal_lock();

  sigused = sigismember(&jvmsigs, sig);
  if (jvm_signal_installed && sigused) {
    /* jvm has installed its signal handler for this signal. */
    /* Save the handler. Don't really install it. */
    if (is_sigset) {
      sigblocked = sigismember(&(sact[sig].sa_mask), sig);
    }
    oldhandler = sact[sig].sa_handler;
    save_signal_handler(sig, disp, is_sigset);

    signal_unlock();
    return oldhandler;
  } else if (jvm_signal_installing) {
    /* jvm is installing its signal handlers. Install the new
     * handlers and save the old ones. jvm uses sigaction().
     * Leave the piece here just in case. */
    oldhandler = call_os_signal(sig, disp, is_sigset);
    save_signal_handler(sig, oldhandler, is_sigset);

    /* Record the signals used by jvm */
    sigaddset(&jvmsigs, sig);

    signal_unlock();
    return oldhandler;
  } else {
    /* jvm has no relation with this signal (yet). Install the
     * the handler. */
    oldhandler = call_os_signal(sig, disp, is_sigset);

    signal_unlock();
    return oldhandler;
  }
}

JNIEXPORT sa_handler_t signal(int sig, sa_handler_t disp) {
  if (sig < 0 || sig >= MAX_SIGNALS) {
    errno = EINVAL;
    return SIG_ERR;
  }

  return set_signal(sig, disp, false);
}

JNIEXPORT sa_handler_t sigset(int sig, sa_handler_t disp) {
#ifdef _ALLBSD_SOURCE
  printf("sigset() is not supported by BSD");
  exit(0);
#else
  if (sig < 0 || sig >= MAX_SIGNALS) {
    errno = EINVAL;
    return (sa_handler_t)-1;
  }

  return set_signal(sig, disp, true);
#endif
}

static int call_os_sigaction(int sig, const struct sigaction  *act,
                             struct sigaction *oact) {
  if (os_sigaction == NULL) {
    os_sigaction = (sigaction_t)dlsym(RTLD_NEXT, "sigaction");
    if (os_sigaction == NULL) {
      printf("%s\n", dlerror());
      exit(0);
    }
  }
  return (*os_sigaction)(sig, act, oact);
}

JNIEXPORT int sigaction(int sig, const struct sigaction *act, struct sigaction *oact) {
  int res;
  bool sigused;
  struct sigaction oldAct;

  if (sig < 0 || sig >= MAX_SIGNALS) {
    errno = EINVAL;
    return -1;
  }

#ifdef MACOSX
  if (reentry) {
    return call_os_sigaction(sig, act, oact);
  }
#endif

  signal_lock();

  sigused = sigismember(&jvmsigs, sig);
  if (jvm_signal_installed && sigused) {
    /* jvm has installed its signal handler for this signal. */
    /* Save the handler. Don't really install it. */
    if (oact != NULL) {
      *oact = sact[sig];
    }
    if (act != NULL) {
      sact[sig] = *act;
    }

    signal_unlock();
    return 0;
  } else if (jvm_signal_installing) {
    /* jvm is installing its signal handlers.
     * - if this is a modifying sigaction call, we install a new signal handler and store the old one
     *   as chained signal handler.
     * - if this is a non-modifying sigaction call, we don't change any state; we just return the existing
     *   signal handler in the system (not the stored one).
     * This works under the assumption that there is only one modifying sigaction call for a specific signal
     * within the JVM_begin_signal_setting-JVM_end_signal_setting-window. There can be any number of non-modifying
     * calls, but they will only return the expected preexisting handler if executed before the modifying call.
     */
    res = call_os_sigaction(sig, act, &oldAct);
    if (res == 0) {
      if (act != NULL) {
        /* store pre-existing handler as chained handler */
        sact[sig] = oldAct;
        /* Record the signals used by jvm. */
        sigaddset(&jvmsigs, sig);
      }
      if (oact != NULL) {
        *oact = oldAct;
      }
    }

    signal_unlock();
    return res;
  } else {
    /* jvm has no relation with this signal (yet). Install the
     * the handler. */
    res = call_os_sigaction(sig, act, oact);

    signal_unlock();
    return res;
  }
}

/* The three functions for the jvm to call into. */
JNIEXPORT void JVM_begin_signal_setting() {
  signal_lock();
  sigemptyset(&jvmsigs);
  jvm_signal_installing = true;
  tid = pthread_self();
  signal_unlock();
}

JNIEXPORT void JVM_end_signal_setting() {
  signal_lock();
  jvm_signal_installed = true;
  jvm_signal_installing = false;
  pthread_cond_broadcast(&cond);
  signal_unlock();
}

JNIEXPORT struct sigaction *JVM_get_signal_action(int sig) {
  /* Does race condition make sense here? */
  if (sigismember(&jvmsigs, sig)) {
    return &sact[sig];
  }
  return NULL;
}
