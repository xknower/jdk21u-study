package sun.awt.www.content.image;

import java.net.*;
import java.io.IOException;
import sun.awt.image.*;
import java.awt.Image;
import java.awt.Toolkit;

public class png extends ContentHandler {
    public Object getContent(URLConnection urlc) throws java.io.IOException {
        return new URLImageSource(urlc);
    }

    @SuppressWarnings("rawtypes")
    public Object getContent(URLConnection urlc, Class[] classes) throws IOException {
        Class<?>[] cls = classes;
        for (int i = 0; i < cls.length; i++) {
            if (cls[i].isAssignableFrom(URLImageSource.class)) {
                return new URLImageSource(urlc);
            }
            if (cls[i].isAssignableFrom(Image.class)) {
                Toolkit tk = Toolkit.getDefaultToolkit();
                return tk.createImage(new URLImageSource(urlc));
            }
        }
        return null;
    }
}
