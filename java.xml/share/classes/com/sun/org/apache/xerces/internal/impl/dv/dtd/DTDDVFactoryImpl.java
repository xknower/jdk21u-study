package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.DTDDVFactory;
import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * the factory to create/return built-in schema DVs and create user-defined DVs
 *
 * @xerces.internal
 *
 * @author Sandy Gao, IBM
 *
 */
public class DTDDVFactoryImpl extends DTDDVFactory {

    static final Map<String, DatatypeValidator> fBuiltInTypes;
    static {
        Map<String, DatatypeValidator> builtInTypes = new HashMap<>();
        DatatypeValidator dvTemp;

        builtInTypes.put("string", new StringDatatypeValidator());
        builtInTypes.put("ID", new IDDatatypeValidator());
        dvTemp = new IDREFDatatypeValidator();
        builtInTypes.put("IDREF", dvTemp);
        builtInTypes.put("IDREFS", new ListDatatypeValidator(dvTemp));
        dvTemp = new ENTITYDatatypeValidator();
        builtInTypes.put("ENTITY", new ENTITYDatatypeValidator());
        builtInTypes.put("ENTITIES", new ListDatatypeValidator(dvTemp));
        builtInTypes.put("NOTATION", new NOTATIONDatatypeValidator());
        dvTemp = new NMTOKENDatatypeValidator();
        builtInTypes.put("NMTOKEN", dvTemp);
        builtInTypes.put("NMTOKENS", new ListDatatypeValidator(dvTemp));

        fBuiltInTypes = Collections.unmodifiableMap(builtInTypes);
    }

    /**
     * return a dtd type of the given name
     *
     * @param name  the name of the datatype
     * @return      the datatype validator of the given name
     */
    @Override
    public DatatypeValidator getBuiltInDV(String name) {
        return fBuiltInTypes.get(name);
    }

    /**
     * get all built-in DVs, which are stored in a Map keyed by the name
     *
     * @return      a Map which contains all datatypes
     */
    @Override
    public Map<String, DatatypeValidator> getBuiltInTypes() {
        return new HashMap<>(fBuiltInTypes);
    }

}// DTDDVFactoryImpl
