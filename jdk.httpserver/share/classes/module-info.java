/**
 * Defines the JDK-specific HTTP server API, and provides the jwebserver tool
 * for running a minimal HTTP server.
 *
 * <p>The {@link com.sun.net.httpserver} package defines a high-level API for
 * building servers that support HTTP and HTTPS. The SimpleFileServer class
 * implements a simple HTTP-only file server intended for testing, development
 * and debugging purposes. A default implementation is provided via the
 * {@code jwebserver} tool and the main entry point of the module, which can
 * also be invoked with {@code java -m jdk.httpserver}.
 *
 * <p>The {@link com.sun.net.httpserver.spi} package specifies a Service Provider
 * Interface (SPI) for locating HTTP server implementations based on the
 * {@code com.sun.net.httpserver} API.
 * <p>
 * <b id="httpserverprops">System properties used by the HTTP server API</b>
 * <p>
 * The following is a list of JDK specific system properties used by the default HTTP
 * server implementation in the JDK. Any properties below that take a numeric value
 * assume the default value if given a string that does not parse as a number.
 * <ul>
 * <li><p><b>{@systemProperty sun.net.httpserver.idleInterval}</b> (default: 30 sec)<br>
 * Maximum duration in seconds which an idle connection is kept open. This timer
 * has an implementation specific granularity that may mean that idle connections are
 * closed later than the specified interval. Values less than or equal to zero are mapped
 * to the default setting.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpserver.maxConnections}</b> (default: -1)<br>
 * The maximum number of open connections at a time. This includes active and idle connections.
 * If zero or negative, then no limit is enforced.
 * </li>
 * <li><p><b>{@systemProperty sun.net.httpserver.maxIdleConnections}</b> (default: 200)<br>
 * The maximum number of idle connections at a time. If set to zero or a negative value
 * then connections are closed after use.
 * </li>
 * <li><p><b>{@systemProperty sun.net.httpserver.drainAmount}</b> (default: 65536)<br>
 * The maximum number of bytes that will be automatically read and discarded from a
 * request body that has not been completely consumed by its
 * {@link com.sun.net.httpserver.HttpHandler HttpHandler}. If the number of remaining
 * unread bytes are less than this limit then the connection will be put in the idle connection
 * cache. If not, then it will be closed.
 * </li>
 * <li><p><b>{@systemProperty sun.net.httpserver.maxReqHeaders}</b> (default: 200)<br>
 * The maxiumum number of header fields accepted in a request. If this limit is exceeded
 * while the headers are being read, then the connection is terminated and the request ignored.
 * If the value is less than or equal to zero, then the default value is used.
 * </li>
 * <li><p><b>{@systemProperty sun.net.httpserver.maxReqTime}</b> (default: -1)<br>
 * The maximum time in milliseconds allowed to receive a request headers and body.
 * In practice, the actual time is a function of request size, network speed, and handler
 * processing delays. A value less than or equal to zero means the time is not limited.
 * If the limit is exceeded then the connection is terminated and the handler will receive a
 * {@link java.io.IOException}. This timer has an implementation specific granularity
 * that may mean requests are aborted later than the specified interval.
 * </li>
 * <li><p><b>{@systemProperty sun.net.httpserver.maxRspTime}</b> (default: -1)<br>
 * The maximum time in milliseconds allowed to receive a response headers and body.
 * In practice, the actual time is a function of response size, network speed, and handler
 * processing delays. A value less than or equal to zero means the time is not limited.
 * If the limit is exceeded then the connection is terminated and the handler will receive a
 * {@link java.io.IOException}. This timer has an implementation specific granularity
 * that may mean responses are aborted later than the specified interval.
 * </li>
 * <li><p><b>{@systemProperty sun.net.httpserver.nodelay}</b> (default: false)<br>
 * Boolean value, which if true, sets the {@link java.net.StandardSocketOptions#TCP_NODELAY TCP_NODELAY}
 * socket option on all incoming connections.
 * </li></ul>
 *
 * @toolGuide jwebserver
 *
 * @uses com.sun.net.httpserver.spi.HttpServerProvider
 *
 * @moduleGraph
 * @since 9
 */
module jdk.httpserver {

    exports com.sun.net.httpserver;
    exports com.sun.net.httpserver.spi;

    uses com.sun.net.httpserver.spi.HttpServerProvider;
}
