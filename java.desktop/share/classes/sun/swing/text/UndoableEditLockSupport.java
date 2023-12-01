package sun.swing.text;

import javax.swing.undo.UndoableEdit;

/**
 * UndoableEdit support for undo/redo actions synchronization
 * @since 9
 */
public interface UndoableEditLockSupport extends UndoableEdit {
    /**
     * lock the UndoableEdit for threadsafe undo/redo
     */
    void lockEdit();

    /**
     * unlock the UndoableEdit
     */
    void unlockEdit();
}
