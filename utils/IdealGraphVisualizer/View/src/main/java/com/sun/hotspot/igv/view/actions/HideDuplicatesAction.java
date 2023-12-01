package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.view.EditorTopComponent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.openide.util.ImageUtilities;

/**
 *
 * @author Tom Rodriguez
 */
public class HideDuplicatesAction extends AbstractAction {

    public HideDuplicatesAction(boolean selected) {
        putValue(AbstractAction.SMALL_ICON, new ImageIcon(ImageUtilities.loadImage(iconResource())));
        putValue(Action.SELECTED_KEY, selected);
        putValue(Action.SHORT_DESCRIPTION, "Hide graphs which are the same as the previous graph");
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        EditorTopComponent editor = EditorTopComponent.getActive();
        if (editor != null) {
            boolean selected = (boolean)getValue(SELECTED_KEY);
            editor.getModel().setHideDuplicates(selected);
        }
    }

    protected String iconResource() {
        return "com/sun/hotspot/igv/view/images/hideDuplicates.png";
    }
}
