package ani;

import java.io.IOException;

/**
 * Command class for all types of commands to inherit from.
 */
abstract public class Command {

    /**
     * Executes the respective command's tasks.
     *
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @throws IOException Exception thrown due to file I/O.
     */
    abstract public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Signals whether the command is one that exits.
     *
     * @return Boolean to exit or not.
     */
    abstract public boolean isExit();


}
