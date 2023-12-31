package com.sun.xml.internal.stream.events ;

import javax.xml.stream.events.StartDocument;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamConstants;

/** Implementation of StartDocumentEvent.
 *
 * @author Neeraj Bajaj Sun Microsystems,Inc.
 * @author K.Venugopal Sun Microsystems,Inc.
 *
 */

public class StartDocumentEvent extends DummyEvent
implements StartDocument {

    protected String fSystemId;
    protected String fEncodingScheam;
    protected boolean fStandalone;
    protected String fVersion;
    private boolean fEncodingSchemeSet = false;
    private boolean fStandaloneSet = false;
    private boolean nestedCall = false;

    public StartDocumentEvent() {
        init("UTF-8","1.0",true,null);
    }

    public StartDocumentEvent(String encoding){
        init(encoding,"1.0",true,null);
    }

    public StartDocumentEvent(String encoding, String version){
        init(encoding,version,true,null);
    }

    public StartDocumentEvent(String encoding, String version, boolean standalone){
        this.fStandaloneSet = true;
        init(encoding,version,standalone,null);
    }

    public StartDocumentEvent(String encoding, String version, boolean standalone,Location loc){
        this.fStandaloneSet = true;
        init(encoding, version, standalone, loc);
    }
    protected void init(String encoding, String version, boolean standalone,Location loc) {
        setEventType(XMLStreamConstants.START_DOCUMENT);
        this.fEncodingScheam = encoding;
        this.fVersion = version;
        this.fStandalone = standalone;
        if (encoding != null && !encoding.isEmpty())
            this.fEncodingSchemeSet = true;
        else {
            this.fEncodingSchemeSet = false;
            this.fEncodingScheam = "UTF-8";
        }
        this.fLocation = loc;
    }

    public String getSystemId() {
        if(fLocation == null )
            return "";
        else
            return fLocation.getSystemId();
    }


    public String getCharacterEncodingScheme() {
        return fEncodingScheam;
    }

    public boolean isStandalone() {
        return fStandalone;
    }

    public String getVersion() {
        return fVersion;
    }

    public void setStandalone(boolean isStandalone, boolean standaloneSet) {
        fStandaloneSet = standaloneSet;
        fStandalone = isStandalone;
    }

    public void setStandalone(String s) {
        fStandaloneSet = true;
        if(s == null) {
            fStandalone = true;
            return;
        }
        if(s.equals("yes"))
            fStandalone = true;
        else
            fStandalone = false;
    }

    public boolean encodingSet() {
        return fEncodingSchemeSet;
    }

    public boolean standaloneSet() {
        return fStandaloneSet;
    }

    public void setEncoding(String encoding) {
        fEncodingScheam = encoding;
    }

    void setDeclaredEncoding(boolean value){
        fEncodingSchemeSet = value;
    }

    public void setVersion(String s) {
        fVersion = s;
    }

    void clear() {
        fEncodingScheam = "UTF-8";
        fStandalone = true;
        fVersion = "1.0";
        fEncodingSchemeSet = false;
        fStandaloneSet = false;
    }

    public String toString() {
        String s = "<?xml version=\"" + fVersion + "\"";
        s = s + " encoding='" + fEncodingScheam + "'";
        if(fStandaloneSet) {
            if(fStandalone)
                s = s + " standalone='yes'?>";
            else
                s = s + " standalone='no'?>";
        } else {
            s = s + "?>";
        }
        return s;
    }

    public boolean isStartDocument() {
        return true;
    }

    protected void writeAsEncodedUnicodeEx(java.io.Writer writer)
    throws java.io.IOException
    {
        writer.write(toString());
    }
}
