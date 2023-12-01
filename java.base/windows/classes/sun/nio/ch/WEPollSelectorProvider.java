package sun.nio.ch;

import java.io.IOException;
import java.nio.channels.spi.AbstractSelector;

public class WEPollSelectorProvider extends SelectorProviderImpl {
    public AbstractSelector openSelector() throws IOException {
        return new WEPollSelectorImpl(this);
    }
}
