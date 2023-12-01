package com.sun.org.apache.xerces.internal.impl.dv.dtd;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeValidator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * the factory to create/return built-in XML 1.1 DVs and create user-defined DVs
 *
 * @xerces.internal
 *
 * @author Neil Graham, IBM
 *
 */
public class XML11DTDDVFactoryImpl extends DTDDVFactoryImpl {

    static Map<String, DatatypeValidator> XML11BUILTINTYPES;
    static {
        Map<String, DatatypeValidator> xml11BuiltInTypes = new HashMap<>();
        xml11BuiltInTypes.put("XML11ID", new XML11IDDatatypeValidator());
        DatatypeValidator dvTemp = new XML11IDREFDatatypeValidator();
        xml11BuiltInTypes.put("XML11IDREF", dvTemp);
        xml11BuiltInTypes.put("XML11IDREFS", new ListDatatypeValidator(dvTemp));
        dvTemp = new XML11NMTOKENDatatypeValidator();
        xml11BuiltInTypes.put("XML11NMTOKEN", dvTemp);
        xml11BuiltInTypes.put("XML11NMTOKENS", new ListDatatypeValidator(dvTemp));
        XML11BUILTINTYPES = Collections.unmodifiableMap(xml11BuiltInTypes);
    } // <clinit>

    /**
     * return a dtd type of the given name
     * This will call the super class if and only if it does not
     * recognize the passed-in name.
     *
     * @param name  the name of the datatype
     * @return      the datatype validator of the given name
     */
    @Override
    public DatatypeValidator getBuiltInDV(String name) {
        if(XML11BUILTINTYPES.get(name) != null) {
            return XML11BUILTINTYPES.get(name);
        }
        return fBuiltInTypes.get(name);
    }

    /**
     * get all built-in DVs, which are stored in a Map keyed by the name
     * New XML 1.1 datatypes are inserted.
     *
     * @return      a Map which contains all datatypes
     */
    @Override
    public Map<String, DatatypeValidator> getBuiltInTypes() {
        final HashMap<String, DatatypeValidator> toReturn = new HashMap<>(fBuiltInTypes);
        toReturn.putAll(XML11BUILTINTYPES);
        return toReturn;
    }
}//XML11DTDDVFactoryImpl
