package jdk.vm.ci.aarch64;

import jdk.vm.ci.meta.PlatformKind;

public enum AArch64Kind implements PlatformKind {

    // scalar
    BYTE(1),
    WORD(2),
    DWORD(4),
    QWORD(8),
    SINGLE(4),
    DOUBLE(8),

    // SIMD
    V32_BYTE(4, BYTE),
    V32_WORD(4, WORD),
    V64_BYTE(8, BYTE),
    V64_WORD(8, WORD),
    V64_DWORD(8, DWORD),
    V128_BYTE(16, BYTE),
    V128_WORD(16, WORD),
    V128_DWORD(16, DWORD),
    V128_QWORD(16, QWORD),
    V128_SINGLE(16, SINGLE),
    V128_DOUBLE(16, DOUBLE);

    private final int size;
    private final int vectorLength;

    private final AArch64Kind scalar;
    private final EnumKey<AArch64Kind> key = new EnumKey<>(this);

    AArch64Kind(int size) {
        this.size = size;
        this.scalar = this;
        this.vectorLength = 1;
    }

    AArch64Kind(int size, AArch64Kind scalar) {
        this.size = size;
        this.scalar = scalar;

        assert size % scalar.size == 0;
        this.vectorLength = size / scalar.size;
    }

    public AArch64Kind getScalar() {
        return scalar;
    }

    @Override
    public int getSizeInBytes() {
        return size;
    }

    @Override
    public int getVectorLength() {
        return vectorLength;
    }

    @Override
    public Key getKey() {
        return key;
    }

    public boolean isInteger() {
        switch (this) {
            case BYTE:
            case WORD:
            case DWORD:
            case QWORD:
                return true;
            default:
                return false;
        }
    }

    public boolean isSIMD() {
        switch (this) {
            case SINGLE:
            case DOUBLE:
            case V32_BYTE:
            case V32_WORD:
            case V64_BYTE:
            case V64_WORD:
            case V64_DWORD:
            case V128_BYTE:
            case V128_WORD:
            case V128_DWORD:
            case V128_QWORD:
            case V128_SINGLE:
            case V128_DOUBLE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public char getTypeChar() {
        switch (this) {
            case BYTE:
                return 'b';
            case WORD:
                return 'w';
            case DWORD:
                return 'd';
            case QWORD:
                return 'q';
            case SINGLE:
                return 'S';
            case DOUBLE:
                return 'D';
            case V32_BYTE:
            case V32_WORD:
            case V64_BYTE:
            case V64_WORD:
            case V64_DWORD:
            case V128_BYTE:
            case V128_WORD:
            case V128_DWORD:
            case V128_QWORD:
            case V128_SINGLE:
            case V128_DOUBLE:
                return 'v';
            default:
                return '-';
        }
    }
}
