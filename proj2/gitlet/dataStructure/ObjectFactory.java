package gitlet.dataStructure;

import java.util.Date;

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
     * create a LooseObject by ObjectType and args.
     * @param type COMMIT, BLOB or TREE
     * @param args pass to specific create function.
     * @return a LooseObject
     */
    public static LooseObject create(ObjectType type, String[] args) {
        LooseObject looseObject = null;
        switch (type) {
            case COMMIT:
                looseObject = createCommit(args);
                break;
            case BLOB:
                looseObject = createBlob(args);
                break;
            case TREE:
                looseObject = createTree(args);
                break;
        }
        return looseObject;
    }
    /**
     * make a commit object
     * @param args args follow order: date, message, tree hash, parent hash, (parent2 hash).
     * @return a new commit object
     */
    private static Commit createCommit(String[] args) {
        Commit commit = new Commit(args[0]);
        commit.setMessage(args[1]);
        commit.setTreeHash(args[2]);
        commit.addParentHash(args[3]);
        for (int i = 4; i < args.length; i++) {
            commit.addParentHash(args[i]);
        }
        commit.setHash();
        return commit;
    }

    /**
     * make a blob object
     * @param args only one arg: file name
     * @return a blob
     */
    private static Blob createBlob(String[] args) {
        return new Blob(args[0]);
    }

    /**
     * make a tree object
     * @param args file names of tree content
     * @return a tree
     */
    private static Tree createTree(String[] args) {
        return new Tree();
    }
}
