package com.sun.media.sound;

/**
 *  ModelDirectedPlayer is the one who is directed by ModelDirector
 *  to play ModelPerformer objects.
 *
 * @author Karl Helgason
 */
public interface ModelDirectedPlayer {

    void play(int performerIndex, ModelConnectionBlock[] connectionBlocks);
}
