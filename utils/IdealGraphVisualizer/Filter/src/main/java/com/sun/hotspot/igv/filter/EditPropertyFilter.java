package com.sun.hotspot.igv.filter;

import com.sun.hotspot.igv.graph.Diagram;
import com.sun.hotspot.igv.graph.Figure;
import com.sun.hotspot.igv.graph.Selector;
import java.util.function.UnaryOperator;
import java.util.List;

public class EditPropertyFilter extends AbstractFilter {

    private String name;
    private Selector selector;
    private final String inputPropertyName;
    private final String outputPropertyName;
    private final UnaryOperator<String> editFunction;

    public EditPropertyFilter(String name, Selector selector,
                              String inputPropertyName, String outputPropertyName,
                              UnaryOperator<String> editFunction) {
        this.name = name;
        this.selector = selector;
        this.inputPropertyName = inputPropertyName;
        this.outputPropertyName = outputPropertyName;
        this.editFunction = editFunction;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void apply(Diagram diagram) {
        List<Figure> list = selector.selected(diagram);
        for (Figure f : list) {
            String inputVal = f.getProperties().get(inputPropertyName);
            String outputVal = editFunction.apply(inputVal);
            f.getProperties().setProperty(outputPropertyName, outputVal);
        }
    }
}
