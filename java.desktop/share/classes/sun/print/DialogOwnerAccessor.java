package sun.print;

import javax.print.attribute.standard.DialogOwner;

public abstract class DialogOwnerAccessor {

    public abstract long getOwnerID(DialogOwner owner);

    public static DialogOwnerAccessor accessor = null;

    public static void setAccessor(DialogOwnerAccessor acc) {
        accessor = acc;
    }

    public static long getID(DialogOwner owner) {
        if (accessor == null || owner == null) {
            return 0;
        } else {
            return accessor.getOwnerID(owner);
        }
    }
}
