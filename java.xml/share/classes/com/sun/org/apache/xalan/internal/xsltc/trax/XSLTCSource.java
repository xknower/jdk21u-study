package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.StripFilter;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.dom.DOMWSFilter;
import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;
import com.sun.org.apache.xalan.internal.xsltc.dom.XSLTCDTMManager;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.SAXException;

/**
 * @author Morten Jorgensen
 * @LastModified: Nov 2017
 */
public final class XSLTCSource implements Source {

    private String     _systemId = null;
    private Source     _source   = null;
    private ThreadLocal<SAXImpl> _dom     = new ThreadLocal<>();

    /**
     * Create a new XSLTC-specific source from a system ID
     */
    public XSLTCSource(String systemId)
    {
        _systemId = systemId;
    }

    /**
     * Create a new XSLTC-specific source from a JAXP Source
     */
    public XSLTCSource(Source source)
    {
        _source = source;
    }

    /**
     * Implements javax.xml.transform.Source.setSystemId()
     * Set the system identifier for this Source.
     * This Source can get its input either directly from a file (in this case
     * it will instanciate and use a JAXP parser) or it can receive it through
     * ContentHandler/LexicalHandler interfaces.
     * @param systemId The system Id for this Source
     */
    public void setSystemId(String systemId) {
        _systemId = systemId;
        if (_source != null) {
            _source.setSystemId(systemId);
        }
    }

    /**
     * Implements javax.xml.transform.Source.getSystemId()
     * Get the system identifier that was set with setSystemId.
     * @return The system identifier that was set with setSystemId,
     *         or null if setSystemId was not called.
     */
    public String getSystemId() {
        if (_source != null) {
            return _source.getSystemId();
        }
        else {
            return(_systemId);
        }
    }

    /**
     * Internal interface which returns a DOM for a given DTMManager and translet.
     */
    protected DOM getDOM(XSLTCDTMManager dtmManager, AbstractTranslet translet)
        throws SAXException
    {
        SAXImpl idom = _dom.get();

        if (idom != null) {
            if (dtmManager != null) {
                idom.migrateTo(dtmManager);
            }
        }
        else {
            Source source = _source;
            if (source == null) {
                if (_systemId != null && _systemId.length() > 0) {
                    source = new StreamSource(_systemId);
                }
                else {
                    ErrorMsg err = new ErrorMsg(ErrorMsg.XSLTC_SOURCE_ERR);
                    throw new SAXException(err.toString());
                }
            }

            DOMWSFilter wsfilter = null;
            if (translet != null && translet instanceof StripFilter) {
                wsfilter = new DOMWSFilter(translet);
            }

            boolean hasIdCall = (translet != null) ? translet.hasIdCall() : false;

            if (dtmManager == null) {
                dtmManager = XSLTCDTMManager.newInstance();
            }

            idom = (SAXImpl)dtmManager.getDTM(source, true, wsfilter, false, false, hasIdCall);

            String systemId = getSystemId();
            if (systemId != null) {
                idom.setDocumentURI(systemId);
            }
            _dom.set(idom);
        }
        return idom;
    }

}
