package jdk.internal.org.jline.terminal.impl.jna.win;

//import com.sun.jna.LastErrorException;
//import com.sun.jna.Pointer;
//import com.sun.jna.ptr.IntByReference;
import jdk.internal.org.jline.terminal.impl.AbstractWindowsConsoleWriter;
import jdk.internal.org.jline.terminal.impl.jna.LastErrorException;

import java.io.IOException;

class JnaWinConsoleWriter extends AbstractWindowsConsoleWriter {

    private final Pointer console;
    private final IntByReference writtenChars = new IntByReference();

    JnaWinConsoleWriter(Pointer console) {
        this.console = console;
    }

    @Override
    protected void writeConsole(char[] text, int len) throws IOException {
        try {
            Kernel32.INSTANCE.WriteConsoleW(this.console, text, len, this.writtenChars, null);
        } catch (LastErrorException e) {
            throw new IOException("Failed to write to console", e);
        }
    }

}
