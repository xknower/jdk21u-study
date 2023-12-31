package jdk.internal.net.http;

import java.net.InetSocketAddress;

class PlainProxyConnection extends PlainHttpConnection {

    PlainProxyConnection(InetSocketAddress proxy, HttpClientImpl client) {
        super(proxy, client);
    }

    @Override
    ConnectionPool.CacheKey cacheKey() {
        return ConnectionPool.cacheKey(false, null, address);
    }

    @Override
    public boolean isProxied() { return true; }

    @Override
    InetSocketAddress proxy() {
        return address;
    }
}
