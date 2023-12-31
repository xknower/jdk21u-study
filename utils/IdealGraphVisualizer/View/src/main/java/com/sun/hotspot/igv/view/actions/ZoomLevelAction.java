package com.sun.hotspot.igv.view.actions;

import com.sun.hotspot.igv.data.ChangedListener;
import com.sun.hotspot.igv.view.DiagramViewer;
import com.sun.hotspot.igv.view.EditorTopComponent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxUI;

public final class ZoomLevelAction extends JComboBox<String> implements ChangedListener<DiagramViewer> {

    private static final String[] CHOICES = { "25%", "50%", "75%", "100%", "125%", "150%", "200%", "400%"};

    private final DiagramViewer diagramScene;

    boolean updateZoomInScene = true;

    @Override
    public void actionPerformed(ActionEvent e) {
        EditorTopComponent editor = EditorTopComponent.getActive();
        if (editor != null) {
            editor.requestActive();
        }

        String levelStr = (String) getSelectedItem();
        assert levelStr != null;
        levelStr = levelStr.replaceAll("\\s","");
        levelStr = levelStr.replaceFirst("%","");
        try {
            int level = Integer.parseInt(levelStr);
            int minLevel = (int) (diagramScene.getZoomMinFactor() * 100.0);
            int maxLevel = (int) (diagramScene.getZoomMaxFactor() * 100.0);
            level = Math.max(level, minLevel);
            level = Math.min(level, maxLevel);
            setZoomLevel(level);
            if (updateZoomInScene) {
                diagramScene.setZoomPercentage(level);
            }
        } catch(NumberFormatException exception) {
            changed(diagramScene);
        }
    }

    public ZoomLevelAction(DiagramViewer scene) {
        diagramScene = scene;

        setModel(new DefaultComboBoxModel<>(CHOICES));
        setSelectedIndex(3); // init value: 100%
        setVisible(true);
        setEditable(true);
        setUI(new BasicComboBoxUI());
        setFont(getFont().deriveFont((float)(getFont().getSize2D()*0.9)));
        addActionListener(this);

        JTextField text = (JTextField) getEditor().getEditorComponent();
        text.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        text.setColumns(3);
        setMaximumSize(getPreferredSize());

        scene.getZoomChangedEvent().addListener(this);
    }

    private void setZoomLevel(int zoomLevel) {
        setSelectedItem(zoomLevel + "%");
    }

    @Override
    public void changed(DiagramViewer diagramViewer) {
        updateZoomInScene = false;
        setZoomLevel(diagramViewer.getZoomPercentage());
        updateZoomInScene = true;
    }
}
