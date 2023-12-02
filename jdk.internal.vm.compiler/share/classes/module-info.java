/**
  * JVMCI compiler implementation for the JVM.
  *
  * This is an empty and upgradeable module that is a placeholder for an
  * external implementation of a JVMCI compiler. It must be upgradeable so
  * that it can be replaced when jlinking a new JDK image without failing
  * the hash check for the qualified exports in jdk.internal.vm.ci's
  * module descriptor.
  *
  * @moduleGraph
  * @since 9
  */
module jdk.internal.vm.compiler {
    requires jdk.internal.vm.ci;
}
