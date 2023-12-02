package jdk.vm.ci.code.site;

/**
 * A reason for infopoint insertion.
 */
public enum InfopointReason {

    SAFEPOINT,
    CALL,
    IMPLICIT_EXCEPTION,
    METHOD_START,
    METHOD_END,
    BYTECODE_POSITION;
}
