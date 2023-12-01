package jdk.tools.jlink.internal;

import java.io.*;
import java.util.Optional;
import java.util.spi.ToolProvider;

public class Main {
    public static void main(String... args) throws Exception {
        System.exit(run(new PrintWriter(System.out, true),
                        new PrintWriter(System.err, true),
                        args));
    }

    /**
     * Entry point that does <i>not</i> call System.exit.
     *
     * @param out output stream
     * @param err error output stream
     * @param args command line arguments
     * @return an exit code. 0 means success, non-zero means an error occurred.
     */
    @SuppressWarnings("removal")
    public static int run(PrintWriter out, PrintWriter err, String... args) {
        if (System.getSecurityManager() != null) {
            System.getSecurityManager().
                    checkPermission(new JlinkPermission("jlink"));
        }

        JlinkTask t = new JlinkTask();
        t.setLog(out, err);
        return t.run(args);
    }

    public static class JlinkToolProvider implements ToolProvider {
        @Override
        public String name() {
            return "jlink";
        }

        @Override
        public Optional<String> description() {
            TaskHelper taskHelper = new TaskHelper(TaskHelper.JLINK_BUNDLE);
            return Optional.of(taskHelper.getMessage("jlink.description"));
        }

        @Override
        public int run(PrintWriter out, PrintWriter err, String... args) {
            return Main.run(out, err, args);
        }
    }
}
