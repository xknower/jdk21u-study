package com.sun.management.internal;

import com.sun.management.VMOption;
import com.sun.management.VMOption.Origin;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.OpenDataException;
import sun.management.LazyCompositeData;
import sun.management.MappedMXBeanType;

/**
 * A CompositeData for VMOption for the local management support.
 * This class avoids the performance penalty paid to the
 * construction of a CompositeData use in the local case.
 */
public class VMOptionCompositeData extends LazyCompositeData {
    @SuppressWarnings("serial") // Type of field is not Serializable
    private final VMOption option;

    private VMOptionCompositeData(VMOption option) {
        this.option = option;
    }

    public VMOption getVMOption() {
        return option;
    }

    public static CompositeData toCompositeData(VMOption option) {
        VMOptionCompositeData vcd = new VMOptionCompositeData(option);
        return vcd.getCompositeData();
    }

    protected CompositeData getCompositeData() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // vmOptionItemNames!
        final Object[] vmOptionItemValues = {
            option.getName(),
            option.getValue(),
            option.isWriteable(),
            option.getOrigin().toString(),
        };

        try {
            return new CompositeDataSupport(vmOptionCompositeType,
                                            vmOptionItemNames,
                                            vmOptionItemValues);
        } catch (OpenDataException e) {
            // Should never reach here
            throw new AssertionError(e);
        }
    }

    private static final CompositeType vmOptionCompositeType;
    static {
        try {
            vmOptionCompositeType = (CompositeType)
                MappedMXBeanType.toOpenType(VMOption.class);
        } catch (OpenDataException e) {
            // Should never reach here
            throw new AssertionError(e);
        }
    }

    static CompositeType getVMOptionCompositeType() {
        return vmOptionCompositeType;
    }

    private static final String NAME      = "name";
    private static final String VALUE     = "value";
    private static final String WRITEABLE = "writeable";
    private static final String ORIGIN    = "origin";

    private static final String[] vmOptionItemNames = {
        NAME,
        VALUE,
        WRITEABLE,
        ORIGIN,
    };

    public static String getName(CompositeData cd) {
        return getString(cd, NAME);
    }
    public static String getValue(CompositeData cd) {
        return getString(cd, VALUE);
    }
    public static Origin getOrigin(CompositeData cd) {
        String o = getString(cd, ORIGIN);
        return Enum.valueOf(Origin.class, o);
    }
    public static boolean isWriteable(CompositeData cd) {
        return getBoolean(cd, WRITEABLE);
    }

    /** Validate if the input CompositeData has the expected
     * CompositeType (i.e. contain all attributes with expected
     * names and types).
     */
    public static void validateCompositeData(CompositeData cd) {
        if (cd == null) {
            throw new NullPointerException("Null CompositeData");
        }

        if (!isTypeMatched(vmOptionCompositeType, cd.getCompositeType())) {
            throw new IllegalArgumentException(
                "Unexpected composite type for VMOption");
        }
    }

    private static final long serialVersionUID = -2395573975093578470L;
}
