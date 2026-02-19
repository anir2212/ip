package ani.command;

import java.io.IOException;

import ani.exception.AniException;
import ani.Storage;
import ani.task.Task;
import ani.TaskList;
import ani.Ui;

/**
 * MarkCommand class that executes the marking of tasks.
 */
public class MarkCommand extends Command {
    private int taskNum;

    /**
     * MarkCommand constructor that takes in the task number to be marked.
     *
     * @param taskNum Task number to be marked.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the marking of the task and updates it in taskList.
     *
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (taskNum > Task.getTaskCount() || taskNum <= 0) {
            throw new AniException("\nPlease provide a valid task number\n");
        }

        Task task = tasks.getTask(taskNum - 1);
        assert task != null : "Task to be marked cannot be null";
        task.changeToMark();

        StringBuilder output = new StringBuilder();
        output.append(ui.showLine()).append("\n");
        output.append("Nice! I have marked this task as done:\n")
                .append(tasks.getTask(taskNum - 1).toString())
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
    @Override
    public boolean isExit() {
        return false;
    }


}
