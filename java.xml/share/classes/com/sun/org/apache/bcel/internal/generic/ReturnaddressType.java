package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;

/**
 * Returnaddress, the type JSR or JSR_W instructions push upon the stack.
 *
 * see vmspec2 3.3.3
 */
public class ReturnaddressType extends Type {

    public static final ReturnaddressType NO_TARGET = new ReturnaddressType();
    private InstructionHandle returnTarget;

    /**
     * A Returnaddress [that doesn't know where to return to].
     */
    private ReturnaddressType() {
        super(Const.T_ADDRESS, "<return address>");
    }

    /**
     * Creates a ReturnaddressType object with a target.
     */
    public ReturnaddressType(final InstructionHandle returnTarget) {
        super(Const.T_ADDRESS, "<return address targeting " + returnTarget + ">");
        this.returnTarget = returnTarget;
    }

    /**
     * Returns if the two Returnaddresses refer to the same target.
     */
    @Override
    public boolean equals(final Object rat) {
        if (!(rat instanceof ReturnaddressType)) {
            return false;
        }
        final ReturnaddressType that = (ReturnaddressType) rat;
        if (this.returnTarget == null || that.returnTarget == null) {
            return that.returnTarget == this.returnTarget;
        }
        return that.returnTarget.equals(this.returnTarget);
    }

    /**
     * @return the target of this ReturnaddressType
     */
    public InstructionHandle getTarget() {
        return returnTarget;
    }

    /**
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        if (returnTarget == null) {
            return 0;
        }
        return returnTarget.hashCode();
    }
}
