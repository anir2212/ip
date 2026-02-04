package ani;
import java.io.IOException;

/**
 * UnmarkCommand class to execute unmarking of task.
 */
public class UnmarkCommand extends Command{
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
     * @param tasks Tasks in taskList.
     * @param ui UI.
     * @param storage Storage in Storage class for tasks present.
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.getTask(num - 1).changeToUnmark();
        System.out.println("_____________________\n" +
                "OK, I've marked this task as not done yet:\n" +
                tasks.getTask(num - 1).toString() + "\n___________________________");
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
