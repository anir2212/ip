package ani;

import java.io.IOException;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        if (deleteNum > Task.count) {
            throw new AniException("Please enter a valid task number");
        }

        Task removed = tasks.getTask(deleteNum - 1);
        tasks.removeTask(deleteNum - 1);
        Task.count--;
        ui.showLine();
        System.out.println("Noted. I've removed this task:\n" + removed.toString()
                + "\nNow you have " + Task.count + " tasks in the list.\n");
        ui.showLine();
        storage.store(tasks);

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
