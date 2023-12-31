package com.sun.tools.javap;

import com.sun.tools.classfile.Attribute;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.Descriptor.InvalidDescriptor;
import com.sun.tools.classfile.Instruction;
import com.sun.tools.classfile.LocalVariableTable_attribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Annotate instructions with details about local variables.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class LocalVariableTableWriter extends  InstructionDetailWriter {
    public enum NoteKind {
        START("start") {
            public boolean match(LocalVariableTable_attribute.Entry entry, int pc) {
                return (pc == entry.start_pc);
            }
        },
        END("end") {
            public boolean match(LocalVariableTable_attribute.Entry entry, int pc) {
                return (pc == entry.start_pc + entry.length);
            }
        };
        NoteKind(String text) {
            this.text = text;
        }
        public abstract boolean match(LocalVariableTable_attribute.Entry entry, int pc);
        public final String text;
    }

    static LocalVariableTableWriter instance(Context context) {
        LocalVariableTableWriter instance = context.get(LocalVariableTableWriter.class);
        if (instance == null)
            instance = new LocalVariableTableWriter(context);
        return instance;
    }

    protected LocalVariableTableWriter(Context context) {
        super(context);
        context.put(LocalVariableTableWriter.class, this);
        classWriter = ClassWriter.instance(context);
    }

    public void reset(Code_attribute attr) {
        codeAttr = attr;
        pcMap = new HashMap<>();
        LocalVariableTable_attribute lvt =
                (LocalVariableTable_attribute) (attr.attributes.get(Attribute.LocalVariableTable));
        if (lvt == null)
            return;

        for (int i = 0; i < lvt.local_variable_table.length; i++) {
            LocalVariableTable_attribute.Entry entry = lvt.local_variable_table[i];
            put(entry.start_pc, entry);
            put(entry.start_pc + entry.length, entry);
        }
    }

    public void writeDetails(Instruction instr) {
        int pc = instr.getPC();
        writeLocalVariables(pc, NoteKind.END);
        writeLocalVariables(pc, NoteKind.START);
    }

    @Override
    public void flush() {
        int pc = codeAttr.code_length;
        writeLocalVariables(pc, NoteKind.END);
    }

    public void writeLocalVariables(int pc, NoteKind kind) {
        ConstantPool constant_pool = classWriter.getClassFile().constant_pool;
        String indent = space(2); // get from Options?
        List<LocalVariableTable_attribute.Entry> entries = pcMap.get(pc);
        if (entries != null) {
            for (ListIterator<LocalVariableTable_attribute.Entry> iter =
                    entries.listIterator(kind == NoteKind.END ? entries.size() : 0);
                    kind == NoteKind.END ? iter.hasPrevious() : iter.hasNext() ; ) {
                LocalVariableTable_attribute.Entry entry =
                        kind == NoteKind.END ? iter.previous() : iter.next();
                if (kind.match(entry, pc)) {
                    print(indent);
                    print(kind.text);
                    print(" local ");
                    print(entry.index);
                    print(" // ");
                    Descriptor d = new Descriptor(entry.descriptor_index);
                    try {
                        print(d.getFieldType(constant_pool));
                    } catch (InvalidDescriptor e) {
                        print(report(e));
                    } catch (ConstantPoolException e) {
                        print(report(e));
                    }
                    print(" ");
                    try {
                        print(constant_pool.getUTF8Value(entry.name_index));
                    } catch (ConstantPoolException e) {
                        print(report(e));
                    }
                    println();
                }
            }
        }
    }

    private void put(int pc, LocalVariableTable_attribute.Entry entry) {
        List<LocalVariableTable_attribute.Entry> list = pcMap.get(pc);
        if (list == null) {
            list = new ArrayList<>();
            pcMap.put(pc, list);
        }
        if (!list.contains(entry))
            list.add(entry);
    }

    private ClassWriter classWriter;
    private Code_attribute codeAttr;
    private Map<Integer, List<LocalVariableTable_attribute.Entry>> pcMap;
}
