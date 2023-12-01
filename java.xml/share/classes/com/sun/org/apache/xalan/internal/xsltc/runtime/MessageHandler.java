package com.sun.org.apache.xalan.internal.xsltc.runtime;

import javax.xml.transform.ErrorListener;

/**
 * @author Morten Jorgensen
 * @LastModified: Aug 2019
 */
public class MessageHandler {
    public void displayMessage(String msg) {
        System.err.println(msg);
    }

    public ErrorListener getErrorListener() {
        return null;
    }
}
