package com.sun.hotspot.igv.graph;

import com.sun.hotspot.igv.data.InputNode;
import com.sun.hotspot.igv.data.Properties;
import com.sun.hotspot.igv.data.Source;
import com.sun.hotspot.igv.layout.Port;
import com.sun.hotspot.igv.layout.Vertex;
import com.sun.hotspot.igv.util.StringUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Thomas Wuerthinger
 */
public abstract class Slot implements Port, Source.Provider, Properties.Provider {

    private int wantedIndex;
    private Source source;
    protected List<FigureConnection> connections;
    private InputNode associatedNode;
    private Color color;
    private String text;
    private String shortName;
    private Figure figure;

    protected Slot(Figure figure, int wantedIndex) {
        this.figure = figure;
        connections = new ArrayList<>(2);
        source = new Source();
        this.wantedIndex = wantedIndex;
        text = "";
        shortName = "";
        assert figure != null;
    }

    @Override
    public Properties getProperties() {
        Properties p = new Properties();
        if (hasSourceNodes()) {
            for (InputNode n : source.getSourceNodes()) {
                p.add(n.getProperties());
            }
        } else {
            p.setProperty("name", "Slot");
            p.setProperty("figure", figure.getProperties().get("name"));
            p.setProperty("connectionCount", Integer.toString(connections.size()));
        }
        return p;
    }
    public static final Comparator<Slot> slotIndexComparator = Comparator.comparingInt(o -> o.wantedIndex);

    public void setAssociatedNode(InputNode node) {
        associatedNode = node;
    }

    public int getWidth() {
        if (shortName == null || shortName.length() <= 1) {
            return Figure.SLOT_WIDTH;
        } else {
            BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            g.setFont(Diagram.SLOT_FONT.deriveFont(Font.BOLD));
            FontMetrics metrics = g.getFontMetrics();
            return Math.max(Figure.SLOT_WIDTH, metrics.stringWidth(shortName) + 6);
        }
    }

    public int getWantedIndex() {
        return wantedIndex;
    }

    @Override
    public Source getSource() {
        return source;
    }

    public String getText() {
        return text;
    }

    public void setShortName(String s) {
        assert s != null;
//        assert s.length() <= 2;
        this.shortName = s;

    }

    public String getShortName() {
        return shortName;
    }

    public String getToolTipText() {
        StringBuilder sb = new StringBuilder();
        String shortNodeText = figure.getDiagram().getShortNodeText();
        if (!text.isEmpty()) {
            sb.append(text);
            if (!shortNodeText.isEmpty()) {
                sb.append(": ");
            }
        }

        for (InputNode n : getSource().getSourceNodes()) {
            sb.append(StringUtils.escapeHTML(n.getProperties().resolveString(shortNodeText)));
        }

        return sb.toString();
    }

    public boolean shouldShowName() {
        return getShortName() != null && getShortName().length() > 0;
    }

    public boolean hasSourceNodes() {
        return !getSource().getSourceNodes().isEmpty();
    }

    public void setText(String s) {
        if (s == null) {
            s = "";
        }
        this.text = s;
    }

    public Figure getFigure() {
        assert figure != null;
        return figure;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color c) {
        color = c;
    }

    public List<FigureConnection> getConnections() {
        return Collections.unmodifiableList(connections);
    }

    public void removeAllConnections() {
        List<FigureConnection> connectionsCopy = new ArrayList<>(this.connections);
        for (FigureConnection c : connectionsCopy) {
            c.remove();
        }
    }

    @Override
    public Vertex getVertex() {
        return figure;
    }

    public abstract int getPosition();

    public abstract void setPosition(int position);
}

