package sun.awt.www.content.audio;

import java.io.IOException;
import java.net.ContentHandler;
import java.net.URLConnection;

import com.sun.media.sound.JavaSoundAudioClip;

/**
 * Basic .wav audio handler returns an JavaSoundAudioClip object.
 *
 * @author Jeff Nisewanger
 */
public class wav extends ContentHandler {
    public Object getContent(URLConnection uc) throws IOException {
        return JavaSoundAudioClip.create(uc);
    }
}
