package com.sun.tools.jdi;

import java.util.EventListener;

interface VMListener extends EventListener {
    boolean vmSuspended(VMAction action);
    boolean vmNotSuspended(VMAction action);
}
