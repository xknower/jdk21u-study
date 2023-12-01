package sun.nio.ch;

import java.io.IOException;
import java.time.Instant;
import sun.nio.ch.Pollset;

/**
 * Poller implementation based on the AIX Pollset library.
 */

class PollsetPoller extends Poller {

    private static final int MAX_EVENTS_TO_POLL;

    static {
        Pollset.init(); /* Dynamically loads pollset C functions */
        MAX_EVENTS_TO_POLL = 512;
    }

    private final int setid;
    private final long pollBuffer;

    PollsetPoller(boolean read) throws IOException {
        super(read);
        this.setid = Pollset.pollsetCreate();
        this.pollBuffer = Pollset.allocatePollArray(MAX_EVENTS_TO_POLL);
    }

    @Override
    int fdVal() {
        return setid;
    }

    @Override
    void implRegister(int fd) throws IOException {
        int ret = Pollset.pollsetCtl(setid, Pollset.PS_MOD, fd,
                          Pollset.PS_POLLPRI | (this.reading() ? Net.POLLIN : Net.POLLOUT));
        if (ret != 0) {
            throw new IOException("Unable to register fd " + fd);
        }
    }

    @Override
    void implDeregister(int fd) {
        int ret = Pollset.pollsetCtl(setid, Pollset.PS_DELETE, fd, 0);
        assert ret == 0;
    }

    /**
      * Main poll method. The AIX Pollset library does not appear to pick up changes to the pollset
      * (the set of fds being polled) while blocked on a call to this method. These changes happen
      * regularly in the poll-loop thread and update thread from Poller.java.
      * To address this difficulty, we break poll calls into 100ms sub-calls and emulate the timout.
      */
    @Override
    int poll(int timeout) throws IOException {
        int n;
        switch (timeout) {
            case 0:
                n = pollInner(0);
                break;
            case Pollset.PS_NO_TIMEOUT:
                do { n = pollInner(100); } while (n == 0);
                break;
            default:
                Instant end = Instant.now().plusMillis(timeout);
                do { n = pollInner(100); } while (n == 0 && Instant.now().isBefore(end));
                break;
        }
        return n;
    }

    int pollInner(int subInterval) throws IOException {
        int n = Pollset.pollsetPoll(setid, pollBuffer, MAX_EVENTS_TO_POLL, subInterval);
        for (int i=0; i<n; i++) {
            long eventAddress = Pollset.getEvent(pollBuffer, i);
            int fd = Pollset.getDescriptor(eventAddress);
            polled(fd);
        }
        return n;
    }
}

