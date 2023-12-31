package com.sun.media.sound;

import java.util.Objects;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.spi.MidiDeviceProvider;

/**
 * Super class for MIDI input or output device provider.
 *
 * @author Florian Bomers
 */
public abstract class AbstractMidiDeviceProvider extends MidiDeviceProvider {

    private static final boolean enabled;

    /**
     * Create objects representing all MIDI output devices on the system.
     */
    static {
        Platform.initialize();
        enabled = Platform.isMidiIOEnabled();
        // $$fb number of MIDI devices may change with time
        // also for memory's sake, do not initialize the arrays here
    }

    final synchronized void readDeviceInfos() {
        Info[] infos = getInfoCache();
        MidiDevice[] devices = getDeviceCache();
        if (!enabled) {
            if (infos == null || infos.length != 0) {
                setInfoCache(new Info[0]);
            }
            if (devices == null || devices.length != 0) {
                setDeviceCache(new MidiDevice[0]);
            }
            return;
        }

        int oldNumDevices = (infos==null)?-1:infos.length;
        int newNumDevices = getNumDevices();
        if (oldNumDevices != newNumDevices) {
            // initialize the arrays
            Info[] newInfos = new Info[newNumDevices];
            MidiDevice[] newDevices = new MidiDevice[newNumDevices];

            for (int i = 0; i < newNumDevices; i++) {
                Info newInfo = createInfo(i);

                // in case that we are re-reading devices, try to find
                // the previous one and reuse it
                if (infos != null) {
                    for (int ii = 0; ii < infos.length; ii++) {
                        Info info = infos[ii];
                        if (info != null && info.equalStrings(newInfo)) {
                            // new info matches the still existing info. Use old one
                            newInfos[i] = info;
                            info.setIndex(i);
                            infos[ii] = null; // prevent re-use
                            newDevices[i] = devices[ii];
                            devices[ii] = null;
                            break;
                        }
                    }
                }
                if (newInfos[i] == null) {
                    newInfos[i] = newInfo;
                }
            }
            // the remaining MidiDevice.Info instances in the infos array
            // have become obsolete.
            if (infos != null) {
                for (int i = 0; i < infos.length; i++) {
                    if (infos[i] != null) {
                        // disable this device info
                        infos[i].setIndex(-1);
                    }
                    // what to do with the MidiDevice instances that are left
                    // in the devices array ?? Close them ?
                }
            }
            // commit new list of infos.
            setInfoCache(newInfos);
            setDeviceCache(newDevices);
        }
    }

    @Override
    public final MidiDevice.Info[] getDeviceInfo() {
        readDeviceInfos();
        Info[] infos = getInfoCache();
        MidiDevice.Info[] localArray = new MidiDevice.Info[infos.length];
        System.arraycopy(infos, 0, localArray, 0, infos.length);
        return localArray;
    }

    @Override
    public final MidiDevice getDevice(final MidiDevice.Info info) {
        Objects.requireNonNull(info);
        if (info instanceof Info) {
            readDeviceInfos();
            MidiDevice[] devices = getDeviceCache();
            Info[] infos = getInfoCache();
            Info thisInfo = (Info) info;
            int index = thisInfo.getIndex();
            if (index >= 0 && index < devices.length && infos[index] == info) {
                if (devices[index] == null) {
                    devices[index] = createDevice(thisInfo);
                }
                if (devices[index] != null) {
                    return devices[index];
                }
            }
        }
        throw MidiUtils.unsupportedDevice(info);
    }

    /**
     * Info class for MidiDevices.  Adds an index value for
     * making native references to a particular device.
     */
    static class Info extends MidiDevice.Info {
        private int index;

        Info(String name, String vendor, String description, String version, int index) {
            super(name, vendor, description, version);
            this.index = index;
        }

        final boolean equalStrings(Info info) {
            return      (info != null
                         && getName().equals(info.getName())
                         && getVendor().equals(info.getVendor())
                         && getDescription().equals(info.getDescription())
                         && getVersion().equals(info.getVersion()));
        }

        final int getIndex() {
            return index;
        }

        final void setIndex(int index) {
            this.index = index;
        }

    } // class Info

    abstract int getNumDevices();
    abstract MidiDevice[] getDeviceCache();
    abstract void setDeviceCache(MidiDevice[] devices);
    abstract Info[] getInfoCache();
    abstract void setInfoCache(Info[] infos);

    abstract Info createInfo(int index);
    abstract MidiDevice createDevice(Info info);
}
