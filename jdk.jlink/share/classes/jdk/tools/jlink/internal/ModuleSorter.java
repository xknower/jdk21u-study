package jdk.tools.jlink.internal;

import jdk.tools.jlink.plugin.PluginException;
import jdk.tools.jlink.plugin.ResourcePoolEntry;
import jdk.tools.jlink.plugin.ResourcePoolModule;
import jdk.tools.jlink.plugin.ResourcePoolModuleView;

import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleDescriptor.Requires;
import java.lang.module.ModuleDescriptor.Requires.Modifier;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Helper class to sort modules in topological order
 */
public final class ModuleSorter {
    private final Map<ResourcePoolModule, Set<ResourcePoolModule>> graph = new HashMap<>();
    private final List<ResourcePoolModule> result = new ArrayList<>();

    private final ResourcePoolModuleView moduleView;

    public ModuleSorter(ResourcePoolModuleView moduleView) {
        this.moduleView = moduleView;

        moduleView.modules().forEach(this::addModule);
    }

    private ModuleDescriptor readModuleDescriptor(ResourcePoolModule module) {
        String p = "/" + module.name() + "/module-info.class";
        ResourcePoolEntry content = module.findEntry(p).orElseThrow(() ->
            new PluginException("module-info.class not found for " +
                module.name() + " module")
        );
        ByteBuffer bb = ByteBuffer.wrap(content.contentBytes());
        return ModuleDescriptor.read(bb);
    }

    private ModuleSorter addModule(ResourcePoolModule module) {
        addNode(module);
        // the module graph will be traversed in a stable order for
        // the topological sort. So add the dependences in the module name order
        readModuleDescriptor(module).requires()
                                    .stream()
                                    .sorted(Comparator.comparing(Requires::name))
                                    .forEach(req ->
        {
            ResourcePoolModule dep = moduleView.findModule(req.name()).orElse(null);
            if (dep != null) {
                addNode(dep);
                graph.get(module).add(dep);
            } else if (!req.modifiers().contains(Modifier.STATIC)) {
                throw new PluginException(req.name() + " not found");
            }
        });
        return this;
    }

    private void addNode(ResourcePoolModule module) {
        graph.computeIfAbsent(module, _n -> new LinkedHashSet<>());
    }

    /*
     * The module graph will be traversed in a stable order
     * (traversing the modules and their dependences in alphabetical order)
     * so that it will produce the same result of a given module graph.
     */
    private synchronized void build() {
        if (!result.isEmpty() || graph.isEmpty())
            return;

        Set<ResourcePoolModule> visited = new HashSet<>();
        Set<ResourcePoolModule> done = new HashSet<>();
        graph.keySet().stream()
             .sorted(Comparator.comparing(ResourcePoolModule::name))
             .forEach(node -> visit(node, visited, done));
    }

    public Stream<ResourcePoolModule> sorted() {
        build();
        return result.stream();
    }

    private void visit(ResourcePoolModule node,
                       Set<ResourcePoolModule> visited,
                       Set<ResourcePoolModule> done) {
        if (visited.contains(node)) {
            if (!done.contains(node)) {
                throw new IllegalArgumentException("Cyclic detected: " +
                    node + " " + graph.get(node));
            }
            return;
        }

        // traverse the dependences of the given module which are
        // also sorted in alphabetical order
        visited.add(node);
        graph.get(node).forEach(x -> visit(x, visited, done));
        done.add(node);
        result.add(node);
    }
}
