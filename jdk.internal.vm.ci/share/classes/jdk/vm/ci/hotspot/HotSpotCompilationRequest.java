package jdk.vm.ci.hotspot;

import jdk.vm.ci.code.CompilationRequest;

/**
 * A compilation request with extra HotSpot specific context such as a compilation identifier and
 * the address of a {@code JVMCIEnv} object that provides native context for a compilation.
 */
public class HotSpotCompilationRequest extends CompilationRequest {
    /**
     * Address of the native {@code JVMCICompileState} associated with the request.
     */
    private final long compileState;

    /**
     * An identifier for the request.
     */
    private final int id;

    /**
     * Creates a request to compile a method starting at a given BCI and allocates an identifier to
     * the request.
     *
     * @param method the method to be compiled
     * @param entryBCI the bytecode index (BCI) at which to start compiling where -1 denotes the
     *            method's entry point
     * @param compileState address of a native {@code JVMCICompileState} object or 0L
     */
    public HotSpotCompilationRequest(HotSpotResolvedJavaMethod method, int entryBCI, long compileState) {
        this(method, entryBCI, compileState, method.allocateCompileId(entryBCI));
    }

    /**
     * Creates a request to compile a method starting at a given BCI.
     *
     * @param method the method to be compiled
     * @param entryBCI the bytecode index (BCI) at which to start compiling where -1 denotes the
     *            method's entry point
     * @param compileState address of a native {@code JVMCICompileState} object or 0L
     * @param id an identifier for the request
     */
    public HotSpotCompilationRequest(HotSpotResolvedJavaMethod method, int entryBCI, long compileState, int id) {
        super(method, entryBCI);
        this.compileState = compileState;
        this.id = id;
    }

    @Override
    public HotSpotResolvedJavaMethod getMethod() {
        return (HotSpotResolvedJavaMethod) super.getMethod();
    }

    /**
     * Gets the address of the native {@code JVMCICompileState} or 0L if no such object exists. This
     * method should really be named {@code getCompileState} but must remain as is for API
     * stability.
     */
    public long getJvmciEnv() {
        return compileState;
    }

    /**
     * Gets the VM allocated identifier for this compilation.
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ":" + super.toString();
    }
}
