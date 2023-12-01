package jdk.jfr.internal.jfc.model;

// Corresponds to <or>
final class XmlOr extends XmlExpression {

    @Override
    boolean isEntity() {
        return false;
    }

    @Override
    protected Result evaluate() {
        Result result = Result.NULL;
        for (XmlElement e : getProducers()) {
            Result r = e.evaluate();
            if (r.isFalse()) {
                result = Result.FALSE;
            }
            if (r.isTrue()) {
                return Result.TRUE;
            }

        }
        return result;
    }
}
