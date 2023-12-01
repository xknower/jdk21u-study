package sun.jvm.hotspot.debugger.posix.elf;

public interface ELFSymbol {
    /** Binding specifying that local symbols are not visible outside the
     * object file that contains its definition. */
    public static final int BINDING_LOCAL = 0;
    /** Binding specifying that global symbols are visible to all object files
     * being combined. */
    public static final int BINDING_GLOBAL = 1;
    /** Binding secifying that the symbol resembles a global symbol, but has
     * a lower precedence. */
    public static final int BINDING_WEAK = 2;
    /** Lower bound binding values reserved for processor specific
     * semantics. */
    public static final int BINDING_LOPROC = 13;
    /** Upper bound binding values reserved for processor specific
     * semantics. */
    public static final int BINDING_HIPROC = 15;

    /** Type specifying that the symbol is unspecified. */
    public static final byte TYPE_NOOBJECT = 0;
    /** Type specifying that the symbol is associated with an object. */
    public static final byte TYPE_OBJECT = 1;
    /** Type specifying that the symbol is associated with a function. */
    public static final byte TYPE_FUNCTION = 2;
    /** Type specifying that the symbol is associated with a section.  Symbol
     * table entries of this type exist for relocation and normally have the
     * binding BINDING_LOCAL. */
    public static final byte TYPE_SECTION = 3;
    /** Type defining that the symbol is associated with a file. */
    public static final byte TYPE_FILE = 4;
    /** Lower bound type reserved for processor specific semantics. */
    public static final byte TYPE_LOPROC = 13;
    /** Upper bound type reserved for processor specific semantics. */
    public static final byte TYPE_HIPROC = 15;

    /** Returns the location from the beginning of the file to the symbol. */
    public long getOffset();
    /** Returns the name of the symbol or null if the symbol has no name. */
    public String getName();
    /** Returns the binding for this symbol. */
    public int getBinding();
    /** Returns the symbol type. */
    public int getType();

    /** Value of the associated symbol.  This may be a relativa address for .so
     * or absolute address for other ELFs. */
    public long getValue();

    /** Size of the symbol.  0 if the symbol has no size or the size
     * is unknown. */
    public long getSize();
}
