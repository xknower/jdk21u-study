package sun.jvm.hotspot.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import sun.jvm.hotspot.debugger.*;

public class ProcessListPanel extends JPanel {
  private Debugger           dbg;
  private AbstractTableModel dataModel;
  private java.util.List<ProcessInfo> els;
  private boolean            sortByName   = true;
  private boolean            sortReversed = false;
  private javax.swing.Timer  timer;
  private JTable             table;

  /** Takes a Debugger in constructor. Updates the list once during
      construction; list can be updated automatically by "starting"
      the panel. */
  public ProcessListPanel(Debugger dbg) {
    super();

    this.dbg = dbg;

    update();

    dataModel = new AbstractTableModel() {
        public int getColumnCount() { return 2;          }
        public int getRowCount()    { return els.size(); }
        public String getColumnName(int col) {
          switch (col) {
          case 0:
            return "Process Name";
          case 1:
            return "Process ID";
          default:
            throw new RuntimeException("Index " + col + " out of bounds");
          }
        }

        public Object getValueAt(int row, int col) {
          ProcessInfo info = els.get(row);

          switch (col) {
          case 0:
            return info.getName();
          case 1:
            return info.getPid();
          default:
            throw new RuntimeException("Index (" + col + ", " + row + ") out of bounds");
          }
        }
      };

    // Create user interface
    setLayout(new BorderLayout());
    table = new JTable(dataModel);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JTableHeader header = table.getTableHeader();
    header.setReorderingAllowed(false);
    table.setRowSelectionAllowed(true);
    table.setColumnSelectionAllowed(false);
    // Provide sorting in similar fashion to Task Manager
    header.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          int viewColumn = table.getColumnModel().getColumnIndexAtX(e.getX());
          int column = table.convertColumnIndexToModel(viewColumn);
          if (column != -1) {
            boolean newSortByName = (column == 0);
            if (newSortByName == sortByName) {
              // Switch sense of "reversed" instead
              sortReversed = !sortReversed;
            } else {
              sortByName = newSortByName;
              sortReversed = false;
            }

            // Keep the current selection if possible
            int i = table.getSelectedRow();
            int pid = getPid(els, i);
            sort(els);
            i = findPid(els, pid);
            dataModel.fireTableDataChanged();
            if ((i >= 0) || (els.size() > 0)) {
              if (i >= 0) {
                table.setRowSelectionInterval(i, i);
              } else {
                table.setRowSelectionInterval(0, 0);
              }
            }
          }
        }
      });

    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);

    if (els.size() > 0) {
      table.setRowSelectionInterval(0, 0);
    }
  }

  /** Set update interval for automatic updating of the process list */

  public void setAutoUpdateInterval(int millis) {
    getTimer().setDelay(millis);
  }

  /** Start auto updating of the panel */
  public void start() {
    getTimer().start();
  }

  /** Stop auto updating of the panel */
  public void stop() {
    getTimer().stop();
  }

  /** Call this to update the panel's notion of the process list */
  public synchronized void update() {
    if (!dbg.hasProcessList()) {
      throw new RuntimeException("ProcessListPanel requires that debugger supports getProcessList()");
    }
    java.util.List<ProcessInfo> newEls = dbg.getProcessList();
    sort(newEls);
    if (table != null) {
      // Keep the current selection if possible
      int i = table.getSelectedRow();
      int pid = getPid(els, i);
      i = findPid(newEls, pid);
      els = newEls;
      dataModel.fireTableDataChanged();
      if ((i >= 0) || (els.size() > 0)) {
        if (i >= 0) {
          table.setRowSelectionInterval(i, i);
        } else {
          table.setRowSelectionInterval(0, 0);
        }
      }
    } else {
      els = newEls;
    }
  }

  /** Call this to get the selected ProcessInfo, or null if none selected */
  public synchronized ProcessInfo getSelectedProcess() {
    int i = table.getSelectedRow();
    if (i < 0) {
      return null;
    }
    return els.get(i);
  }

  private synchronized void sort(java.util.List<ProcessInfo> els) {
    Comparator<ProcessInfo> c;
    if (sortByName) {
      c = new Comparator<>() {
          public int compare(ProcessInfo o1, ProcessInfo o2) {
            int scale = (sortReversed ? -1 : 1);
            return scale * o1.getName().compareToIgnoreCase(o2.getName());
          }
        };
    } else {
      c = new Comparator<>() {
          public int compare(ProcessInfo o1, ProcessInfo o2) {
            int scale = (sortReversed ? -1 : 1);
            int pid1 = o1.getPid();
            int pid2 = o2.getPid();
            int ret;
            if      (pid1 <  pid2) ret = -1;
            else if (pid1 == pid2) ret = 0;
            else ret = 1;
            return ret * scale;
          }
        };
    }
    els.sort(c);
  }

  private javax.swing.Timer getTimer() {
    if (timer == null) {
      timer = new javax.swing.Timer(1000, new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            update();
          }
        });
    }
    return timer;
  }

  private synchronized int getPid(java.util.List<ProcessInfo> els, int index) {
    return els.get(index).getPid();
  }

  private synchronized int findPid(java.util.List<ProcessInfo> els, int pid) {
    for (int i = 0; i < els.size(); i++) {
      ProcessInfo info = els.get(i);
      if (info.getPid() == pid) {
        return i;
      }
    }
    return -1;
  }
}
