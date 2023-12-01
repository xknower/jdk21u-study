package com.sun.hotspot.igv.connection;

import com.sun.hotspot.igv.data.serialization.Parser;
import com.sun.hotspot.igv.data.services.GroupCallback;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import org.openide.util.Exceptions;

public class Client implements Runnable {
    private final SocketChannel socket;
    private final GroupCallback callback;

    public Client(SocketChannel socket, GroupCallback callback) {
        this.callback = callback;
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            final SocketChannel channel = socket;
            channel.configureBlocking(true);
            channel.socket().getOutputStream().write('y');
            new Parser(channel, null, callback).parse();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
