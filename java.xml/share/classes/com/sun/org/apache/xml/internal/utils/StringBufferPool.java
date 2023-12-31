package com.sun.org.apache.xml.internal.utils;

/**
 * This class pools string buffers, since they are reused so often.
 * String buffers are good candidates for pooling, because of
 * their supporting character arrays.
 * @xsl.usage internal
 */
public class StringBufferPool
{

  /** The global pool of string buffers.   */
  private static ObjectPool m_stringBufPool =
    new ObjectPool(com.sun.org.apache.xml.internal.utils.FastStringBuffer.class);

  /**
   * Get the first free instance of a string buffer, or create one
   * if there are no free instances.
   *
   * @return A string buffer ready for use.
   */
  public synchronized static FastStringBuffer get()
  {
    return (FastStringBuffer) m_stringBufPool.getInstance();
  }

  /**
   * Return a string buffer back to the pool.
   *
   * @param sb Must be a non-null reference to a string buffer.
   */
  public synchronized static void free(FastStringBuffer sb)
  {
    // Since this isn't synchronized, setLength must be
    // done before the instance is freed.
    // Fix attributed to Peter Speck <speck@ruc.dk>.
    sb.setLength(0);
    m_stringBufPool.freeInstance(sb);
  }
}
