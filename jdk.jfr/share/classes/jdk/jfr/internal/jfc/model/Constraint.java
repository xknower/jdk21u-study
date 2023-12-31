package jdk.jfr.internal.jfc.model;

record Constraint(Class<? extends XmlElement> type, int min, int max) {

    public static Constraint any(Class<? extends XmlElement> type) {
        return new Constraint(type, 0, Integer.MAX_VALUE);
    }

    public static Constraint atLeast(Class<? extends XmlElement> type, int min) {
        return new Constraint(type, min, Integer.MAX_VALUE);
    }
}
