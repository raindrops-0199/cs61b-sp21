package gitlet.dataStructure;


import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

/**
 * A file descriptor class.
 * Stands for a regular file, a sub_tree or other files.
 * A Tree object contains a collection of FileD.
 *
 * @author jason
 * TODO
 */
public class FileD implements Serializable {
    /**
     * Represents the type and permissions of the file.
     * In Git, common file modes include:
     * - 100644: Regular file
     * - 100755: Executable file
     * - 040000: Subdirectory (another tree)
     * - 160000: Git link
     */
    private String mode;

    /**
     * a blob or a tree
     */
    private String type;

    /** sha1 of the file */
    private String fileHash;

    /** file name or folder name */
    private String fileName;

    private String path;

    /**
     * Construct via file or directory path
     * Don't know if needed
     * @param path path of file
     * @param fileHash hash of file. Need this because we can't get hash from path, it's bound with blob.
     * @throws IOException IOException
     */
    public FileD(String path, String fileHash) throws IOException {
        this.path = path;
        Path filePath = Paths.get(path);
        this.fileName = filePath.getFileName().toString();
        this.mode = computeMode(filePath);
        this.fileHash = fileHash;
        if (Files.isRegularFile(filePath)) {
            this.type = ObjectType.BLOB.getRepresent();
        } else if (Files.isDirectory(filePath)) {
            this.type = ObjectType.TREE.getRepresent();
        }
    }

    /**
     * Construct via a blob
     * @param blob A Blob object to add
     * @throws IOException if an I/O error occurs while accessing the file
     */
    public FileD(Blob blob) throws IOException {
        this.type = blob.getType().getRepresent();
        this.path = blob.getPath();
        this.fileHash = blob.getHash();
        Path filePath = Paths.get(path);
        this.fileName = filePath.getFileName().toString();
        this.mode = computeMode(filePath);
    }

    /**
     * Construct via a tree
     * @param tree Tree object to add
     * TODO
     */
    public FileD(Tree tree) {}

    /**
     * Concatenate all attributes together.
     * @return  mode + type + fileHash + fileName
     */
    public String toString() {
        return getMode() + " " +
                getType() + " " +
                getFileHash() + "    " +
                getFileName();
    }

    public String getMode() {
        return mode;
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

    /**
     * Computes the mode of the given file path.
     *
     * @param filePath the file path for which to compute the mode
     * @return the octal representation of the file mode
     * @throws IOException if an I/O error occurs while accessing the file
     */
    private String computeMode(Path filePath) throws IOException {
        int mode = 0;
        if (Files.isDirectory(filePath)) {
            mode |= 040000;
        }
        else if (Files.isRegularFile(filePath)) {
            // Get the file's permission set
            Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(filePath);
            // Convert the permission set to the octal mode
            mode = convertPermissionsToMode(permissions);
            mode |= 0100000;
        }
        return String.format("%06o", mode);
    }

    /**
     * Converts a set of file permissions to Git's octal mode representation.
     *
     * @param permissions The set of file permissions
     * @return Git's octal mode representation
     */
    private static int convertPermissionsToMode(Set<PosixFilePermission> permissions) {
        int mode = 0;
        // Mapping array of permission bits to corresponding permission values
        int[] permissionBits = {0400, 0200, 0100, 040, 020, 010, 04, 02, 01};
        // Array of POSIX file permission enum values
        PosixFilePermission[] posixPermissions = {
                PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_EXECUTE,
                PosixFilePermission.GROUP_READ, PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_EXECUTE,
                PosixFilePermission.OTHERS_READ, PosixFilePermission.OTHERS_WRITE, PosixFilePermission.OTHERS_EXECUTE
        };

        // Iterate over each permission bit and permission value, performing the mapping conversion
        for (int i = 0; i < permissionBits.length; i++) {
            if (permissions.contains(posixPermissions[i])) {
                mode |= permissionBits[i];
            }
        }

        return mode;
    }
}
