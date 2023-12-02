package jdk.internal.org.jline.terminal.impl.jna;

import java.io.IOException;
import jdk.internal.org.jline.terminal.Attributes;
import jdk.internal.org.jline.terminal.Size;
import jdk.internal.org.jline.terminal.impl.jna.osx.OsXNativePty;
import jdk.internal.org.jline.terminal.spi.TerminalProvider;

class JDKNativePty {

    static JnaNativePty current(TerminalProvider.Stream console) throws IOException {
        return OsXNativePty.current(console);
    }

    static JnaNativePty open(Attributes attr, Size size) throws IOException {
        return OsXNativePty.open(attr, size);
    }

    static int isatty(int fd) {
        return OsXNativePty.isatty(fd);
    }

    static String ttyname(int fd) {
        return OsXNativePty.ttyname(fd);
    }

}
