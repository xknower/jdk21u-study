/**
 * Defines the HTTP Client and WebSocket APIs.
 * <p>
 * <b id="httpclientprops">System properties used by the java.net.http API</b>
 * <p>
 * The following is a list of system networking properties used by the java.net.http
 * client implementation in the JDK. Any properties below that take a numeric value
 * assume the default value if given a string that does not parse as a number.
 * Unless otherwise specified below, all values can be set in the {@code conf/net.properties}
 * file. In all cases, values can be specified as system properties on the command line,
 * in which case, any value in {@code conf/net.properties} is overridden. No guarantee is
 * provided that property values can be set programatically with {@code System.setProperty()}.
 * Other implementations of this API may choose not to support these properties.
 * <ul>
 * <li><p><b>{@systemProperty jdk.httpclient.allowRestrictedHeaders}</b> (default: see below)<br>
 * A comma-separated list of normally restricted HTTP header names that users may set in HTTP
 * requests or by user code in HttpRequest instances. By default, the following request
 * headers are not allowed to be set by user code: connection, content-length, expect, host,
 * and upgrade. You can override this behavior with this property. The names are case-insensitive
 * and whitespace is ignored. Note that this property is intended for testing and not for
 * real-world deployments. Protocol errors or other undefined behavior are likely to occur
 * when using this property. There may be other headers that are restricted from being set
 * depending on the context. This includes the "Authorization" header when the relevant
 * HttpClient has an authenticator set. These restrictions cannot be overridden by this
 * property.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.bufsize}</b> (default: 16384 bytes or 16 kB)<br>
 * The size to use for internal allocated buffers in bytes.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.connectionPoolSize}</b> (default: 0)<br>
 * The maximum number of connections to keep in the HTTP/1.1 keep alive cache. A value of 0
 * means that the cache is unbounded.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.connectionWindowSize}</b> (default: 2^26)<br>
 * The HTTP/2 client connection window size in bytes. The maximum size is 2^31-1. This value
 * cannot be smaller than the stream window size, which can be configured through the
 * {@code jdk.httpclient.windowsize} system property.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.disableRetryConnect}</b> (default: false)<br>
 * Whether automatic retry of connection failures is disabled. If false, then retries are
 * attempted (subject to the retry limit).
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.enableAllMethodRetry}</b> (default: false)<br>
 * Whether it is permitted to automatically retry non-idempotent HTTP requests.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.enablepush}</b> (default: 1)<br>
 * Whether HTTP/2 push promise is enabled. A value of 1 enables push promise; a value of 0
 * disables it.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.hpack.maxheadertablesize}</b> (default: 16384 or
 * 16 kB)<br> The HTTP/2 client maximum HPACK header table size in bytes.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.HttpClient.log}</b> (default: none)<br>
 * Enables high-level logging of various events through the {@linkplain java.lang.System.Logger
 * Platform Logging API}. The value contains a comma-separated list of any of the
 * following items:
 * <ul>
 *   <li>errors</li>
 *   <li>requests</li>
 *   <li>headers</li>
 *   <li>content</li>
 *   <li>frames</li>
 *   <li>ssl</li>
 *   <li>trace</li>
 *   <li>channel</li>
 * </ul><br>
 * You can append the frames item with a colon-separated list of any of the following items:
 * <ul>
 *   <li>control</li>
 *   <li>data</li>
 *   <li>window</li>
 *   <li>all</li>
 * </ul><br>
 * Specifying an item adds it to the HTTP client's log. For example, if you specify the
 * following value, then the Platform Logging API logs all possible HTTP Client events:<br>
 * "errors,requests,headers,frames:control:data:window,ssl,trace,channel"<br>
 * Note that you can replace control:data:window with all. The name of the logger is
 * "jdk.httpclient.HttpClient", and all logging is at level INFO.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.keepalive.timeout}</b> (default: 30)<br>
 * The number of seconds to keep idle HTTP connections alive in the keep alive cache. This
 * property applies to both HTTP/1.1 and HTTP/2. The value for HTTP/2 can be overridden
 * with the {@code jdk.httpclient.keepalive.timeout.h2 property}.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.keepalive.timeout.h2}</b> (default: see
 * below)<br>The number of seconds to keep idle HTTP/2 connections alive. If not set, then the
 * {@code jdk.httpclient.keepalive.timeout} setting is used.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.maxframesize}</b> (default: 16384 or 16kB)<br>
 * The HTTP/2 client maximum frame size in bytes. The server is not permitted to send a frame
 * larger than this.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.maxstreams}</b> (default: 100)<br>
 * The maximum number of HTTP/2 push streams that the client will permit servers to open
 * simultaneously.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.receiveBufferSize}</b> (default: operating system
 * default)<br>The HTTP client <a href="../java.base/java/net/StandardSocketOptions.html#SO_RCVBUF">
 * socket receive buffer size</a> in bytes.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.redirects.retrylimit}</b> (default: 5)<br>
 * The maximum number of attempts to send a HTTP request when redirected or any failure occurs
 * for any reason.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.websocket.writeBufferSize}</b> (default: 16384
 * or 16kB)<br>The buffer size used by the web socket implementation for socket writes.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.windowsize}</b> (default: 16777216 or 16 MB)<br>
 * The HTTP/2 client stream window size in bytes.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.auth.retrylimit}</b> (default: 3)<br>
 * The number of attempts the Basic authentication filter will attempt to retry a failed
 * authentication.
 * </li>
 * <li><p><b>{@systemProperty jdk.httpclient.sendBufferSize}</b> (default: operating system
 * default)<br>The HTTP client socket
 * <a href="../java.base/java/net/StandardSocketOptions.html#SO_SNDBUF">send buffer size</a>.
 * Values less than or equal to zero are ignored.
 * </li>
 * <li><p><b>{@systemProperty jdk.internal.httpclient.disableHostnameVerification}</b> (default:
 * false)<br>If true (or set to an empty string), hostname verification in SSL certificates
 * is disabled. This is a system property only and not available in {@code conf/net.properties}.
 * It is provided for testing purposes only.
 * </li>
 * <li><p><b>{@systemProperty jdk.http.auth.proxying.disabledSchemes}</b> (default: see
 * conf/net.properties)<br>A comma separated list of HTTP authentication scheme names,
 * that are disallowed for use by the HTTP client implementation, for HTTP proxying.
 * </li>
 * <li><p><b>{@systemProperty jdk.http.auth.tunneling.disabledSchemes}</b> (default: see
 * conf/net.properties)<br>A comma separated list of HTTP authentication scheme names, that
 * are disallowed for use by the HTTP client implementation, for HTTP CONNECT tunneling.
 * </li>
 * </ul>
 * @moduleGraph
 * @since 11
 */
module java.net.http {
    exports java.net.http;
}
