package jdk.internal.org.jline.terminal.impl.jna.osx;

class NativeLong {

    public long value;

    public NativeLong(long value) {
        this.value = value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long longValue() {
        return value;
    }

}
