package sun.security.pkcs11;

import java.lang.ref.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import java.security.*;

import sun.security.pkcs11.wrapper.*;

/**
 * A session object. Sessions are obtained via the SessionManager,
 * see there for details. Most code will only ever need one method in
 * this class, the id() method to obtain the session id.
 *
 * @author  Andreas Sterbenz
 * @since   1.5
 */
final class Session implements Comparable<Session> {

    // time after which to close idle sessions, in milliseconds (3 minutes)
    private static final long MAX_IDLE_TIME = 3 * 60 * 1000;

    // token instance
    final Token token;

    // session id
    private final long id;

    // number of objects created within this session
    private final AtomicInteger createdObjects;

    // time this session was last used
    // not synchronized/volatile for performance, so may be unreliable
    // this could lead to idle sessions being closed early, but that is harmless
    private long lastAccess;

    private final SessionRef sessionRef;

    Session(Token token, long id) {
        this.token = token;
        this.id = id;
        createdObjects = new AtomicInteger();
        id();
        sessionRef = new SessionRef(this, id, token);
    }

    public int compareTo(Session other) {
        if (this.lastAccess == other.lastAccess) {
            return 0;
        } else {
            return (this.lastAccess < other.lastAccess) ? -1 : 1;
        }
    }

    boolean isLive(long currentTime) {
        return currentTime - lastAccess < MAX_IDLE_TIME;
    }

    long id() {
        if (!token.isPresent(this.id)) {
            throw new ProviderException("Token has been removed");
        }
        lastAccess = System.currentTimeMillis();
        return id;
    }

    void addObject() {
        int n = createdObjects.incrementAndGet();
        // XXX update statistics in session manager if n == 1
    }

    void removeObject() {
        int n = createdObjects.decrementAndGet();
        if (n == 0) {
            token.sessionManager.demoteObjSession(this);
        } else if (n < 0) {
            throw new ProviderException("Internal error: objects created " + n);
        }
    }

    boolean hasObjects() {
        return createdObjects.get() != 0;
    }

    // regular close which will not close sessions when there are objects(keys)
    // still associated with them
    void close() {
        close(true);
    }

    // forced close which will close sessions regardless if there are objects
    // associated with them. Note that closing the sessions this way may
    // lead to those associated objects(keys) un-usable. Thus should only be
    // used for scenarios such as the token is about to be removed, etc.
    void kill() {
        close(false);
    }

    private void close(boolean checkObjCtr) {
        if (hasObjects() && checkObjCtr) {
            throw new ProviderException(
                    "Internal error: close session with active objects");
        }
        sessionRef.dispose();
    }

    // Called by the NativeResourceCleaner at specified intervals
    // See NativeResourceCleaner for more information
    static boolean drainRefQueue() {
        boolean found = false;
        SessionRef next;
        while ((next = (SessionRef) SessionRef.REF_QUEUE.poll())!= null) {
            found = true;
            next.dispose();
        }
        return found;
    }
}
/*
 * NOTE: Use PhantomReference here and not WeakReference
 * otherwise the sessions maybe closed before other objects
 * which are still being finalized.
 */
final class SessionRef extends PhantomReference<Session>
        implements Comparable<SessionRef> {

    static final ReferenceQueue<Session> REF_QUEUE = new ReferenceQueue<>();

    private static final Set<SessionRef> REF_LIST =
        Collections.synchronizedSortedSet(new TreeSet<>());

    // handle to the native session
    private final long id;
    private final Token token;

    SessionRef(Session session, long id, Token token) {
        super(session, REF_QUEUE);
        this.id = id;
        this.token = token;
        REF_LIST.add(this);
    }

    void dispose() {
        REF_LIST.remove(this);
        try {
            if (token.isPresent(id)) {
                token.p11.C_CloseSession(id);
            }
        } catch (PKCS11Exception | ProviderException e1) {
            // ignore
        } finally {
            this.clear();
        }
    }

    public int compareTo(SessionRef other) {
        if (this.id == other.id) {
            return 0;
        } else {
            return (this.id < other.id) ? -1 : 1;
        }
    }
}
