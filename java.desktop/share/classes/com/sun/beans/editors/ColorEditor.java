package com.sun.beans.editors;

import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Panel;
import java.awt.TextField;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyEditor;
import java.io.Serial;

public class ColorEditor extends Panel implements PropertyEditor {

    /**
     * Use serialVersionUID from JDK 1.7 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 1781257185164716054L;

    @SuppressWarnings("deprecation")
    public ColorEditor() {
        setLayout(null);

        ourWidth = hPad;

        // Create a sample color block bordered in black
        Panel p = new Panel();
        p.setLayout(null);
        p.setBackground(Color.black);
        sample = new Canvas();
        p.add(sample);
        sample.reshape(2, 2, sampleWidth, sampleHeight);
        add(p);
        p.reshape(ourWidth, 2, sampleWidth+4, sampleHeight+4);
        ourWidth += sampleWidth + 4 + hPad;

        text = new TextField("", 14);
        add(text);
        text.reshape(ourWidth,0,100,30);
        ourWidth += 100 + hPad;

        chooser = new Choice();
        int active = 0;
        for (int i = 0; i < colorNames.length; i++) {
            chooser.addItem(colorNames[i]);
        }
        add(chooser);
        chooser.reshape(ourWidth,0,100,30);
        ourWidth += 100 + hPad;

        resize(ourWidth,40);
    }

    public void setValue(Object o) {
        Color c = (Color)o;
        changeColor(c);
    }

    @SuppressWarnings("deprecation")
    public Dimension preferredSize() {
        return new Dimension(ourWidth, 40);
    }

    @SuppressWarnings("deprecation")
    public boolean keyUp(Event e, int key) {
        if (e.target == text) {
            try {
                setAsText(text.getText());
            } catch (IllegalArgumentException ex) {
                // Quietly ignore.
            }
        }
        return (false);
    }

    public void setAsText(String s) throws java.lang.IllegalArgumentException {
        if (s == null) {
            changeColor(null);
            return;
        }
        int c1 = s.indexOf(',');
        int c2 = s.indexOf(',', c1+1);
        if (c1 < 0 || c2 < 0) {
            // Invalid string.
            throw new IllegalArgumentException(s);
        }
        try {
            int r = Integer.parseInt(s.substring(0,c1));
            int g = Integer.parseInt(s.substring(c1+1, c2));
            int b = Integer.parseInt(s.substring(c2+1));
            Color c = new Color(r,g,b);
            changeColor(c);
        } catch (Exception ex) {
            throw new IllegalArgumentException(s);
        }

    }

    @SuppressWarnings("deprecation")
    public boolean action(Event e, Object arg) {
        if (e.target == chooser) {
            changeColor(colors[chooser.getSelectedIndex()]);
        }
        return false;
    }

    public String getJavaInitializationString() {
        return (this.color != null)
                ? "new java.awt.Color(" + this.color.getRGB() + ",true)"
                : "null";
    }


    private void changeColor(Color c) {

        if (c == null) {
            this.color = null;
            this.text.setText("");
            return;
        }

        color = c;

        text.setText("" + c.getRed() + "," + c.getGreen() + "," + c.getBlue());

        int active = 0;
        for (int i = 0; i < colorNames.length; i++) {
            if (color.equals(colors[i])) {
                active = i;
            }
        }
        chooser.select(active);

        sample.setBackground(color);
        sample.repaint();

        support.firePropertyChange("", null, null);
    }

    public Object getValue() {
        return color;
    }

    public boolean isPaintable() {
        return true;
    }

    public void paintValue(java.awt.Graphics gfx, java.awt.Rectangle box) {
        Color oldColor = gfx.getColor();
        gfx.setColor(Color.black);
        gfx.drawRect(box.x, box.y, box.width-3, box.height-3);
        gfx.setColor(color);
        gfx.fillRect(box.x+1, box.y+1, box.width-4, box.height-4);
        gfx.setColor(oldColor);
    }

    public String getAsText() {
        return (this.color != null)
                ? this.color.getRed() + "," + this.color.getGreen() + "," + this.color.getBlue()
                : null;
    }

    public String[] getTags() {
        return null;
    }

    public java.awt.Component getCustomEditor() {
        return this;
    }

    public boolean supportsCustomEditor() {
        return true;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        support.removePropertyChangeListener(l);
    }


    private String[] colorNames = { " ", "white", "lightGray", "gray", "darkGray",
                        "black", "red", "pink", "orange",
                        "yellow", "green", "magenta", "cyan",
                        "blue"};
    private Color[] colors = { null, Color.white, Color.lightGray, Color.gray, Color.darkGray,
                        Color.black, Color.red, Color.pink, Color.orange,
                        Color.yellow, Color.green, Color.magenta, Color.cyan,
                        Color.blue};

    private Canvas sample;
    private int sampleHeight = 20;
    private int sampleWidth = 40;
    private int hPad = 5;
    private int ourWidth;

    private Color color;
    private TextField text;
    private Choice chooser;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);
}
