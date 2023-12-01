package jdk.internal.classfile.impl;

import jdk.internal.classfile.CodeBuilder;

public sealed interface TerminalCodeBuilder extends CodeBuilder
        permits DirectCodeBuilder, BufferedCodeBuilder, TransformingCodeBuilder {

}
