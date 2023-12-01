package java.lang.constant;

/*
 * Implementation of {@code ModuleDesc}
 * @param name must have been validated
 */
record ModuleDescImpl(String name) implements ModuleDesc {

    @Override
    public String toString() {
        return String.format("ModuleDesc[%s]", name());
    }
}
