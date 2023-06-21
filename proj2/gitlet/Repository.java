package gitlet;

import gitlet.command.Command;

import java.io.File;
import java.io.IOException;

import static gitlet.utils.Utils.*;

/**
 * Repository is the invoker class of specific command.
 *
 * @author jason
 * TODO
 */
public class Repository {
    /** the command to execute */
    private static Command command;

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");


    public static void setCommand(Command command, String[] args) {
        Repository.command = command;
        Repository.command.setParameter(args);
    }

    public static void invoke() throws IOException {
        command.execute();
    }
}
