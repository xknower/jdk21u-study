package jdk.jfr.internal.settings;

import jdk.jfr.SettingControl;

/**
 * SettingControls that derive from this class avoids executing settings
 * modifications in a AccessController.doPrivilege(...) block.
 */
public abstract class JDKSettingControl extends SettingControl {
}
