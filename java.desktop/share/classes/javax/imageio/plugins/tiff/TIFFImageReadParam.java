package javax.imageio.plugins.tiff;

import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageReadParam;

/**
 * A subclass of {@link ImageReadParam} allowing control over
 * the TIFF reading process.
 *
 * <p> Because TIFF is an extensible format, the reader requires
 * information about any tags used by TIFF extensions in order to emit
 * meaningful metadata.  Also, TIFF extensions may define new
 * compression types.  Both types of information about extensions may
 * be provided by this interface.
 *
 * <p> Additional TIFF tags must be organized into
 * {@code TIFFTagSet}s.  A {@code TIFFTagSet} may be
 * provided to the reader by means of the
 * {@code addAllowedTagSet} method.  By default, the tag sets
 * {@code BaselineTIFFTagSet}, {@code FaxTIFFTagSet},
 * {@code ExifParentTIFFTagSet}, and {@code GeoTIFFTagSet}
 * are included.
 *
 * <p> Forcing reading of fields corresponding to {@code TIFFTag}s
 * not in any of the allowed {@code TIFFTagSet}s may be effected via
 * {@link #setReadUnknownTags setReadUnknownTags}.
 *
 * @since 9
 */
public final class TIFFImageReadParam extends ImageReadParam {

    private final List<TIFFTagSet> allowedTagSets =
        new ArrayList<TIFFTagSet>(4);

    private boolean readUnknownTags = false;

    /**
     * Constructs a {@code TIFFImageReadParam}.  Tags defined by
     * the {@code TIFFTagSet}s {@code BaselineTIFFTagSet},
     * {@code FaxTIFFTagSet}, {@code ExifParentTIFFTagSet}, and
     * {@code GeoTIFFTagSet} will be supported.
     *
     * @see BaselineTIFFTagSet
     * @see FaxTIFFTagSet
     * @see ExifParentTIFFTagSet
     * @see GeoTIFFTagSet
     */
    public TIFFImageReadParam() {
        addAllowedTagSet(BaselineTIFFTagSet.getInstance());
        addAllowedTagSet(FaxTIFFTagSet.getInstance());
        addAllowedTagSet(ExifParentTIFFTagSet.getInstance());
        addAllowedTagSet(GeoTIFFTagSet.getInstance());
    }

    /**
     * Adds a {@code TIFFTagSet} object to the list of allowed
     * tag sets.  Attempting to add a duplicate object to the list
     * has no effect.
     *
     * @param tagSet a {@code TIFFTagSet}.
     *
     * @throws IllegalArgumentException if {@code tagSet} is
     * {@code null}.
     */
    public void addAllowedTagSet(TIFFTagSet tagSet) {
        if (tagSet == null) {
            throw new IllegalArgumentException("tagSet == null!");
        }
        if (!allowedTagSets.contains(tagSet)) {
            allowedTagSets.add(tagSet);
        }
    }

    /**
     * Removes a {@code TIFFTagSet} object from the list of
     * allowed tag sets.  Removal is based on the {@code equals}
     * method of the {@code TIFFTagSet}, which is normally
     * defined as reference equality.
     *
     * @param tagSet a {@code TIFFTagSet}.
     *
     * @throws IllegalArgumentException if {@code tagSet} is
     * {@code null}.
     */
    public void removeAllowedTagSet(TIFFTagSet tagSet) {
        if (tagSet == null) {
            throw new IllegalArgumentException("tagSet == null!");
        }
        allowedTagSets.remove(tagSet);
    }

    /**
     * Returns a {@code List} containing the allowed
     * {@code TIFFTagSet} objects.
     *
     * @return a {@code List} of {@code TIFFTagSet}s.
     */
    public List<TIFFTagSet> getAllowedTagSets() {
        return allowedTagSets;
    }

    /**
     * Set whether to read fields corresponding to {@code TIFFTag}s not in
     * the allowed {@code TIFFTagSet}s. The default setting is {@code false}.
     * If the TIFF {@code ImageReader} is ignoring metadata, then a setting
     * of {@code true} is overridden as all metadata are ignored except those
     * essential to reading the image itself.
     *
     * @param readUnknownTags Whether to read fields of unrecognized tags
     */
    public void setReadUnknownTags(boolean readUnknownTags) {
        this.readUnknownTags = readUnknownTags;
    }

    /**
     * Retrieve the setting of whether to read fields corresponding to unknown
     * {@code TIFFTag}s.
     *
     * @return Whether to read fields of unrecognized tags
     */
    public boolean getReadUnknownTags() {
        return readUnknownTags;
    }
}
