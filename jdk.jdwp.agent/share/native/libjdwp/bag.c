/* General routines for manipulating a bag data structure */

#include "util.h"
#include "bag.h"

struct bag {
    void *items;    /* hold items in bag, must align on itemSize */
    int used;       /* number of items in bag */
    int allocated;  /* space reserved */
    int itemSize;   /* size of each item, should init to sizeof item */
};

struct bag *
bagCreateBag(int itemSize, int initialAllocation) {
    struct bag *theBag = (struct bag *)jvmtiAllocate(sizeof(struct bag));
    if (theBag == NULL) {
        return NULL;
    }
    itemSize = (itemSize + 7) & ~7;    /* fit 8 byte boundary */
    theBag->items = jvmtiAllocate(initialAllocation * itemSize);
    if (theBag->items == NULL) {
        jvmtiDeallocate(theBag);
        return NULL;
    }
    theBag->used = 0;
    theBag->allocated = initialAllocation;
    theBag->itemSize = itemSize;
    return theBag;
}

struct bag *
bagDup(struct bag *oldBag)
{
    struct bag *newBag = bagCreateBag(oldBag->itemSize,
                                      oldBag->allocated);
    if (newBag != NULL) {
        newBag->used = oldBag->used;
        (void)memcpy(newBag->items, oldBag->items, newBag->used * newBag->itemSize);
    }
    return newBag;
}

void
bagDestroyBag(struct bag *theBag)
{
    if (theBag != NULL) {
        jvmtiDeallocate(theBag->items);
        jvmtiDeallocate(theBag);
    }
}

void *
bagFind(struct bag *theBag, void *key)
{
    char *items = theBag->items;
    int itemSize = theBag->itemSize;
    char *itemsEnd = items + (itemSize * theBag->used);

    for (; items < itemsEnd; items += itemSize) {
        /*LINTED*/
        if (*((void**)items) == key) {
            return items;
        }
    }
    return NULL;
}

void *
bagAdd(struct bag *theBag)
{
    int allocated = theBag->allocated;
    int itemSize = theBag->itemSize;
    void *items = theBag->items;
    void *ret;

    /* if there are no unused slots reallocate */
    if (theBag->used >= allocated) {
        void *new_items;
        allocated *= 2;
        new_items = jvmtiAllocate(allocated * itemSize);
        if (new_items == NULL) {
            return NULL;
        }
        (void)memcpy(new_items, items, (theBag->used) * itemSize);
        jvmtiDeallocate(items);
        items = new_items;
        theBag->allocated = allocated;
        theBag->items = items;
    }
    ret = ((char *)items) + (itemSize * (theBag->used)++);
    (void)memset(ret, 0, itemSize);
    return ret;
}

void
bagDelete(struct bag *theBag, void *condemned)
{
    int used = --(theBag->used);
    int itemSize = theBag->itemSize;
    void *items = theBag->items;
    void *tailItem = ((char *)items) + (used * itemSize);

    if (condemned != tailItem) {
        (void)memcpy(condemned, tailItem, itemSize);
    }
}

void
bagDeleteAll(struct bag *theBag)
{
    theBag->used = 0;
}


int
bagSize(struct bag *theBag)
{
    return theBag->used;
}

jboolean
bagEnumerateOver(struct bag *theBag, bagEnumerateFunction func, void *arg)
{
    char *items = theBag->items;
    int itemSize = theBag->itemSize;
    char *itemsEnd = items + (itemSize * theBag->used);

    for (; items < itemsEnd; items += itemSize) {
        if (!(func)((void *)items, arg)) {
            return JNI_FALSE;
        }
    }
    return JNI_TRUE;
}
