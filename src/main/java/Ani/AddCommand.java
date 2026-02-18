package ani;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Executes commands that are added to the taskList.
 * Includes todo, deadline and event tasks.
 */
public class AddCommand extends Command {
    private String taskName;
    private String input;

    /**
     * Creates AddCommand with the task description and task type.
     *
     * @param taskName Description of task.
     * @param input    Type of task.
     */
    public AddCommand(String taskName, String input) {
        this.taskName = taskName;
        this.input = input;
    }


    /**
     * Executes command in user input based on extracted of task type.
     *
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @return String output after implementation of task
     * @throws IOException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-d");
        String result = "";

        switch (taskName) {
        case "todo":
            result = todoCommand(input, tasks, ui, storage);
            break;

        case "deadline":
            result = deadlineCommand(input, tasks, ui, storage);
            break;

        case "event":
            result = eventCommand(input, tasks, ui, storage);
            break;
        }
        return result;
    }

    /**
     * Implements todo type tasks.
     *
     * @param input   Input entered by user.
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @return String output after implementation of todo task.
     */
    public String todoCommand(String input, TaskList tasks, Ui ui, Storage storage) {
        String result = "";
        String taskTodo = input.trim().split(" ", 2)[1];
        if (taskTodo.isEmpty()) {
            throw new AniException("\nPlease state a task\n");
        }
        Todo t = new Todo(Task.count, taskTodo, false);
        tasks.addTask(t);
        Task.countIncrease();
        result += ui.showLine();
        result += "\nGot it. I've added this task:\n"
                + t + "\nNow you have " + Task.count + " tasks in the list.\n";
        result += ui.showLine();
        storage.store(tasks);
        return result;

    }

    /**
     * Implements deadline type tasks.
     *
     * @param input   Input entered by user.
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @return String output after implementation of deadline task.
     */
    public String deadlineCommand(String input, TaskList tasks, Ui ui, Storage storage) {
        String result = "";
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-d");
            String[] parts = input.split("/");
            String date = parts[1].trim().split(" ", 2)[1];
            LocalDate d1 = LocalDate.parse(date, dateFormat);
            String taskName = parts[0].trim().split(" ", 2)[1];

            String finalDate = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            Deadlines d = new Deadlines(Task.count, taskName, false, finalDate);
            tasks.addTask(d);
            Task.countIncrease();
            result += ui.showLine();
            result += "\nGot it. I've added this task:\n"
                    + d + "\nNow you have " + Task.count + " tasks in the list.\n";
            result += ui.showLine();
            storage.store(tasks);
            return result;

        } catch (DateTimeParseException e) {
            throw new AniException("\nPlease input date in valid format YYYY-MM-DD\n");
        }

    }


    /**
     * Implements event type tasks.
     *
     * @param input   Input entered by user.
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @return String output after impleemntation of event task.
     */
    public String eventCommand(String input, TaskList tasks, Ui ui, Storage storage) {
        String result = "";
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-d");
            String[] eventParts = input.split("/");
            String start = eventParts[1].trim().split(" ", 2)[1];
            String end = eventParts[2].trim().split(" ", 2)[1];
            String event_task = eventParts[0].trim().split(" ", 2)[1];

            LocalDate startDate = LocalDate.parse(start, dateFormat);
            LocalDate endDate = LocalDate.parse(end, dateFormat);
            String finalStart = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String finalEnd = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            Event e = new Event(Task.count, event_task, false, finalStart, finalEnd);
            tasks.addTask(e);
            Task.countIncrease();
            result += ui.showLine();
            result += "\nGot it. I've added this task:\n"
                    + e + "\nNow you have " + Task.count + " tasks in the list.\n";
            result += ui.showLine();
            storage.store(tasks);
            return result;
        } catch (DateTimeParseException e) {
            throw new AniException("\nPlease input date in valid format YYYY-MM-DD\n");
        }
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
