package com.sun.org.apache.xml.internal.res;

import java.util.Locale;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/**
 * A utility class for issuing XML error messages.
 * @xsl.usage internal
 * @LastModified: Sep 2017
 */
public class XMLMessages
{

  /** The local object to use.  */
  protected Locale fLocale = Locale.getDefault();

  /** The language specific resource object for XML messages.  */
  private static ResourceBundle XMLBundle = null;

  /** The class name of the XML error message string table.    */
  private static final String XML_ERROR_RESOURCES =
    "com.sun.org.apache.xml.internal.res.XMLErrorResources";

  /** String to use if a bad message code is used. */
  protected static final String BAD_CODE = "BAD_CODE";

  /** String to use if the message format operation failed.  */
  protected static final String FORMAT_FAILED = "FORMAT_FAILED";

  /**
   * Set the Locale object to use.
   *
   * @param locale non-null reference to Locale object.
   */
   public void setLocale(Locale locale)
  {
    fLocale = locale;
  }

  /**
   * Get the Locale object that is being used.
   *
   * @return non-null reference to Locale object.
   */
  public Locale getLocale()
  {
    return fLocale;
  }

  /**
   * Creates a message from the specified key and replacement
   * arguments, localized to the given locale.
   *
   * @param msgKey    The key for the message text.
   * @param args      The arguments to be used as replacement text
   *                  in the message created.
   *
   * @return The formatted message string.
   */
  public static final String createXMLMessage(String msgKey, Object args[])
  {
    if (XMLBundle == null) {
        XMLBundle = SecuritySupport.getResourceBundle(XML_ERROR_RESOURCES);
    }

    if (XMLBundle != null)
    {
      return createMsg(XMLBundle, msgKey, args);
    }
    else
      return "Could not load any resource bundles.";
  }

  /**
   * Creates a message from the specified key and replacement
   * arguments, localized to the given locale.
   *
   * @param fResourceBundle The resource bundle to use.
   * @param msgKey  The message key to use.
   * @param args      The arguments to be used as replacement text
   *                  in the message created.
   *
   * @return The formatted message string.
   */
  public static final String createMsg(ResourceBundle fResourceBundle,
        String msgKey, Object args[])  //throws Exception
  {

    String fmsg = null;
    boolean throwex = false;
    String msg = null;

    if (msgKey != null)
      msg = fResourceBundle.getString(msgKey);

    if (msg == null)
    {
      msg = fResourceBundle.getString(BAD_CODE);
      throwex = true;
    }

    if (args != null)
    {
      try
      {

        // Do this to keep format from crying.
        // This is better than making a bunch of conditional
        // code all over the place.
        int n = args.length;

        for (int i = 0; i < n; i++)
        {
          if (null == args[i])
            args[i] = "";
        }

        fmsg = java.text.MessageFormat.format(msg, args);
      }
      catch (Exception e)
      {
        fmsg = fResourceBundle.getString(FORMAT_FAILED);
        fmsg += " " + msg;
      }
    }
    else
      fmsg = msg;

    if (throwex)
    {
      throw new RuntimeException(fmsg);
    }

    return fmsg;
  }

}
