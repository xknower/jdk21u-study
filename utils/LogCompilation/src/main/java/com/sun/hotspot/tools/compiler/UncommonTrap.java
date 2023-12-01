package com.sun.hotspot.tools.compiler;

import java.io.PrintStream;

/**
 * An instance of this class represents an uncommon trap associated with a
 * given bytecode instruction. An uncommon trap is described in terms of its
 * reason and action to be taken. An instance of this class is always relative
 * to a specific method and only contains the relevant bytecode instruction
 * index.
 */
class UncommonTrap {

    private int bci;
    private String reason;
    private String action;
    private String bytecode;

    public UncommonTrap(int b, String r, String a, String bc) {
        bci = b;
        reason = r;
        action = a;
        bytecode = bc;
    }

    public int getBCI() {
        return bci;
    }

    public String getReason() {
        return reason;
    }

    public String getAction() {
        return action;
    }

    public String getBytecode() {
        return bytecode;
    }

    void emit(PrintStream stream, int indent) {
        for (int i = 0; i < indent; i++) {
            stream.print(' ');
        }
    }

    public void print(PrintStream stream, int indent) {
        emit(stream, indent);
        stream.println(this);
    }

    public String toString() {
        return "@ " + bci  + " " + getBytecode() + " uncommon trap " + getReason() + " " + getAction();
    }
}
