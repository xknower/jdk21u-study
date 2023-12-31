/**
 * A Java API for Stream Control Transport Protocol.
 *
 * <P> The Stream Control Transport Protocol (SCTP) is a reliable,
 * message-oriented, transport protocol existing at an equivalent level with UDP
 * (User Datagram Protocol) and TCP (Transmission Control Protocol). SCTP is
 * session oriented and an association between the endpoints must be established
 * before any data can be transmitted.
 *
 * <P> SCTP has direct support for multi-homing, meaning than an endpoint may be
 * represented by more than one address and each address may be used for sending
 * and receiving data, thus providing network redundancy. The connection between
 * two endpoints is referred to as an association between those endpoints.
 * Endpoints can exchange a list of addresses during association setup. One
 * address is designated as the primary address, this is the default address that
 * the peer will use for sending data. A single port number is used across the
 * entire address list at an endpoint for a specific session.
 *
 * <P> SCTP is message based. I/O operations operate upon messages and message
 * boundaries are preserved. Each association may support multiple independent
 * logical streams. Each stream represents a sequence of messages within a single
 * association and streams are independent of one another, meaning that stream
 * identifiers and sequence numbers are included in the data packet to allow
 * sequencing of messages on a per-stream basis.
 *
 * <P> This package provides two programming model styles. The one-to-one style
 * supported by {@link com.sun.nio.sctp.SctpChannel} and {@link
 * com.sun.nio.sctp.SctpServerChannel}, and the one-to-many
 * style supported by {@link com.sun.nio.sctp.SctpMultiChannel}.
 * The semantics of the one-to-one style interface are very similar to TCP.
 * An {@code SctpChannel} can only control one SCTP association. The
 * semantics of the one-to-many style interface are very similar to UDP. An
 * {@code SctpMutliChannel} can control multiple SCTP associations.
 *
 * <P> Applications can send and receive per-message ancillary information through
 * {@link com.sun.nio.sctp.MessageInfo}. For example, the stream number that
 * the message it is to be sent or received from. The SCTP stack is event driven
 * and applications can receive notifications of certain SCTP events by invoking
 * the {@code receive} method of the SCTP channel with an appropriate {@link
 * com.sun.nio.sctp.NotificationHandler notification handler}.
 *
 * <P> The SCTP protocol is defined by
 * <A HREF="https://tools.ietf.org/html/rfc4960">RFC4960</A>, and the optional
 * extension for <I>Dynamic Address Reconfiguration</I> is defined by
 * <A HREF="https://tools.ietf.org/html/rfc5061">RFC5061</A>.
 *
 * @since 1.7
 */
package com.sun.nio.sctp;
