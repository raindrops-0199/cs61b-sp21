package gitlet;

import gitlet.command.Command;

import java.io.IOException;

/**
 * Repository is the invoker class of specific command.
 *
 * @author jason
 * TODO
 */
public class Repository {
    /** the command to execute */
    private static Command command;

    public static void setCommand(Command command, String[] args) {
        Repository.command = command;
        Repository.command.setParameter(args);
    }

    public static void invoke() throws IOException {
        command.execute();
    }
}
