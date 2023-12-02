package jdk.internal.org.jline.reader;

public class Macro implements Binding {

    private final String sequence;

    public Macro(String sequence) {
        this.sequence = sequence;
    }

    public String getSequence() {
        return sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Macro macro = (Macro) o;
        return sequence.equals(macro.sequence);
    }

    @Override
    public int hashCode() {
        return sequence.hashCode();
    }

    @Override
    public String toString() {
        return "Macro[" +
                sequence + ']';
    }
}
