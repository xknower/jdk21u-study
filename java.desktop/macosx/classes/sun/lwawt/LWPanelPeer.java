package sun.lwawt;

import java.awt.Panel;
import java.awt.peer.PanelPeer;

import javax.swing.JPanel;

/**
 * Lightweight implementation of {@link PanelPeer}. Delegates most of the work
 * to the {@link JPanel}.
 */
final class LWPanelPeer extends LWContainerPeer<Panel, JPanel>
        implements PanelPeer {

    LWPanelPeer(final Panel target, final PlatformComponent platformComponent) {
        super(target, platformComponent);
    }

    @Override
    JPanel createDelegate() {
        return new JPanel();
    }
}
