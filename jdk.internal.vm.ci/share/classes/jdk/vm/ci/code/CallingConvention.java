package jdk.vm.ci.code;

import static jdk.vm.ci.code.ValueUtil.isAllocatableValue;
import static jdk.vm.ci.code.ValueUtil.isStackSlot;
import jdk.vm.ci.meta.AllocatableValue;
import jdk.vm.ci.meta.Value;

/**
 * A calling convention describes the locations in which the arguments for a call are placed and the
 * location in which the return value is placed if the call is not void.
 */
public class CallingConvention {

    /**
     * Marker interface denoting the type of a call for which a calling convention is requested.
     */
    public interface Type {
    }

    /**
     * The amount of stack space (in bytes) required for the stack-based arguments of the call.
     */
    private final int stackSize;

    private final AllocatableValue returnLocation;

    /**
     * The ordered locations in which the arguments are placed.
     */
    private final AllocatableValue[] argumentLocations;

    /**
     * Creates a description of the registers and stack locations used by a call.
     *
     * @param stackSize amount of stack space (in bytes) required for the stack-based arguments of
     *            the call
     * @param returnLocation the location for the return value or {@link Value#ILLEGAL} if a void
     *            call
     * @param argumentLocations the ordered locations in which the arguments are placed
     */
    public CallingConvention(int stackSize, AllocatableValue returnLocation, AllocatableValue... argumentLocations) {
        assert argumentLocations != null;
        assert returnLocation != null;
        this.argumentLocations = argumentLocations;
        this.stackSize = stackSize;
        this.returnLocation = returnLocation;
        assert verify();
    }

    /**
     * Gets the location for the return value or {@link Value#ILLEGAL} if a void call.
     */
    public AllocatableValue getReturn() {
        return returnLocation;
    }

    /**
     * Gets the location for the {@code index}'th argument.
     */
    public AllocatableValue getArgument(int index) {
        return argumentLocations[index];
    }

    /**
     * Gets the amount of stack space (in bytes) required for the stack-based arguments of the call.
     */
    public int getStackSize() {
        return stackSize;
    }

    /**
     * Gets the number of locations required for the arguments.
     */
    public int getArgumentCount() {
        return argumentLocations.length;
    }

    /**
     * Gets the locations required for the arguments.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "FB false positive")
    public AllocatableValue[] getArguments() {
        if (argumentLocations.length == 0) {
            return argumentLocations;
        }
        return argumentLocations.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CallingConvention[");
        String sep = "";
        for (Value op : argumentLocations) {
            sb.append(sep).append(op);
            sep = ", ";
        }
        if (!returnLocation.equals(Value.ILLEGAL)) {
            sb.append(" -> ").append(returnLocation);
        }
        sb.append("]");
        return sb.toString();
    }

    private boolean verify() {
        for (int i = 0; i < argumentLocations.length; i++) {
            Value location = argumentLocations[i];
            assert isStackSlot(location) || isAllocatableValue(location);
        }
        return true;
    }
}
