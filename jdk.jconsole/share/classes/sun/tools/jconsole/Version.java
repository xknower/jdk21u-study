package sun.tools.jconsole;

import java.io.PrintStream;
import sun.tools.jconsole.Messages;

public class Version {
    private static final String jconsole_version =
        System.getProperty("java.runtime.version");

    public static void print(PrintStream ps) {
        printFullVersion(ps);

        ps.println(Resources.format(Messages.NAME_AND_BUILD,
                                    System.getProperty("java.runtime.name"),
                                    System.getProperty("java.runtime.version")));

        ps.println(Resources.format(Messages.NAME_AND_BUILD,
                                    System.getProperty("java.vm.name"),
                                    System.getProperty("java.vm.version"),
                                    System.getProperty("java.vm.info")));

    }

    public static void printFullVersion(PrintStream ps) {
        ps.println(Resources.format(Messages.JCONSOLE_VERSION, jconsole_version));
    }

    static String getVersion() {
        return jconsole_version;
    }
}
