package gitlet.dataStructure;

// TODO: any imports you need here

import java.util.Date;

/**
 * Represents a gitlet commit object.
 *
 * @author jason
 * TODO
 */
public class Commit extends LooseObject{
    /** type is "commit" */
    private String type;
    /** the hash of commit tree */
    private String treeHash;
    /** hash of parent commit */
    private String parentHash;
    /** author of the commit */
    private String author;
    /** date info about the commit */
    private String date;
    /** commit message */
    private String message;

    /** constructor of a commit*/
    public Commit(Date date, String message, String author, String parentHash, String treeHash) {}

    @Override
    public void writeLooseObject() {}

    @Override
    public String computeHash() {
        return "";
    }

    @Override
    public String toString() {
        return "";
    }
}
