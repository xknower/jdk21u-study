package com.sun.hotspot.igv.coordinator.actions;

import com.sun.hotspot.igv.coordinator.OutlineTopComponent;
import com.sun.hotspot.igv.data.GraphDocument;
import com.sun.hotspot.igv.data.serialization.GraphParser;
import com.sun.hotspot.igv.data.serialization.ParseMonitor;
import com.sun.hotspot.igv.data.serialization.Parser;
import com.sun.hotspot.igv.settings.Settings;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.*;
import org.openide.util.actions.CallableSystemAction;

/**
 *
 * @author Thomas Wuerthinger
 */

public final class ImportAction extends CallableSystemAction {
    private static final int WORKUNITS = 10000;

    public static FileFilter getFileFilter() {
        return new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".xml") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Graph files (*.xml)";
            }
        };
    }

    @Override
    public void performAction() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(ImportAction.getFileFilter());
        fc.setCurrentDirectory(new File(Settings.get().get(Settings.DIRECTORY, Settings.DIRECTORY_DEFAULT)));
        fc.setMultiSelectionEnabled(true);

        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            for (final File file : fc.getSelectedFiles()) {
                File dir = file;
                if (!dir.isDirectory()) {
                    dir = dir.getParentFile();
                }

                Settings.get().put(Settings.DIRECTORY, dir.getAbsolutePath());
                try {
                    final FileChannel channel = FileChannel.open(file.toPath(), StandardOpenOption.READ);
                    final ProgressHandle handle = ProgressHandleFactory.createHandle("Opening file " + file.getName());
                    handle.start(WORKUNITS);
                    final long startTime = System.currentTimeMillis();
                    final long start = channel.size();
                    ParseMonitor monitor = new ParseMonitor() {
                            @Override
                            public void updateProgress() {
                                try {
                                    int prog = (int) (WORKUNITS * (double) channel.position() / (double) start);
                                    handle.progress(prog);
                                } catch (IOException ignored) {}
                            }
                            @Override
                            public void setState(String state) {
                                updateProgress();
                                handle.progress(state);
                            }
                        };
                    final GraphParser parser;
                    final OutlineTopComponent component = OutlineTopComponent.findInstance();
                    if (file.getName().endsWith(".xml")) {
                        parser = new Parser(channel, monitor, null);
                    } else {
                        parser = null;
                    }
                    RequestProcessor.getDefault().post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    final GraphDocument document = parser.parse();
                                    if (document != null) {
                                        SwingUtilities.invokeLater(() -> {
                                            component.requestActive();
                                            component.getDocument().addGraphDocument(document);
                                        });
                                    }
                                } catch (IOException ex) {
                                    Exceptions.printStackTrace(ex);
                                }
                                handle.finish();
                                long stop = System.currentTimeMillis();
                                Logger.getLogger(getClass().getName()).log(Level.INFO, "Loaded in " + file + " in " + ((stop - startTime) / 1000.0) + " seconds");
                            }
                        });
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(ImportAction.class, "CTL_ImportAction");
    }

    public ImportAction() {
        putValue(Action.SHORT_DESCRIPTION, "Open");
        // D is the Control key on most platforms, the Command (meta) key on Macintosh
        putValue(Action.ACCELERATOR_KEY, Utilities.stringToKey("D-O"));
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/coordinator/images/import.png";
    }
}
