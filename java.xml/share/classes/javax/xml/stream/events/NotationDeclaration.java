package javax.xml.stream.events;
/**
 * An interface for handling Notation Declarations
 *
 * Receive notification of a notation declaration event.
 * It is up to the application to record the notation for later reference,
 * At least one of publicId and systemId must be non-null.
 * There is no guarantee that the notation declaration
 * will be reported before any unparsed entities that use it.
 *
 * @version 1.0
 * @author Copyright (c) 2009 by Oracle Corporation. All Rights Reserved.
 * @since 1.6
 */
public interface NotationDeclaration extends XMLEvent {
  /**
   * The notation name.
   * @return the notation name
   */
  String getName();

  /**
   * The notation's public identifier, or null if none was given.
   * @return the public identifier
   */
  String getPublicId();

  /**
   * The notation's system identifier, or null if none was given.
   * @return the system identifier
   */
  String getSystemId();
}
