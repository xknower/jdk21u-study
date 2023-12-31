package com.sun.jndi.dns;


import javax.naming.*;


/**
 * The Header class represents the header of a DNS message.
 *
 * @author Scott Seligman
 */


class Header {

    static final int HEADER_SIZE = 12;  // octets in a DNS header

    // Masks and shift amounts for DNS header flag fields.
    static final short QR_BIT =         (short) 0x8000;
    static final short OPCODE_MASK =    (short) 0x7800;
    static final int   OPCODE_SHIFT =   11;
    static final short AA_BIT =         (short) 0x0400;
    static final short TC_BIT =         (short) 0x0200;
    static final short RD_BIT =         (short) 0x0100;
    static final short RA_BIT =         (short) 0x0080;
    static final short RCODE_MASK =     (short) 0x000F;

    int xid;                    // ID:  16-bit query identifier
    boolean query;              // QR:  true if query, false if response
    int opcode;                 // OPCODE:  4-bit opcode
    boolean authoritative;      // AA
    boolean truncated;          // TC
    boolean recursionDesired;   // RD
    boolean recursionAvail;     // RA
    int rcode;                  // RCODE:  4-bit response code
    int numQuestions;
    int numAnswers;
    int numAuthorities;
    int numAdditionals;

    /*
     * Returns a representation of a decoded DNS message header.
     * Does not modify or store a reference to the msg array.
     */
    Header(byte[] msg, int msgLen) throws NamingException {
        decode(msg, msgLen);
    }

    /*
     * Decodes a DNS message header.  Does not modify or store a
     * reference to the msg array.
     */
    private void decode(byte[] msg, int msgLen) throws NamingException {

        try {
            int pos = 0;        // current offset into msg

            if (msgLen < HEADER_SIZE) {
                throw new CommunicationException(
                        "DNS error: corrupted message header");
            }

            xid = getShort(msg, pos);
            pos += 2;

            // Flags
            short flags = (short) getShort(msg, pos);
            pos += 2;
            query = (flags & QR_BIT) == 0;
            opcode = (flags & OPCODE_MASK) >>> OPCODE_SHIFT;
            authoritative = (flags & AA_BIT) != 0;
            truncated = (flags & TC_BIT) != 0;
            recursionDesired = (flags & RD_BIT) != 0;
            recursionAvail = (flags & RA_BIT) != 0;
            rcode = (flags & RCODE_MASK);

            // RR counts
            numQuestions = getShort(msg, pos);
            pos += 2;
            numAnswers = getShort(msg, pos);
            pos += 2;
            numAuthorities = getShort(msg, pos);
            pos += 2;
            numAdditionals = getShort(msg, pos);
            pos += 2;

        } catch (IndexOutOfBoundsException e) {
            throw new CommunicationException(
                    "DNS error: corrupted message header");
        }
    }

    /*
     * Returns the 2-byte unsigned value at msg[pos].  The high
     * order byte comes first.
     */
    private static int getShort(byte[] msg, int pos) {
        return (((msg[pos] & 0xFF) << 8) |
                (msg[pos + 1] & 0xFF));
    }
}
