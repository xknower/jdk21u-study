package jdk.internal.net.http.hpack;

final class LiteralWriter extends IndexNameValueWriter {

    LiteralWriter() {
        super(0b0000_0000, 4);
    }
}
