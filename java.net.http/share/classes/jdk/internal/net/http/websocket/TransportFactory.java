package jdk.internal.net.http.websocket;

@FunctionalInterface
public interface TransportFactory {

    Transport createTransport(MessageQueue queue,
                              MessageStreamConsumer consumer);
}
