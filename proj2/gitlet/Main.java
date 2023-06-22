package gitlet;

import gitlet.command.Command;
import gitlet.command.addCommand;
import gitlet.command.initCommand;

import java.io.IOException;
import java.util.Arrays;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) throws IOException {
        // if args is empty
        if (args.length < 1) {
            System.err.println("Please enter a command.");
            System.exit(0);
        }

        String firstArg = args[0];
        Command command = null;
        String[] restArgs = Arrays.copyOfRange(args, 1, args.length);
        String[] commandArgs = new String[0];
        switch(firstArg) {
            case "init":
                command = new initCommand();
                break;
            case "add":
                command = new addCommand();
                commandArgs = new String[]{restArgs[0]};
                break;
        }
        Repository.setCommand(command, commandArgs);
        Repository.invoke();
    }
}
