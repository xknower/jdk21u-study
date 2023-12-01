package jdk.internal.net.http.websocket;

public class TransportFactoryImpl implements TransportFactory {

    private final RawChannel channel;

    public TransportFactoryImpl(RawChannel channel) {
        this.channel = channel;
    }

    @Override
    public Transport createTransport(MessageQueue queue,
                                     MessageStreamConsumer consumer) {
        return new TransportImpl(queue, consumer, channel);
    }
}
