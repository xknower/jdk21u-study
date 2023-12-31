package jdk.tools.jlink.internal;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jdk.internal.jimage.ImageHeader;
import jdk.internal.jimage.ImageStream;
import jdk.internal.jimage.ImageStringsReader;

public final class BasicImageWriter {
    public static final String MODULES_IMAGE_NAME = "modules";

    private ByteOrder byteOrder;
    private ImageStringsWriter strings;
    private int length;
    private int[] redirect;
    private ImageLocationWriter[] locations;
    private List<ImageLocationWriter> input;
    private ImageStream headerStream;
    private ImageStream redirectStream;
    private ImageStream locationOffsetStream;
    private ImageStream locationStream;
    private ImageStream allIndexStream;

    public BasicImageWriter() {
        this(ByteOrder.nativeOrder());
    }

    public BasicImageWriter(ByteOrder byteOrder) {
        this.byteOrder = Objects.requireNonNull(byteOrder);
        this.input = new ArrayList<>();
        this.strings = new ImageStringsWriter();
        this.headerStream = new ImageStream(byteOrder);
        this.redirectStream = new ImageStream(byteOrder);
        this.locationOffsetStream = new ImageStream(byteOrder);
        this.locationStream = new ImageStream(byteOrder);
        this.allIndexStream = new ImageStream(byteOrder);
    }

    public ByteOrder getByteOrder() {
        return byteOrder;
    }

    public int addString(String string) {
        return strings.add(string);
    }

    public String getString(int offset) {
        return strings.get(offset);
    }

    public void addLocation(String fullname, long contentOffset,
            long compressedSize, long uncompressedSize) {
        ImageLocationWriter location =
                ImageLocationWriter.newLocation(fullname, strings,
                        contentOffset, compressedSize, uncompressedSize);
        input.add(location);
        length++;
    }

    ImageLocationWriter[] getLocations() {
        return locations;
    }

    int getLocationsCount() {
        return input.size();
    }

    private void generatePerfectHash() {
        PerfectHashBuilder<ImageLocationWriter> builder =
            new PerfectHashBuilder<>(
                        PerfectHashBuilder.Entry.class,
                        PerfectHashBuilder.Bucket.class);

        input.forEach((location) -> {
            builder.put(location.getFullName(), location);
        });

        builder.generate();

        length = builder.getCount();
        redirect = builder.getRedirect();
        PerfectHashBuilder.Entry<ImageLocationWriter>[] order = builder.getOrder();
        locations = new ImageLocationWriter[length];

        for (int i = 0; i < length; i++) {
            locations[i] = order[i].getValue();
        }
    }

    private void prepareStringBytes() {
        strings.getStream().align(2);
    }

    private void prepareRedirectBytes() {
        for (int i = 0; i < length; i++) {
            redirectStream.putInt(redirect[i]);
        }
    }

    private void prepareLocationBytes() {
        // Reserve location offset zero for empty locations
        locationStream.put(ImageLocationWriter.ATTRIBUTE_END << 3);

        for (int i = 0; i < length; i++) {
            ImageLocationWriter location = locations[i];

            if (location != null) {
                location.writeTo(locationStream);
            }
        }

        locationStream.align(2);
    }

    private void prepareOffsetBytes() {
        for (int i = 0; i < length; i++) {
            ImageLocationWriter location = locations[i];
            int offset = location != null ? location.getLocationOffset() : 0;
            locationOffsetStream.putInt(offset);
        }
    }

    private void prepareHeaderBytes() {
        ImageHeader header = new ImageHeader(input.size(), length,
                locationStream.getSize(), strings.getSize());
        header.writeTo(headerStream);
    }

    private void prepareTableBytes() {
        allIndexStream.put(headerStream);
        allIndexStream.put(redirectStream);
        allIndexStream.put(locationOffsetStream);
        allIndexStream.put(locationStream);
        allIndexStream.put(strings.getStream());
    }

    public byte[] getBytes() {
        if (allIndexStream.getSize() == 0) {
            generatePerfectHash();
            prepareStringBytes();
            prepareRedirectBytes();
            prepareLocationBytes();
            prepareOffsetBytes();
            prepareHeaderBytes();
            prepareTableBytes();
        }

        return allIndexStream.toArray();
    }

    ImageLocationWriter find(String key) {
        int index = redirect[ImageStringsReader.hashCode(key) % length];

        if (index < 0) {
            index = -index - 1;
        } else {
            index = ImageStringsReader.hashCode(key, index) % length;
        }

        return locations[index];
    }
}
