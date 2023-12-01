package jdk.jfr.internal.jfc.model;

import java.util.List;

// Corresponds to <test>
final class XmlTest extends XmlExpression {

    public String getName() {
        return attribute("name");
    }

    public String getOperator() {
        return attribute("operator");
    }

    public String getValue() {
        return attribute("value");
    }

    @Override
    boolean isEntity() {
        return false;
    }

    @Override
    protected List<String> attributes() {
        return List.of("name", "operator", "value");
    }

    @Override
    protected void validateChildConstraints() throws JFCModelException {
        if (!getExpressions().isEmpty()) {
            throw new JFCModelException("Expected <test> to not have child elements");
        }
    }

    @Override
    protected void validateAttributes() throws JFCModelException {
        super.validateAttributes();
        if (!getOperator().equalsIgnoreCase("equal")) {
            throw new JFCModelException("Unknown operator '" + getOperator() + "', only supported is 'equal'");
        }
    }

    @Override
    protected Result evaluate() {
        Result ret = Result.NULL;
        List<XmlElement> producers = getProducers();
        if (!producers.isEmpty()) {
            XmlElement producer = producers.get(0);
            Result r = producer.evaluate();
            if (!r.isNull()) {
                ret = getValue().equals(r.value()) ? Result.TRUE : Result.FALSE;
            }

        }
        return ret;
    }
}
