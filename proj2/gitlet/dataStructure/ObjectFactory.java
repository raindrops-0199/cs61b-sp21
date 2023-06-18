package gitlet.dataStructure;

import java.util.ArrayList;

/**
 * Using of Simple Factory Pattern
 * <p>
 * This factory is used to create loose objects.
 * A loose object may be a commit, blob or tree.
 *
 * @author jason
 * TODO
 */
public class ObjectFactory {

    /**
     * make a commit object
     * @param args it should contain: message, author, parent hash and a tree hash.
     * @return a new commit object
     */
    public static Commit createCommit(String[] args) {}

    public static Blob createBlob(String path) {}

    public static Tree createTree(ArrayList<String> fileNames) {}
}
