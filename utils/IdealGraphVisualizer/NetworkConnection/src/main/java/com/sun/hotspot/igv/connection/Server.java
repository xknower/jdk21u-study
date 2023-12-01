package com.sun.hotspot.igv.connection;

import com.sun.hotspot.igv.data.services.GroupCallback;
import com.sun.hotspot.igv.settings.Settings;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.RequestProcessor;

/**
 *
 * @author Thomas Wuerthinger
 */
public class Server implements PreferenceChangeListener {
    private ServerSocketChannel serverSocket;
    private final GroupCallback callback;
    private int port;
    private Runnable serverRunnable;

    public Server(GroupCallback callback) {
        this.callback = callback;
        initializeNetwork();
        Settings.get().addPreferenceChangeListener(this);
    }

    @Override
    public void preferenceChange(PreferenceChangeEvent e) {
        int curPort = Integer.parseInt(Settings.get().get(Settings.PORT, Settings.PORT_DEFAULT));
        if (curPort != port) {
            initializeNetwork();
        }
    }

    private void initializeNetwork() {
        int curPort = Integer.parseInt(Settings.get().get(Settings.PORT, Settings.PORT_DEFAULT));
        this.port = curPort;
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(curPort));
        } catch (Throwable ex) {
            NotifyDescriptor message = new NotifyDescriptor.Message("Could not create server. Listening for incoming data is disabled.", NotifyDescriptor.ERROR_MESSAGE);
            DialogDisplayer.getDefault().notifyLater(message);
            return;
        }

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        SocketChannel clientSocket = serverSocket.accept();
                        if (serverRunnable != this) {
                            clientSocket.close();
                            return;
                        }
                        RequestProcessor.getDefault().post(new Client(clientSocket, callback), 0, Thread.MAX_PRIORITY);
                    } catch (IOException ex) {
                        serverSocket = null;
                        NotifyDescriptor message = new NotifyDescriptor.Message("Error during listening for incoming connections. Listening for incoming data is disabled.", NotifyDescriptor.ERROR_MESSAGE);
                        DialogDisplayer.getDefault().notifyLater(message);
                        return;
                    }
                }
            }
        };

        serverRunnable = runnable;

        RequestProcessor.getDefault().post(runnable, 0, Thread.MAX_PRIORITY);
    }
}
