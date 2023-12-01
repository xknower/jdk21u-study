package sun.nio.fs;

class LinuxUserDefinedFileAttributeView
    extends UnixUserDefinedFileAttributeView
{

    LinuxUserDefinedFileAttributeView(UnixPath file, boolean followLinks) {
        super(file, followLinks);
    }

    @Override
    protected int maxNameLength() {
        return 255;
    }

}
