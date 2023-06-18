package gitlet.dataStructure;

import java.io.Serializable;

/**
 * A file descriptor class.
 * Will mainly be used in Tree class.
 *
 * @author jason
 * TODO
 */
public class FileD implements Serializable {
    private String mode;
    private String type;
    private String fileHash;
    private String fileName;

    public FileD(String mode, String type, String hash, String name) {}

    public String getDetail() {
        return "";
    }

    public String getMode() { return mode;
    }

    public String getType() {
        return type;
    }

    public String getFileHash() {
        return fileHash;
    }

    public String getFileName() {
        return fileName;
    }

}
