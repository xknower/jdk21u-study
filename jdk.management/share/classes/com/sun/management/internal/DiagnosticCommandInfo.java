package com.sun.management.internal;

import java.util.List;

/**
 * Diagnostic command information. It contains the description of a
 * diagnostic command.
 *
 * @since 1.8
 */

class DiagnosticCommandInfo {
    private final String name;
    private final String description;
    private final String impact;
    private final String permissionClass;
    private final String permissionName;
    private final String permissionAction;
    private final boolean enabled;
    private final List<DiagnosticCommandArgumentInfo> arguments;

    /**
     * Returns the diagnostic command name.
     *
     * @return the diagnostic command name
     */
    String getName() {
        return name;
    }

   /**
     * Returns the diagnostic command description.
     *
     * @return the diagnostic command description
     */
    String getDescription() {
        return description;
    }

    /**
     * Returns the potential impact of the diagnostic command execution
     *         on the Java virtual machine behavior.
     *
     * @return the potential impact of the diagnostic command execution
     *         on the Java virtual machine behavior
     */
    String getImpact() {
        return impact;
    }

    /**
     * Returns the name of the permission class required to be allowed
     *         to invoke the diagnostic command, or null if no permission
     *         is required.
     *
     * @return the name of the permission class name required to be allowed
     *         to invoke the diagnostic command, or null if no permission
     *         is required
     */
    String getPermissionClass() {
        return permissionClass;
    }

    /**
     * Returns the permission name required to be allowed to invoke the
     *         diagnostic command, or null if no permission is required.
     *
     * @return the permission name required to be allowed to invoke the
     *         diagnostic command, or null if no permission is required
     */
    String getPermissionName() {
        return permissionName;
    }

    /**
     * Returns the permission action required to be allowed to invoke the
     *         diagnostic command, or null if no permission is required or
     *         if the permission has no action specified.
     *
     * @return the permission action required to be allowed to invoke the
     *         diagnostic command, or null if no permission is required or
     *         if the permission has no action specified
     */
    String getPermissionAction() {
        return permissionAction;
    }

    /**
     * Returns {@code true} if the diagnostic command is enabled,
     *         {@code false} otherwise. The enabled/disabled
     *         status of a diagnostic command can evolve during
     *         the lifetime of the Java virtual machine.
     *
     * @return {@code true} if the diagnostic command is enabled,
     *         {@code false} otherwise
     */
    boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns the list of the diagnostic command arguments description.
     * If the diagnostic command has no arguments, it returns an empty list.
     *
     * @return a list of the diagnostic command arguments description
     */
    List<DiagnosticCommandArgumentInfo> getArgumentsInfo() {
        return arguments;
    }

    DiagnosticCommandInfo(String name, String description,
                                    String impact, String permissionClass,
                                    String permissionName, String permissionAction,
                                    boolean enabled,
                                    List<DiagnosticCommandArgumentInfo> arguments)
    {
        this.name = name;
        this.description = description;
        this.impact = impact;
        this.permissionClass = permissionClass;
        this.permissionName = permissionName;
        this.permissionAction = permissionAction;
        this.enabled = enabled;
        this.arguments = arguments;
    }
}
