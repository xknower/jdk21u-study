package com.sun.tools.classfile;

import java.io.IOException;

/**
 * See JVMS, section 4.8.15.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class Deprecated_attribute extends Attribute {
    Deprecated_attribute(ClassReader cr, int name_index, int length) throws IOException {
        super(name_index, length);
    }

    public Deprecated_attribute(ConstantPool constant_pool)
            throws ConstantPoolException {
        this(constant_pool.getUTF8Index(Attribute.Deprecated));
    }

    public Deprecated_attribute(int name_index) {
        super(name_index, 0);
    }

    public <R, D> R accept(Visitor<R, D> visitor, D data) {
        return visitor.visitDeprecated(this, data);
    }
}
