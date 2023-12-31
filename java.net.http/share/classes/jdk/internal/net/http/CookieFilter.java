package jdk.internal.net.http;

import java.io.IOException;
import java.net.CookieHandler;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.net.http.HttpHeaders;
import jdk.internal.net.http.common.HttpHeadersBuilder;
import jdk.internal.net.http.common.Log;
import jdk.internal.net.http.common.Utils;

class CookieFilter implements HeaderFilter {

    public CookieFilter() {
    }

    @Override
    public void request(HttpRequestImpl r, MultiExchange<?> e) throws IOException {
        HttpClientImpl client = e.client();
        Optional<CookieHandler> cookieHandlerOpt = client.cookieHandler();
        if (cookieHandlerOpt.isPresent()) {
            CookieHandler cookieHandler = cookieHandlerOpt.get();
            Map<String,List<String>> userheaders = r.getUserHeaders().map();
            Map<String,List<String>> cookies = cookieHandler.get(r.uri(), userheaders);

            // add the returned cookies
            HttpHeadersBuilder systemHeadersBuilder = r.getSystemHeadersBuilder();
            if (cookies.isEmpty()) {
                Log.logTrace("Request: no cookie to add for {0}", r.uri());
            } else {
                Log.logTrace("Request: adding cookies for {0}", r.uri());
            }
            for (Map.Entry<String,List<String>> entry : cookies.entrySet()) {
                final String hdrname = entry.getKey();
                if (!hdrname.equalsIgnoreCase("Cookie")
                        && !hdrname.equalsIgnoreCase("Cookie2"))
                    continue;
                List<String> values = entry.getValue();
                if (values == null || values.isEmpty()) continue;
                for (String val : values) {
                    if (Utils.isValidValue(val)) {
                        systemHeadersBuilder.addHeader(hdrname, val);
                    }
                }
            }
        } else {
            Log.logTrace("Request: No cookie manager found for {0}", r.uri());
        }
    }

    @Override
    public HttpRequestImpl response(Response r) throws IOException {
        HttpHeaders hdrs = r.headers();
        HttpRequestImpl request = r.request();
        Exchange<?> e = r.exchange;
        Log.logTrace("Response: processing cookies for {0}", request.uri());
        Optional<CookieHandler> cookieHandlerOpt = e.client().cookieHandler();
        if (cookieHandlerOpt.isPresent()) {
            CookieHandler cookieHandler = cookieHandlerOpt.get();
            Log.logTrace("Response: parsing cookies from {0}", hdrs.map());
            cookieHandler.put(request.uri(), hdrs.map());
        } else {
            Log.logTrace("Response: No cookie manager found for {0}",
                         request.uri());
        }
        return null;
    }
}
