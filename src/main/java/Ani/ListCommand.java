package ani;

/**
 * ListCommand class that executes the output of the list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the printing of the list of tasks in taskList.
     *
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.len() + 1; i++) {

            System.out.println(i + ". " + tasks.getTask(i - 1).toString());
        }
        ui.showLine();
    }

    /**
     * Signals whether the command is one that exits.
     *
     * @return Boolean to not exit.
     */
    public boolean isExit() {
        return false;
    }

}
