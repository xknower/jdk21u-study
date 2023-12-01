package jdk.internal.access;

import java.util.concurrent.ForkJoinPool;

public interface JavaUtilConcurrentFJPAccess {
    long beginCompensatedBlock(ForkJoinPool pool);
    void endCompensatedBlock(ForkJoinPool pool, long post);
}
