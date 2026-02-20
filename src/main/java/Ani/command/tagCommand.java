package ani.command;

import java.io.IOException;

import ani.Storage;
import ani.TaskList;
import ani.Ui;
import ani.exception.AniException;
import ani.task.Task;

public class TagCommand extends Command {

    private int taskNumber;
    private String tag;

    /**
     * TagCommand constructor that takes in a task number and the tag input to tag task.
     *
     * @param taskNumber
     * @param tag
     */
    public TagCommand(int taskNumber, String tag) {
        this.taskNumber = taskNumber;
        this.tag = tag;
    }

    /**
     * Executes the tagging of task and storing it as well.
     *
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @return String output after successful tagging.
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (taskNumber > Task.getTaskCount() || taskNumber <= 0) {
            throw new AniException("\nPlease provide a valid task number\n");
        }

        tasks.getTask(taskNumber - 1).changeTag(tag);

        StringBuilder output = new StringBuilder();
        output.append(ui.showLine()).append("\n");
        output.append("Nice! I have tagged this task as:\n")
                .append(tasks.getTask(taskNumber - 1).toString())
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
