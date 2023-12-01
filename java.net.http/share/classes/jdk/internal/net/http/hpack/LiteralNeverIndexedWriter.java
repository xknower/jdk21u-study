package jdk.internal.net.http.hpack;

final class LiteralNeverIndexedWriter extends IndexNameValueWriter {

    LiteralNeverIndexedWriter() {
        super(0b0001_0000, 4);
    }
}
