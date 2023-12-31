package com.sun.hotspot.igv.bytecodes;

import com.sun.hotspot.igv.data.InputBytecode;
import com.sun.hotspot.igv.data.InputGraph;
import com.sun.hotspot.igv.data.InputNode;
import com.sun.hotspot.igv.data.Properties;
import com.sun.hotspot.igv.data.Properties.StringPropertyMatcher;
import java.awt.Image;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Thomas Wuerthinger
 */
public class BytecodeNode extends AbstractNode {

    private Set<InputNode> nodes;

    public BytecodeNode(InputBytecode bytecode, InputGraph graph, String bciValue) {

        super(Children.LEAF);
        String displayName = bytecode.getBci() + " " + bytecode.getName() + " " + bytecode.getOperands();

        bciValue = bytecode.getBci() + " " + bciValue;
        bciValue = bciValue.trim();

        Properties.PropertySelector<InputNode> selector = new Properties.PropertySelector<>(graph.getNodes());
        StringPropertyMatcher matcher = new StringPropertyMatcher("bci", bciValue);
        List<InputNode> nodeList = selector.selectMultiple(matcher);
        if (nodeList.size() > 0) {
            nodes = new LinkedHashSet<>();
            nodes.addAll(nodeList);
            displayName += " (" + nodes.size() + " nodes)";
        }

        if (bytecode.getComment() != null) {
            displayName += " // " + bytecode.getComment();
        }

        this.setDisplayName(displayName);
    }

    @Override
    public Image getIcon(int i) {
        if (nodes != null) {
            return ImageUtilities.loadImage("com/sun/hotspot/igv/bytecodes/images/link.png");
        } else {
            return ImageUtilities.loadImage("com/sun/hotspot/igv/bytecodes/images/bytecode.png");
        }
    }

    @Override
    public Image getOpenedIcon(int i) {
        return getIcon(i);
    }

    @Override
    public Action[] getActions(boolean b) {
        return new Action[]{SelectBytecodesAction.findObject(SelectBytecodesAction.class, true)};
    }

    @Override
    public Action getPreferredAction() {
        return SelectBytecodesAction.findObject(SelectBytecodesAction.class, true);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Node.Cookie> T getCookie(Class<T> aClass) {
        if (aClass == SelectBytecodesCookie.class && nodes != null) {
            return (T) (new SelectBytecodesCookie(nodes));
        }
        return super.getCookie(aClass);
    }
}
