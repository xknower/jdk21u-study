package sun.jvm.hotspot.debugger.posix.elf;

public interface ELFHashTable {
    /**
     * Returns the ELFSymbol that has the specified name or null if no symbol
     * with that name exists.  NOTE: Currently this method does not work and
     * will always return null.
     */
    public ELFSymbol getSymbol(String symbolName);
}
