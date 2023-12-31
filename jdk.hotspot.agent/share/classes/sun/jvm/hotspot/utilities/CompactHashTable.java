package sun.jvm.hotspot.utilities;

import java.util.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.types.*;
import sun.jvm.hotspot.runtime.*;
import sun.jvm.hotspot.utilities.*;
import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

public class CompactHashTable extends VMObject {
  static {
    VM.registerVMInitializedObserver(new Observer() {
      public void update(Observable o, Object data) {
        initialize(VM.getVM().getTypeDataBase());
      }
    });
  }

  private static synchronized void initialize(TypeDataBase db) throws WrongTypeException {
    Type type = db.lookupType("SymbolCompactHashTable");
    baseAddressField = type.getAddressField("_base_address");
    bucketCountField = type.getCIntegerField("_bucket_count");
    entryCountField = type.getCIntegerField("_entry_count");
    bucketsField = type.getAddressField("_buckets");
    entriesField = type.getAddressField("_entries");
    uintSize = db.lookupType("u4").getSize();
  }

  // Fields
  private static CIntegerField bucketCountField;
  private static CIntegerField entryCountField;
  private static AddressField  baseAddressField;
  private static AddressField  bucketsField;
  private static AddressField  entriesField;
  private static long uintSize;

  private static int BUCKET_OFFSET_MASK = 0x3FFFFFFF;
  private static int BUCKET_TYPE_SHIFT = 30;
  private static int VALUE_ONLY_BUCKET_TYPE = 1;

  public CompactHashTable(Address addr) {
    super(addr);
  }

  private int bucketCount() {
    return (int)bucketCountField.getValue(addr);
  }

  private boolean isValueOnlyBucket(int bucket_info) {
    return (bucket_info >> BUCKET_TYPE_SHIFT) == VALUE_ONLY_BUCKET_TYPE;
  }

  private int bucketOffset(int bucket_info) {
    return bucket_info & BUCKET_OFFSET_MASK;
  }

  public Symbol probe(byte[] name, long hash) {
    if (bucketCount() <= 0) {
      // This CompactHashTable is not in use
      return null;
    }

    long    symOffset;
    Symbol  sym;
    Address baseAddress = baseAddressField.getValue(addr);
    Address bucket = bucketsField.getValue(addr);
    long index = hash % bucketCount();
    int bucketInfo = (int)bucket.getCIntegerAt(index * uintSize, uintSize, true);
    int bucketOffset = bucketOffset(bucketInfo);
    int nextBucketInfo = (int)bucket.getCIntegerAt((index+1) * uintSize, uintSize, true);
    int nextBucketOffset = bucketOffset(nextBucketInfo);

    Address entry = entriesField.getValue(addr).addOffsetTo(bucketOffset * uintSize);

    if (isValueOnlyBucket(bucketInfo)) {
      symOffset = entry.getCIntegerAt(0, uintSize, true);
      sym = Symbol.create(baseAddress.addOffsetTo(symOffset));
      if (sym.equals(name)) {
        return sym;
      }
    } else {
      Address entryMax = entriesField.getValue(addr).addOffsetTo(nextBucketOffset * uintSize);
      while (entry.lessThan(entryMax)) {
        long symHash = entry.getCIntegerAt(0, uintSize, true);
        if (symHash == hash) {
          symOffset = entry.getCIntegerAt(uintSize, uintSize, true);
          Address symAddr = baseAddress.addOffsetTo(symOffset);
          sym = Symbol.create(symAddr);
          if (sym.equals(name)) {
            return sym;
          }
        }
        entry = entry.addOffsetTo(2 * uintSize);
      }
    }
    return null;
  }

  public interface SymbolVisitor {
    public void visit(Symbol sym);
  }

  public void symbolsDo(SymbolVisitor visitor) {
    long    symOffset;
    Symbol  sym;
    Address baseAddress = baseAddressField.getValue(addr);
    Address bucket = bucketsField.getValue(addr);
    for (long index = 0; index < bucketCount(); index++) {
      int bucketInfo = (int)bucket.getCIntegerAt(index * uintSize, uintSize, true);
      int bucketOffset = bucketOffset(bucketInfo);
      int nextBucketInfo = (int)bucket.getCIntegerAt((index+1) * uintSize, uintSize, true);
      int nextBucketOffset = bucketOffset(nextBucketInfo);

      Address entry = entriesField.getValue(addr).addOffsetTo(bucketOffset * uintSize);

      if (isValueOnlyBucket(bucketInfo)) {
        symOffset = entry.getCIntegerAt(0, uintSize, true);
        sym = Symbol.create(baseAddress.addOffsetTo(symOffset));
        visitor.visit(sym);
      } else {
        Address entryMax = entriesField.getValue(addr).addOffsetTo(nextBucketOffset * uintSize);
        while (entry.lessThan(entryMax)) {
          symOffset = entry.getCIntegerAt(uintSize, uintSize, true);
          Address symAddr = baseAddress.addOffsetTo(symOffset);
          sym = Symbol.create(symAddr);
          visitor.visit(sym);
          entry = entry.addOffsetTo(2 * uintSize);
        }
      }
    }
  }
}

