package jdk.vm.ci.code;

import jdk.vm.ci.code.CallingConvention.Type;
import jdk.vm.ci.meta.JavaKind;
import jdk.vm.ci.meta.JavaType;
import jdk.vm.ci.meta.PlatformKind;
import jdk.vm.ci.meta.ValueKind;

/**
 * A register configuration binds roles and {@linkplain RegisterAttributes attributes} to physical
 * registers.
 */
public interface RegisterConfig {

    /**
     * Gets the register to be used for returning a value of a given kind.
     */
    Register getReturnRegister(JavaKind kind);

    /**
     * Gets the maximum allowed size of the frame.
     */
    default int getMaximumFrameSize() {
        return Integer.MAX_VALUE;
    }

    /**
     * Gets the register used as the frame pointer. Spill slots and outgoing stack-based arguments
     * are addressed relative to this register.
     */
    Register getFrameRegister();

    /**
     * Gets the calling convention describing how arguments are passed.
     *
     * @param type the type of calling convention being requested
     * @param returnType the return type (can be null for methods returning {@code void})
     * @param parameterTypes the types of the arguments of the call
     * @param valueKindFactory the factory to create custom {@link ValueKind ValueKinds}
     */
    CallingConvention getCallingConvention(Type type, JavaType returnType, JavaType[] parameterTypes, ValueKindFactory<?> valueKindFactory);

    /**
     * Gets the ordered set of registers that are can be used to pass parameters according to a
     * given calling convention.
     *
     * @param type the type of calling convention
     * @param kind specifies what kind of registers is being requested
     * @return the ordered set of registers that may be used to pass parameters in a call conforming
     *         to {@code type}
     */
    RegisterArray getCallingConventionRegisters(Type type, JavaKind kind);

    /**
     * Gets the set of all registers that might be used by the register allocator.
     */
    RegisterArray getAllocatableRegisters();

    /**
     * Filters a set of registers and returns only those that can be used by the register allocator
     * for a value of a particular kind.
     */
    RegisterArray filterAllocatableRegisters(PlatformKind kind, RegisterArray registers);

    /**
     * Gets the registers whose values must be preserved by a method across any call it makes.
     */
    RegisterArray getCallerSaveRegisters();

    /**
     * Gets the registers whose values must be preserved by the callee.
     */
    RegisterArray getCalleeSaveRegisters();

    /**
     * Gets a map from register {@linkplain Register#number numbers} to register
     * {@linkplain RegisterAttributes attributes} for this register configuration.
     *
     * @return an array where an element at index i holds the attributes of the register whose
     *         number is i
     */
    RegisterAttributes[] getAttributesMap();

    /**
     * Determines if all {@link #getAllocatableRegisters() allocatable} registers are
     * {@link #getCallerSaveRegisters() caller saved}.
     */
    boolean areAllAllocatableRegistersCallerSaved();
}
