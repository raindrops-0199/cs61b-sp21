package gitlet.dataStructure;

import gitlet.Repository;
import gitlet.utils.Utils;

import java.io.File;
import java.io.IOException;

/**
 * A class represents staging area in gitlet.
 * Mapping to index file in .gitlet folder.
 * Also offers options to interact with index file.
 *
 * @author jason
 * TODO
 */
public class Index {
    private static Tree stage;
    private static final String name = "index";

    private static void readIndex() {
        File f = Utils.join(Repository.GITLET_DIR, name);
        stage = Utils.readObject(f, Tree.class);
    }

    private static void writeIndex() {
        File f = Utils.join(Repository.GITLET_DIR, name);
        Utils.writeObject(f, stage);
    }

    /**
     * create index file when initiating repo
     * @throws IOException IOException
     */
    public static void createStage() throws IOException {
        stage = new Tree();
        File indexF = Utils.join(Repository.GITLET_DIR, name);
        if (!indexF.exists()) {
            indexF.createNewFile();
        }
        writeIndex();
    }

    // TODO
    public static Tree getAddition() {
        return null;
    }

    // TODO
    public static Tree getRemoval() {
        return null;
    }

    // TODO
    public static Tree getModified() {
        return null;
    }

    // TODO
    public static Tree getUntracked() {
        return null;
    }

    /**
     * Add a file to index. A file may be in a new folder, which needs to make a new Tree object.
     * For now, there's no subdirectories, so not considering tree.
     * @param filePath path of file.
     * @param fileHash hash of file.
     * @throws IOException IOException
     */
    public static void add(String filePath, String fileHash) throws IOException {
        readIndex();
        stage.addObj(filePath, fileHash);
        writeIndex();
    }

    // remove a file from index.
    // TODO
    public static void remove() {}
}
