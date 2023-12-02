package jdk.vm.ci.hotspot;

import jdk.vm.ci.meta.Constant;

/**
 * Marker interface for hotspot specific constants.
 */
public interface HotSpotConstant extends Constant {

    boolean isCompressed();

    Constant compress();

    Constant uncompress();
}
