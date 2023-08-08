package gitlet.dataStructure;

import gitlet.Config;
import gitlet.utils.Utils;

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
        stage = Utils.readObject(Config.INDEX_FILE, Tree.class);
    }

    /**
     * write stage content to index file
     */
    private void writeIndex() {
        Utils.writeObject(Config.INDEX_FILE, stage);
    }

    /**
     * check if index file already created
     * @return true if index file exists
     */
    private boolean exists() {
        return Config.INDEX_FILE.exists();
    }

    @Override
    public void createStage() throws IOException {
        stage = new Tree();
        if (!Config.INDEX_FILE.exists()) {
            if (!Config.INDEX_FILE.createNewFile()) {
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
