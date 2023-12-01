package sun.net.www.protocol.ftp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.Proxy;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import sun.net.ftp.FtpClient;
import sun.net.www.protocol.http.HttpURLConnection;

/** open an ftp connection given a URL */
public class Handler extends java.net.URLStreamHandler {

    protected int getDefaultPort() {
        return 21;
    }

    protected boolean equals(URL u1, URL u2) {
        String userInfo1 = u1.getUserInfo();
        String userInfo2 = u2.getUserInfo();
        return super.equals(u1, u2) && Objects.equals(userInfo1, userInfo2);
    }

    protected java.net.URLConnection openConnection(URL u)
        throws IOException {
        return openConnection(u, null);
    }

    protected java.net.URLConnection openConnection(URL u, Proxy p)
        throws IOException {
        FtpURLConnection connection = null;
        try {
            connection = new FtpURLConnection(u, p);
        } catch (IllegalArgumentException e) {
            var mfue = new MalformedURLException(e.getMessage());
            mfue.initCause(e);
            throw mfue;
        }
        return connection;
    }
}
