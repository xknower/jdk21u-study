package sun.jvm.hotspot.ui.tree;

import sun.jvm.hotspot.oops.*;

/** Encapsulates a boolean value in a tree handled by SimpleTreeModel */

public class BooleanTreeNodeAdapter extends FieldTreeNodeAdapter {
  private boolean val;

  public BooleanTreeNodeAdapter(boolean val, FieldIdentifier id) {
    this(val, id, false);
  }

  public BooleanTreeNodeAdapter(boolean val, FieldIdentifier id, boolean treeTableMode) {
    super(id, treeTableMode);
    this.val = val;
  }

  public int getChildCount() {
    return 0;
  }

  public SimpleTreeNode getChild(int index) {
    return null;
  }

  public boolean isLeaf() {
    return true;
  }

  public int getIndexOfChild(SimpleTreeNode child) {
    return 0;
  }

  public String getValue() {
    return (val ? "true" : "false");
  }
}
