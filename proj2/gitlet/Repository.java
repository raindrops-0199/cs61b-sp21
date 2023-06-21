package gitlet;

import gitlet.command.Command;

import java.io.File;
import static gitlet.utils.Utils.*;

/**
 * Repository is the invoker class of specific command.
 *
 * @author jason
 * TODO
 */
public class Repository {
    /** the command to execute */
    Command command;

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");


    public void setCommand(Command command, String[] args) {
        this.command = command;
        this.command.setParameter(args);
    }

    public void invoke() {
        command.execute();
    }
}
