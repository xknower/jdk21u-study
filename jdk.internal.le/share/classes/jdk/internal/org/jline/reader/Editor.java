package jdk.internal.org.jline.reader;

import java.io.IOException;
import java.util.List;

public interface Editor {
    public void open(List<String> files) throws IOException;
    public void run() throws IOException;
    public void setRestricted(boolean restricted);
}
