package com.sun.org.apache.xerces.internal.impl.dv;

import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xs.XSAnnotation;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import java.util.List;

/**
 * The class used to pass all facets to {@link XSSimpleType#applyFacets}.
 *
 * @xerces.internal
 *
 * @author Sandy Gao, IBM
 *
 * @LastModified: Oct 2017
 */
public class XSFacets {

    /**
     * value of length facet.
     */
    public int length;

    /**
     * value of minLength facet.
     */
    public int minLength;

    /**
     * value of maxLength facet.
     */
    public int maxLength;

    /**
     * value of whiteSpace facet.
     */
    public short whiteSpace;

    /**
     * value of totalDigits facet.
     */
    public int totalDigits;

    /**
     * value of fractionDigits facet.
     */
    public int fractionDigits;

    /**
     * string containing value of pattern facet, for multiple patterns values
     * are ORed together.
     */
    public String pattern;

    /**
     * ArrayList containing values of Enumeration facet, as String's.
     */
    public List<String> enumeration;

    /**
     * An array parallel to "Vector enumeration". It contains namespace context
     * of each enumeration value. Elements of this vector are NamespaceContext
     * objects.
     */
    public List<NamespaceContext> enumNSDecls;

    /**
     * value of maxInclusive facet.
     */
    public String maxInclusive;

    /**
     * value of maxExclusive facet.
     */
    public String maxExclusive;

    /**
     * value of minInclusive facet.
     */
    public String minInclusive;

    /**
     * value of minExclusive facet.
     */
    public String minExclusive;



    public XSAnnotation lengthAnnotation;
    public XSAnnotation minLengthAnnotation;
    public XSAnnotation maxLengthAnnotation;
    public XSAnnotation whiteSpaceAnnotation;
    public XSAnnotation totalDigitsAnnotation;
    public XSAnnotation fractionDigitsAnnotation;
    public XSObjectListImpl patternAnnotations;
    public XSObjectList enumAnnotations;
    public XSAnnotation maxInclusiveAnnotation;
    public XSAnnotation maxExclusiveAnnotation;
    public XSAnnotation minInclusiveAnnotation;
    public XSAnnotation minExclusiveAnnotation;

    public void reset(){
        lengthAnnotation = null;
        minLengthAnnotation = null;
        maxLengthAnnotation = null;
        whiteSpaceAnnotation = null;
        totalDigitsAnnotation = null;
        fractionDigitsAnnotation = null;
        patternAnnotations = null;
        enumAnnotations = null;
        maxInclusiveAnnotation = null;
        maxExclusiveAnnotation = null;
        minInclusiveAnnotation = null;
        minExclusiveAnnotation = null;
    }
}
