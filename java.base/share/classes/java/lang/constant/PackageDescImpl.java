package java.lang.constant;

/*
 * Implementation of {@code PackageDesc}
 * @param internalName must have been validated
 */
record PackageDescImpl(String internalName) implements PackageDesc {

    @Override
    public String toString() {
        return String.format("PackageDesc[%s]", name());
    }
}
