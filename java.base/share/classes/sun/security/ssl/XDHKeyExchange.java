package sun.security.ssl;

import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.XECPublicKey;
import java.security.spec.*;
import sun.security.ssl.NamedGroup.NamedGroupSpec;
import sun.security.util.*;

/**
 * Specifics for XEC/XDH Keys/Exchanges
 */
final class XDHKeyExchange {

    static final SSLKeyAgreementGenerator xdheKAGenerator
            = new XDHEKAGenerator();

    static final class XDHECredentials implements NamedGroupCredentials {

        final XECPublicKey popPublicKey;
        final NamedGroup namedGroup;

        XDHECredentials(XECPublicKey popPublicKey, NamedGroup namedGroup) {
            this.popPublicKey = popPublicKey;
            this.namedGroup = namedGroup;
        }

        @Override
        public PublicKey getPublicKey() {
            return popPublicKey;
        }

        @Override
        public NamedGroup getNamedGroup() {
            return namedGroup;
        }

        /**
         * Parse the encoded Point into the XDHECredentials using the
         * namedGroup.
         */
        static XDHECredentials valueOf(NamedGroup namedGroup,
                byte[] encodedPoint) throws IOException,
                GeneralSecurityException {

            if (namedGroup.spec != NamedGroupSpec.NAMED_GROUP_XDH) {
                throw new RuntimeException(
                        "Credentials decoding:  Not XDH named group");
            }

            if (encodedPoint == null || encodedPoint.length == 0) {
                return null;
            }

            byte[] uBytes = encodedPoint.clone();
            Utilities.reverseBytes(uBytes);
            BigInteger u = new BigInteger(1, uBytes);

            XECPublicKeySpec xecPublicKeySpec = new XECPublicKeySpec(
                    new NamedParameterSpec(namedGroup.name), u);
            KeyFactory factory = KeyFactory.getInstance(namedGroup.algorithm);
            XECPublicKey publicKey = (XECPublicKey) factory.generatePublic(
                    xecPublicKeySpec);

            return new XDHECredentials(publicKey, namedGroup);
        }
    }

    static final class XDHEPossession implements NamedGroupPossession {

        final PrivateKey privateKey;
        final XECPublicKey publicKey;
        final NamedGroup namedGroup;

        XDHEPossession(NamedGroup namedGroup, SecureRandom random) {
            try {
                KeyPairGenerator kpg
                        = KeyPairGenerator.getInstance(namedGroup.algorithm);
                kpg.initialize(namedGroup.keAlgParamSpec, random);
                KeyPair kp = kpg.generateKeyPair();
                privateKey = kp.getPrivate();
                publicKey = (XECPublicKey) kp.getPublic();
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(
                        "Could not generate XDH keypair", e);
            }

            this.namedGroup = namedGroup;
        }

        @Override
        public byte[] encode() {

            byte[] uBytes = ECUtil.trimZeroes(publicKey.getU().toByteArray());

            int expLength;
            switch (namedGroup) {
                case X25519:
                    expLength = 32;
                    break;
                case X448:
                    expLength = 56;
                    break;
                default:
                    throw new RuntimeException("Invalid XDH group");
            }

            if (uBytes.length > expLength) {
                throw new RuntimeException("Encoded XDH key too large");
            }

            if (uBytes.length != expLength) {
                byte[] tmp = new byte[expLength];
                System.arraycopy(uBytes, 0, tmp,
                        expLength - uBytes.length, uBytes.length);
                uBytes = tmp;
            }

            Utilities.reverseBytes(uBytes);
            return (uBytes);
        }

        @Override
        public PublicKey getPublicKey() {
            return publicKey;
        }

        @Override
        public NamedGroup getNamedGroup() {
            return namedGroup;
        }

        @Override
        public PrivateKey getPrivateKey() {
            return privateKey;
        }
    }

    private static final class XDHEKAGenerator
            implements SSLKeyAgreementGenerator {

        // Prevent instantiation of this class.
        private XDHEKAGenerator() {
            // blank
        }

        @Override
        public SSLKeyDerivation createKeyDerivation(
                HandshakeContext context) throws IOException {
            XDHEPossession xdhePossession = null;
            XDHECredentials xdheCredentials = null;
            for (SSLPossession poss : context.handshakePossessions) {
                if (!(poss instanceof XDHEPossession)) {
                    continue;
                }

                NamedGroup ng = ((XDHEPossession) poss).namedGroup;
                for (SSLCredentials cred : context.handshakeCredentials) {
                    if (!(cred instanceof XDHECredentials)) {
                        continue;
                    }
                    if (ng.equals(((XDHECredentials) cred).namedGroup)) {
                        xdheCredentials = (XDHECredentials) cred;
                        break;
                    }
                }

                if (xdheCredentials != null) {
                    xdhePossession = (XDHEPossession) poss;
                    break;
                }
            }

            if (xdhePossession == null || xdheCredentials == null) {
                context.conContext.fatal(Alert.HANDSHAKE_FAILURE,
                        "No sufficient XDHE key agreement "
                        + "parameters negotiated");
            }

            return new KAKeyDerivation("XDH", context,
                    xdhePossession.privateKey, xdheCredentials.popPublicKey);
        }
    }
}
