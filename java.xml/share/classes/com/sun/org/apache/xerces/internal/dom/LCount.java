package com.sun.org.apache.xerces.internal.dom;


/** Internal class LCount is used to track the number of
    listeners registered for a given event name, as an entry
    in a global Map. This should allow us to avoid generating,
    or discarding, events for which no listeners are registered.

    ***** There should undoubtedly be methods here to manipulate
    this table. At the moment that code's residing in NodeImpl.
    Move it when we have a chance to do so. Sorry; we were
    rushed.
*/
/**
 * @xerces.internal
 *
 */

class LCount
{
    static final java.util.Map<String, LCount> lCounts=new java.util.concurrent.ConcurrentHashMap<>();
    public int captures=0,bubbles=0,defaults, total=0;

    static LCount lookup(String evtName)
    {
        return lCounts.computeIfAbsent(evtName, (key) -> new LCount());
    }
} // class LCount
