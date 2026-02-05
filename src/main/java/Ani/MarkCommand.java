package ani;

import java.io.IOException;

/**
 * MarkCommand class that executes the marking of tasks.
 */
public class MarkCommand extends Command {
    private int num;

    /**
     * MarkCommand constructor that takes in the task number to be marked.
     *
     * @param num Task number to be marked.
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the marking of the task and updates it in taskList.
     *
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (num > Task.count) {
            throw new AniException("Please provide a valid task number");
        }

        tasks.getTask(num - 1).changeToMark();
        ui.showLine();
        System.out.println("Nice! I have marked this task as done:\n" +
                tasks.getTask(num - 1).toString());
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
