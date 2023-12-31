package sun.print;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;

import java.awt.font.TextLayout;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;

/**
 * Maintain information about the type of drawing
 * performed by a printing application.
 */
public class PeekMetrics {

    private boolean mHasNonSolidColors;

    private boolean mHasCompositing;

    private boolean mHasText;

    private boolean mHasImages;

    /**
     * Return {@code true} if the application
     * has done any drawing with a Paint that
     * is not an instance of {@code Color}
     */
    public boolean hasNonSolidColors() {
        return mHasNonSolidColors;
    }

    /**
     * Return true if the application has
     * done any drawing with an alpha other
     * than 1.0.
     */
    public boolean hasCompositing() {
        return mHasCompositing;
    }

    /**
     * Return true if the application has
     * drawn any text.
     */
    public boolean hasText() {
        return mHasText;
    }

    /**
     * Return true if the application has
     * drawn any images.
     */
    public boolean hasImages() {
        return mHasImages;
    }

    /**
     * The application is performing a fill
     * so record the needed information.
     */
    public void fill(Graphics2D g) {
        checkDrawingMode(g);
    }

    /**
     * The application is performing a draw
     * so record the needed information.
     */
    public void draw(Graphics2D g) {
        checkDrawingMode(g);
    }

    /**
     * The application is performing a clearRect
     * so record the needed information.
     */
    public void clear(Graphics2D g) {
        checkPaint(g.getBackground());
    }
    /**
     * The application is drawing text
     * so record the needed information.
     */
    public void drawText(Graphics2D g) {
        mHasText = true;
        checkDrawingMode(g);
    }

    /**
     * The application is drawing text
     * defined by {@code TextLayout}
     * so record the needed information.
     */
    public void drawText(Graphics2D g, TextLayout textLayout) {
        mHasText = true;
        checkDrawingMode(g);
    }

    /**
     * The application is drawing the passed
     * in image.
     */
    public void drawImage(Graphics2D g, Image image) {
        mHasImages = true;
    }

    /**
     * The application is drawing the passed
     * in image.
     */
    public void drawImage(Graphics2D g, RenderedImage image) {
        mHasImages = true;
    }

    /**
     * The application is drawing the passed
     * in image.
     */
    public void drawImage(Graphics2D g, RenderableImage image) {
        mHasImages = true;
    }

    /**
     * Record information about the current paint
     * and composite.
     */
    private void checkDrawingMode(Graphics2D g) {

        checkPaint(g.getPaint());
        checkAlpha(g.getComposite());

    }

    /**
     * Record information about drawing done
     * with the supplied {@code Paint}.
     */
    private void checkPaint(Paint paint) {

        if (paint instanceof Color) {
            if (((Color)paint).getAlpha() < 255) {
                mHasNonSolidColors = true;
            }
        } else {
            mHasNonSolidColors = true;
        }
    }

    /**
     * Record information about drawing done
     * with the supplied {@code Composite}.
     */
    private void checkAlpha(Composite composite) {

        if (composite instanceof AlphaComposite) {
            AlphaComposite alphaComposite = (AlphaComposite) composite;
            float alpha = alphaComposite.getAlpha();
            int rule = alphaComposite.getRule();

            if (alpha != 1.0
                    || (rule != AlphaComposite.SRC
                        && rule != AlphaComposite.SRC_OVER)) {

                mHasCompositing = true;
            }

        } else {
            mHasCompositing = true;
        }

    }

}
