package sun.swing;

import java.awt.Component;
import java.awt.Point;
import java.lang.invoke.MethodHandles;
import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * The SwingAccessor utility class.
 * The main purpose of this class is to enable accessing
 * private and package-private fields of classes from
 * different classes/packages. See sun.misc.SharedSecretes
 * for another example.
 */
public final class SwingAccessor {
    /**
     * We don't need any objects of this class.
     * It's rather a collection of static methods
     * and interfaces.
     */
    private SwingAccessor() {
    }

    /**
     * This interface provides access to the renderer's accessibility component.
     * For example, the renderer of a list element, a table cell, or a tree node
     */
    public interface AccessibleComponentAccessor {
        Accessible getCurrentAccessible(AccessibleContext ac);
    }

    /**
     * An accessor for the JComponent class.
     */
    public interface JComponentAccessor {

        boolean getFlag(JComponent comp, int aFlag);

        void compWriteObjectNotify(JComponent comp);
    }

    /**
     * An accessor for the JTextComponent class.
     * Note that we intentionally introduce the JTextComponentAccessor,
     * and not the JComponentAccessor because the needed methods
     * aren't override methods.
     */
    public interface JTextComponentAccessor {

        /**
         * Calculates a custom drop location for the text component,
         * representing where a drop at the given point should insert data.
         */
        TransferHandler.DropLocation dropLocationForPoint(JTextComponent textComp, Point p);

        /**
         * Called to set or clear the drop location during a DnD operation.
         */
        Object setDropLocation(JTextComponent textComp, TransferHandler.DropLocation location,
                               Object state, boolean forDrop);
    }

    /**
     * An accessor for the JLightweightFrame class.
     */
    public interface JLightweightFrameAccessor {
        /**
         * Notifies the JLightweight frame that it needs to update a cursor
         */
        void updateCursor(JLightweightFrame frame);
    }

    /**
     * An accessor for the UIDefaults class.
     */
    public interface UIDefaultsAccessor {
        /**
         * Adds a resource bundle to the list of resource bundles.
         */
        void addInternalBundle(UIDefaults uiDefaults, String bundleName);
    }

    /**
     * An accessor for the RepaintManager class.
     */
    public interface RepaintManagerAccessor {
        void addRepaintListener(RepaintManager rm, SwingUtilities2.RepaintListener l);
        void removeRepaintListener(RepaintManager rm, SwingUtilities2.RepaintListener l);
    }

    /**
     * An accessor for PopupFactory class.
     */
    public interface PopupFactoryAccessor {
        Popup getHeavyWeightPopup(PopupFactory factory, Component owner, Component contents,
                                  int ownerX, int ownerY);
    }

    /*
     * An accessor for the KeyStroke class
     */
    public interface KeyStrokeAccessor {

        KeyStroke create();
    }

    /**
     * The javax.swing.JComponent class accessor object.
     */
    private static JComponentAccessor jComponentAccessor;

    /**
     * Set an accessor object for the javax.swing.JComponent class.
     */
    public static void setJComponentAccessor(JComponentAccessor jCompAccessor) {
        jComponentAccessor = jCompAccessor;
    }

    /**
     * Retrieve the accessor object for the javax.swing.JComponent class.
     */
    public static JComponentAccessor getJComponentAccessor() {
        var access = jComponentAccessor;
        if (access == null) {
            ensureClassInitialized(JComponent.class);
            access = jComponentAccessor;
        }
        return access;
    }

    /**
     * The javax.swing.text.JTextComponent class accessor object.
     */
    private static JTextComponentAccessor jtextComponentAccessor;

    /**
     * Set an accessor object for the javax.swing.text.JTextComponent class.
     */
    public static void setJTextComponentAccessor(JTextComponentAccessor jtca) {
         jtextComponentAccessor = jtca;
    }

    /**
     * Retrieve the accessor object for the javax.swing.text.JTextComponent class.
     */
    public static JTextComponentAccessor getJTextComponentAccessor() {
        var access = jtextComponentAccessor;
        if (access == null) {
            ensureClassInitialized(JTextComponent.class);
            access = jtextComponentAccessor;
        }
        return access;
    }

    /**
     * The JLightweightFrame class accessor object
     */
    private static JLightweightFrameAccessor jLightweightFrameAccessor;

