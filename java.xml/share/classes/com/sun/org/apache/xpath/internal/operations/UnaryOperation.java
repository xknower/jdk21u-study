package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;

/**
 * The unary operation base class.
 *
 * @LastModified: Oct 2017
 */
public abstract class UnaryOperation extends Expression implements ExpressionOwner
{
    static final long serialVersionUID = 6536083808424286166L;

  /** The operand for the operation.
   *  @serial */
  protected Expression m_right;

  /**
   * This function is used to fixup variables from QNames to stack frame
   * indexes at stylesheet build time.
   * @param vars List of QNames that correspond to variables.  This list
   * should be searched backwards for the first qualified name that
   * corresponds to the variable reference qname.  The position of the
   * QName in the vector from the start of the vector will be its position
   * in the stack frame (but variables above the globalsTop value will need
   * to be offset to the current stack frame).
   */
  public void fixupVariables(List<QName> vars, int globalsSize)
  {
    m_right.fixupVariables(vars, globalsSize);
  }

  /**
   * Tell if this expression or it's subexpressions can traverse outside
   * the current subtree.
   *
   * @return true if traversal outside the context node's subtree can occur.
   */
  public boolean canTraverseOutsideSubtree()
  {

    if (null != m_right && m_right.canTraverseOutsideSubtree())
      return true;

    return false;
  }

  /**
   * Set the expression operand for the operation.
   *
   *
   * @param r The expression operand to which the unary operation will be
   *          applied.
   */
  public void setRight(Expression r)
  {
    m_right = r;
    r.exprSetParent(this);
  }

  /**
   * Execute the operand and apply the unary operation to the result.
   *
   *
   * @param xctxt The runtime execution context.
   *
   * @return An XObject that represents the result of applying the unary
   *         operation to the evaluated operand.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {

    return operate(m_right.execute(xctxt));
  }

  /**
   * Apply the operation to two operands, and return the result.
   *
   *
   * @param right non-null reference to the evaluated right operand.
   *
   * @return non-null reference to the XObject that represents the result of the operation.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public abstract XObject operate(XObject right)
    throws javax.xml.transform.TransformerException;

  /** @return the operand of unary operation, as an Expression.
   */
  public Expression getOperand(){
    return m_right;
  }

  /**
   * @see com.sun.org.apache.xpath.internal.XPathVisitable#callVisitors(ExpressionOwner, XPathVisitor)
   */
  public void callVisitors(ExpressionOwner owner, XPathVisitor visitor)
  {
        if(visitor.visitUnaryOperation(owner, this))
        {
                m_right.callVisitors(this, visitor);
        }
  }


  /**
   * @see ExpressionOwner#getExpression()
   */
  public Expression getExpression()
  {
    return m_right;
  }

  /**
   * @see ExpressionOwner#setExpression(Expression)
   */
  public void setExpression(Expression exp)
  {
        exp.exprSetParent(this);
        m_right = exp;
  }

  /**
   * @see Expression#deepEquals(Expression)
   */
  public boolean deepEquals(Expression expr)
  {
        if(!isSameClass(expr))
                return false;

        if(!m_right.deepEquals(((UnaryOperation)expr).m_right))
                return false;

        return true;
  }


}
