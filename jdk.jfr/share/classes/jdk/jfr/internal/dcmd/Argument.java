package jdk.jfr.internal.dcmd;

record Argument(
    String name,
    String description,
    String type,
    boolean mandatory,
    boolean option,
    String defaultValue,
    boolean allowMultiple
) { }
