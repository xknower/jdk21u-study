package jdk.tools.jlink.internal;

import java.util.List;

/**
 * Plugin wishing to post-process must implement this interface. PostProcessors
 * are called once the image has been generated and is executable.
 **/
public interface PostProcessor {

    /**
     * Post process an image.
     *
     * @param image The executable image.
     * @return The list of arguments to add to launchers (if any).
     */
    public List<String> process(ExecutableImage image);
}
