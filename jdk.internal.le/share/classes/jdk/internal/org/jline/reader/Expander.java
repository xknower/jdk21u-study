package jdk.internal.org.jline.reader;

public interface Expander {

    String expandHistory(History history, String line);

    String expandVar(String word);

}
