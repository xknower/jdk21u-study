package com.sun.tools.jdi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sun.jdi.Bootstrap;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.connect.ListeningConnector;
import com.sun.jdi.connect.Transport;
import com.sun.jdi.connect.spi.Connection;
import com.sun.jdi.connect.spi.TransportService;

/*
 * A ListeningConnector to listen for connections from target VM
 * using the configured transport service
 */
public class GenericListeningConnector
        extends ConnectorImpl implements ListeningConnector
{
    static final String ARG_ADDRESS = "address";
    static final String ARG_TIMEOUT = "timeout";

    Map<Map<String,? extends Connector.Argument>, TransportService.ListenKey>  listenMap;
    TransportService transportService;
    Transport transport;

    /**
     * Initialize a new instance of this connector. The connector
     * encapsulates a transport service, has a "timeout" connector argument,
     * and optionally an "address" connector argument.
     */
    private GenericListeningConnector(TransportService ts,
                                      boolean addAddressArgument)
    {
        transportService = ts;
        transport = new Transport() {
                public String name() {
                    return transportService.name();
                }
            };

        if (addAddressArgument) {
            addStringArgument(
                ARG_ADDRESS,
                getString("generic_listening.address.label"),
                getString("generic_listening.address"),
                "",
                false);
        }

        addIntegerArgument(
                ARG_TIMEOUT,
                getString("generic_listening.timeout.label"),
                getString("generic_listening.timeout"),
                "",
                false,
                0, Integer.MAX_VALUE);

        listenMap = new HashMap<Map<String, ? extends Connector.Argument>, TransportService.ListenKey>(10);
    }

    /**
     * Initialize a new instance of this connector. This constructor is used
     * when sub-classing - the resulting connector will a "timeout" connector
     * argument.
     */
    protected GenericListeningConnector(TransportService ts) {
        this(ts, false);
    }

    /**
     * Create an instance of this Connector. The resulting ListeningConnector will
     * have "address" and "timeout" connector arguments.
     */
    public static GenericListeningConnector create(TransportService ts) {
        return new GenericListeningConnector(ts, true);
    }

    public String startListening(String address, Map<String,? extends Connector.Argument> args)
        throws IOException, IllegalConnectorArgumentsException
    {
        TransportService.ListenKey listener = listenMap.get(args);
        if (listener != null) {
           throw new IllegalConnectorArgumentsException("Already listening",
               new ArrayList<>(args.keySet()));
        }
        listener = transportService.startListening(address);
        updateArgumentMapIfRequired(args, listener);
        listenMap.put(args, listener);
        return listener.address();
    }

    public String
        startListening(Map<String, ? extends Connector.Argument> args)
        throws IOException, IllegalConnectorArgumentsException
    {
        String address = argument(ARG_ADDRESS, args).value();
        return startListening(address, args);
    }

    public void stopListening(Map<String, ? extends Connector.Argument> args)
        throws IOException, IllegalConnectorArgumentsException
    {
        TransportService.ListenKey listener = listenMap.get(args);
        if (listener == null) {
           throw new IllegalConnectorArgumentsException("Not listening",
               new ArrayList<>(args.keySet()));
        }
        transportService.stopListening(listener);
        listenMap.remove(args);
    }

    public VirtualMachine
        accept(Map<String, ? extends Connector.Argument> args)
        throws IOException, IllegalConnectorArgumentsException
    {
        String ts = argument(ARG_TIMEOUT, args).value();
        int timeout = 0;
        if (ts.length() > 0) {
            timeout = Integer.decode(ts).intValue();
        }

        TransportService.ListenKey listener = listenMap.get(args);
        Connection connection;
        if (listener != null) {
            connection = transportService.accept(listener, timeout, 0);
        } else {
            /*
             * Keep compatibility with previous releases - if the
             * debugger hasn't called startListening then we do a
             * once-off accept
             */
             startListening(args);
             listener = listenMap.get(args);
             assert listener != null;
             connection = transportService.accept(listener, timeout, 0);
             stopListening(args);
        }
        return Bootstrap.virtualMachineManager().createVirtualMachine(connection);
    }

    public boolean supportsMultipleConnections() {
        return transportService.capabilities().supportsMultipleConnections();
    }

    public String name() {
        return transport.name() + "Listen";
    }

    public String description() {
        return transportService.description();
    }

    public Transport transport() {
        return transport;
    }

    protected void updateArgumentMapIfRequired(
        Map<String, ? extends Connector.Argument> args, TransportService.ListenKey listener) {
    }

}
