package sun.nio.ch.sctp;

import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.nio.channels.SelectionKey;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetBoundException;
import java.nio.channels.spi.SelectorProvider;
import com.sun.nio.sctp.IllegalUnbindException;
import com.sun.nio.sctp.SctpChannel;
import com.sun.nio.sctp.SctpServerChannel;
import com.sun.nio.sctp.SctpSocketOption;
import com.sun.nio.sctp.SctpStandardSocketOptions;
import sun.nio.ch.NativeThread;
import sun.nio.ch.IOStatus;
import sun.nio.ch.IOUtil;
import sun.nio.ch.Net;
import sun.nio.ch.SelChImpl;
import sun.nio.ch.SelectionKeyImpl;

/**
 * An implementation of SctpServerChannel
 */
public class SctpServerChannelImpl extends SctpServerChannel
    implements SelChImpl
{
    private final FileDescriptor fd;

    private final int fdVal;

    /* IDs of native thread doing accept, for signalling */
    private volatile long thread;

    /* Lock held by thread currently blocked in this channel */
    private final Object lock = new Object();

    /* Lock held by any thread that modifies the state fields declared below
     * DO NOT invoke a blocking I/O operation while holding this lock! */
    private final Object stateLock = new Object();

    private enum ChannelState {
        UNINITIALIZED,
        INUSE,
        KILLPENDING,
        KILLED,
    }
    /* -- The following fields are protected by stateLock -- */
    private ChannelState state = ChannelState.UNINITIALIZED;

    /* Binding: Once bound the port will remain constant. */
    int port = -1;
    private final HashSet<InetSocketAddress> localAddresses = new HashSet<>();
    /* Has the channel been bound to the wildcard address */
    private boolean wildcard; /* false */

    /* -- End of fields protected by stateLock -- */

    /**
     * Initializes a new instance of this class.
     */
    public SctpServerChannelImpl(SelectorProvider provider)
            throws IOException {
        //TODO: update provider remove public modifier
        super(provider);
        this.fd = SctpNet.socket(true);
        this.fdVal = IOUtil.fdVal(fd);
        this.state = ChannelState.INUSE;
    }

    @Override
    public SctpServerChannel bind(SocketAddress local, int backlog)
            throws IOException {
        synchronized (lock) {
            synchronized (stateLock) {
                if (!isOpen())
                    throw new ClosedChannelException();
                if (isBound())
                    SctpNet.throwAlreadyBoundException();

                InetSocketAddress isa = (local == null) ?
                    new InetSocketAddress(0) : Net.checkAddress(local);
                @SuppressWarnings("removal")
                SecurityManager sm = System.getSecurityManager();
                if (sm != null)
                    sm.checkListen(isa.getPort());
                Net.bind(fd, isa.getAddress(), isa.getPort());

                InetSocketAddress boundIsa = Net.localAddress(fd);
                port = boundIsa.getPort();
                localAddresses.add(isa);
                    if (isa.getAddress().isAnyLocalAddress())
                        wildcard = true;

                SctpNet.listen(fdVal, backlog < 1 ? 50 : backlog);
            }
        }
        return this;
    }

    @Override
    public SctpServerChannel bindAddress(InetAddress address)
            throws IOException {
        return bindUnbindAddress(address, true);
    }

    @Override
    public SctpServerChannel unbindAddress(InetAddress address)
            throws IOException {
        return bindUnbindAddress(address, false);
    }

    private SctpServerChannel bindUnbindAddress(InetAddress address, boolean add)
            throws IOException {
        if (address == null)
            throw new IllegalArgumentException();

        synchronized (lock) {
            synchronized (stateLock) {
                if (!isOpen())
                    throw new ClosedChannelException();
                if (!isBound())
                    throw new NotYetBoundException();
                if (wildcard)
                    throw new IllegalStateException(
                            "Cannot add or remove addresses from a channel that is bound to the wildcard address");
                if (address.isAnyLocalAddress())
                    throw new IllegalArgumentException(
                            "Cannot add or remove the wildcard address");
                if (add) {
                    for (InetSocketAddress addr : localAddresses) {
                        if (addr.getAddress().equals(address)) {
                            SctpNet.throwAlreadyBoundException();
                        }
                    }
                } else { /*removing */
                    /* Verify that there is more than one address
                     * and that address is already bound */
                    if (localAddresses.size() <= 1)
                        throw new IllegalUnbindException("Cannot remove address from a channel with only one address bound");
                    boolean foundAddress = false;
                    for (InetSocketAddress addr : localAddresses) {
                        if (addr.getAddress().equals(address)) {
                            foundAddress = true;
                            break;
                        }
                    }
                    if (!foundAddress )
                        throw new IllegalUnbindException("Cannot remove address from a channel that is not bound to that address");
                }

                SctpNet.bindx(fdVal, new InetAddress[]{address}, port, add);

                /* Update our internal Set to reflect the addition/removal */
                if (add)
                    localAddresses.add(new InetSocketAddress(address, port));
                else {
                    for (InetSocketAddress addr : localAddresses) {
                        if (addr.getAddress().equals(address)) {
                            localAddresses.remove(addr);
                            break;
                        }
                    }
                }
            }
        }
        return this;
    }

    private boolean isBound() {
        synchronized (stateLock) {
            return port != -1;
        }
    }

    private void acceptCleanup() throws IOException {
        synchronized (stateLock) {
            thread = 0;
            if (state == ChannelState.KILLPENDING)
                kill();
        }
    }

    @Override
    public SctpChannel accept() throws IOException {
        synchronized (lock) {
            if (!isOpen())
                throw new ClosedChannelException();
            if (!isBound())
                throw new NotYetBoundException();
            SctpChannel sc = null;

            int n = 0;
            FileDescriptor newfd = new FileDescriptor();
            InetSocketAddress[] isaa = new InetSocketAddress[1];

            try {
                begin();
                if (!isOpen())
                    return null;
                thread = NativeThread.current();
                for (;;) {
                    n = Net.accept(fd, newfd, isaa);
                    if ((n == IOStatus.INTERRUPTED) && isOpen())
                        continue;
                    break;
                }
            } finally {
                acceptCleanup();
                end(n > 0);
                assert IOStatus.check(n);
            }

            if (n < 1)
                return null;

            IOUtil.configureBlocking(newfd, true);
            InetSocketAddress isa = isaa[0];
            sc = new SctpChannelImpl(provider(), newfd);

            @SuppressWarnings("removal")
            SecurityManager sm = System.getSecurityManager();
            if (sm != null)
                sm.checkAccept(isa.getAddress().getHostAddress(),
                               isa.getPort());

            return sc;
        }
    }

    @Override
    protected void implConfigureBlocking(boolean block) throws IOException {
        IOUtil.configureBlocking(fd, block);
    }

    @Override
    public void implCloseSelectableChannel() throws IOException {
        synchronized (stateLock) {
            if (state != ChannelState.KILLED)
                SctpNet.preClose(fdVal);
            if (thread != 0)
                NativeThread.signal(thread);
            if (!isRegistered())
                kill();
        }
    }

    @Override
    public void kill() throws IOException {
        synchronized (stateLock) {
            if (state == ChannelState.KILLED)
                return;
            if (state == ChannelState.UNINITIALIZED) {
                state = ChannelState.KILLED;
                SctpNet.close(fdVal);
                return;
            }
            assert !isOpen() && !isRegistered();

            // Postpone the kill if there is a thread in accept
            if (thread == 0) {
                state = ChannelState.KILLED;
                SctpNet.close(fdVal);
            } else {
                state = ChannelState.KILLPENDING;
            }
        }
    }

    @Override
    public FileDescriptor getFD() {
        return fd;
    }

    @Override
    public int getFDVal() {
        return fdVal;
    }

    /**
     * Translates native poll revent ops into a ready operation ops
     */
    private boolean translateReadyOps(int ops, int initialOps,
                                     SelectionKeyImpl sk) {
        int intOps = sk.nioInterestOps();
        int oldOps = sk.nioReadyOps();
        int newOps = initialOps;

        if ((ops & Net.POLLNVAL) != 0) {
            /* This should only happen if this channel is pre-closed while a
             * selection operation is in progress
             * ## Throw an error if this channel has not been pre-closed */
            return false;
        }

        if ((ops & (Net.POLLERR | Net.POLLHUP)) != 0) {
            newOps = intOps;
            sk.nioReadyOps(newOps);
            return (newOps & ~oldOps) != 0;
        }

        if (((ops & Net.POLLIN) != 0) &&
            ((intOps & SelectionKey.OP_ACCEPT) != 0))
                newOps |= SelectionKey.OP_ACCEPT;

        sk.nioReadyOps(newOps);
        return (newOps & ~oldOps) != 0;
    }

    @Override
    public boolean translateAndUpdateReadyOps(int ops, SelectionKeyImpl sk) {
        return translateReadyOps(ops, sk.nioReadyOps(), sk);
    }

    @Override
    public boolean translateAndSetReadyOps(int ops, SelectionKeyImpl sk) {
        return translateReadyOps(ops, 0, sk);
    }

    @Override
    public int translateInterestOps(int ops) {
        int newOps = 0;
        if ((ops & SelectionKey.OP_ACCEPT) != 0)
            newOps |= Net.POLLIN;
        return newOps;
    }

    @Override
    public <T> SctpServerChannel setOption(SctpSocketOption<T> name, T value)
            throws IOException {
        if (name == null)
            throw new NullPointerException();
        if (!supportedOptions().contains(name))
            throw new UnsupportedOperationException("'" + name + "' not supported");

        synchronized (stateLock) {
            if (!isOpen())
                throw new ClosedChannelException();

            SctpNet.setSocketOption(fdVal, name, value, 0 /*oneToOne*/);
            return this;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getOption(SctpSocketOption<T> name) throws IOException {
        if (name == null)
            throw new NullPointerException();
        if (!supportedOptions().contains(name))
            throw new UnsupportedOperationException("'" + name + "' not supported");

        synchronized (stateLock) {
            if (!isOpen())
                throw new ClosedChannelException();

            return (T) SctpNet.getSocketOption(fdVal, name, 0 /*oneToOne*/);
        }
    }

    @Override
    public final Set<SctpSocketOption<?>> supportedOptions() {
        final class Holder {
            static final Set<SctpSocketOption<?>> DEFAULT_OPTIONS =
                    Set.of(SctpStandardSocketOptions.SCTP_INIT_MAXSTREAMS);
        }
        return Holder.DEFAULT_OPTIONS;
    }

    @Override
    public Set<SocketAddress> getAllLocalAddresses()
            throws IOException {
        synchronized (stateLock) {
            if (!isOpen())
                throw new ClosedChannelException();
            if (!isBound())
                return Collections.emptySet();

            return SctpNet.getLocalAddresses(fdVal);
        }
    }
}
