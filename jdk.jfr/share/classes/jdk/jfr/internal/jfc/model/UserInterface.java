package jdk.jfr.internal.jfc.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class UserInterface {

    public void println() {
        System.out.println();
    }

    public void println(String text) {
        System.out.println(text);
    }

    public String readLine() throws AbortException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            if (line == null || line.equalsIgnoreCase("Q")) {
                println();
                throw new AbortException();
            }
            return line;
        } catch (IOException e) {
            throw new Error("Unable to read input", e);
        }
    }
}
