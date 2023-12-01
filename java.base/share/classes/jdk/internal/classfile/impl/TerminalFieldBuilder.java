package jdk.internal.classfile.impl;

import jdk.internal.classfile.FieldBuilder;

public sealed interface TerminalFieldBuilder
        extends FieldBuilder
        permits DirectFieldBuilder, BufferedFieldBuilder {
}
