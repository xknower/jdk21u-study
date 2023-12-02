package com.sun.tools.jdeps;

import com.sun.tools.classfile.Dependency.Location;

import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static com.sun.tools.jdeps.Module.trace;

/**
 * Represents the source of the class files.
 */
public class Archive implements Closeable {
    public static Archive getInstance(Path p, Runtime.Version version) {
        try {
            return new Archive(p, ClassFileReader.newInstance(p, version));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private final URI location;
    private final Path path;
    private final String filename;
    private final ClassFileReader reader;

    protected Map<Location, Set<Location>> deps = new ConcurrentHashMap<>();

    protected Archive(String name) {
        this(name, null, null);
    }
    protected Archive(String name, URI location, ClassFileReader reader) {
        this.location = location;
        this.path = location != null ? Paths.get(location) : null;
        this.filename = name;
        this.reader = reader;
    }
    protected Archive(Path p, ClassFileReader reader) {
        this.location = null;
        this.path = p;
        this.filename = path.getFileName().toString();
        this.reader = reader;
    }

    public ClassFileReader reader() {
        return reader;
    }

    public String getName() {
        return filename;
    }

    public Module getModule() {
        return Module.UNNAMED_MODULE;
    }

    public boolean contains(String entry) {
        return reader.entries().contains(entry);
    }

    public void addClass(Location origin) {
        deps.computeIfAbsent(origin, _k -> new HashSet<>());
    }

    public void addClass(Location origin, Location target) {
        deps.computeIfAbsent(origin, _k -> new HashSet<>()).add(target);
    }

    public Set<Location> getClasses() {
        return deps.keySet();
    }

    public Stream<Location> getDependencies() {
        return deps.values().stream()
                   .flatMap(Set::stream);
    }

    public boolean hasDependences() {
        return getDependencies().count() > 0;
    }

    public void visitDependences(Visitor v) {
        for (Map.Entry<Location,Set<Location>> e: deps.entrySet()) {
            for (Location target : e.getValue()) {
                v.visit(e.getKey(), target);
            }
        }
    }

    /**
     * Tests if any class has been parsed.
     */
    public boolean isEmpty() {
        return getClasses().isEmpty();
    }

    public String getPathName() {
        return path != null ? path.toString() : filename;
    }

    public Optional<Path> path() {
        return Optional.ofNullable(path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.filename, this.path);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Archive) {
            Archive other = (Archive)o;
            if (path == other.path || isSameLocation(this, other))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return filename;
    }


    public static boolean isSameLocation(Archive archive, Archive other) {
        if (archive.path == null || other.path == null)
            return false;

        if (archive.location != null && other.location != null &&
                archive.location.equals(other.location)) {
            return true;
        }

        if (archive.isJrt() || other.isJrt()) {
            return false;
        }

        try {
            return Files.isSameFile(archive.path, other.path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private boolean isJrt() {
        return location != null && location.getScheme().equals("jrt");
    }

    @Override
    public void close() throws IOException {
        trace("closing %s %n", getPathName());
        if (reader != null)
            reader.close();
    }

    interface Visitor {
        void visit(Location origin, Location target);
    }
}
