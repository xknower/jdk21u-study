package javax.swing.text.html.parser;

import sun.awt.AppContext;

import javax.swing.text.html.HTMLEditorKit;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.io.Serial;
import java.io.Serializable;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Responsible for starting up a new DocumentParser
 * each time its parse method is invoked. Stores a
 * reference to the dtd.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans has been added to the <code>java.beans</code> package.
 *
 * @author  Sunita Mani
 */
@SuppressWarnings("serial") // Same-version serialization only
public class ParserDelegator extends HTMLEditorKit.Parser implements Serializable {
    private static final Object DTD_KEY = new Object();

    /**
     * Sets the default DTD.
     */
    protected static void setDefaultDTD() {
        getDefaultDTD();
    }

    private static synchronized DTD getDefaultDTD() {
        AppContext appContext = AppContext.getAppContext();

        DTD dtd = (DTD) appContext.get(DTD_KEY);

        if (dtd == null) {
            DTD _dtd = null;
            // (PENDING) Hate having to hard code!
            String nm = "html32";
            try {
                _dtd = DTD.getDTD(nm);
            } catch (IOException e) {
                // (PENDING) UGLY!
                System.out.println("Throw an exception: could not get default dtd: " + nm);
            }
            dtd = createDTD(_dtd, nm);

            appContext.put(DTD_KEY, dtd);
        }

        return dtd;
    }

    /**
     * Recreates a DTD from an archived format with the specified {@code name}.
     *
     * @param dtd a DTD
     * @param name the name of the resource, relative to the  ParserDelegator class.
     * @return the DTD with the specified {@code name}.
     */
    protected static DTD createDTD(DTD dtd, String name) {

        InputStream in = null;
        boolean debug = true;
        try {
            String path = name + ".bdtd";
            in = getResourceAsStream(path);
            if (in != null) {
                dtd.read(new DataInputStream(new BufferedInputStream(in)));
                DTD.putDTDHash(name, dtd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return dtd;
    }

    /**
     * Creates {@code ParserDelegator} with default DTD.
     */
    public ParserDelegator() {
        setDefaultDTD();
    }

    public void parse(Reader r, HTMLEditorKit.ParserCallback cb, boolean ignoreCharSet) throws IOException {
        new DocumentParser(getDefaultDTD()).parse(r, cb, ignoreCharSet);
    }

    /**
     * Fetch a resource relative to the ParserDelegator classfile.
     * If this is called on 1.2 the loading will occur under the
     * protection of a doPrivileged call to allow the ParserDelegator
     * to function when used in an applet.
     *
     * @param name the name of the resource, relative to the
     *  ParserDelegator class.
     * @return a stream representing the resource
     */
    @SuppressWarnings("removal")
    static InputStream getResourceAsStream(final String name) {
        return AccessController.doPrivileged(
                new PrivilegedAction<InputStream>() {
                    public InputStream run() {
                        return ParserDelegator.class.getResourceAsStream(name);
                    }
                });
    }

    @Serial
    private void readObject(ObjectInputStream s)
        throws ClassNotFoundException, IOException {
        s.defaultReadObject();
        setDefaultDTD();
    }
}
