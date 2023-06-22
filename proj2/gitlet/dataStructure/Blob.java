package gitlet.dataStructure;

import gitlet.utils.Utils;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * A Blob represents a regular file created by user.
 *
 * @author jason
 */
public class Blob extends LooseObject{
    private final byte[] content;
    private final String path;
    //private String content;

    public Blob(String path) {
        this.path = path;
        this.type = ObjectType.BLOB;
        File f = Utils.join(path);
        this.content = Utils.readContents(f);
        this.hash = computeHash();
    }

    @Override
    public String computeHash() {
        return Utils.sha1((Object) content);
    }

    @Override
    public String toString() {
        return new String(this.content, StandardCharsets.UTF_8);
    }

    public String getPath() {
        return path;
    }
}
