package sun.jvm.hotspot.ui.tree;

import java.io.*;
import sun.jvm.hotspot.oops.*;
import sun.jvm.hotspot.debugger.*;
import sun.jvm.hotspot.runtime.*;

/** An adapter class which allows oops to be displayed in a tree via
    the SimpleTreeNode interface. FIXME: must attach this to some sort
    of policy object which determines how to display names and whether
    VM fields should be shown. (Must also fix oop visitation mechanism
    in oops package.) */

public class OopTreeNodeAdapter extends FieldTreeNodeAdapter {
  private Oop oop;

  /** The oop may be null (for oop fields of oops which are null); the
      FieldIdentifier may also be null (for the root node).
      treeTableMode defaults to false. */
  public OopTreeNodeAdapter(Oop oop, FieldIdentifier id) {
    this(oop, id, false);
  }

  /** The oop may be null (for oop fields of oops which are null); the
      FieldIdentifier may also be null (for the root node). */
  public OopTreeNodeAdapter(Oop oop, FieldIdentifier id, boolean treeTableMode) {
    super(id, treeTableMode);
    this.oop = oop;
  }

  public Oop getOop() {
    return oop;
  }

  public int getChildCount() {
    if (oop == null) {
      return 0;
    }

    Counter c = new Counter();
    oop.iterate(c, true);
    return c.getNumFields() + (VM.getVM().getRevPtrs() == null ? 0 : 1);
  }

  public SimpleTreeNode getChild(int index) {
    if (oop == null) {
      return null;
    }
    if (VM.getVM().getRevPtrs() != null) {
      if (index == 0) {
        return new RevPtrsTreeNodeAdapter(oop, getTreeTableMode());
      } else {
        index -= 1;
      }
    }

    Fetcher f = new Fetcher(index);
    oop.iterate(f, true);
    return f.getChild();
  }

  public boolean isLeaf() {
    return (oop == null);
  }

  public int getIndexOfChild(SimpleTreeNode child) {
    if (child instanceof RevPtrsTreeNodeAdapter) {
      // assert(VM.getVM().getRevPtrs() != null, "Only created from revptrs");
      return 0;
    }
    FieldIdentifier id = ((FieldTreeNodeAdapter) child).getID();
    Finder f = new Finder(id);
    oop.iterate(f, true);
    return f.getIndex() + (VM.getVM().getRevPtrs() == null ? 0 : 1);
  }

  public String getValue() {
    if (oop != null) {
      // FIXME: choose style of printing depending on whether we're
      // displaying VM fields? Want to make Java objects look like
      // Java objects.
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      Oop.printOopValueOn(oop, new PrintStream(bos));
      return bos.toString();
    }
    return "null";
  }

  /** Should be applied to one oop at a time, then have the number of
      fields fetched. FIXME: want this to distinguish between VM and
      non-VM fields. */
  static class Counter extends DefaultOopVisitor {
    private int numFields;

    public int getNumFields() {
      return numFields;
    }

    public void prologue() {
      numFields = 0;
    }

    public void doMetadata(MetadataField field, boolean isVMField) { ++numFields; }
    public void doOop(OopField field, boolean isVMField)         { ++numFields; }
    public void doByte(ByteField field, boolean isVMField)       { ++numFields; }
    public void doChar(CharField field, boolean isVMField)       { ++numFields; }
    public void doBoolean(BooleanField field, boolean isVMField) { ++numFields; }
    public void doShort(ShortField field, boolean isVMField)     { ++numFields; }
    public void doInt(IntField field, boolean isVMField)         { ++numFields; }
    public void doLong(LongField field, boolean isVMField)       { ++numFields; }
    public void doFloat(FloatField field, boolean isVMField)     { ++numFields; }
    public void doDouble(DoubleField field, boolean isVMField)   { ++numFields; }
    public void doCInt(CIntField field, boolean isVMField)       { ++numFields; }
  }

  /** Creates a new SimpleTreeNode for the given field. FIXME: want
      this to distinguish between VM and non-VM fields. */
  class Fetcher extends DefaultOopVisitor {
    private int index;
    private int curField;
    private SimpleTreeNode child;

    public Fetcher(int index) {
      this.index = index;
    }

    public SimpleTreeNode getChild() {
      return child;
    }

    public void prologue() {
      curField = 0;
    }

