package jdk.dynalink.beans;

import java.lang.invoke.MethodHandle;
import jdk.dynalink.linker.GuardedInvocation;

/**
 * Represents one component for a GuardedInvocation of a potentially multi-namespace operation of an
 * {@link AbstractJavaLinker}. In addition to holding a guarded invocation, it holds semantic information about its
 * guard. All guards produced in the AbstractJavaLinker are either "Class.isInstance()" or "getClass() == clazz"
 * expressions. This allows choosing the most restrictive guard as the guard for the composition of two components.
 */
class GuardedInvocationComponent {
    enum ValidationType {
        NONE, // No guard; the operation can be linked unconditionally (quite rare); least strict.
        INSTANCE_OF, // "validatorClass.isInstance(obj)" guard
        EXACT_CLASS, // "obj.getClass() == validatorClass" guard; most strict.
        IS_ARRAY, // "obj.getClass().isArray()"
    }

    private final GuardedInvocation guardedInvocation;
    private final Validator validator;

    GuardedInvocationComponent(final MethodHandle invocation) {
        this(invocation, null, ValidationType.NONE);
    }

    GuardedInvocationComponent(final MethodHandle invocation, final MethodHandle guard, final ValidationType validationType) {
        this(invocation, guard, null, validationType);
    }

    GuardedInvocationComponent(final MethodHandle invocation, final MethodHandle guard, final Class<?> validatorClass,
            final ValidationType validationType) {
        this(invocation, guard, new Validator(validatorClass, validationType));
    }

    GuardedInvocationComponent(final GuardedInvocation guardedInvocation, final Class<?> validatorClass,
            final ValidationType validationType) {
        this(guardedInvocation, new Validator(validatorClass, validationType));
    }

    GuardedInvocationComponent replaceInvocation(final MethodHandle newInvocation) {
        return replaceInvocation(newInvocation, guardedInvocation.getGuard());
    }

    GuardedInvocationComponent replaceInvocation(final MethodHandle newInvocation, final MethodHandle newGuard) {
        return new GuardedInvocationComponent(guardedInvocation.replaceMethods(newInvocation,
                newGuard), validator);
    }

    private GuardedInvocationComponent(final MethodHandle invocation, final MethodHandle guard, final Validator validator) {
        this(new GuardedInvocation(invocation, guard), validator);
    }

    private GuardedInvocationComponent(final GuardedInvocation guardedInvocation, final Validator validator) {
        this.guardedInvocation = guardedInvocation;
        this.validator = validator;
    }

    GuardedInvocation getGuardedInvocation() {
        return guardedInvocation;
    }

    Class<?> getValidatorClass() {
        return validator.validatorClass;
    }

    ValidationType getValidationType() {
        return validator.validationType;
    }

    GuardedInvocationComponent compose(final MethodHandle compositeInvocation, final MethodHandle otherGuard,
            final Class<?> otherValidatorClass, final ValidationType otherValidationType) {
        final Validator compositeValidator = validator.compose(new Validator(otherValidatorClass, otherValidationType));
        final MethodHandle compositeGuard = compositeValidator == validator ? guardedInvocation.getGuard() : otherGuard;
        return new GuardedInvocationComponent(compositeInvocation, compositeGuard, compositeValidator);
    }

    private static class Validator {
        /*private*/ final Class<?> validatorClass;
        /*private*/ final ValidationType validationType;

        Validator(final Class<?> validatorClass, final ValidationType validationType) {
            this.validatorClass = validatorClass;
            this.validationType = validationType;
        }

        Validator compose(final Validator other) {
            if(other.validationType == ValidationType.NONE) {
                return this;
            }
            switch(validationType) {
                case NONE:
                    return other;
                case INSTANCE_OF:
                    switch(other.validationType) {
                        case INSTANCE_OF:
                            if(isAssignableFrom(other)) {
                                return other;
                            } else if(other.isAssignableFrom(this)) {
                                return this;
                            }
                            break;
                        case EXACT_CLASS:
                            if(isAssignableFrom(other)) {
                                return other;
                            }
                            break;
                        case IS_ARRAY:
                            if(validatorClass.isArray()) {
                                return this;
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case EXACT_CLASS:
                    switch(other.validationType) {
                        case INSTANCE_OF:
                            if(other.isAssignableFrom(this)) {
                                return this;
                            }
                            break;
                        case EXACT_CLASS:
                            if(validatorClass == other.validatorClass) {
                                return this;
                            }
                            break;
                        case IS_ARRAY:
                            if(validatorClass.isArray()) {
                                return this;
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                case IS_ARRAY:
                    switch(other.validationType) {
                        case INSTANCE_OF:
                        case EXACT_CLASS:
                            if(other.validatorClass.isArray()) {
                                return other;
                            }
                            break;
                        case IS_ARRAY:
                            return this;
                        default:
                            throw new AssertionError();
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            throw new AssertionError("Incompatible composition " + this + " vs " + other);
        }

        private boolean isAssignableFrom(final Validator other) {
            return validatorClass.isAssignableFrom(other.validatorClass);
        }

        @Override
        public String toString() {
            return "Validator[" + validationType + (validatorClass == null ? "" : (" " + validatorClass.getName())) + "]";
        }
    }
}
