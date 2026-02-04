package ani;

/**
 * ExitCommand class to execute an exit task.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit line from the UI and terminates.
     *
     * @param tasks Tasks in taskList.
     * @param ui UI.
     * @param storage Storage in Storage class for tasks present.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * Signals whether the command is one that exits.
     *
     * @return Boolean to exit.
     */
    public boolean isExit() {
        return true;
    }
}
