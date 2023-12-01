package sun.security.ssl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.net.ssl.SSLException;
import sun.security.ssl.SSLCipher.SSLReadCipher;
import sun.security.ssl.SSLCipher.SSLWriteCipher;
import sun.security.ssl.SSLHandshake.HandshakeMessage;
import sun.security.ssl.SSLTrafficKeyDerivation.LegacyTrafficKeyDerivation;

/**
 * Pack of the ChangeCipherSpec message.
 */
final class ChangeCipherSpec {
    static final SSLConsumer t10Consumer =
            new T10ChangeCipherSpecConsumer();
    static final HandshakeProducer t10Producer =
            new T10ChangeCipherSpecProducer();
    static final SSLConsumer t13Consumer =
            new T13ChangeCipherSpecConsumer();

    /**
     * The "ChangeCipherSpec" message producer.
     */
    private static final
            class T10ChangeCipherSpecProducer implements HandshakeProducer {
        // Prevent instantiation of this class.
        private T10ChangeCipherSpecProducer() {
            // blank
        }

        @Override
        public byte[] produce(ConnectionContext context,
                HandshakeMessage message) throws IOException {
            HandshakeContext hc = (HandshakeContext)context;
            SSLKeyDerivation kd = hc.handshakeKeyDerivation;

            if (!(kd instanceof LegacyTrafficKeyDerivation tkd)) {
                throw new UnsupportedOperationException("Not supported.");
            }
            CipherSuite ncs = hc.negotiatedCipherSuite;
            Authenticator writeAuthenticator;
            if (ncs.bulkCipher.cipherType == CipherType.AEAD_CIPHER) {
                writeAuthenticator =
                        Authenticator.valueOf(hc.negotiatedProtocol);
            } else {
                try {
                    writeAuthenticator = Authenticator.valueOf(
                            hc.negotiatedProtocol, ncs.macAlg,
                            tkd.getTrafficKey(hc.sslConfig.isClientMode ?
                                    "clientMacKey" : "serverMacKey"));
                } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                    // unlikely
                    throw new SSLException("Algorithm missing:  ", e);
                }
            }

            SecretKey writeKey =
                    tkd.getTrafficKey(hc.sslConfig.isClientMode ?
                                    "clientWriteKey" : "serverWriteKey");
            SecretKey writeIv =
                    tkd.getTrafficKey(hc.sslConfig.isClientMode ?
                                    "clientWriteIv" : "serverWriteIv");
            IvParameterSpec iv = (writeIv == null) ? null :
                    new IvParameterSpec(writeIv.getEncoded());
            SSLWriteCipher writeCipher;
            try {
                writeCipher = ncs.bulkCipher.createWriteCipher(
                        writeAuthenticator,
                        hc.negotiatedProtocol, writeKey, iv,
                        hc.sslContext.getSecureRandom());
            } catch (GeneralSecurityException gse) {
                // unlikely
                throw new SSLException("Algorithm missing:  ", gse);
            }

            if (writeCipher == null) {
                throw hc.conContext.fatal(Alert.ILLEGAL_PARAMETER,
                    "Illegal cipher suite (" + ncs +
                    ") and protocol version (" + hc.negotiatedProtocol + ")");
            }

            if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                SSLLogger.fine("Produced ChangeCipherSpec message");
            }

            hc.conContext.outputRecord.changeWriteCiphers(writeCipher, true);

