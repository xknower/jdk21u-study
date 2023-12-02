package jdk.vm.ci.runtime;

import jdk.vm.ci.code.Architecture;

/**
 * Interface for accessing the {@link JVMCI} APIs supported by the runtime.
 */
public interface JVMCIRuntime {

    /**
     * Gets the default system compiler.
     */
    JVMCICompiler getCompiler();

    /**
     * Gets the host JVMCI backend.
     */
    JVMCIBackend getHostJVMCIBackend();

    /**
     * Gets the backend for a given architecture.
     *
     * @param arch a specific architecture class
     */
    <T extends Architecture> JVMCIBackend getJVMCIBackend(Class<T> arch);
}
