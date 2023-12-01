package com.sun.hotspot.igv.data;

/**
 *
 * @author Thomas Wuerthinger
 */
public class Pair<L, R> {

    private L l;
    private R r;

    public Pair() {
    }

    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    public L getLeft() {
        return l;
    }

    public void setLeft(L l) {
        this.l = l;
    }

    public R getRight() {
        return r;
    }

    public void setRight(R r) {
        this.r = r;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair<?, ?>)) {
            return false;
        }
        Pair<?, ?> obj = (Pair<?, ?>) o;
        boolean b1 = (l == obj.l);
        if (l != null) {
            b1 = l.equals(obj.l);
        }

        boolean b2 = (r == obj.r);
        if (r != null) {
            b2 = r.equals(obj.r);
        }

        return b1 && b2;
    }

    @Override
    public int hashCode() {
        return ((l == null) ? 0 : l.hashCode()) * 71 + ((r == null) ? 0 : r.hashCode());
    }

    @Override
    public String toString() {
        return "[" + l + "/" + r + "]";
    }
}
