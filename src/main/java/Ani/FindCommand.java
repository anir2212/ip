package ani;

/**
 * FindCommand class to execute the finding of a task from the taskList.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * FindCommand constructor that takes in keyword from user input.
     *
     * @param keyword Keyword entered in by user to find from tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the finding of the tasks that match the keyword.
     *
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int count = 1;
        ui.showLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i < tasks.len() + 1; i++) {
            String nameTask = tasks.getTask(i - 1).getTaskName();
            String[] words = nameTask.split(" ");
            for (String word : words) {
                if (word.equals(keyword)) {
                    System.out.println(count + ". " + tasks.getTask(i - 1).toString());
                    count++;
                    break;
                }
            }
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
