package jdk.jfr.internal.tool;

import java.io.PrintWriter;
import java.util.List;

import jdk.jfr.EventType;
import jdk.jfr.ValueDescriptor;
import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordedFrame;
import jdk.jfr.consumer.RecordedObject;

final class XMLWriter extends EventPrintWriter {
    public XMLWriter(PrintWriter destination) {
        super(destination);
    }

    @Override
    protected void printBegin() {
        println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        println("<recording xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
        indent();
        printIndent();
        println("<events>");
        indent();
    }

    @Override
    protected void printEnd() {
        retract();
        printIndent();
        println("</events>");
        retract();
        println("</recording>");
    }

    @Override
    protected void print(List<RecordedEvent> events) {
        for (RecordedEvent event : events) {
            printEvent(event);
        }
    }

    private void printEvent(RecordedEvent event) {
        EventType type = event.getEventType();
        printIndent();
        print("<event");
        printAttribute("type", type.getName());
        print(">");
        println();
        indent();
        for (ValueDescriptor v : event.getFields()) {
            printValueDescriptor(v, getValue(event, v), -1);
        }
        retract();
        printIndent();
        println("</event>");
        println();
    }

    private void printAttribute(String name, String value) {
        print(" ");
        print(name); // Only known strings
        print("=\"");
        printEscaped(value);
        print("\"");
    }

    public void printObject(RecordedObject struct) {
        println();
        indent();
        for (ValueDescriptor v : struct.getFields()) {
            printValueDescriptor(v, getValue(struct, v), -1);
        }
        retract();
    }

    private void printArray(ValueDescriptor v, Object[] array) {
        println();
        indent();
        int depth = 0;
        for (int index = 0; index < array.length; index++) {
            Object arrayElement = array[index];
            if (!(arrayElement instanceof RecordedFrame) || depth < getStackDepth()) {
                printValueDescriptor(v, array[index], index);
            }
            depth++;
        }
        retract();
    }

    private void printValueDescriptor(ValueDescriptor vd, Object value, int index) {
        boolean arrayElement = index != -1;
        String name = arrayElement ? null : vd.getName();
        if (vd.isArray() && !arrayElement) {
            if (printBeginElement("array", name, value, index)) {
                printArray(vd, (Object[]) value);
                printIndent();
                printEndElement("array");
            }
            return;
        }
        if (!vd.getFields().isEmpty()) {
            if (printBeginElement("struct", name, value, index)) {
                printObject((RecordedObject) value);
                printIndent();
                printEndElement("struct");
            }
            return;
        }
        if (printBeginElement("value", name, value, index)) {
            printEscaped(String.valueOf(value));
            printEndElement("value");
        }
    }

    private boolean printBeginElement(String elementName, String name, Object value, int index) {
        printIndent();
        print("<", elementName);
        if (name != null) {
            printAttribute("name", name);
        }
        if (index != -1) {
            printAttribute("index", Integer.toString(index));
        }
        if (value == null) {
            printAttribute("xsi:nil", "true");
            println("/>");
            return false;
        }
        if (value.getClass().isArray()) {
            Object[] array = (Object[]) value;
            printAttribute("size", Integer.toString(array.length));
        }
        print(">");
        return true;
    }

    private void printEndElement(String elementName) {
        print("</");
        print(elementName);
        println(">");
    }

    private void printEscaped(String text) {
        for (int i = 0; i < text.length(); i++) {
            printEscaped(text.charAt(i));
        }
    }

    private void printEscaped(char c) {
        if (c == 34) {
            print("&quot;");
            return;
        }
        if (c == 38) {
            print("&amp;");
            return;
        }
        if (c == 39) {
            print("&apos;");
            return;
        }
        if (c == 60) {
            print("&lt;");
            return;
        }
        if (c == 62) {
            print("&gt;");
            return;
        }
        if (c > 0x7F) {
            print("&#");
            print((int) c);
            print(';');
            return;
        }
        print(c);
    }
}
