#ifndef LIBJIMAGE_OSSUPPORT_HPP
#define LIBJIMAGE_OSSUPPORT_HPP

#ifdef WIN32
#include <Windows.h>
#else
#include <pthread.h>
#endif

class osSupport {
public:
    /**
     * Open a regular file read-only.
     * Return the file descriptor.
     */
    static jint openReadOnly(const char *path);

    /**
     * Close a file descriptor.
     */
    static jint close(jint fd);

    /**
     * Return the size of a regular file.
     */
    static jlong size(const char *path);

    /**
     * Read nBytes at offset into a buffer.
     */
    static jlong read(jint fd, char *buf, jlong nBytes, jlong offset);

    /**
     * Map nBytes at offset into memory and return the address.
     * The system chooses the address.
     */
    static void* map_memory(jint fd, const char *filename, size_t file_offset, size_t bytes);

    /**
     * Unmap nBytes of memory at address.
     */
    static int unmap_memory(void* addr, size_t bytes);
};

/**
 * A CriticalSection to protect a small section of code.
 */
class SimpleCriticalSection {
    friend class SimpleCriticalSectionLock;
private:
    void enter();
    void exit();
public:
    SimpleCriticalSection();
    //~SimpleCriticalSection(); // Cretes a dependency on Solaris on a C++ exit registration

private:
#ifdef WIN32
    CRITICAL_SECTION critical_section;
#else
    pthread_mutex_t mutex;
#endif // WIN32
};

/**
 * SimpleCriticalSectionLock instance.
 * The constructor locks a SimpleCriticalSection and the
 * destructor does the unlock.
 */
class SimpleCriticalSectionLock {
private:
    SimpleCriticalSection *lock;
public:

    SimpleCriticalSectionLock(SimpleCriticalSection *cslock) {
        this->lock = cslock;
        lock->enter();
    }

    ~SimpleCriticalSectionLock() {
        lock->exit();
    }
};

#endif  // LIBJIMAGE_OSSUPPORT_HPP
