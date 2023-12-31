package jdk.internal.net.http;

import java.io.IOException;
import java.lang.ref.Cleaner;
import java.lang.ref.Reference;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.PushPromiseHandler;
import java.net.http.WebSocket;
import jdk.internal.net.http.common.OperationTrackers.Trackable;
import jdk.internal.net.http.common.OperationTrackers.Tracker;
import jdk.internal.ref.CleanerFactory;

/**
 * An HttpClientFacade is a simple class that wraps an HttpClient implementation
 * and delegates everything to its implementation delegate.
 * @implSpec
 * Though the facade strongly reference its implementation, the
 * implementation MUST NOT strongly reference the facade.
 * It MAY use weak references if needed.
 */
public final class HttpClientFacade extends HttpClient implements Trackable {

    static final Cleaner cleaner = CleanerFactory.cleaner();

    final HttpClientImpl impl;

    /**
     * Creates an HttpClientFacade.
     */
    HttpClientFacade(HttpClientImpl impl) {
        this.impl = impl;
        // wakeup the impl when the facade is gc'ed
        cleaner.register(this, impl::facadeCleanup);
    }

    @Override // for tests
    public Tracker getOperationsTracker() {
        return impl.getOperationsTracker();
    }

    @Override
    public Optional<CookieHandler> cookieHandler() {
        return impl.cookieHandler();
    }

    @Override
    public Optional<Duration> connectTimeout() {
        return impl.connectTimeout();
    }

    @Override
    public Redirect followRedirects() {
        return impl.followRedirects();
    }

    @Override
    public Optional<ProxySelector> proxy() {
        return impl.proxy();
    }

    @Override
    public SSLContext sslContext() {
        return impl.sslContext();
    }

    @Override
    public SSLParameters sslParameters() {
        return impl.sslParameters();
    }

    @Override
    public Optional<Authenticator> authenticator() {
        return impl.authenticator();
    }

    @Override
    public HttpClient.Version version() {
        return impl.version();
    }

    @Override
    public Optional<Executor> executor() {
        return impl.executor();
    }

    public Executor theExecutor() {
        return impl.theExecutor();
    }

    @Override
    public <T> HttpResponse<T>
    send(HttpRequest req, HttpResponse.BodyHandler<T> responseBodyHandler)
        throws IOException, InterruptedException
    {
        try {
            return impl.send(req, responseBodyHandler);
        } finally {
            Reference.reachabilityFence(this);
        }
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>>
    sendAsync(HttpRequest req, HttpResponse.BodyHandler<T> responseBodyHandler) {
        try {
            return impl.sendAsync(req, responseBodyHandler);
        } finally {
            Reference.reachabilityFence(this);
        }
    }

    @Override
    public <T> CompletableFuture<HttpResponse<T>>
    sendAsync(HttpRequest req,
              BodyHandler<T> responseBodyHandler,
              PushPromiseHandler<T> pushPromiseHandler){
        try {
            return impl.sendAsync(req, responseBodyHandler, pushPromiseHandler);
        } finally {
            Reference.reachabilityFence(this);
        }
    }

    @Override
    public WebSocket.Builder newWebSocketBuilder() {
        try {
            return impl.newWebSocketBuilder();
        } finally {
            Reference.reachabilityFence(this);
        }
    }

    @Override
    public boolean isTerminated() {
        return impl.isTerminated();
    }

    @Override
    public void shutdown() {
        impl.shutdown();
    }

    @Override
    public void shutdownNow() {
        impl.shutdownNow();
    }

    @Override
    public boolean awaitTermination(Duration duration) throws InterruptedException {
        return impl.awaitTermination(duration);
    }

    @Override
    public void close() {
        impl.close();
    }

    @Override
    public String toString() {
        // Used by tests to get the client's id.
        return impl.toString();
    }
}
