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
            case COMMIT -> {
                looseObject = createCommit(args);
            }
            case BLOB -> {
                looseObject = createBlob(args);
            }
            case TREE -> {
                looseObject = createTree(args);
            }
        }
        return looseObject;
    }
    /**
     * make a commit object
     * @param args args follow order: message, tree hash, parent hash, (parent2 hash).
     * @return a new commit object
     */
    private static Commit createCommit(String[] args) {
        Commit commit = new Commit(new Date());
        commit.setMessage(args[0]);
        commit.setTreeHash(args[1]);
        commit.addParentHash(args[2]);
        for (int i = 3; i < args.length; i++) {
            commit.addParentHash(args[i]);
        }
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
