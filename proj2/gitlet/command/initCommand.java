package gitlet.command;

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
    private final String initPath = ".gitlet";
    @Override
    public void setParameter(String[] args) {}

    @Override
    public void execute() throws IOException {
        // create .gitlet folder
        File gitlet = Utils.join(initPath);
        if (!gitlet.createNewFile()) {
            System.err.println("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }

        /*
        create related files and folders
        TODO
         */

        // create initial commit and write to file
        Date date = new Date();
        date.setTime(0);
        String message = "initial commit";
        String treeHash = "0".repeat(Utils.UID_LENGTH);
        String parentHash = "0".repeat(Utils.UID_LENGTH);

        String[] args = {date.toString(), message, treeHash, parentHash};
        LooseObject initCommit = ObjectFactory.create(ObjectType.COMMIT, args);
        initCommit.writeLooseObject();

        /*
        do log things
        TODO
         */
    }
}
