package gitlet.command;

import gitlet.Config;
import gitlet.dataStructure.LooseObject;
import gitlet.dataStructure.ObjectFactory;
import gitlet.dataStructure.ObjectType;
import gitlet.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * A concrete class of Command.
 * functionality: initiate a new gitlet repository.
 *
 * @author jason
 * TODO
 */
public class initCommand implements Command{
    private final String defaultBranch = "master";
    @Override
    public void setParameter(String[] args) {}

    @Override
    public void execute() throws IOException {
        // create .gitlet folder
        if (!Config.GITLET_DIR.mkdir()) {
            System.err.println("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }

        // create default file or folder in .gitlet directory
        Config.HEAD_FILE.createNewFile();
        Config.REFS_DIR.mkdir();
        Config.HEADS_DIR.mkdir();
        Config.OBJ_DIR.mkdir();
        Config.INDEX_FILE.createNewFile();

        // create init file
        File branch = Utils.join(Config.HEADS_DIR, defaultBranch);
        branch.createNewFile();

        // write HEAD file
        Utils.writeContents(Config.HEAD_FILE, Config.REFS_DIR.getPath());


        // create initial commit and write to file
        Date date = new Date();
        date.setTime(0);
        String message = "initial commit";
        String treeHash = "0".repeat(Utils.UID_LENGTH);
        String parentHash = "0".repeat(Utils.UID_LENGTH);

        String[] args = {date.toString(), message, treeHash, parentHash};
        LooseObject initCommit = ObjectFactory.create(ObjectType.COMMIT, args);
        initCommit.writeLooseObject();

        // update default branch's content
        Utils.writeContents(branch, initCommit.getHash());

        /*
        do log things
        TODO
         */
    }
}
