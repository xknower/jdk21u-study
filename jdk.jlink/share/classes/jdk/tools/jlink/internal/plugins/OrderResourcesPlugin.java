package jdk.tools.jlink.internal.plugins;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

import jdk.tools.jlink.internal.Utils;
import jdk.tools.jlink.plugin.ResourcePool;
import jdk.tools.jlink.plugin.ResourcePoolBuilder;
import jdk.tools.jlink.plugin.ResourcePoolEntry;

/**
 *
 * Order Resources plugin
 */
public final class OrderResourcesPlugin extends AbstractPlugin {
    private static final FileSystem JRT_FILE_SYSTEM = Utils.jrtFileSystem();

    private final List<ToIntFunction<String>> filters;
    private final Map<String, Integer> orderedPaths;

    public OrderResourcesPlugin() {
        super("order-resources");
        this.filters = new ArrayList<>();
        this.orderedPaths = new HashMap<>();
    }

    static class SortWrapper {
        private final ResourcePoolEntry resource;
        private final int ordinal;

        SortWrapper(ResourcePoolEntry resource, int ordinal) {
            this.resource = resource;
            this.ordinal = ordinal;
        }

        ResourcePoolEntry getResource() {
            return resource;
        }

        String getPath() {
            return resource.path();
        }

        int getOrdinal() {
            return ordinal;
        }
    }

    private String stripModule(String path) {
        if (path.startsWith("/")) {
            int index = path.indexOf('/', 1);

            if (index != -1) {
                return path.substring(index + 1);
            }
        }

        return path;
    }

    private int getOrdinal(ResourcePoolEntry resource) {
        String path = resource.path();

        Integer value = orderedPaths.get(stripModule(path));

        if (value != null) {
            return value;
        }

        for (ToIntFunction<String> function : filters) {
            int ordinal = function.applyAsInt(path);

            if (ordinal != Integer.MAX_VALUE) {
                return ordinal;
            }
        }

        return Integer.MAX_VALUE;
    }

    private static int compare(SortWrapper wrapper1, SortWrapper wrapper2) {
        int compare = wrapper1.getOrdinal() - wrapper2.getOrdinal();

        if (compare != 0) {
            return compare;
        }

        return wrapper1.getPath().compareTo(wrapper2.getPath());
    }

    @Override
    public ResourcePool transform(ResourcePool in, ResourcePoolBuilder out) {
        in.entries()
                .filter(resource -> resource.type()
                        .equals(ResourcePoolEntry.Type.CLASS_OR_RESOURCE))
                .map((resource) -> new SortWrapper(resource, getOrdinal(resource)))
                .sorted(OrderResourcesPlugin::compare)
                .forEach((wrapper) -> out.add(wrapper.getResource()));
        in.entries()
                .filter(other -> !other.type()
                        .equals(ResourcePoolEntry.Type.CLASS_OR_RESOURCE))
                .forEach((other) -> out.add(other));

        return out.build();
    }

    @Override
    public Category getType() {
        return Category.SORTER;
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public void configure(Map<String, String> config) {
        List<String> patterns = Utils.parseList(config.get(getName()));
        int ordinal = 0;

        for (String pattern : patterns) {
            if (pattern.startsWith("@")) {
                File file = new File(pattern.substring(1));

                if (file.exists()) {
                    List<String> lines;

                    try {
                        lines = Files.readAllLines(file.toPath());
                    } catch (IOException ex) {
                        throw new UncheckedIOException(ex);
                    }

                    for (String line : lines) {
                        if (!line.startsWith("#")) {
                            orderedPaths.put(line + ".class", ordinal++);
                        }
                    }
                }
            } else {
                final int result = ordinal++;
                final PathMatcher matcher = Utils.getPathMatcher(JRT_FILE_SYSTEM, pattern);
                ToIntFunction<String> function = (path)-> matcher.matches(JRT_FILE_SYSTEM.getPath(path)) ? result : Integer.MAX_VALUE;
                filters.add(function);
             }
        }
    }
}
