package gitlet.command;

/**
 * Use of Command Pattern.
 * <p>
 * Every concrete command must implements Command interface, which
 * regulates the interface of set parameters and execute the command.
 *
 * @author jason
 */
public interface Command {
    /** set parameters of the command */
    void setParameter(String[] args);

    /** execute command */
    void execute();
}
