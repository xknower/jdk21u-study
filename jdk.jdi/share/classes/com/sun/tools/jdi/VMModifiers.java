package com.sun.tools.jdi;

public interface VMModifiers {
    int PUBLIC = 0x00000001;        /* visible to everyone */
    int PRIVATE = 0x00000002;       /* visible only to the defining class */
    int PROTECTED = 0x00000004;     /* visible to subclasses */
    int STATIC = 0x00000008;        /* instance variable is static */
    int FINAL = 0x00000010;         /* no further subclassing, overriding */
    int SYNCHRONIZED = 0x00000020;  /* wrap method call in monitor lock */
    int VOLATILE = 0x00000040;      /* can cache in registers */
    int BRIDGE = 0x00000040;        /* Bridge method generated by compiler */
    int TRANSIENT = 0x00000080;     /* not persistent */
    int VARARGS = 0x00000080;       /* Method accepts var. args*/
    int NATIVE = 0x00000100;        /* implemented in C */
    int INTERFACE = 0x00000200;     /* class is an interface */
    int ABSTRACT = 0x00000400;      /* no definition provided */
    int ENUM_CONSTANT = 0x00004000; /* enum constant field*/
    int SYNTHETIC = 0xf0000000;     /* not in source code */
}
