package ani.command;

import java.io.IOException;

import ani.exception.AniException;
import ani.Storage;
import ani.task.Task;
import ani.TaskList;
import ani.Ui;

/**
 * DeleteCommand class for a delete command to be executed.
 */
public class DeleteCommand extends Command {
    private int deleteNum;

    /**
     * DeleteCommand constructor that takes in task to be deleted through task number.
     *
     * @param deleteNum Task number to be deleted.
     */
    public DeleteCommand(int deleteNum) {
        this.deleteNum = deleteNum;
    }

    /**
     * Executes the deletion of task from taskList and storage.
     *
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @throws IOException
     * @throws AniException Exception thrown for invalid task number.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        /** Used AI to identify edge case of deleteNum <= 0 not being handled yet */
        if (deleteNum > Task.getTaskCount() || deleteNum <= 0) {
            throw new AniException("\nPlease enter a valid task number\n");
        }

        Task removed = tasks.getTask(deleteNum - 1);
        assert removed != null: "Task removed cannot be null";

        tasks.removeTask(deleteNum - 1);
        Task.countDecrease();

        StringBuilder output = new StringBuilder();
        output.append(ui.showLine()).append("\n");
        output.append("Noted. I've removed this task:\n")
                .append(removed).append("\n")
                .append("Now you have ").append(Task.getTaskCount())
                .append(" tasks in the list.\n");
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
