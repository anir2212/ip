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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();

        output.append(ui.showLine()).append("\n");
        output.append("Here are the tasks in your list:\n");

        for (int i = 1; i <= tasks.len(); i++) {
            output.append(i)
                    .append(". ")
                    .append(tasks.getTask(i - 1).toString())
                    .append("\n");
        }

        output.append(ui.showLine());

        return output.toString();
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
