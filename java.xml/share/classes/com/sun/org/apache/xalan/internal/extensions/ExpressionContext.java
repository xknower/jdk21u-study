package com.sun.org.apache.xalan.internal.extensions;

import javax.xml.transform.ErrorListener;

import com.sun.org.apache.xpath.internal.objects.XObject;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

/**
 * An object that implements this interface can supply
 * information about the current XPath expression context.
 */
public interface ExpressionContext
{

  /**
   * Get the current context node.
   * @return The current context node.
   */
  public Node getContextNode();

  /**
   * Get the current context node list.
   * @return An iterator for the current context list, as
   * defined in XSLT.
   */
  public NodeIterator getContextNodes();

  /**
   * Get the error listener.
   * @return The registered error listener.
   */
  public ErrorListener getErrorListener();

  /**
   * Get the value of a node as a number.
   * @param n Node to be converted to a number.  May be null.
   * @return value of n as a number.
   */
  public double toNumber(Node n);

  /**
   * Get the value of a node as a string.
   * @param n Node to be converted to a string.  May be null.
   * @return value of n as a string, or an empty string if n is null.
   */
  public String toString(Node n);

  /**
   * Get a variable based on it's qualified name.
   *
   * @param qname The qualified name of the variable.
   *
   * @return The evaluated value of the variable.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public XObject getVariableOrParam(com.sun.org.apache.xml.internal.utils.QName qname)
            throws javax.xml.transform.TransformerException;

  /**
   * Get the XPathContext that owns this ExpressionContext.
   *
   * Note: exslt:function requires the XPathContext to access
   * the variable stack and TransformerImpl.
   *
   * @return The current XPathContext.
   * @throws javax.xml.transform.TransformerException
   */
  public com.sun.org.apache.xpath.internal.XPathContext getXPathContext()
            throws javax.xml.transform.TransformerException;

}
