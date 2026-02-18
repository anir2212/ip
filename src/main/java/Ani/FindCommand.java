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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder();
        int count = 1;

        output.append(ui.showLine()).append("\n");
        output.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.len(); i++) {
            String nameTask = tasks.getTask(i).getTaskName();
            if (!nameTask.contains(keyword)) {
                continue;
            }
            output.append(count)
                    .append(". ")
                    .append(tasks.getTask(i).toString())
                    .append("\n");
            count++;
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
