package sun.jvm.hotspot.utilities;

/** Minimal bitmap interface to support bitmaps spanning more than Integer.MAX_VALUE bits. */
public interface BitMapInterface {
  public boolean at(long offset);
  public void atPut(long offset, boolean value);
  public void clear();
}
