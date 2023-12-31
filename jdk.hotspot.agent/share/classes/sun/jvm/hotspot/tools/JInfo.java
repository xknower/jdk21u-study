package sun.jvm.hotspot.tools;

import sun.jvm.hotspot.debugger.JVMDebugger;
import sun.jvm.hotspot.runtime.Arguments;
import sun.jvm.hotspot.runtime.VM;

public class JInfo extends Tool {
    public JInfo() {
        super();
    }

    public JInfo(int m) {
        mode = m;
    }

    public JInfo(JVMDebugger d) {
        super(d);
    }

    protected boolean needsJavaPrefix() {
        return false;
    }

    @Override
    public String getName() {
        return "jinfo";
    }

    protected void printFlagsUsage() {
        System.out.println("    -flags\tto print VM flags");
        System.out.println("    -sysprops\tto print Java System properties");
        System.out.println("    <no option>\tto print both of the above");
        super.printFlagsUsage();
    }

    public static final int MODE_FLAGS = 0;
    public static final int MODE_SYSPROPS = 1;
    public static final int MODE_BOTH = 2;

    public void run() {
        Tool tool = null;
        switch (mode) {
        case MODE_FLAGS:
            printVMFlags();
            return;
        case MODE_SYSPROPS:
            tool = new SysPropsDumper();
            break;
        case MODE_BOTH: {
            tool = new Tool() {
                    public void run() {
                        Tool sysProps = new SysPropsDumper();
                        sysProps.setAgent(getAgent());
                        System.out.println("Java System Properties:");
                        System.out.println();
                        sysProps.run();
                        System.out.println();
                        System.out.println("VM Flags:");
                        printVMFlags();
                        System.out.println();
                    }
                };
            }
            break;

        default:
            usage();
            break;
        }
        tool.setAgent(getAgent());
        tool.run();
    }

    public void runWithArgs(String... args) {
        int mode = -1;
        switch (args.length) {
        case 1:
            if (args[0].charAt(0) == '-') {
                // -h or -help or some invalid flag
                usage();
            } else {
                mode = MODE_BOTH;
            }
            break;
        case 2:
        case 3: {
            String modeFlag = args[0];
            if (modeFlag.equals("-flags")) {
                mode = MODE_FLAGS;
            } else if (modeFlag.equals("-sysprops")) {
                mode = MODE_SYSPROPS;
            } else if (modeFlag.charAt(0) == '-') {
                // -h or -help or some invalid flag
                usage();
            } else {
                mode = MODE_BOTH;
            }

            if (mode != MODE_BOTH) {
                // we have to consume first flag argument.
                String[] newArgs = new String[args.length - 1];
                for (int i = 0; i < newArgs.length; i++) {
                    newArgs[i] = args[i + 1];
                }
                args = newArgs;
            }
            break;
        }

        default:
            usage();
        }

        this.mode = mode;
        execute(args);
    }

    public static void main(String[] args) {
        JInfo jinfo = new JInfo();
        jinfo.runWithArgs(args);
    }

    private void printVMFlags() {
        VM.Flag[] flags = VM.getVM().getCommandLineFlags();
        System.out.print("Non-default VM flags: ");
        for (VM.Flag flag : flags) {
            if (flag.getOrigin() == VM.Flags_DEFAULT) {
                // only print flags which aren't their defaults
                continue;
            }
            if (flag.isBool()) {
                String onoff = flag.getBool() ? "+" : "-";
                System.out.print("-XX:" + onoff + flag.getName() + " ");
            } else {
                System.out.print("-XX:" + flag.getName() + "="
                        + flag.getValue() + " ");
            }
        }
        System.out.println();

        System.out.print("Command line: ");
        String str = Arguments.getJVMFlags();
        if (str != null) {
            System.out.print(str + " ");
        }
        str = Arguments.getJVMArgs();
        if (str != null) {
            System.out.print(str);
        }
        System.out.println();
    }

    private int mode;
}
