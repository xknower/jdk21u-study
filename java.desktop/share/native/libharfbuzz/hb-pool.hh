#ifndef HB_POOL_HH
#define HB_POOL_HH

#include "hb.hh"

/* Memory pool for persistent allocation of small objects.
 *
 * Some AI musings on this, not necessarily true:
 *
 * This is a very simple implementation, but it's good enough for our
 * purposes.  It's not thread-safe.  It's not very fast.  It's not
 * very memory efficient.  It's not very cache efficient.  It's not
 * very anything efficient.  But it's simple and it works.  And it's
 * good enough for our purposes.  If you need something more
 * sophisticated, use a real allocator.  Or use a real language. */

template <typename T, unsigned ChunkLen = 32>
struct hb_pool_t
{
  hb_pool_t () : next (nullptr) {}
  ~hb_pool_t ()
  {
    next = nullptr;

    + hb_iter (chunks)
    | hb_apply (hb_free)
    ;
  }

  T* alloc ()
  {
    if (unlikely (!next))
    {
      if (unlikely (!chunks.alloc (chunks.length + 1))) return nullptr;
      chunk_t *chunk = (chunk_t *) hb_malloc (sizeof (chunk_t));
      if (unlikely (!chunk)) return nullptr;
      chunks.push (chunk);
      next = chunk->thread ();
    }

    T* obj = next;
    next = * ((T**) next);

    hb_memset (obj, 0, sizeof (T));

    return obj;
  }

  void release (T* obj)
  {
    * (T**) obj = next;
    next = obj;
  }

  private:

  static_assert (ChunkLen > 1, "");
  static_assert (sizeof (T) >= sizeof (void *), "");
  static_assert (alignof (T) % alignof (void *) == 0, "");

  struct chunk_t
  {
    T* thread ()
    {
      for (unsigned i = 0; i < ARRAY_LENGTH (arrayZ) - 1; i++)
        * (T**) &arrayZ[i] = &arrayZ[i + 1];

      * (T**) &arrayZ[ARRAY_LENGTH (arrayZ) - 1] = nullptr;

      return arrayZ;
    }

    T arrayZ[ChunkLen];
  };

  T* next;
  hb_vector_t<chunk_t *> chunks;
};


#endif /* HB_POOL_HH */
