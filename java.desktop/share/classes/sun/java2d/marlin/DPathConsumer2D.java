package sun.java2d.marlin;

public interface DPathConsumer2D {
    /**
     * @see java.awt.geom.Path2D.Double#moveTo
     */
    public void moveTo(double x, double y);

    /**
     * @see java.awt.geom.Path2D.Double#lineTo
     */
    public void lineTo(double x, double y);

    /**
     * @see java.awt.geom.Path2D.Double#quadTo
     */
    public void quadTo(double x1, double y1,
                       double x2, double y2);

    /**
     * @see java.awt.geom.Path2D.Double#curveTo
     */
    public void curveTo(double x1, double y1,
                        double x2, double y2,
                        double x3, double y3);

    /**
     * @see java.awt.geom.Path2D.Double#closePath
     */
    public void closePath();

    /**
     * Called after the last segment of the last subpath when the
     * iteration of the path segments is completely done.  This
     * method serves to trigger the end of path processing in the
     * consumer that would normally be triggered when a
     * {@link java.awt.geom.PathIterator PathIterator}
     * returns {@code true} from its {@code done} method.
     */
    public void pathDone();

    /**
     * If a given PathConsumer performs all or most of its work
     * natively then it can return a (non-zero) pointer to a
     * native function vector that defines C functions for all
     * of the above methods.
     * The specific pointer it returns is a pointer to a
     * PathConsumerVec structure as defined in the include file
     * src/share/native/sun/java2d/pipe/PathConsumer2D.h
     * @return a native pointer to a PathConsumerVec structure.
     */
    public long getNativeConsumer();
}
