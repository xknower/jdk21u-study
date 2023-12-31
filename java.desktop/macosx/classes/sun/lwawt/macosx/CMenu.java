package sun.lwawt.macosx;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.peer.MenuItemPeer;
import java.awt.peer.MenuPeer;

public class CMenu extends CMenuItem implements MenuPeer {

    public CMenu(Menu target) {
        super(target);
    }

    // This way we avoiding invocation of the setters twice
    @Override
    protected final void initialize(MenuItem target) {
        setLabel(target.getLabel());
        setEnabled(target.isEnabled());
    }

    @Override
    public final void setEnabled(final boolean b) {
        super.setEnabled(b);
        final Menu target = (Menu) getTarget();
        final int count = target.getItemCount();
        for (int i = 0; i < count; ++i) {
            MenuItem item = target.getItem(i);
            MenuItemPeer p = (MenuItemPeer) LWCToolkit.targetToPeer(item);
            if (p != null) {
                p.setEnabled(b && item.isEnabled());
            }
        }
    }

    @Override
    long createModel() {
        CMenuComponent parent = (CMenuComponent)
            LWCToolkit.targetToPeer(getTarget().getParent());

        if (parent instanceof CMenu) {
            return parent.executeGet(this::nativeCreateSubMenu);
        }
        if (parent instanceof CMenuBar) {
            MenuBar parentContainer = (MenuBar)getTarget().getParent();
            boolean isHelpMenu = parentContainer.getHelpMenu() == getTarget();
            int insertionLocation = ((CMenuBar)parent).getNextInsertionIndex();
            return parent.executeGet(ptr -> nativeCreateMenu(ptr, isHelpMenu,
                                                             insertionLocation));
        }
        throw new InternalError("Parent must be CMenu or CMenuBar");
    }

    @Override
    public final void addItem(MenuItem item) {
        // Nothing to do here -- we added it when we created the
        // menu item's peer.
    }

    @Override
    public final void delItem(final int index) {
        execute(ptr -> nativeDeleteItem(ptr, index));
    }

    @Override
    public final void setLabel(final String label) {
        execute(ptr->nativeSetMenuTitle(ptr, label));
        super.setLabel(label);
    }

    // Used by ScreenMenuBar to get to the native menu for event handling.
    public final long getNativeMenu() {
        return executeGet(this::nativeGetNSMenu);
    }

    private native long nativeCreateMenu(long parentMenuPtr,
                                         boolean isHelpMenu,
                                         int insertionLocation);
    private native long nativeCreateSubMenu(long parentMenuPtr);
    private native void nativeSetMenuTitle(long menuPtr, String title);
    private native void nativeDeleteItem(long menuPtr, int index);

    // Returns a retained NSMenu object! We have to explicitly
    // release at some point!
    private native long nativeGetNSMenu(long menuPtr);
}
