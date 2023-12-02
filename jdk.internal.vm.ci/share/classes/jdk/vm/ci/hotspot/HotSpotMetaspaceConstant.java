package jdk.vm.ci.hotspot;

import jdk.vm.ci.meta.VMConstant;

public interface HotSpotMetaspaceConstant extends HotSpotConstant, VMConstant {

    HotSpotResolvedObjectType asResolvedJavaType();

    HotSpotResolvedJavaMethod asResolvedJavaMethod();
}
