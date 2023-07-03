package gitlet.dataStructure;

import gitlet.Config;
import gitlet.Repository;
import gitlet.utils.Utils;

import java.io.File;
import java.io.Serializable;

/**
 * An abstract class of LooseObjects in git.
 * A LooseObject can be Commit, Blob and Tree.
 *
 * @author jason
 */
public abstract class LooseObject implements Serializable {
    /**
     * the type of the object
     * the field has only three possible values: "blob", "commit", "tree"
     * type is set by specific object class
     */
    protected ObjectType type;
    /** stores the hash of the loose object */
    protected String hash;

    /**
     * this method is used to get the type of object
     * @return the type of loose object
     */
    public ObjectType getType() {
        return type;
    }

    /**
     * to get hash of loose object
     * @return the hash of the object
     */
    public String getHash() {
        return hash;
    }

    /**
     * write object to disk
     */
    public void writeLooseObject() {
        String objDir = hash.substring(0, 2);
        File dir = Utils.join(Config.OBJ_DIR, objDir);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                System.err.println("Create directory failed");
            }
        }
        File file = Utils.join(dir, hash.substring(2));
        if (!file.exists()) {
            Utils.writeObject(file, this);
        }
    }

    /**
     * compute the SHA-1 hash of the object
     * @return hash
     */
    public abstract String computeHash();

    /**
     * convert the content of object to a String
     * @return content of object as String
     */
    public abstract String toString();
}

