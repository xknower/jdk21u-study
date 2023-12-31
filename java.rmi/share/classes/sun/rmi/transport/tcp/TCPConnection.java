package sun.rmi.transport.tcp;

import java.io.*;
import java.net.Socket;
import java.rmi.*;
import sun.rmi.runtime.Log;
import sun.rmi.transport.*;

public class TCPConnection implements Connection {

    private Socket socket;
    private Channel channel;
    private InputStream in = null;
    private OutputStream out = null;
    private long expiration = Long.MAX_VALUE;
    private long lastuse = Long.MIN_VALUE;
    private long roundtrip = 5; // round-trip time for ping

    /**
     * Constructor used for creating a connection to accept call
     * (an input connection)
     */
    TCPConnection(TCPChannel ch, Socket s, InputStream in, OutputStream out)
    {
        socket   = s;
        channel  = ch;
        this.in  = in;
        this.out = out;
    }

    /**
     * Constructor used by subclass when underlying input and output streams
     * are already available.
     */
    TCPConnection(TCPChannel ch, InputStream in, OutputStream out)
    {
        this(ch, null, in, out);
    }

    /**
     * Constructor used when socket is available, but not underlying
     * streams.
     */
    TCPConnection(TCPChannel ch, Socket s)
    {
        this(ch, s, null, null);
    }

    /**
     * Gets the output stream for this connection
     */
    public OutputStream getOutputStream() throws IOException
    {
        if (out == null)
            out = new BufferedOutputStream(socket.getOutputStream());
        return out;
    }

    /**
     * Release the output stream for this connection.
     */
    public void releaseOutputStream() throws IOException
    {
        if (out != null)
            out.flush();
    }

    /**
     * Gets the input stream for this connection.
     */
    public InputStream getInputStream() throws IOException
    {
        if (in == null)
            in = new BufferedInputStream(socket.getInputStream());
        return in;
    }


    /**
     * Release the input stream for this connection.
     */
    public void releaseInputStream()
    {
    }

    /**
     * Determine if this connection can be used for multiple operations.
     * If the socket implements RMISocketInfo, then we can query it about
     * this; otherwise, assume that it does provide a full-duplex
     * persistent connection like java.net.Socket.
     */
    public boolean isReusable()
    {
        return true;
    }

    /**
     * Set the expiration time of this connection.
     * @param time The time at which the time out expires.
     */
    void setExpiration(long time)
    {
        expiration = time;
    }

    /**
     * Set the timestamp at which this connection was last used successfully.
     * The connection will be pinged for liveness if reused long after
     * this time.
     * @param time The time at which the connection was last active.
     */
    void setLastUseTime(long time)
    {
        lastuse = time;
    }

    /**
     * Returns true if the timeout has expired on this connection;
     * otherwise returns false.
     * @param time The current time.
     */
    boolean expired(long time)
    {
        return expiration <= time;
    }

    /**
     * Probes the connection to see if it still alive and connected to
     * a responsive server.  If the connection has been idle for too
     * long, the server is pinged.  ``Too long'' means ``longer than the
     * last ping round-trip time''.
     * <P>
     * This method may misdiagnose a dead connection as live, but it
     * will never misdiagnose a live connection as dead.
     * @return true if the connection and server are recently alive
     */
    public boolean isDead()
    {
        InputStream i;
        OutputStream o;

        // skip ping if recently used within 1 RTT
        long start = System.currentTimeMillis();
        if ((roundtrip > 0) && (start < lastuse + roundtrip))
            return (false);     // still alive and warm

        // Get the streams
        try {
            i = getInputStream();
            o = getOutputStream();
        } catch (IOException e) {
            return (true);      // can't even get a stream, must be very dead
        }

        // Write the ping byte and read the reply byte
        int response = 0;
        try {
            o.write(TransportConstants.Ping);
            o.flush();
            response = i.read();
        } catch (IOException ex) {
            TCPTransport.tcpLog.log(Log.VERBOSE, "exception: ", ex);
            TCPTransport.tcpLog.log(Log.BRIEF, "server ping failed");

            return (true);      // server failed the ping test
        }

        if (response == TransportConstants.PingAck) {
            // save most recent RTT for future use
            roundtrip = (System.currentTimeMillis() - start) * 2;
            // clock-correction may make roundtrip < 0; doesn't matter
            return (false);     // it's alive and 5-by-5
        }

        if (TCPTransport.tcpLog.isLoggable(Log.BRIEF)) {
            TCPTransport.tcpLog.log(Log.BRIEF,
                (response == -1 ? "server has been deactivated" :
                "server protocol error: ping response = " + response));
        }
        return (true);
    }

    /**
     * Close the connection.  */
    public void close() throws IOException
    {
        if (TCPTransport.tcpLog.isLoggable(Log.BRIEF)) {
            TCPTransport.tcpLog.log(Log.BRIEF,
                    "close connection, socket: " + socket);
        }

        if (socket != null)
            socket.close();
        else {
            in.close();
            out.close();
        }
    }

    /**
     * Returns the channel for this connection.
     */
    public Channel getChannel()
    {
        return channel;
    }
}
