package jdk.tools.jlink.plugin;

/**
 * Resource pool builder to build a resource pool by incrementally
 * adding a set of resources one at a time.
 */
public interface ResourcePoolBuilder {
    /**
     * Add a ResourcePoolEntry.
     *
     * @param data The ResourcePoolEntry to add.
     */
    public void add(ResourcePoolEntry data);

    /**
     * Done with adding resource entries. Construct
     * a ResourcePool with the added resource entries.
     *
     * @return a new ResourcePool filled with entries added.
     */
    public ResourcePool build();
}
