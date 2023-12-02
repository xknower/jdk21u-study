package jdk.internal.org.jline.terminal.impl.jna;

import java.io.IOException;
import jdk.internal.org.jline.terminal.Attributes;
import jdk.internal.org.jline.terminal.Size;
import jdk.internal.org.jline.terminal.spi.TerminalProvider;

class JDKNativePty {

    static JnaNativePty current(TerminalProvider.Stream console) throws IOException {
        throw new UnsupportedOperationException("Not supported.");
    }

    static JnaNativePty open(Attributes attr, Size size) throws IOException {
        throw new UnsupportedOperationException("Not supported.");
    }

    static int isatty(int fd) {
        throw new UnsupportedOperationException("Not supported.");
    }

    static String ttyname(int fd) {
        throw new UnsupportedOperationException("Not supported.");
    }

}
