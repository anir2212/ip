package ani.command;

import java.io.IOException;

import ani.Storage;
import ani.TaskList;
import ani.Ui;
import ani.exception.AniException;
import ani.task.Task;

/**
 * UnmarkCommand class to execute unmarking of task.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * UnmarkCommand constructor that takes in task number to be unmarked.
     *
     * @param taskNumber Task number.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the unmarking of task.
     *
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (taskNumber > Task.getTaskCount() || taskNumber <= 0) {
            throw new AniException("\nPlease provide a valid task number\n");
        }

        tasks.getTask(taskNumber - 1).changeToUnmark();

        StringBuilder output = new StringBuilder();
        output.append(ui.showLine()).append("\n");
        String taskUndone = "OK, I've marked this task as not done yet:\n";
        output.append(taskUndone)
                .append(tasks.getTask(taskNumber - 1).toString())
                .append("\n");
        output.append(ui.showLine());

        storage.store(tasks);
        return output.toString();
    }


    /**
     * Signals whether the command is the one that exits.
     *
     * @return Boolean to not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
