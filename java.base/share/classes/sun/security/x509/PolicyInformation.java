package sun.security.x509;

import java.io.IOException;
import java.security.cert.PolicyQualifierInfo;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import sun.security.util.DerEncoder;
import sun.security.util.DerValue;
import sun.security.util.DerOutputStream;
/**
 * PolicyInformation is the class that contains a specific certificate policy
 * that is part of the CertificatePoliciesExtension. A
 * CertificatePolicyExtension value consists of a vector of these objects.
 * <p>
 * The ASN.1 syntax for PolicyInformation (IMPLICIT tagging is defined in the
 * module definition):
 * <pre>
 *
 * PolicyInformation ::= SEQUENCE {
 *      policyIdentifier   CertPolicyId,
 *      policyQualifiers   SEQUENCE SIZE (1..MAX) OF
 *                              PolicyQualifierInfo OPTIONAL }
 *
 * CertPolicyId ::= OBJECT IDENTIFIER
 *
 * PolicyQualifierInfo ::= SEQUENCE {
 *      policyQualifierId  PolicyQualifierId,
 *      qualifier          ANY DEFINED BY policyQualifierId }
 * </pre>
 *
 * @author Sean Mullan
 * @author Anne Anderson
 * @since       1.4
 */
public class PolicyInformation implements DerEncoder {

    // Attribute names
    public static final String NAME       = "PolicyInformation";
    public static final String ID         = "id";
    public static final String QUALIFIERS = "qualifiers";

    /* The policy OID */
    private CertificatePolicyId policyIdentifier;

    /* A Set of java.security.cert.PolicyQualifierInfo objects */
    private Set<PolicyQualifierInfo> policyQualifiers;

    /**
     * Create an instance of PolicyInformation
     *
     * @param policyIdentifier the policyIdentifier as a
     *          CertificatePolicyId
     * @param policyQualifiers a Set of PolicyQualifierInfo objects.
     *          Must not be NULL. Specify an empty Set for no qualifiers.
     * @exception IOException on decoding errors.
     */
    public PolicyInformation(CertificatePolicyId policyIdentifier,
            Set<PolicyQualifierInfo> policyQualifiers) throws IOException {
        if (policyQualifiers == null) {
            throw new NullPointerException("policyQualifiers is null");
        }
        this.policyQualifiers =
                new LinkedHashSet<>(policyQualifiers);
        this.policyIdentifier = Objects.requireNonNull(policyIdentifier);
    }

    /**
     * Create an instance of PolicyInformation, decoding from
     * the passed DerValue.
     *
     * @param val the DerValue to construct the PolicyInformation from.
     * @exception IOException on decoding errors.
     */
    public PolicyInformation(DerValue val) throws IOException {
        if (val.tag != DerValue.tag_Sequence) {
            throw new IOException("Invalid encoding of PolicyInformation");
        }
        policyIdentifier = new CertificatePolicyId(val.data.getDerValue());
        if (val.data.available() != 0) {
            policyQualifiers = new LinkedHashSet<>();
            DerValue opt = val.data.getDerValue();
            if (opt.tag != DerValue.tag_Sequence)
                throw new IOException("Invalid encoding of PolicyInformation");
            if (opt.data.available() == 0)
                throw new IOException("No data available in policyQualifiers");
            while (opt.data.available() != 0)
                policyQualifiers.add(new PolicyQualifierInfo
                        (opt.data.getDerValue().toByteArray()));
        } else {
            policyQualifiers = Collections.emptySet();
        }
    }

    /**
     * Compare this PolicyInformation with another object for equality
     *
     * @param other object to be compared with this
     * @return true iff the PolicyInformation objects match
     */
    public boolean equals(Object other) {
        if (!(other instanceof PolicyInformation piOther))
            return false;

        if (!policyIdentifier.equals(piOther.getPolicyIdentifier()))
            return false;

        return policyQualifiers.equals(piOther.getPolicyQualifiers());
    }

    /**
     * Returns the hash code for this PolicyInformation.
     *
     * @return a hash code value.
     */
    public int hashCode() {
        int myhash = 37 + policyIdentifier.hashCode();
        myhash = 37 * myhash + policyQualifiers.hashCode();
        return myhash;
    }

    /**
     * Return the policyIdentifier value
     *
     * @return The CertificatePolicyId object containing
     *     the policyIdentifier (not a copy).
     */
    public CertificatePolicyId getPolicyIdentifier() {
        return policyIdentifier;
    }

    /**
     * Return the policyQualifiers value
     *
     * @return a Set of PolicyQualifierInfo objects associated
     *    with this certificate policy (not a copy).
     *    Returns an empty Set if there are no qualifiers.
     *    Never returns null.
     */
    public Set<PolicyQualifierInfo> getPolicyQualifiers() {
        return policyQualifiers;
    }

    /**
     * Return a printable representation of the PolicyInformation.
     */
    public String toString() {
        return "  [" + policyIdentifier + policyQualifiers + "  ]\n";
    }

    /**
     * Write the PolicyInformation to the DerOutputStream.
     *
     * @param out the DerOutputStream to write the extension to.
     */
    @Override
    public void encode(DerOutputStream out) {
        DerOutputStream tmp = new DerOutputStream();
        policyIdentifier.encode(tmp);
        if (!policyQualifiers.isEmpty()) {
            DerOutputStream tmp2 = new DerOutputStream();
            for (PolicyQualifierInfo pq : policyQualifiers) {
                tmp2.writeBytes(pq.getEncoded());
            }
            tmp.write(DerValue.tag_Sequence, tmp2);
        }
        out.write(DerValue.tag_Sequence, tmp);
    }
}
