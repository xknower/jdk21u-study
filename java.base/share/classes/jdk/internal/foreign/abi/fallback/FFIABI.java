package jdk.internal.foreign.abi.fallback;

/**
 * enum which maps the {@code ffi_abi} enum
 */
enum FFIABI {
    DEFAULT(LibFallback.defaultABI());

    private final int value;

    FFIABI(int abi) {
        this.value = abi;
    }

    int value() {
        return value;
    }
}
