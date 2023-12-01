package jdk.jfr.events;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.internal.Type;

@Name(Type.EVENT_NAME_PREFIX + "JavaExceptionThrow")
@Label("Java Exception")
@Category("Java Application")
@Description("An object derived from java.lang.Exception has been created")
public final class ExceptionThrownEvent extends AbstractJDKEvent {

    // The order of these fields must be the same as the parameters in
    // commit(..., String, Class)

    @Label("Message")
    public String message;

    @Label("Class")
    public Class<?> thrownClass;

    public static void commit(long start, long duration, String message, Class<? extends Throwable> thrownClass) {
        // Generated
    }
}
