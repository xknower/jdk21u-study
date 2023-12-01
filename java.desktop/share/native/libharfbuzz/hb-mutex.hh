#ifndef HB_MUTEX_HH
#define HB_MUTEX_HH

#include "hb.hh"


/* mutex */

/* We need external help for these */

#if defined(hb_mutex_impl_init) \
 && defined(hb_mutex_impl_lock) \
 && defined(hb_mutex_impl_unlock) \
 && defined(hb_mutex_impl_finish)

/* Defined externally, i.e. in config.h; must have typedef'ed hb_mutex_impl_t as well. */


#elif !defined(HB_NO_MT) && !defined(HB_MUTEX_IMPL_STD_MUTEX) && (defined(HAVE_PTHREAD) || defined(__APPLE__))

#include <pthread.h>
typedef pthread_mutex_t hb_mutex_impl_t;
#define hb_mutex_impl_init(M)   pthread_mutex_init (M, nullptr)
#define hb_mutex_impl_lock(M)   pthread_mutex_lock (M)
#define hb_mutex_impl_unlock(M) pthread_mutex_unlock (M)
#define hb_mutex_impl_finish(M) pthread_mutex_destroy (M)


#elif !defined(HB_NO_MT) && !defined(HB_MUTEX_IMPL_STD_MUTEX) && defined(_WIN32)

typedef CRITICAL_SECTION hb_mutex_impl_t;
#if !WINAPI_FAMILY_PARTITION(WINAPI_PARTITION_DESKTOP) && WINAPI_FAMILY_PARTITION(WINAPI_PARTITION_APP)
#define hb_mutex_impl_init(M)   InitializeCriticalSectionEx (M, 0, 0)
#else
#define hb_mutex_impl_init(M)   InitializeCriticalSection (M)
#endif
#define hb_mutex_impl_lock(M)   EnterCriticalSection (M)
#define hb_mutex_impl_unlock(M) LeaveCriticalSection (M)
#define hb_mutex_impl_finish(M) DeleteCriticalSection (M)


#elif !defined(HB_NO_MT)

#include <mutex>
typedef std::mutex              hb_mutex_impl_t;
#define hb_mutex_impl_init(M)   HB_STMT_START { new (M) hb_mutex_impl_t; } HB_STMT_END
#define hb_mutex_impl_lock(M)   (M)->lock ()
#define hb_mutex_impl_unlock(M) (M)->unlock ()
#define hb_mutex_impl_finish(M) HB_STMT_START { (M)->~hb_mutex_impl_t(); } HB_STMT_END


#else /* defined(HB_NO_MT) */

typedef int hb_mutex_impl_t;
#define hb_mutex_impl_init(M)   HB_STMT_START {} HB_STMT_END
#define hb_mutex_impl_lock(M)   HB_STMT_START {} HB_STMT_END
#define hb_mutex_impl_unlock(M) HB_STMT_START {} HB_STMT_END
#define hb_mutex_impl_finish(M) HB_STMT_START {} HB_STMT_END


#endif


struct hb_mutex_t
{
  /* Create space for, but do not initialize m. */
  alignas(hb_mutex_impl_t) char m[sizeof (hb_mutex_impl_t)];

  hb_mutex_t () { init (); }
  ~hb_mutex_t () { fini (); }

#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wcast-align"
  void init   () { hb_mutex_impl_init   ((hb_mutex_impl_t *) m); }
  void lock   () { hb_mutex_impl_lock   ((hb_mutex_impl_t *) m); }
  void unlock () { hb_mutex_impl_unlock ((hb_mutex_impl_t *) m); }
  void fini   () { hb_mutex_impl_finish ((hb_mutex_impl_t *) m); }
#pragma GCC diagnostic pop
};

struct hb_lock_t
{
  hb_lock_t (hb_mutex_t &mutex_) : mutex (&mutex_) { mutex->lock (); }
  hb_lock_t (hb_mutex_t *mutex_) : mutex (mutex_) { if (mutex) mutex->lock (); }
  ~hb_lock_t () { if (mutex) mutex->unlock (); }
  private:
  hb_mutex_t *mutex;
};


#endif /* HB_MUTEX_HH */
