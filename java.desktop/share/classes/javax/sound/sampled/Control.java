package javax.sound.sampled;

/**
 * {@link Line Lines} often have a set of controls, such as gain and pan, that
 * affect the audio signal passing through the line. Java Sound's {@code Line}
 * objects let you obtain a particular control object by passing its class as
 * the argument to a {@link Line#getControl(Control.Type) getControl} method.
 * <p>
 * Because the various types of controls have different purposes and features,
 * all of their functionality is accessed from the subclasses that define each
 * kind of control.
 *
 * @author Kara Kytle
 * @see Line#getControls
 * @see Line#isControlSupported
 * @since 1.3
 */
public abstract class Control {

    /**
     * The control type.
     */
    private final Type type;

    /**
     * Constructs a control with the specified type.
     *
     * @param  type the kind of control desired
     */
    protected Control(Type type) {
        this.type = type;
    }

    /**
     * Obtains the control's type.
     *
     * @return the control's type
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns a string representation of the control.
     *
     * @return a string representation of the control
     */
    @Override
    public String toString() {
        return String.format("%s control", getType());
    }

    /**
     * An instance of the {@code Type} class represents the type of the control.
     */
    public static class Type {

        /**
         * Type name.
         */
        private final String name;

        /**
         * Constructs a new control type with the name specified. The name
         * should be a descriptive string appropriate for labelling the control
         * in an application, such as "Gain" or "Balance".
         *
         * @param  name the name of the new control type
         */
        protected Type(String name) {
            this.name = name;
        }

        /**
         * Indicates whether the specified object is equal to this control type,
         * returning {@code true} if the objects are the same.
         *
         * @param  obj the reference object with which to compare
         * @return {@code true} if the specified object is equal to this control
         *         type; {@code false} otherwise
         */
        @Override
        public final boolean equals(Object obj) {
            return super.equals(obj);
        }

        /**
         * Returns a hash code value for this control type.
         *
         * @return a hash code value for this control type
         */
        @Override
        public final int hashCode() {
            return super.hashCode();
        }

        /**
         * Returns type's name as the string representation of the control type.
         *
         * @return a string representation of the control type
         */
        @Override
        public final String toString() {
            return name;
        }
    }
}
