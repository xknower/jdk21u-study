package jdk.internal.org.jline.terminal.impl.jna;

@SuppressWarnings("serial")
public class LastErrorException extends RuntimeException{

    public final long lastError;

    public LastErrorException(long lastError) {
        this.lastError = lastError;
    }

}
