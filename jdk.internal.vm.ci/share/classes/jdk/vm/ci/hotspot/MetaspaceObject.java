package jdk.vm.ci.hotspot;

/**
 * The marker interface for an object which wraps a HotSpot Metaspace object.
 */
interface MetaspaceObject {

    /**
     * Gets the raw pointer to the {@code Metaspace} object.
     *
     * @return a {@code Klass*}, {@code Method*} or {@code ConstantPool*} value
     */
    long getMetaspacePointer();
}
