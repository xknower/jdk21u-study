package jdk.internal.net.http.common;

import java.net.http.HttpHeaders;

public class HeaderDecoder extends ValidatingHeadersConsumer {

    private final HttpHeadersBuilder headersBuilder;

    public HeaderDecoder() {
        this.headersBuilder = new HttpHeadersBuilder();
    }

    @Override
    public void onDecoded(CharSequence name, CharSequence value) {
        String n = name.toString();
        String v = value.toString();
        super.onDecoded(n, v);
        headersBuilder.addHeader(n, v);
    }

    public HttpHeaders headers() {
        return headersBuilder.build();
    }
}
