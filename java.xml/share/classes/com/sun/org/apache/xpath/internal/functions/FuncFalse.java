package com.sun.org.apache.xpath.internal.functions;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.util.List;

/**
 * Execute the False() function.
 * @xsl.usage advanced
 * @LastModified: Oct 2017
 */
public class FuncFalse extends Function
{
    static final long serialVersionUID = 6150918062759769887L;

  /**
   * Execute the function.  The function must return
   * a valid object.
   * @param xctxt The current execution context.
   * @return A valid XObject.
   *
   * @throws javax.xml.transform.TransformerException
   */
  public XObject execute(XPathContext xctxt) throws javax.xml.transform.TransformerException
  {
    return XBoolean.S_FALSE;
  }

  /**
   * No arguments to process, so this does nothing.
   */
  public void fixupVariables(List<QName> vars, int globalsSize)
  {
    // no-op
  }

}
