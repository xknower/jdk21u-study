package com.sun.org.apache.bcel.internal.generic;

/**
 * Thrown by InstructionList.remove() when one or multiple disposed instructions are still being referenced by an
 * InstructionTargeter object. I.e. the InstructionTargeter has to be notified that (one of) the InstructionHandle it is
 * referencing is being removed from the InstructionList and thus not valid anymore.
 *
 * <p>
 * Making this an exception instead of a return value forces the user to handle these case explicitly in a try { ... }
 * catch. The following code illustrates how this may be done:
 * </p>
 *
 * <PRE>
 *     ...
 *     try {
 *         il.delete(start_ih, end_ih);
 *     } catch(TargetLostException e) {
 *         for (InstructionHandle target : e.getTargets()) {
 *             for (InstructionTargeter targeter : target.getTargeters()) {
 *                 targeter.updateTarget(target, new_target);
 *             }
 *         }
 *     }
 * </PRE>
 *
 * @see InstructionHandle
 * @see InstructionList
 * @see InstructionTargeter
 * @LastModified: Feb 2023
 */
public final class TargetLostException extends Exception {

    private static final long serialVersionUID = -6857272667645328384L;
    @SuppressWarnings("serial") // Array component type is not Serializable
    private final InstructionHandle[] targets;

    TargetLostException(final InstructionHandle[] t, final String mesg) {
        super(mesg);
        targets = t;
    }

    /**
     * @return list of instructions still being targeted.
     */
    public InstructionHandle[] getTargets() {
        return targets;
    }
}
