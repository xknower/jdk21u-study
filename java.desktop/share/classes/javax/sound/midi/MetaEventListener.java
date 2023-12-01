package javax.sound.midi;

import java.util.EventListener;

/**
 * The {@code MetaEventListener} interface should be implemented by classes
 * whose instances need to be notified when a {@link Sequencer} has processed a
 * {@link MetaMessage}. To register a {@code MetaEventListener} object to
 * receive such notifications, pass it as the argument to the
 * {@link Sequencer#addMetaEventListener(MetaEventListener)
 * addMetaEventListener} method of {@code Sequencer}.
 *
 * @author Kara Kytle
 */
public interface MetaEventListener extends EventListener {

    /**
     * Invoked when a {@link Sequencer} has encountered and processed a
     * {@code MetaMessage} in the {@code Sequence} it is processing.
     *
     * @param  meta the meta-message that the sequencer encountered
     */
    void meta(MetaMessage meta);
}
