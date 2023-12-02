package jdk.internal.org.jline.terminal.spi;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jdk.internal.org.jline.terminal.Attributes;
import jdk.internal.org.jline.terminal.Size;

public interface Pty extends Closeable {

    InputStream getMasterInput() throws IOException;

    OutputStream getMasterOutput() throws IOException;

    InputStream getSlaveInput() throws IOException;

    OutputStream getSlaveOutput() throws IOException;

    Attributes getAttr() throws IOException;

    void setAttr(Attributes attr) throws IOException;

    Size getSize() throws IOException;

    void setSize(Size size) throws IOException;

}
