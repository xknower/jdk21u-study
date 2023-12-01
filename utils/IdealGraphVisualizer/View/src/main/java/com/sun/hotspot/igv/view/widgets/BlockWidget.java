package com.sun.hotspot.igv.view.widgets;

import com.sun.hotspot.igv.data.InputBlock;
import com.sun.hotspot.igv.data.services.InputGraphProvider;
import com.sun.hotspot.igv.util.DoubleClickHandler;
import com.sun.hotspot.igv.util.LookupHistory;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import org.netbeans.api.visual.action.WidgetAction;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import org.openide.util.Utilities;

/**
 *
 * @author Thomas Wuerthinger
 */
public class BlockWidget extends Widget implements DoubleClickHandler {

    public static final Color BACKGROUND_COLOR = new Color(235, 235, 255);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 14);
    public static final Color TITLE_COLOR = new Color(42, 42, 171);
    private final InputBlock blockNode;

    public BlockWidget(Scene scene, InputBlock blockNode) {
        super(scene);
        this.blockNode = blockNode;
        this.setBackground(BACKGROUND_COLOR);
        this.setOpaque(true);
        this.setCheckClipping(true);
    }

    @Override
    protected void paintWidget() {
        super.paintWidget();
        Graphics2D g = this.getGraphics();
        Stroke old = g.getStroke();
        g.setColor(Color.BLUE);
        Rectangle r = new Rectangle(this.getPreferredBounds());
        r.width--;
        r.height--;
        if (this.getBounds().width > 0 && this.getBounds().height > 0) {
            g.setStroke(new BasicStroke(2));
            g.drawRect(r.x, r.y, r.width, r.height);
        }

        g.setColor(TITLE_COLOR);
        g.setFont(TITLE_FONT);

        String s = "B" + blockNode.getName();
        Rectangle2D r1 = g.getFontMetrics().getStringBounds(s, g);
        g.drawString(s, r.x + 5, r.y + (int) r1.getHeight());
        g.setStroke(old);
    }

    private void addToSelection(BlockWidget blockWidget, boolean additiveSelection) {
        InputGraphProvider graphProvider = LookupHistory.getLast(InputGraphProvider.class);
        if (graphProvider != null) {
            if (!additiveSelection) {
                graphProvider.clearSelectedNodes();
            }
            graphProvider.addSelectedNodes(blockWidget.blockNode.getNodes(), false);
        }
    }

    private int getModifierMask () {
        return Utilities.isMac() ? MouseEvent.META_DOWN_MASK : MouseEvent.CTRL_DOWN_MASK;
    }

    @Override
    public void handleDoubleClick(Widget widget, WidgetAction.WidgetMouseEvent event) {
        assert widget instanceof BlockWidget;
        BlockWidget blockWidget = (BlockWidget) widget;
        boolean additiveSelection = (event.getModifiersEx() & getModifierMask()) != 0;
        addToSelection(blockWidget, additiveSelection);
    }
}
