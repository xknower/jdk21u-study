package jdk.javadoc.internal.doclets.formats.html;

import jdk.javadoc.internal.doclets.formats.html.markup.Comment;

/**
 * Marker comments to identify regions in the generated files.
 */
public class MarkerComments {

    /**
     * Marker to identify start of top navigation bar.
     */
    public static final Comment START_OF_TOP_NAVBAR =
            new Comment("========= START OF TOP NAVBAR =======");

    /**
     * Marker to identify end of top navigation bar.
     */
    public static final Comment END_OF_TOP_NAVBAR =
            new Comment("========= END OF TOP NAVBAR =========");

    /**
     * Marker to identify start of module description.
     */
    public static final Comment START_OF_MODULE_DESCRIPTION =
            new Comment("============ MODULE DESCRIPTION ===========");

    /**
     * Marker to identify start of modules summary.
     */
    public static final Comment START_OF_MODULES_SUMMARY =
            new Comment("============ MODULES SUMMARY ===========");

    /**
     * Marker to identify start of packages summary.
     */
    public static final Comment START_OF_PACKAGES_SUMMARY =
            new Comment("============ PACKAGES SUMMARY ===========");

    /**
     * Marker to identify start of services summary.
     */
    public static final Comment START_OF_SERVICES_SUMMARY =
            new Comment("============ SERVICES SUMMARY ===========");

    /**
     * Marker to identify start of class data.
     */
    public static final Comment START_OF_CLASS_DATA =
            new Comment("======== START OF CLASS DATA ========");

    /**
     * Marker to identify end of class data.
     */
    public static final Comment END_OF_CLASS_DATA =
            new Comment("========= END OF CLASS DATA =========");

    /**
     * Marker to identify start of nested class summary.
     */
    public static final Comment START_OF_NESTED_CLASS_SUMMARY =
            new Comment("======== NESTED CLASS SUMMARY ========");

    /**
     * Marker to identify start of annotation type optional member summary.
     */
    public static final Comment START_OF_ANNOTATION_TYPE_OPTIONAL_MEMBER_SUMMARY =
            new Comment("=========== ANNOTATION TYPE OPTIONAL MEMBER SUMMARY ===========");

    /**
     * Marker to identify start of annotation interface optional member summary.
     */
    public static final Comment START_OF_ANNOTATION_INTERFACE_OPTIONAL_MEMBER_SUMMARY =
            new Comment("=========== ANNOTATION INTERFACE OPTIONAL MEMBER SUMMARY ===========");

    /**
     * Marker to identify start of annotation type required member summary.
     */
    public static final Comment START_OF_ANNOTATION_TYPE_REQUIRED_MEMBER_SUMMARY =
            new Comment("=========== ANNOTATION TYPE REQUIRED MEMBER SUMMARY ===========");

    /**
     * Marker to identify start of annotation interface required member summary.
     */
    public static final Comment START_OF_ANNOTATION_INTERFACE_REQUIRED_MEMBER_SUMMARY =
            new Comment("=========== ANNOTATION INTERFACE REQUIRED MEMBER SUMMARY ===========");

    /**
     * Marker to identify start of constructor summary.
     */
    public static final Comment START_OF_CONSTRUCTOR_SUMMARY =
            new Comment("======== CONSTRUCTOR SUMMARY ========");

    /**
     * Marker to identify start of enum constants summary.
     */
    public static final Comment START_OF_ENUM_CONSTANT_SUMMARY =
            new Comment("=========== ENUM CONSTANT SUMMARY ===========");

    /**
     * Marker to identify start of field summary.
     */
    public static final Comment START_OF_FIELD_SUMMARY =
            new Comment("=========== FIELD SUMMARY ===========");

    /**
     * Marker to identify start of properties summary.
     */
    public static final Comment START_OF_PROPERTY_SUMMARY =
            new Comment("=========== PROPERTY SUMMARY ===========");

    /**
     * Marker to identify start of method summary.
     */
    public static final Comment START_OF_METHOD_SUMMARY =
            new Comment("========== METHOD SUMMARY ===========");

    /**
     * Marker to identify start of annotation type details.
     */
    public static final Comment START_OF_ANNOTATION_TYPE_DETAILS =
            new Comment("============ ANNOTATION TYPE MEMBER DETAIL ===========");

    /**
     * Marker to identify start of annotation interface details.
     */
    public static final Comment START_OF_ANNOTATION_INTERFACE_DETAILS =
            new Comment("============ ANNOTATION INTERFACE MEMBER DETAIL ===========");

    /**
     * Marker to identify start of method details.
     */
    public static final Comment START_OF_METHOD_DETAILS =
            new Comment("============ METHOD DETAIL ==========");

    /**
     * Marker to identify start of field details.
     */
    public static final Comment START_OF_FIELD_DETAILS =
            new Comment("============ FIELD DETAIL ===========");

    /**
     * Marker to identify start of property details.
     */
    public static final Comment START_OF_PROPERTY_DETAILS =
            new Comment("============ PROPERTY DETAIL ===========");

    /**
     * Marker to identify start of constructor details.
     */
    public static final Comment START_OF_CONSTRUCTOR_DETAILS =
            new Comment("========= CONSTRUCTOR DETAIL ========");

    /**
     * Marker to identify start of enum constants details.
     */
    public static final Comment START_OF_ENUM_CONSTANT_DETAILS =
            new Comment("============ ENUM CONSTANT DETAIL ===========");

}
