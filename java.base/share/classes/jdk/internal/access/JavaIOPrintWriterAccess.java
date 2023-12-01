package jdk.internal.access;

import java.io.PrintWriter;

public interface JavaIOPrintWriterAccess {
    Object lock(PrintWriter pw);
}