            // The handshake message has been delivered.
            return null;
        }
    }

    /**
     * The "ChangeCipherSpec" message producer.
     */
    private static final
            class T10ChangeCipherSpecConsumer implements SSLConsumer {
        // Prevent instantiation of this class.
        private T10ChangeCipherSpecConsumer() {
            // blank
        }

        @Override
        public void consume(ConnectionContext context,
                ByteBuffer message) throws IOException {
            TransportContext tc = (TransportContext)context;

            // This consumer can be used only once.
            tc.consumers.remove(ContentType.CHANGE_CIPHER_SPEC.id);

            // parse
            if (message.remaining() != 1 || message.get() != 1) {
                throw tc.fatal(Alert.UNEXPECTED_MESSAGE,
                        "Malformed or unexpected ChangeCipherSpec message");
            }
            if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                SSLLogger.fine("Consuming ChangeCipherSpec message");
            }

            // validate
            if (tc.handshakeContext == null) {
                throw tc.fatal(Alert.HANDSHAKE_FAILURE,
                        "Unexpected ChangeCipherSpec message");
            }


            HandshakeContext hc = tc.handshakeContext;

            if (hc.handshakeKeyDerivation == null) {
                throw tc.fatal(Alert.UNEXPECTED_MESSAGE,
                        "Unexpected ChangeCipherSpec message");
            }

            SSLKeyDerivation kd = hc.handshakeKeyDerivation;
            if (kd instanceof LegacyTrafficKeyDerivation tkd) {
                CipherSuite ncs = hc.negotiatedCipherSuite;
                Authenticator readAuthenticator;
                if (ncs.bulkCipher.cipherType == CipherType.AEAD_CIPHER) {
                    readAuthenticator =
                            Authenticator.valueOf(hc.negotiatedProtocol);
                } else {
                    try {
                        readAuthenticator = Authenticator.valueOf(
                                hc.negotiatedProtocol, ncs.macAlg,
                                tkd.getTrafficKey(hc.sslConfig.isClientMode ?
                                        "serverMacKey" : "clientMacKey"));
                    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                        // unlikely
                        throw new SSLException("Algorithm missing:  ", e);
                    }
                }

                SecretKey readKey =
                        tkd.getTrafficKey(hc.sslConfig.isClientMode ?
                                        "serverWriteKey" : "clientWriteKey");
                SecretKey readIv =
                        tkd.getTrafficKey(hc.sslConfig.isClientMode ?
                                        "serverWriteIv" : "clientWriteIv");
                IvParameterSpec iv = (readIv == null) ? null :
                        new IvParameterSpec(readIv.getEncoded());
                SSLReadCipher readCipher;
                try {
                    readCipher = ncs.bulkCipher.createReadCipher(
                            readAuthenticator,
                            hc.negotiatedProtocol, readKey, iv,
                            hc.sslContext.getSecureRandom());
                } catch (GeneralSecurityException gse) {
                    // unlikely
                    throw new SSLException("Algorithm missing:  ", gse);
                }

                if (readCipher == null) {
                    throw hc.conContext.fatal(Alert.ILLEGAL_PARAMETER,
                        "Illegal cipher suite (" + hc.negotiatedCipherSuite +
                        ") and protocol version (" + hc.negotiatedProtocol +
                        ")");
                }

                tc.inputRecord.changeReadCiphers(readCipher);
            } else {
                throw new UnsupportedOperationException("Not supported.");
            }
        }
    }

    private static final
            class T13ChangeCipherSpecConsumer implements SSLConsumer {
        // Prevent instantiation of this class.
        private T13ChangeCipherSpecConsumer() {
            // blank
        }

        // An implementation may receive an unencrypted record of type
        // change_cipher_spec consisting of the single byte value 0x01
        // at any time after the first ClientHello message has been
        // sent or received and before the peer's Finished message has
        // been received and MUST simply drop it without further
        // processing.
        @Override
        public void consume(ConnectionContext context,
                ByteBuffer message) throws IOException {
            TransportContext tc = (TransportContext)context;

            // This consumer can be used only once.
            tc.consumers.remove(ContentType.CHANGE_CIPHER_SPEC.id);

            // parse
            if (message.remaining() != 1 || message.get() != 1) {
                throw tc.fatal(Alert.UNEXPECTED_MESSAGE,
                        "Malformed or unexpected ChangeCipherSpec message");
            }
            if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                SSLLogger.fine("Consuming ChangeCipherSpec message");
            }

            // no further processing
        }
    }
}
