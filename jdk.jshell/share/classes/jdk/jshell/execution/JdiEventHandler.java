package jdk.jshell.execution;

import java.util.function.Consumer;
import com.sun.jdi.*;
import com.sun.jdi.event.*;

/**
 * Handler of Java Debug Interface events.
 * Adapted from jdb EventHandler.
 * Only exit and disconnect events processed.
 */
class JdiEventHandler implements Runnable {

    private final Thread thread;
    private volatile boolean connected = true;
    private boolean completed = false;
    private final VirtualMachine vm;
    private final Consumer<String> reportVMExit;

    /**
     * Creates an event handler. Start with {@code start()}.
     *
     * @param vm the virtual machine for which to handle events
     * @param reportVMExit callback to report exit/disconnect
     * (passed true if the VM has died)
     */
    JdiEventHandler(VirtualMachine vm, Consumer<String> reportVMExit) {
        this.vm = vm;
        this.reportVMExit = reportVMExit;
        this.thread = new Thread(this, "event-handler");
        this.thread.setDaemon(true);
    }

    /**
     * Starts the event handler.
     */
    void start() {
        thread.start();
    }

    synchronized void shutdown() {
        connected = false;  // force run() loop termination
        thread.interrupt();
        while (!completed) {
            try {wait();} catch (InterruptedException exc) {}
        }
    }

    @Override
    public void run() {
        EventQueue queue = vm.eventQueue();
        while (connected) {
            try {
                EventSet eventSet = queue.remove();
                boolean resumeStoppedApp = false;
                EventIterator it = eventSet.eventIterator();
                while (it.hasNext()) {
                    resumeStoppedApp |= handleEvent(it.nextEvent());
                }

                if (resumeStoppedApp) {
                    eventSet.resume();
                }
            } catch (InterruptedException exc) {
                // Do nothing. Any changes will be seen at top of loop.
            } catch (VMDisconnectedException discExc) {
                handleDisconnectedException();
                break;
            }
        }
        synchronized (this) {
            completed = true;
            notifyAll();
        }
    }

    private boolean handleEvent(Event event) {
        handleExitEvent(event);
        return true;
    }

    private void handleExitEvent(Event event) {
        if (event instanceof VMDeathEvent) {
            reportVMExit.accept("VM Died");
        } else if (event instanceof VMDisconnectEvent) {
            connected = false;
            reportVMExit.accept("VM Disconnected");
        } else {
            // ignore everything else
        }
    }

    private synchronized void handleDisconnectedException() {
        /*
         * A VMDisconnectedException has happened while dealing with
         * another event. We need to flush the event queue, dealing only
         * with exit events (VMDeath, VMDisconnect) so that we terminate
         * correctly.
         */
        EventQueue queue = vm.eventQueue();
        while (connected) {
            try {
                EventSet eventSet = queue.remove();
                EventIterator iter = eventSet.eventIterator();
                while (iter.hasNext()) {
                    handleExitEvent(iter.next());
                }
            } catch (InterruptedException exc) {
                // ignore
            } catch (InternalError exc) {
                // ignore
            }
        }
    }
}
