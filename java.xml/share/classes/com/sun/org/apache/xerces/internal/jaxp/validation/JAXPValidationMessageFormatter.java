package com.sun.org.apache.xerces.internal.jaxp.validation;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import jdk.xml.internal.SecuritySupport;

/**
 * <p>Used to format JAXP Validation API error messages using a specified locale.</p>
 *
 * @author Michael Glavassevich, IBM
 * @LastModified: Sep 2017
 */
final class JAXPValidationMessageFormatter {

    /**
     * Formats a message with the specified arguments using the given
     * locale information.
     *
     * @param locale    The locale of the message.
     * @param key       The message key.
     * @param arguments The message replacement text arguments. The order
     *                  of the arguments must match that of the placeholders
     *                  in the actual message.
     *
     * @return          the formatted message.
     *
     * @throws MissingResourceException Thrown if the message with the
     *                                  specified key cannot be found.
     */
    public static String formatMessage(Locale locale,
        String key, Object[] arguments)
        throws MissingResourceException {

        ResourceBundle resourceBundle = null;
        if (locale != null) {
            resourceBundle =
                SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.JAXPValidationMessages", locale);
        }
        else {
            resourceBundle =
                SecuritySupport.getResourceBundle("com.sun.org.apache.xerces.internal.impl.msg.JAXPValidationMessages");
        }

        // format message
        String msg;
        try {
            msg = resourceBundle.getString(key);
            if (arguments != null) {
                try {
                    msg = java.text.MessageFormat.format(msg, arguments);
                }
                catch (Exception e) {
                    msg = resourceBundle.getString("FormatFailed");
                    msg += " " + resourceBundle.getString(key);
                }
            }
        }

        // error
        catch (MissingResourceException e) {
            msg = resourceBundle.getString("BadMessageKey");
            throw new MissingResourceException(key, msg, key);
        }

        // no message
        if (msg == null) {
            msg = key;
            if (arguments.length > 0) {
                StringBuffer str = new StringBuffer(msg);
                str.append('?');
                for (int i = 0; i < arguments.length; i++) {
                    if (i > 0) {
                        str.append('&');
                    }
                    str.append(String.valueOf(arguments[i]));
                }
            }
        }
        return msg;
    }
}
