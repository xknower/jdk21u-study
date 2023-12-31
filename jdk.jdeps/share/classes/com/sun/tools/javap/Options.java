package com.sun.tools.javap;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import com.sun.tools.classfile.AccessFlags;

/*
 *  Provides access to javap's options, set via the command line
 *  or JSR 199 API.
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class Options {
    public static Options instance(Context context) {
        Options instance = context.get(Options.class);
        if (instance == null)
            instance = new Options(context);
        return instance;
    }

    protected Options(Context context) {
        context.put(Options.class, this);
    }

    /**
     * Checks access of class, field or method.
     */
    public boolean checkAccess(AccessFlags flags){

        boolean isPublic = flags.is(AccessFlags.ACC_PUBLIC);
        boolean isProtected = flags.is(AccessFlags.ACC_PROTECTED);
        boolean isPrivate = flags.is(AccessFlags.ACC_PRIVATE);
        boolean isPackage = !(isPublic || isProtected || isPrivate);

        if ((showAccess == AccessFlags.ACC_PUBLIC) && (isProtected || isPrivate || isPackage))
            return false;
        else if ((showAccess == AccessFlags.ACC_PROTECTED) && (isPrivate || isPackage))
            return false;
        else if ((showAccess == 0) && (isPrivate))
            return false;
        else
            return true;
    }

    public boolean help;
    public boolean verbose;
    public boolean version;
    public boolean fullVersion;
    public boolean showFlags;
    public boolean showLineAndLocalVariableTables;
    public int showAccess;
    public Set<String> accessOptions = new HashSet<>();
    public Set<InstructionDetailWriter.Kind> details = EnumSet.noneOf(InstructionDetailWriter.Kind.class);
    public boolean showDisassembled;
    public boolean showDescriptors;
    public boolean showAllAttrs;
    public boolean showConstants;
    public boolean sysInfo;
    public boolean showInnerClasses;
    public int indentWidth = 2;   // #spaces per indentWidth level; must be > 0
    public int tabColumn = 40;    // column number for comments; must be > 0
    public String moduleName;
}
