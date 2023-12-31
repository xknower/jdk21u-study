package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pool of object of a given type to pick from to help memory usage
 * @xsl.usage internal
 * @LastModified: Oct 2017
 */
public final class IteratorPool implements java.io.Serializable
{
    static final long serialVersionUID = -460927331149566998L;

  /**
   * Type of objects in this pool.
   */
  @SuppressWarnings("serial") // Type of field is not Serializable
  private final DTMIterator m_orig;

  /**
   * Stack of given objects this points to.
   */
  @SuppressWarnings("serial") // Type of field is not Serializable
  private final List<DTMIterator> m_freeStack;

  /**
   * Constructor IteratorPool
   *
   * @param original The original iterator from which all others will be cloned.
   */
  public IteratorPool(DTMIterator original)
  {
    m_orig = original;
    m_freeStack = new ArrayList<>();
  }

  /**
   * Get an instance of the given object in this pool
   *
   * @return An instance of the given object
   */
  public synchronized DTMIterator getInstanceOrThrow()
    throws CloneNotSupportedException
  {
    // Check if the pool is empty.
    if (m_freeStack.isEmpty())
    {

      // Create a new object if so.
      return (DTMIterator)m_orig.clone();
    }
    else
    {
      // Remove object from end of free pool.
      DTMIterator result = m_freeStack.remove(m_freeStack.size() - 1);
      return result;
    }
  }

  /**
   * Get an instance of the given object in this pool
   *
   * @return An instance of the given object
   */
  public synchronized DTMIterator getInstance()
  {
    // Check if the pool is empty.
    if (m_freeStack.isEmpty())
    {

      // Create a new object if so.
      try
      {
        return (DTMIterator)m_orig.clone();
      }
      catch (Exception ex)
      {
        throw new WrappedRuntimeException(ex);
      }
    }
    else
    {
      // Remove object from end of free pool.
      DTMIterator result = m_freeStack.remove(m_freeStack.size() - 1);
      return result;
    }
  }

  /**
   * Add an instance of the given object to the pool
   *
   *
   * @param obj Object to add.
   */
  public synchronized void freeInstance(DTMIterator obj)
  {
    m_freeStack.add(obj);
  }
}
