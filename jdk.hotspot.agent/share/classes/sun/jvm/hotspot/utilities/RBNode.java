package sun.jvm.hotspot.utilities;

/** This class implements a node in a red-black tree. It provides
    accessors for the left and right children as well as the color of
    the node. */

public class RBNode {
  private Object data;
  private RBNode left;
  private RBNode right;
  private RBNode parent;
  private RBColor color;

  /** Newly-created nodes are colored red */
  public RBNode(Object data) {
    this.data  = data;
    color = RBColor.RED;
  }

  public Object getData() {
    return data;
  }

  /** Must copy all user-defined fields from the given node. For
      example, the base implementation copies the "data" field.
      However, it does not (and must not) copy the link fields
      (parent, left child, right child). It also does not need to copy
      any computed information for the node, as the node will be
      updated when necessary. Subclasses must be careful to call the
      superclass implementation. */
  public void copyFrom(RBNode arg) {
    this.data  = arg.data;
  }

  /** This is called by the base RBTree's insertion and deletion
      methods when necessary. Subclasses can use this to update any
      computed information based on the information in their left or
      right children. For multi-node updates it is guaranteed that
      this method will be called in the correct order. This should
      return true if an update actually occurred, false if not. */
  public boolean update() {
    return false;
  }

  public RBColor getColor()            { return color;         }
  public void setColor(RBColor color)  { this.color = color;   }

  public RBNode getParent()            { return parent;        }
  public void setParent(RBNode parent) { this.parent = parent; }

  /** Access to left child */
  public RBNode getLeft()              { return left;          }
  public void setLeft(RBNode left)     { this.left = left;     }

  /** Access to right child */
  public RBNode getRight()             { return right;         }
  public void setRight(RBNode right)   { this.right = right;   }
}
