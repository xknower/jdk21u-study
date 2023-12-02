package jdk.vm.ci.code;

/**
 * Represents a compiled instance of a method. It may have been invalidated or removed in the
 * meantime.
 */
public class InstalledCode {

    /**
     * Address of the entity (e.g., HotSpot {@code nmethod} or {@code RuntimeStub}) representing
     * this installed code.
     */
    protected long address;

    /**
     * Address of the entryPoint of this installed code.
     */
    protected long entryPoint;

    /**
     * Counts how often the address field was reassigned.
     */
    protected long version;

    protected final String name;

    public InstalledCode(String name) {
        this.name = name;
    }

    /**
     * @return the address of entity (e.g., HotSpot {@code nmethod} or {@code RuntimeStub})
     *         representing this installed code
     */
    public long getAddress() {
        return address;
    }

    /**
     * @return the address of the normal entry point of the installed code.
     */
    public long getEntryPoint() {
        return entryPoint;
    }

    /**
     * @return the version number of this installed code
     */
    public final long getVersion() {
        return version;
    }

    /**
     * Returns the name of this installed code.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the start address of this installed code if it is {@linkplain #isValid() valid}, 0
     * otherwise.
     */
    public long getStart() {
        return 0;
    }

    /**
     * @return true if the code represented by this object is still valid for invocation, false
     *         otherwise (may happen due to deopt, etc.)
     */
    public boolean isValid() {
        return entryPoint != 0;
    }

    /**
     * @return true if this object still points to installed code
     */
    public boolean isAlive() {
        return address != 0;
    }

    /**
     * Returns a copy of this installed code if it is {@linkplain #isValid() valid}, null otherwise.
     */
    public byte[] getCode() {
        return null;
    }

    /**
     * Equivalent to calling {@link #invalidate(boolean)} with a {@code true} argument.
     */
    public void invalidate() {
        invalidate(true);
    }

    /**
     * Invalidates this installed code such that any subsequent
     * {@linkplain #executeVarargs(Object...) invocation} will throw an
     * {@link InvalidInstalledCodeException}.
     *
     * If this installed code is already {@linkplain #isValid() invalid}, this method has no effect.
     * A subsequent call to {@link #isAlive()} or {@link #isValid()} on this object will return
     * {@code false}.
     *
     * @param deoptimize if {@code true}, all existing invocations will be immediately deoptimized.
     *            If {@code false}, any existing invocation will continue until it completes or
     *            there is a subsequent call to this method with {@code deoptimize == true} before
     *            the invocation completes.
     */
    public void invalidate(boolean deoptimize) {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the installed code with a variable number of arguments.
     *
     * @param args the array of object arguments
     * @return the value returned by the executed code
     */
    @SuppressWarnings("unused")
    public Object executeVarargs(Object... args) throws InvalidInstalledCodeException {
        throw new UnsupportedOperationException();
    }
}
