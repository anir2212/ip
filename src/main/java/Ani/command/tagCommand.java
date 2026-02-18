package ani.command;

import java.io.IOException;

import ani.Storage;
import ani.TaskList;
import ani.Ui;
import ani.exception.AniException;
import ani.task.Task;

public class tagCommand extends Command{

    private int taskNumber;
    private String tag;
    public tagCommand(int taskNumber, String tag) {
        this.taskNumber = taskNumber;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (taskNumber > Task.getTaskCount()) {
            throw new AniException("Please provide a valid task number");
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
