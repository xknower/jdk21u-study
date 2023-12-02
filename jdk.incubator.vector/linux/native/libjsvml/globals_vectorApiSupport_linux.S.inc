// This file is used to provide some global declarations related to building
// VM with Vector API support. Also, the reason the file is separated is because
// the intent of this file to provide a header that can be included in .s files.

#ifndef OS_CPU_LINUX_X86_GLOBALS_VECTORAPISUPPORT_LINUX_HPP
#define OS_CPU_LINUX_X86_GLOBALS_VECTORAPISUPPORT_LINUX_HPP

// GCC 4.9+ can build all .s files for Linux
#if defined(_LP64) && (defined(__GNUC__) && ((__GNUC__ > 4) || ((__GNUC__ == 4) && (__GNUC_MINOR__ >= 9))))
#define __VECTOR_API_MATH_INTRINSICS_LINUX
#endif

#endif //OS_CPU_LINUX_X86_GLOBALS_VECTORAPISUPPORT_LINUX_HPP