    /**
     * Set an accessor object for the JLightweightFrame class.
     */
    public static void setJLightweightFrameAccessor(JLightweightFrameAccessor accessor) {
        jLightweightFrameAccessor = accessor;
    }

    /**
     * Retrieve the accessor object for the JLightweightFrame class
     */
    public static JLightweightFrameAccessor getJLightweightFrameAccessor() {
        var access = jLightweightFrameAccessor;
        if (access == null) {
            ensureClassInitialized(JLightweightFrame.class);
            access = jLightweightFrameAccessor;
        }
        return access;
    }

    /**
     * The UIDefaults class accessor object
     */
    private static UIDefaultsAccessor uiDefaultsAccessor;

    /**
     * Set an accessor object for the UIDefaults class.
     */
    public static void setUIDefaultsAccessor(UIDefaultsAccessor accessor) {
        uiDefaultsAccessor = accessor;
    }

    /**
     * Retrieve the accessor object for the JLightweightFrame class
     */
    public static UIDefaultsAccessor getUIDefaultsAccessor() {
        var access = uiDefaultsAccessor;
        if (access == null) {
            ensureClassInitialized(UIDefaults.class);
            access = uiDefaultsAccessor;
        }
        return access;
    }

    /**
     * The RepaintManager class accessor object.
     */
    private static RepaintManagerAccessor repaintManagerAccessor;

    /**
     * Set an accessor object for the RepaintManager class.
     */
    public static void setRepaintManagerAccessor(RepaintManagerAccessor accessor) {
        repaintManagerAccessor = accessor;
    }

    /**
     * Retrieve the accessor object for the RepaintManager class.
     */
    public static RepaintManagerAccessor getRepaintManagerAccessor() {
        var access = repaintManagerAccessor;
        if (access == null) {
            ensureClassInitialized(RepaintManager.class);
            access = repaintManagerAccessor;
        }
        return access;
    }

    /**
     * The PopupFactory class accessor object.
     */
    private static PopupFactoryAccessor popupFactoryAccessor;

    /**
     * Retrieve the accessor object for the PopupFactory class.
     */
    public static PopupFactoryAccessor getPopupFactoryAccessor() {
        var access = popupFactoryAccessor;
        if (access == null) {
            ensureClassInitialized(PopupFactory.class);
            access = popupFactoryAccessor;
        }
        return access;
    }

    /**
     * Set an Accessor object for the PopupFactory class.
     */
    public static void setPopupFactoryAccessor(PopupFactoryAccessor popupFactoryAccessor) {
        SwingAccessor.popupFactoryAccessor = popupFactoryAccessor;
    }

    /**
     * The KeyStroke class accessor object.
     */
    private static KeyStrokeAccessor keyStrokeAccessor;

    /**
     * Retrieve the accessor object for the KeyStroke class.
     */
    public static KeyStrokeAccessor getKeyStrokeAccessor() {
        var access = keyStrokeAccessor;
        if (access == null) {
            ensureClassInitialized(KeyStroke.class);
            access = keyStrokeAccessor;
        }
        return access;
    }

    /*
     * Set the accessor object for the KeyStroke class.
     */
    public static void setKeyStrokeAccessor(KeyStrokeAccessor accessor) {
        SwingAccessor.keyStrokeAccessor = accessor;
    }

    private static AccessibleComponentAccessor accessibleComponentAccessor = null;

    public static AccessibleComponentAccessor getAccessibleComponentAccessor() {
        var access = accessibleComponentAccessor;
        if (access == null) {
            ensureClassInitialized(JTree.class);
            access = accessibleComponentAccessor;
        }
        return access;
    }

    public static void setAccessibleComponentAccessor(final AccessibleComponentAccessor accessibleAccessor) {
        accessibleComponentAccessor = accessibleAccessor;
    }

    private static void ensureClassInitialized(Class<?> c) {
        try {
            MethodHandles.lookup().ensureInitialized(c);
        } catch (IllegalAccessException e) {}
    }

    private static ThreadLocal<Boolean> tlObj = new ThreadLocal<Boolean>();

    public static Boolean getAllowHTMLObject() {
        Boolean b = tlObj.get();
        if (b == null) {
            return Boolean.TRUE;
        } else {
            return b;
        }
    }

    public static void setAllowHTMLObject(Boolean val) {
        tlObj.set(val);
    }
}
