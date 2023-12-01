package jdk.internal.classfile.impl;

import jdk.internal.classfile.CodeModel;
import jdk.internal.classfile.MethodBuilder;

public sealed interface TerminalMethodBuilder
        extends MethodBuilder
        permits BufferedMethodBuilder, DirectMethodBuilder {
    BufferedCodeBuilder bufferedCodeBuilder(CodeModel original);
}
