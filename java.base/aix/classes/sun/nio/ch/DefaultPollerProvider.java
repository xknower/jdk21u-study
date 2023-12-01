package sun.nio.ch;

import java.io.IOException;

/**
 * Default PollerProvider for AIX.
 */
class DefaultPollerProvider extends PollerProvider {
    DefaultPollerProvider() { }

    @Override
    Poller readPoller() throws IOException {
        return new PollsetPoller(true);
    }

    @Override
    Poller writePoller() throws IOException {
        return new PollsetPoller(false);
    }
}
