package gitlet.dataStructure;

import gitlet.Config;
import gitlet.Repository;
import gitlet.utils.Utils;

import java.io.File;
import java.io.IOException;

/**
 * A class represents staging area in gitlet.
 * Mapping to index file in .gitlet folder.
 * Also offers options to interact with index file.
 * Use Singleton Pattern
 *
 * @author jason
 * TODO
 */
public class Index implements Stage{
    /** stage content */
    private Tree stage;
    /** index file path */
    private final String NAME = "index";

    private Index() throws IOException {
        if (!this.exists()) {
            this.createStage();
        }
    }

    /**
     * a holder class that makes sure only one Index instance exists
     */
    private static class IndexHolder {
        private static final Index instance;

        static {
            try {
                instance = new Index();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Singleton Pattern
     * for user to get an instance of Index class and make sure only one instance exists
     * @return the only instance of Index class
     */
    public static Index getInstance() {
        return IndexHolder.instance;
    }

    /**
     * read stage content from index file
     */
    private void readIndex() {
        File f = Utils.join(Config.GITLET_DIR, NAME);
        stage = Utils.readObject(f, Tree.class);
    }

    /**
     * write stage content to index file
     */
    private void writeIndex() {
        File f = Utils.join(Config.GITLET_DIR, NAME);
        Utils.writeObject(f, stage);
    }

    /**
     * check if index file already created
     * @return true if index file exists
     */
    private boolean exists() {
        File f = Utils.join(Config.GITLET_DIR, NAME);
        return f.exists();
    }

    @Override
    public void createStage() throws IOException {
        stage = new Tree();
        File indexF = Utils.join(Config.GITLET_DIR, NAME);
        if (!indexF.exists()) {
            if (!indexF.createNewFile()) {
                throw new IOException("Create index file fail");
            }
        }
        writeIndex();
    }

    // TODO
    @Override
    public String getAddition() {
        return "";
    }

    // TODO
    @Override
    public String getRemoval() {
        return "";
    }

    // TODO
    @Override
    public String getModified() {
        return "";
    }

    // TODO
    @Override
    public String getUntracked() {
        return "";
    }

    @Override
    public void add(String filePath, String fileHash) throws IOException {
        readIndex();
        stage.addObj(filePath, fileHash);
        writeIndex();
    }

    @Override
    public void remove(String filePath) {
        readIndex();
        stage.removeObj(filePath);
        writeIndex();
    }
}
