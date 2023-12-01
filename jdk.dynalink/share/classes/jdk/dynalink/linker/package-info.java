/**
 * <p>
 * Contains interfaces and classes needed by language runtimes to implement
 * their own language-specific object models and type conversions. The main
 * entry point is the
 * {@link jdk.dynalink.linker.GuardingDynamicLinker} interface. It needs to be
 * implemented in order to provide linking for the runtime's own object model.
 * A language runtime can have more than one guarding dynamic linker
 * implementation. When a runtime is configuring Dynalink for itself, it will
 * normally set these guarding linkers as the prioritized linkers in its
 * {@link jdk.dynalink.DynamicLinkerFactory} (and maybe some of them as fallback
 * linkers, for e.g. handling "method not found" and similar errors in a
 * language-specific manner if no other linker managed to handle the operation.)
 * </p><p>
 * A language runtime that wishes to make at least some of its linkers available
 * to other language runtimes for interoperability will need to use a
 * {@link jdk.dynalink.linker.GuardingDynamicLinkerExporter}.
 * </p><p>
 * Most language runtimes will be able to implement their own linking logic by
 * implementing {@link jdk.dynalink.linker.TypeBasedGuardingDynamicLinker}
 * instead of {@link jdk.dynalink.linker.GuardingDynamicLinker}; it allows for
 * faster type-based linking dispatch.
 * </p><p>
 * Language runtimes that allow type conversions other than those provided by
 * Java will need to have their guarding dynamic linker (or linkers) also
 * implement the {@link jdk.dynalink.linker.GuardingTypeConverterFactory}
 * interface to provide the logic for these conversions.
 * </p>
 * @since 9
 */
package jdk.dynalink.linker;
