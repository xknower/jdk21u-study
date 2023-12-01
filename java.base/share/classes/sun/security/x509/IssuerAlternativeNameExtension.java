package sun.security.x509;

import java.io.IOException;

import sun.security.util.*;

/**
 * This represents the Issuer Alternative Name Extension.
 *
 * This extension, if present, allows the issuer to specify multiple
 * alternative names.
 *
 * <p>Extensions are represented as a sequence of the extension identifier
 * (Object Identifier), a boolean flag stating whether the extension is to
 * be treated as being critical and the extension value itself (this is again
 * a DER encoding of the extension value).
 *
 * @author Amit Kapoor
 * @author Hemma Prafullchandra
 * @see Extension
 */
public class IssuerAlternativeNameExtension extends Extension {

    public static final String NAME = "IssuerAlternativeName";

    // private data members
    GeneralNames names;

    // Encode this extension
    private void encodeThis() {
        if (names == null || names.isEmpty()) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream os = new DerOutputStream();
        names.encode(os);
        this.extensionValue = os.toByteArray();
    }

    /**
     * Create a IssuerAlternativeNameExtension with the passed GeneralNames.
     *
     * @param names the GeneralNames for the issuer.
     */
    public IssuerAlternativeNameExtension(GeneralNames names) {
        this(false, names);
    }

    /**
     * Create a IssuerAlternativeNameExtension with the passed criticality
     * and GeneralNames.
     *
     * @param critical true if the extension is to be treated as critical.
     * @param names the GeneralNames for the issuer, cannot be null or empty.
     */
    public IssuerAlternativeNameExtension(Boolean critical, GeneralNames names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("names cannot be null or empty");
        }
        this.names = names;
        this.extensionId = PKIXExtensions.IssuerAlternativeName_Id;
        this.critical = critical.booleanValue();
        encodeThis();
    }

    /**
     * Create the extension from the passed DER encoded value.
     *
     * @param critical true if the extension is to be treated as critical.
     * @param value an array of DER encoded bytes of the actual value.
     * @exception ClassCastException if value is not an array of bytes
     * @exception IOException on error.
     */
    public IssuerAlternativeNameExtension(Boolean critical, Object value)
    throws IOException {
        this.extensionId = PKIXExtensions.IssuerAlternativeName_Id;
        this.critical = critical.booleanValue();
        this.extensionValue = (byte[]) value;
        DerValue val = new DerValue(this.extensionValue);
        if (val.data == null) {
            names = new GeneralNames();
            return;
        }

        names = new GeneralNames(val);
    }

    /**
     * Returns a printable representation of the IssuerAlternativeName.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
            .append("IssuerAlternativeName [\n");
        if (names == null) {
            sb.append("  null\n");
        } else {
            for (GeneralName name : names.names()) {
                sb.append("  ")
                    .append(name)
                    .append('\n');
            }
        }
        sb.append("]\n");
        return sb.toString();
    }

    /**
     * Write the extension to the OutputStream.
     *
     * @param out the DerOutputStream to write the extension to.
     */
    @Override
    public void encode(DerOutputStream out) {
        if (extensionValue == null) {
            extensionId = PKIXExtensions.IssuerAlternativeName_Id;
            critical = false;
            encodeThis();
        }
        super.encode(out);
    }

    public GeneralNames getNames() {
        return names;
    }

    /**
     * Return the name of this extension.
     */
    @Override
    public String getName() {
        return NAME;
    }
}
