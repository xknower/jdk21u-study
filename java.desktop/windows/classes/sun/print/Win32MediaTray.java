package sun.print;

import javax.print.attribute.standard.MediaTray;
import javax.print.attribute.EnumSyntax;
import java.util.ArrayList;

/**
 * Class Win32MediaTray is a subclass of MediaTray which declares
 * Windows media trays or bins not covered by MediaTray's standard values.
 * It also implements driver-defined trays.
 **/
@SuppressWarnings("serial") // JDK implementation class
public class Win32MediaTray extends MediaTray {

    static final Win32MediaTray ENVELOPE_MANUAL = new Win32MediaTray(0,
                                                      6); //DMBIN_ENVMANUAL
    static final Win32MediaTray AUTO = new Win32MediaTray(1,
                                                      7); //DMBIN_AUTO
    static final Win32MediaTray TRACTOR = new Win32MediaTray(2,
                                                      8); //DMBIN_TRACTOR
    static final Win32MediaTray SMALL_FORMAT = new Win32MediaTray(3,
                                                      9); //DMBIN_SMALLFMT
    static final Win32MediaTray LARGE_FORMAT = new Win32MediaTray(4,
                                                      10); //DMBIN_LARGEFMT
    static final Win32MediaTray FORMSOURCE = new Win32MediaTray(5,
                                                      15); //DMBIN_FORMSOURCE

    private static ArrayList<String> winStringTable = new ArrayList<>();
    private static ArrayList<Win32MediaTray> winEnumTable = new ArrayList<>();
    public int winID;

    private Win32MediaTray(int value, int id) {
        super (value);
        winID = id;
    }

    private static synchronized int nextValue(String name) {
      winStringTable.add(name);
      return (getTraySize()-1);
    }

    protected Win32MediaTray(int id, String name) {
        super (nextValue(name));
        winID = id;
        winEnumTable.add(this);
    }

    public int getDMBinID() {
        return winID;
    }

    private static final String[] myStringTable ={
        "Manual-Envelope",
        "Automatic-Feeder",
        "Tractor-Feeder",
        "Small-Format",
        "Large-Format",
        "Form-Source",
    };

    private static final MediaTray[] myEnumValueTable = {
        ENVELOPE_MANUAL,
        AUTO,
        TRACTOR,
        SMALL_FORMAT,
        LARGE_FORMAT,
        FORMSOURCE,
    };

    protected static int getTraySize() {
      return (myStringTable.length+winStringTable.size());
    }

    protected String[] getStringTable() {
      ArrayList<String> completeList = new ArrayList<>();
      for (int i=0; i < myStringTable.length; i++) {
        completeList.add(myStringTable[i]);
      }
      completeList.addAll(winStringTable);
      String[] nameTable = new String[completeList.size()];
      return completeList.toArray(nameTable);
    }

    protected EnumSyntax[] getEnumValueTable() {
      ArrayList<MediaTray> completeList = new ArrayList<>();
      for (int i=0; i < myEnumValueTable.length; i++) {
        completeList.add(myEnumValueTable[i]);
      }
      completeList.addAll(winEnumTable);
      MediaTray[] enumTable = new MediaTray[completeList.size()];
      return completeList.toArray(enumTable);
    }
}