    public void doMetadata(MetadataField field, boolean isVMField) {
      if (curField == index) {
        try {
          child = new MetadataTreeNodeAdapter(field.getValue(getObj()), field.getID(), getTreeTableMode());
        } catch (AddressException | UnknownOopException e) {
          child = new BadAddressTreeNodeAdapter(getObj().getHandle().getAddressAt(field.getOffset()), field, getTreeTableMode());
        }
      }
      ++curField;
    }

    public void doOop(OopField field, boolean isVMField) {
      if (curField == index) {
        try {
          child = new OopTreeNodeAdapter(field.getValue(getObj()), field.getID(), getTreeTableMode());
        } catch (AddressException | UnknownOopException e) {
          child = new BadAddressTreeNodeAdapter(field.getValueAsOopHandle(getObj()), field, getTreeTableMode());
        }
      }
      ++curField;
    }

    public void doByte(ByteField field, boolean isVMField) {
      if (curField == index) {
        child = new LongTreeNodeAdapter(field.getValue(getObj()) & 0xFF, field.getID(), getTreeTableMode());
      }
      ++curField;
    }

    public void doChar(CharField field, boolean isVMField) {
      if (curField == index) {
        child = new CharTreeNodeAdapter(field.getValue(getObj()), field.getID(), getTreeTableMode());
      }
      ++curField;
    }

    public void doBoolean(BooleanField field, boolean isVMField) {
      if (curField == index) {
        child = new BooleanTreeNodeAdapter(field.getValue(getObj()), field.getID(), getTreeTableMode());
      }
      ++curField;
    }

    public void doShort(ShortField field, boolean isVMField) {
      if (curField == index) {
        child = new LongTreeNodeAdapter(field.getValue(getObj()) & 0xFFFF, field.getID(), getTreeTableMode());
      }
      ++curField;
    }

    public void doInt(IntField field, boolean isVMField) {
      if (curField == index) {
        child = new LongTreeNodeAdapter(field.getValue(getObj()) & 0xFFFFFFFF, field.getID(), getTreeTableMode());
      }
      ++curField;
    }

    public void doLong(LongField field, boolean isVMField) {
      if (curField == index) {
        child = new LongTreeNodeAdapter(field.getValue(getObj()), field.getID(), getTreeTableMode());
      }
      ++curField;
    }

    public void doFloat(FloatField field, boolean isVMField) {
      if (curField == index) {
        child = new FloatTreeNodeAdapter(field.getValue(getObj()), field.getID(), getTreeTableMode());
      }
      ++curField;
    }

    public void doDouble(DoubleField field, boolean isVMField) {
      if (curField == index) {
        child = new DoubleTreeNodeAdapter(field.getValue(getObj()), field.getID(), getTreeTableMode());
      }
      ++curField;
    }

    public void doCInt(CIntField field, boolean isVMField) {
      if (curField == index) {
        child = new LongTreeNodeAdapter(field.getValue(getObj()), field.getID(), getTreeTableMode());
      }
      ++curField;
    }
  }

  /** Finds the index of the given FieldIdentifier. */
  static class Finder extends DefaultOopVisitor {
    private FieldIdentifier id;
    private int curField;
    private int index;

    public Finder(FieldIdentifier id) {
      this.id = id;
    }

    /** Returns -1 if not found */
    public int getIndex() {
      return index;
    }

    public void prologue() {
      curField = 0;
      index = -1;
    }

    public void doOop(OopField field, boolean isVMField)         { if (field.getID().equals(id)) { index = curField; } ++curField; }
    public void doByte(ByteField field, boolean isVMField)       { if (field.getID().equals(id)) { index = curField; } ++curField; }
    public void doChar(CharField field, boolean isVMField)       { if (field.getID().equals(id)) { index = curField; } ++curField; }
    public void doBoolean(BooleanField field, boolean isVMField) { if (field.getID().equals(id)) { index = curField; } ++curField; }
    public void doShort(ShortField field, boolean isVMField)     { if (field.getID().equals(id)) { index = curField; } ++curField; }
    public void doInt(IntField field, boolean isVMField)         { if (field.getID().equals(id)) { index = curField; } ++curField; }
    public void doLong(LongField field, boolean isVMField)       { if (field.getID().equals(id)) { index = curField; } ++curField; }
    public void doFloat(FloatField field, boolean isVMField)     { if (field.getID().equals(id)) { index = curField; } ++curField; }
    public void doDouble(DoubleField field, boolean isVMField)   { if (field.getID().equals(id)) { index = curField; } ++curField; }
    public void doCInt(CIntField field, boolean isVMField)       { if (field.getID().equals(id)) { index = curField; } ++curField; }
  }
}
