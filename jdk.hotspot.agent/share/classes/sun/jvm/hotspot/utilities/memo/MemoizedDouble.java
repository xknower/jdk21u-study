package sun.jvm.hotspot.utilities.memo;

/** A memoized double. Override {@link #computeValue} in subclasses;
    call {@link #getValue} in using code. */

public abstract class MemoizedDouble {
  private boolean computed;
  private double value;

  /** Should compute the value of this memoized object. This will only
      be called once, upon the first call to {@link #getValue}. */
  protected abstract double computeValue();

  /** Public accessor for the memoized value. */
  public double getValue() {
    if (!computed) {
      value = computeValue();
      computed = true;
    }
    return value;
  }
}
