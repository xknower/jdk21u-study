package jdk.internal.classfile.impl;

import jdk.internal.classfile.Label;

public sealed interface LabelContext
        permits BufferedCodeBuilder, CodeImpl, DirectCodeBuilder {
    Label newLabel();
    Label getLabel(int bci);
    void setLabelTarget(Label label, int bci);
    int labelToBci(Label label);
}
