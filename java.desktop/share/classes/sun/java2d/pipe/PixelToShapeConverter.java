package sun.java2d.pipe;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.GeneralPath;
import sun.java2d.SunGraphics2D;

/**
 * This class converts calls to the basic pixel rendering methods
 * into calls to a generic Shape rendering pipeline.
 */
public class PixelToShapeConverter
    implements PixelDrawPipe, PixelFillPipe
{
    ShapeDrawPipe outpipe;

    public PixelToShapeConverter(ShapeDrawPipe pipe) {
        outpipe = pipe;
    }

    public void drawLine(SunGraphics2D sg,
                         int x1, int y1, int x2, int y2) {
        outpipe.draw(sg, new Line2D.Float(x1, y1, x2, y2));
    }

    public void drawRect(SunGraphics2D sg,
                         int x, int y, int w, int h) {
        outpipe.draw(sg, new Rectangle(x, y, w, h));
    }

    public void fillRect(SunGraphics2D sg,
                         int x, int y, int w, int h) {
        outpipe.fill(sg, new Rectangle(x, y, w, h));
    }

    public void drawRoundRect(SunGraphics2D sg,
                              int x, int y, int w, int h,
                              int aW, int aH) {
        outpipe.draw(sg, new RoundRectangle2D.Float(x, y, w, h, aW, aH));
    }

    public void fillRoundRect(SunGraphics2D sg,
                              int x, int y, int w, int h,
                              int aW, int aH) {
        outpipe.fill(sg, new RoundRectangle2D.Float(x, y, w, h, aW, aH));
    }

    public void drawOval(SunGraphics2D sg,
                         int x, int y, int w, int h) {
        outpipe.draw(sg, new Ellipse2D.Float(x, y, w, h));
    }

    public void fillOval(SunGraphics2D sg,
                         int x, int y, int w, int h) {
        outpipe.fill(sg, new Ellipse2D.Float(x, y, w, h));
    }

    public void drawArc(SunGraphics2D sg,
                        int x, int y, int w, int h,
                        int start, int extent) {
        outpipe.draw(sg, new Arc2D.Float(x, y, w, h,
                                         start, extent, Arc2D.OPEN));
    }

    public void fillArc(SunGraphics2D sg,
                        int x, int y, int w, int h,
                        int start, int extent) {
        outpipe.fill(sg, new Arc2D.Float(x, y, w, h,
                                         start, extent, Arc2D.PIE));
    }

    private Shape makePoly(int[] xPoints, int[] yPoints,
                           int nPoints, boolean close) {
        GeneralPath gp = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        if (nPoints > 0) {
            gp.moveTo(xPoints[0], yPoints[0]);
            for (int i = 1; i < nPoints; i++) {
                gp.lineTo(xPoints[i], yPoints[i]);
            }
            if (close) {
                gp.closePath();
            }
        }
        return gp;
    }

    public void drawPolyline(SunGraphics2D sg,
                             int[] xPoints, int[] yPoints,
                             int nPoints) {
        outpipe.draw(sg, makePoly(xPoints, yPoints, nPoints, false));
    }

    public void drawPolygon(SunGraphics2D sg,
                            int[] xPoints, int[] yPoints,
                            int nPoints) {
        outpipe.draw(sg, makePoly(xPoints, yPoints, nPoints, true));
    }

    public void fillPolygon(SunGraphics2D sg,
                            int[] xPoints, int[] yPoints,
                            int nPoints) {
        outpipe.fill(sg, makePoly(xPoints, yPoints, nPoints, true));
    }
}
