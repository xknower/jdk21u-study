package com.sun.nio.file;

import java.nio.file.CopyOption;
import jdk.internal.misc.FileSystemOption;

/**
 * Defines <em>extended</em> copy options supported on some platforms
 * by Sun's provider implementation.
 *
 * @since 1.7
 */

public enum ExtendedCopyOption implements CopyOption {
    /**
     * The copy may be interrupted by the {@link Thread#interrupt interrupt}
     * method.
     */
    INTERRUPTIBLE(FileSystemOption.INTERRUPTIBLE);

    ExtendedCopyOption(FileSystemOption<Void> option) {
        option.register(this);
    }
}
