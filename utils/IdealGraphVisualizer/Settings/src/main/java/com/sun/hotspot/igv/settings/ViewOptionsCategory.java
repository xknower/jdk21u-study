package com.sun.hotspot.igv.settings;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.spi.options.OptionsCategory;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

/**
 *
 * @author Thomas Wuerthinger
 */
public final class ViewOptionsCategory extends OptionsCategory {

    @Override
    public Icon getIcon() {
        return new ImageIcon(ImageUtilities.loadImage("com/sun/hotspot/igv/settings/settings.png"));
    }

    @Override
    public String getCategoryName() {
        return NbBundle.getMessage(ViewOptionsCategory.class, "OptionsCategory_Name_View");
    }

    @Override
    public String getTitle() {
        return NbBundle.getMessage(ViewOptionsCategory.class, "OptionsCategory_Title_View");
    }

    @Override
    public OptionsPanelController create() {
        return new ViewOptionsPanelController();
    }
}
