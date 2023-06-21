package gitlet.dataStructure;

import gitlet.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Represents a gitlet commit object.
 *
 * @author jason
 */
public class Commit extends LooseObject{
    /** the hash of commit tree */
    private String treeHash;
    /** hash of parent commit */
    private final List<String> parentHash = new ArrayList<>();
    /** date info about the commit */
    private final String date;
    /** commit message */
    private String message;

    /** constructor of a commit*/
    public Commit(String date) {
        this.type = ObjectType.COMMIT;
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTreeHash(String treeHash) {
        this.treeHash = treeHash;
    }

    public void addParentHash(String hash) {
        this.parentHash.add(hash);
    }

    /**
     * Adding treeHash, parentHashes, date and message together then compute sha1
     * @return sha1 of commit contents
     */
    @Override
    public String computeHash() {
        List<String> contents = new ArrayList<>();
        contents.add(treeHash);
        contents.addAll(parentHash);
        contents.add(date);
        contents.add(message);
        this.hash = Utils.sha1(contents);
        return this.hash;
    }

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        lines.add("tree " + this.treeHash);
        for (String parent : parentHash) {
            lines.add("parent " + parent);
        }
        lines.add("Date " + this.date);
        lines.add(this.message);
        return lines2String(lines);
    }

    /**
     * message printed to git log command
     * @return String
     */
    public String toLog() {
        List<String> lines = new ArrayList<>();
        lines.add("commit " + this.hash);
        if (parentHash.size() > 1) {
            lines.add("Merge: " + parentHash.get(0).substring(0, 7) + parentHash.get(1).substring(0, 7));
        }
        lines.add("Date: " + this.date);
        lines.add(this.message);
        return lines2String(lines);
    }

    /**
     * helper function.
     * convert Lise lines to one String.
     * @param lines a List of lines
     * @return one string
     */
    private String lines2String(List<String> lines) {
        StringBuilder res = new StringBuilder();
        for (String line : lines) {
            res.append(line);
            res.append(System.lineSeparator());
        }
        res.append(System.lineSeparator());
        return res.toString();
    }
}
