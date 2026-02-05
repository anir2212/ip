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
     * Executes all tasks that need to be added into storage.
     *
     * @param tasks   Tasks in the taskList.
     * @param ui      UI.
     * @param storage Storage object to store the new task.
     * @throws IOException  Exception thrown if errors with reading and writing file.
     * @throws AniException Exception thrown if errors in todo, deadline and event.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-d");
        switch (taskName) {
        case "todo":
            String taskTodo = input.trim().split(" ", 2)[1];
            if (taskTodo.isEmpty()) {
                throw new AniException("Please state a task");
            }
            Todo t = new Todo(Task.count, taskTodo, false);
            tasks.addTask(t);
            Task.countIncrease();
            ui.showLine();
            System.out.println("Got it. I've added this task:\n"
                    + t + "\nNow you have " + Task.count + " tasks in the list.\n");
            ui.showLine();
            storage.store(tasks);
            break;

        case "deadline":
            try {
                String[] parts = input.split("/");
                String date = parts[1].trim().split(" ", 2)[1];
                LocalDate d1 = LocalDate.parse(date, dateFormat);
                String taskName = parts[0].trim().split(" ", 2)[1];


                String finalDate = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                Deadlines d = new Deadlines(Task.count, taskName, false, finalDate);
                tasks.addTask(d);
                Task.countIncrease();
                ui.showLine();
                System.out.println("Got it. I've added this task:\n"
                        + d + "\nNow you have " + Task.count + " tasks in the list.\n");
                ui.showLine();
                storage.store(tasks);
                break;

            } catch (DateTimeParseException e) {
                throw new AniException("Please input date in valid format YYYY-MM-DD");
            }

        case "event":
            try {
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
                ui.showLine();
                System.out.println("Got it. I've added this task:\n"
                        + e + "\nNow you have " + Task.count + " tasks in the list.\n");
                ui.showLine();
                storage.store(tasks);
                break;
            } catch (DateTimeParseException e) {
                throw new AniException("Please input date in valid format YYYY-MM-DD");
            }
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
