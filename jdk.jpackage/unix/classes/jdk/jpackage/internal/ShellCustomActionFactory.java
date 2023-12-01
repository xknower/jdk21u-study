package jdk.jpackage.internal;

import java.io.IOException;
import java.util.Map;

interface ShellCustomActionFactory {

    ShellCustomAction create(PlatformPackage thePackage,
            Map<String, ? super Object> params) throws IOException;
}
