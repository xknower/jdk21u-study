package jdk.vm.ci.hotspot;

import jdk.vm.ci.meta.ResolvedJavaType;
import jdk.vm.ci.services.Services;

/**
 * Indicates a path in HotSpot JVMCI related code that is unsupported in the current execution
 * environment. For example, certain operations are not supported by JVMCI code running in an ahead
 * of time compiled {@linkplain Services#IS_IN_NATIVE_IMAGE native image}. This usually reflects
 * functionality only needed in non-native image execution that would require a more complex
 * implementation to support in a native image.
 *
 * An example of such functionality is {@link ResolvedJavaType#isLocal()}. This can be conveniently
 * implemented when JVMCI is running on the HotSpot heap as we can obtain the {@link Class} mirror
 * for the {@link ResolvedJavaType} and call {@link Class#isLocalClass()}. In a native image, there
 * is no {@link Class} mirror available in the native image heap so implementing this would involve
 * a call into VM native code that in turn would make an upcall into Java code executing on the
 * HotSpot heap. We have opted to defer implementing functionality such as this until there's a
 * demonstrated need for it.
 */
public class HotSpotJVMCIUnsupportedOperationError extends Error {

    public HotSpotJVMCIUnsupportedOperationError(String reason) {
        super(reason);
    }

    public HotSpotJVMCIUnsupportedOperationError(String reason, Throwable cause) {
        super(reason, cause);
    }

    private static final long serialVersionUID = 7782431672678016392L;

}
