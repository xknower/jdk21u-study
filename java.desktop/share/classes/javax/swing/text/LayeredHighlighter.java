package javax.swing.text;

import java.awt.Graphics;
import java.awt.Shape;

/**
 * Implementation of {@code Highlighter} interface to mark up the
 * background of leaf views with colored areas.
 *
 * @author  Scott Violet
 * @author  Timothy Prinzing
 * @see     Highlighter
 */
public abstract class LayeredHighlighter implements Highlighter {
    /**
     * Constructor for subclasses to call.
     */
    protected LayeredHighlighter() {}

    /**
     * When leaf Views (such as LabelView) are rendering they should
     * call into this method. If a highlight is in the given region it will
     * be drawn immediately.
     *
     * @param g Graphics used to draw
     * @param p0 starting offset of view
     * @param p1 ending offset of view
     * @param viewBounds Bounds of View
     * @param editor JTextComponent
     * @param view View instance being rendered
     */
    public abstract void paintLayeredHighlights(Graphics g, int p0, int p1,
                                                Shape viewBounds,
                                                JTextComponent editor,
                                                View view);


    /**
     * Layered highlight renderer.
     */
    public abstract static class LayerPainter implements Highlighter.HighlightPainter {
        /**
         * Constructor for subclasses to call.
         */
        protected LayerPainter() {}

        /**
         * Paints a portion of a highlight.
         *
         * @return a shape
         * @param g Graphics used to draw
         * @param p0 starting offset of view
         * @param p1 ending offset of view
         * @param viewBounds Bounds of View
         * @param editor JTextComponent
         * @param view View instance being rendered
         */
        public abstract Shape paintLayer(Graphics g, int p0, int p1,
                                        Shape viewBounds,JTextComponent editor,
                                        View view);
    }
}
