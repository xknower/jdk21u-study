package sun.nio.ch;

import java.io.IOException;

/**
 * Default PollerProvider for Windows based on wepoll.
 */
class DefaultPollerProvider extends PollerProvider {
    DefaultPollerProvider() { }

    @Override
    Poller readPoller() throws IOException {
        return new WEPollPoller(true);
    }

    @Override
    Poller writePoller() throws IOException {
        return new WEPollPoller(false);
    }
}
