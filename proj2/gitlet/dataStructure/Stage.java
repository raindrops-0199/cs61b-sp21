package gitlet.dataStructure;

import java.io.IOException;

public interface Stage {
    /**
     * create stage area file
     * @throws IOException IOException
     */
    public void createStage() throws IOException;

    /**
     * get staged files
     * @return staged file in formatted string
     */
    public String getAddition();

    /**
     * get removed files
     * @return removed files in formatted string
     */
    public String getRemoval();

    /**
     * get modified but not stated files
     * @return files in formatted string
     */
    public String getModified();

    /**
     * get untracked files
     * @return files in formatted string
     */
    public String getUntracked();

    /**
     * Add a blob or tree to stage area. A file may be in a new folder, which needs to make a new Tree object.
     * For now, there's no subdirectories, so not considering tree.
     * @param filePath path of file.
     * @param fileHash hash of file.
     */
    public void add(String filePath, String fileHash) throws IOException;

    /**
     * remove a blob or tree from stage area
     * @param filePath path to file or directory
     */
    public void remove(String filePath); 
}
