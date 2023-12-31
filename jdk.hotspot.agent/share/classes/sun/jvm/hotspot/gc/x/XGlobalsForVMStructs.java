package sun.jvm.hotspot.gc.x;

import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.runtime.VMObject;
import sun.jvm.hotspot.types.AddressField;
import sun.jvm.hotspot.types.Type;
import sun.jvm.hotspot.types.TypeDataBase;

class XGlobalsForVMStructs extends VMObject {
    private static AddressField XGlobalPhaseField;
    private static AddressField XGlobalSeqNumField;
    private static AddressField XAddressOffsetMaskField;
    private static AddressField XAddressMetadataMaskField;
    private static AddressField XAddressMetadataFinalizableField;
    private static AddressField XAddressGoodMaskField;
    private static AddressField XAddressBadMaskField;
    private static AddressField XAddressWeakBadMaskField;
    private static AddressField XObjectAlignmentSmallShiftField;
    private static AddressField XObjectAlignmentSmallField;

    static {
        VM.registerVMInitializedObserver((o, d) -> initialize(VM.getVM().getTypeDataBase()));
    }

    private static synchronized void initialize(TypeDataBase db) {
        Type type = db.lookupType("XGlobalsForVMStructs");

        XGlobalPhaseField = type.getAddressField("_XGlobalPhase");
        XGlobalSeqNumField = type.getAddressField("_XGlobalSeqNum");
        XAddressOffsetMaskField = type.getAddressField("_XAddressOffsetMask");
        XAddressMetadataMaskField = type.getAddressField("_XAddressMetadataMask");
        XAddressMetadataFinalizableField = type.getAddressField("_XAddressMetadataFinalizable");
        XAddressGoodMaskField = type.getAddressField("_XAddressGoodMask");
        XAddressBadMaskField = type.getAddressField("_XAddressBadMask");
        XAddressWeakBadMaskField = type.getAddressField("_XAddressWeakBadMask");
        XObjectAlignmentSmallShiftField = type.getAddressField("_XObjectAlignmentSmallShift");
        XObjectAlignmentSmallField = type.getAddressField("_XObjectAlignmentSmall");
    }

    XGlobalsForVMStructs(Address addr) {
        super(addr);
    }

    int XGlobalPhase() {
        return XGlobalPhaseField.getValue(addr).getJIntAt(0);
    }

    int XGlobalSeqNum() {
        return XGlobalSeqNumField.getValue(addr).getJIntAt(0);
    }

    long XAddressOffsetMask() {
        return XAddressOffsetMaskField.getValue(addr).getJLongAt(0);
    }

    long XAddressMetadataMask() {
        return XAddressMetadataMaskField.getValue(addr).getJLongAt(0);
    }

    long XAddressMetadataFinalizable() {
        return XAddressMetadataFinalizableField.getValue(addr).getJLongAt(0);
    }

    long XAddressGoodMask() {
        return XAddressGoodMaskField.getValue(addr).getJLongAt(0);
    }

    long XAddressBadMask() {
        return XAddressBadMaskField.getValue(addr).getJLongAt(0);
    }

    long XAddressWeakBadMask() {
        return XAddressWeakBadMaskField.getValue(addr).getJLongAt(0);
    }

    int XObjectAlignmentSmallShift() {
        return XObjectAlignmentSmallShiftField.getValue(addr).getJIntAt(0);
    }

    int XObjectAlignmentSmall() {
        return XObjectAlignmentSmallField.getValue(addr).getJIntAt(0);
    }
}
