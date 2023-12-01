package jdk.internal.access;

public interface JavaAWTFontAccess {

    // java.awt.font.TextAttribute constants
    public Object getTextAttributeConstant(String name);

    // java.awt.font.NumericShaper
    public void shape(Object shaper, char[] text, int start, int count);
}
