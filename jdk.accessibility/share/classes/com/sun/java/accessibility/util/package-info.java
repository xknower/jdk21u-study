/**
 * Provides a collection of interfaces and classes that compose the Java Accessibility
 * Utilities.  The classes are used by Assistive Technologies, such as the screen
 * readers which are used by those who are blind, and help provide access to GUI
 * toolkits that implement the Java Accessibility API.  An overview of the important
 * classes follows.
 *
 * <p>The class {@code AccessibilityEventMonitor} implements a PropertyChange
 * listener on every UI object that implements interface {@code Accessible} in the Java
 * Virtual Machine.
 *
 * <p> The class {@code AWTEventMonitor} implements a suite of listeners that are
 * conditionally installed on every AWT component instance in the Java Virtual Machine.
 *
 * <p>The class {@code EventQueueMonitor} provides key core functionality for
 * Assistive Technologies (and other system-level technologies that need some of
 * the same things that Assistive Technology needs).
 *
 * <p>The class {@code GUIInitializedMulticaster} is used to maintain a list of
 * {@code GUIInitializedListener} classes which are used by the {@code EventQueueMonitor}
 * class to notify an interested party when the GUI subsystem has been initialized.
 * Note that this class is intended to be used primarily for internal support in
 * the {@code EventQueueMonitor} class, and is not intended to be used by classes
 * outside the Java Accessibility Utility package.
 *
 * <p>The class {@code SwingEventMonitor} extends {@code AWTEventMonitor} by adding
 * a suite of listeners conditionally installed on every Swing component instance
 * in the Java Virtual Machine.
 *
 * <p>The class {@code TopLevelWindowMulticaster} is used to maintain a list of
 * {@code TopLevelWindowListener} classes which are used by the {@code EventQueueMonitor}
 * class to notify an interested party when a top level window is created or destroyed
 * in the Java Virtual Machine  Note that this class is intended to be used primarily
 * for internal support in the {@code EventQueueMonitor} class, and is not intended
 * to be used by classes outside the Java Accessibility Utility package.
 *
 * <p>The class {@code Translator} provides a translation to interface {@code Accessible}
 * for objects that do not implement interface {@code Accessible}.
 *
 * @since JDK1.7
 */
package com.sun.java.accessibility.util;
