import jdk.internal.javac.ParticipatesInPreview;

/**
 * Defines an API for expressing computations that can be reliably compiled
 * at runtime into SIMD instructions, such as AVX instructions on x64, and
 * NEON instructions on AArch64.
 * {@Incubating}
 *
 * @moduleGraph
 */
@ParticipatesInPreview
module jdk.incubator.vector {
    exports jdk.incubator.vector;
}
