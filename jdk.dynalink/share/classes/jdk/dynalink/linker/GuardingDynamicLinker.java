package jdk.dynalink.linker;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.List;
import jdk.dynalink.DynamicLinkerFactory;

/**
 * The base interface for language-specific dynamic linkers. Such linkers
 * always have to produce method handles with guards, as the validity of the
 * method handle for calls at a call site inevitably depends on some condition
 * (at the very least, it depends on the receiver belonging to the language
 * runtime of the linker). Language runtime implementors will normally implement
 * the linking logic for their own language as one or more
 * {@link GuardingDynamicLinker} classes. They will typically set them as
 * {@link DynamicLinkerFactory#setPrioritizedLinkers(List) prioritized linkers}
 * in the {@code DynamicLinkerFactory} they configure for themselves, and maybe also
 * set some as {@link DynamicLinkerFactory#setFallbackLinkers(List) fallback
 * linkers} to handle language-specific "property not found" etc. conditions.
 * <p>
 * Consider implementing {@link TypeBasedGuardingDynamicLinker} interface
 * instead of this interface for those linkers that are based on the Java class
 * of the objects. If you need to implement language-specific type conversions,
 * have your {@code GuardingDynamicLinker} also implement the
 * {@link GuardingTypeConverterFactory} interface.
 * <p>
 * Languages can export linkers to other language runtimes for
 * {@link DynamicLinkerFactory#setClassLoader(ClassLoader) automatic discovery}
 * using a {@link GuardingDynamicLinkerExporter}.
 */
public interface GuardingDynamicLinker {
    /**
     * Creates a guarded invocation appropriate for a particular invocation with
     * the specified arguments at a call site.
     *
     * @param linkRequest the object describing the request for linking a
     * particular invocation
     * @param linkerServices linker services
     * @return a guarded invocation with a method handle suitable for the
     * arguments, as well as a guard condition that if fails should trigger
     * relinking. Must return null if it can't resolve the invocation. If the
     * returned invocation is unconditional (which is actually quite rare), the
     * guard in the return value can be null. The invocation can also have any
     * number of switch points for asynchronous invalidation of the linkage, as
     * well as a {@link Throwable} subclass that describes an expected exception
     * condition that also triggers relinking (often it is faster to rely on an
     * infrequent but expected {@link ClassCastException} than on an always
     * evaluated {@code instanceof} guard). While the linker must produce an
     * invocation with parameter types matching those in the call site
     * descriptor of the link request, it should not try to match the return
     * type expected at the call site except when it can do it with only the
     * conversions that lose neither precision nor magnitude, see
     * {@link LinkerServices#asTypeLosslessReturn(MethodHandle, MethodType)} for
     * further explanation.
     * @throws Exception if the operation fails for whatever reason
     */
    public GuardedInvocation getGuardedInvocation(LinkRequest linkRequest, LinkerServices linkerServices)
            throws Exception;
}
