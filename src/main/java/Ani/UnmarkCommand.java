package ani;

import java.io.IOException;

/**
 * UnmarkCommand class to execute unmarking of task.
 */
public class UnmarkCommand extends Command {
    private int num;

    /**
     * UnmarkCommand constructor that takes in task number to be unmarked.
     *
     * @param num Task number.
     */
    public UnmarkCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the unmarking of task.
     *
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @throws IOException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.getTask(num - 1).changeToUnmark();

        StringBuilder output = new StringBuilder();
        output.append(ui.showLine()).append("\n");
        String taskUndone = "OK, I've marked this task as not done yet:\n";
        output.append(taskUndone)
                .append(tasks.getTask(num - 1).toString())
                .append("\n");
        output.append(ui.showLine());

        storage.store(tasks);
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
