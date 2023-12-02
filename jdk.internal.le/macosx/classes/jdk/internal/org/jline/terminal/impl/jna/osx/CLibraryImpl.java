package jdk.internal.org.jline.terminal.impl.jna.osx;

import jdk.internal.org.jline.terminal.impl.jna.LastErrorException;

public final class CLibraryImpl implements CLibrary {

    static {
        System.loadLibrary("le");
        initIDs();
    }

    private static native void initIDs();

    @Override
    public native void tcgetattr(int fd, termios termios) throws LastErrorException;

    @Override
    public native void tcsetattr(int fd, int cmd, termios termios) throws LastErrorException;

    @Override
    public void ioctl(int fd, NativeLong cmd, winsize data) throws LastErrorException {
        if (cmd.longValue() == CLibrary.TIOCGWINSZ || cmd.longValue() == CLibrary.TIOCSWINSZ) {
            ioctl0(fd, cmd.longValue(), data);
        } else {
            throw new UnsupportedOperationException("Command: " + cmd + ", not supported.");
        }
    }

    private native void ioctl0(int fd, long cmd, winsize data) throws LastErrorException;

    @Override
    public native int isatty(int fd);

    @Override
    public native void ttyname_r(int fd, byte[] buf, int len) throws LastErrorException;

    @Override
    public void openpty(int[] master, int[] slave, byte[] name, termios t, winsize s) throws LastErrorException {
        throw new UnsupportedOperationException();
    }

}
