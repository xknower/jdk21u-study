/*
 * A class to manage queueing of messages for IPC
 */

#include "AccessBridgeDebug.h"
#include "AccessBridgeMessageQueue.h"
#include "AccessBridgePackages.h"               // for debugging only
#include <windows.h>
#include <malloc.h>
#include <new>

DEBUG_CODE(extern HWND theDialogWindow);
extern "C" {
    DEBUG_CODE(void AppendToCallInfo(const char *s));
}

// -------------------


AccessBridgeQueueElement::AccessBridgeQueueElement(char *buf, int size) {
    bufsize = size;
    next = (AccessBridgeQueueElement *) 0;
    previous = (AccessBridgeQueueElement *) 0;
    buffer = (char *) malloc(bufsize);
    if (buffer == NULL) {
        throw std::bad_alloc();
    }
    memcpy(buffer, buf, bufsize);
}

AccessBridgeQueueElement::~AccessBridgeQueueElement() {
    //  delete buffer;
    free(buffer);
}


// -------------------


AccessBridgeMessageQueue::AccessBridgeMessageQueue() {
    queueLocked = FALSE;
    queueRemoveLocked = FALSE;
    start = (AccessBridgeQueueElement *) 0;
    end = (AccessBridgeQueueElement *) 0;
    size = 0;
}

AccessBridgeMessageQueue::~AccessBridgeMessageQueue() {
    // empty queue, then exit
}

/**
 * getEventsWaiting - gets the number of events waiting to fire
 */
int
AccessBridgeMessageQueue::getEventsWaiting() {
    return size;
}

/**
 * add - add an element to the queue, which is locked with semaphores
 *
 */
QueueReturns
AccessBridgeMessageQueue::add(AccessBridgeQueueElement *element) {
    PrintDebugString("[INFO]:   in AccessBridgeMessageQueue::add()");
    PrintDebugString("[INFO]:     queue size = %d", size);

    QueueReturns returnVal = cElementPushedOK;
    if (queueLocked) {
        PrintDebugString("[WARN]:     queue was locked; returning cQueueInUse!");
        return cQueueInUse;
    }
    queueLocked = TRUE;
    {
        PrintDebugString("[INFO]:     adding element to queue!");
        if (end == (AccessBridgeQueueElement *) 0) {
            if (start == (AccessBridgeQueueElement *) 0 && size == 0) {
                start = element;
                end = element;
                element->previous = (AccessBridgeQueueElement *) 0;
                element->next = (AccessBridgeQueueElement *) 0;
                size++;
            } else {
                returnVal = cQueueBroken;       // bad voodo!
            }
        } else {
            element->previous = end;
            element->next = (AccessBridgeQueueElement *) 0;
            end->next = element;
            end = element;
            size++;
        }
    }
    queueLocked = FALSE;
    PrintDebugString("[INFO]:     returning from AccessBridgeMessageQueue::add()");
    return returnVal;
}


/**
 * remove - remove an element from the queue, which is locked with semaphores
 *
 */
QueueReturns
AccessBridgeMessageQueue::remove(AccessBridgeQueueElement **element) {
    PrintDebugString("[INFO]:   in AccessBridgeMessageQueue::remove()");
    PrintDebugString("[INFO]:     queue size = %d", size);

    QueueReturns returnVal = cMoreMessages;
    if (queueLocked) {
        PrintDebugString("[WARN]:     queue was locked; returning cQueueInUse!");
        return cQueueInUse;
    }
    queueLocked = TRUE;
    {
        PrintDebugString("[INFO]:     removing element from queue!");
        if (size > 0) {
            if (start != (AccessBridgeQueueElement *) 0) {
                *element = start;
                start = start->next;
                if (start != (AccessBridgeQueueElement *) 0) {
                    start->previous = (AccessBridgeQueueElement *) 0;
                } else {
                    end = (AccessBridgeQueueElement *) 0;
                    if (size != 1) {
                        returnVal = cQueueBroken;       // bad voodo, should only be 1 in this situation
                    }
                }
                size--;
            } else {
                returnVal = cQueueBroken;       // bad voodo!
            }
        } else {
            returnVal = cQueueEmpty;
        }
    }
    queueLocked = FALSE;
    PrintDebugString("[INFO]:     returning from AccessBridgeMessageQueue::remove()");
    return returnVal;
}


/**
 * setRemoveLock - set the state of the removeLock (TRUE or FALSE)
 *
 */
QueueReturns
AccessBridgeMessageQueue::setRemoveLock(BOOL removeLockSetting) {
    if (queueLocked) {
        return cQueueInUse;
    }
    queueRemoveLocked = removeLockSetting;

    return cQueueOK;
}

/**
 * setRemoveLock - set the state of the removeLock (TRUE or FALSE)
 *
 */
BOOL
AccessBridgeMessageQueue::getRemoveLockSetting() {
    return queueRemoveLocked;
}
