package jdk.tools.jlink.internal.plugins;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.zip.Deflater;
import jdk.tools.jlink.internal.ResourcePoolManager;
import jdk.tools.jlink.internal.ResourcePoolManager.ResourcePoolImpl;
import jdk.tools.jlink.plugin.ResourcePool;
import jdk.tools.jlink.plugin.ResourcePoolBuilder;
import jdk.tools.jlink.plugin.ResourcePoolEntry;

/**
 *
 * ZIP Compression plugin
 */
public final class ZipPlugin extends AbstractPlugin {

    private Predicate<String> predicate;

    private static final int DEFAULT_COMPRESSION = 6;
    private final int compressionLevel;

    public ZipPlugin() {
        this((Predicate<String>) null);
    }

    ZipPlugin(String[] patterns) {
        this(ResourceFilter.includeFilter(Arrays.asList(patterns)));
    }

    ZipPlugin(Predicate<String> predicate) {
        this(predicate, DEFAULT_COMPRESSION);
    }

    ZipPlugin(Predicate<String> predicate, int compressionLevel) {
        super("zip");
        this.predicate = predicate;
        this.compressionLevel = compressionLevel;
    }

    @Override
    public Category getType() {
        return Category.COMPRESSOR;
    }

    @Override
    public boolean hasArguments() {
        return false;
    }

    @Override
    public void configure(Map<String, String> config) {
        predicate = ResourceFilter.includeFilter(config.get(getName()));
    }

    static byte[] compress(byte[] bytesIn, int compressionLevel) {
        Deflater deflater = new Deflater(compressionLevel);
        deflater.setInput(bytesIn);
        ByteArrayOutputStream stream = new ByteArrayOutputStream(bytesIn.length);
        byte[] buffer = new byte[1024];

        deflater.finish();
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            stream.write(buffer, 0, count);
        }

        try {
            stream.close();
        } catch (IOException ex) {
            return bytesIn;
        }

        byte[] bytesOut = stream.toByteArray();
        deflater.end();

        return bytesOut;
    }

    @Override
    public ResourcePool transform(ResourcePool in, ResourcePoolBuilder out) {
        in.transformAndCopy((resource) -> {
            ResourcePoolEntry res = resource;
            if (resource.type().equals(ResourcePoolEntry.Type.CLASS_OR_RESOURCE)
                    && predicate.test(resource.path())) {
                byte[] compressed;
                compressed = compress(resource.contentBytes(), this.compressionLevel);
                res = ResourcePoolManager.newCompressedResource(resource,
                        ByteBuffer.wrap(compressed), getName(), null,
                        ((ResourcePoolImpl)in).getStringTable(), in.byteOrder());
            }
            return res;
        }, out);

        return out.build();
    }
}
