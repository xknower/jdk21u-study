package com.sun.hotspot.igv.controlflow;

import com.sun.hotspot.igv.data.ChangedListener;
import com.sun.hotspot.igv.data.InputGraph;
import com.sun.hotspot.igv.data.services.InputGraphProvider;
import com.sun.hotspot.igv.util.LookupHistory;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import org.openide.ErrorManager;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Thomas Wuerthinger
 */
final class ControlFlowTopComponent extends TopComponent implements ChangedListener<InputGraphProvider> {

    private static ControlFlowTopComponent instance;
    private static final String PREFERRED_ID = "ControlFlowTopComponent";
    private final ControlFlowScene scene;

    private ControlFlowTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ControlFlowTopComponent.class, "CTL_ControlFlowTopComponent"));
        setToolTipText(NbBundle.getMessage(ControlFlowTopComponent.class, "HINT_ControlFlowTopComponent"));

        scene = new ControlFlowScene();
        setLayout(new BorderLayout());
        associateLookup(scene.getLookup());

        JScrollPane panel = new JScrollPane(scene.createView());
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance()}.
     */
    public static synchronized ControlFlowTopComponent getDefault() {
        if (instance == null) {
            instance = new ControlFlowTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the ControlFlowTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized ControlFlowTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot find ControlFlow component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof ControlFlowTopComponent) {
            return (ControlFlowTopComponent) win;
        }
        ErrorManager.getDefault().log(ErrorManager.WARNING, "There seem to be multiple components with the '" + PREFERRED_ID + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        LookupHistory.addListener(InputGraphProvider.class, this);
    }

    @Override
    public void componentClosed() {
        LookupHistory.removeListener(InputGraphProvider.class, this);
    }

    @Override
    public void changed(InputGraphProvider lastProvider) {
        SwingUtilities.invokeLater(() -> {
            if (lastProvider != null) {
                InputGraph graph = lastProvider.getGraph();
                if (graph != null) {
                    scene.setGraph(graph);
                    return;
                }
            }
            scene.setGraph(new InputGraph(""));
        });
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    @Override
    public void requestActive() {
        super.requestActive();
        scene.getView().requestFocus();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
