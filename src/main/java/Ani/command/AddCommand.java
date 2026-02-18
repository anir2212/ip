package ani.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ani.exception.AniException;
import ani.task.Deadlines;
import ani.task.Event;
import ani.Storage;
import ani.task.Task;
import ani.TaskList;
import ani.task.Todo;
import ani.Ui;

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
        String taskTodoWithTag = input.trim().split(" ", 2)[1];
        String taskTodo = taskTodoWithTag.substring(0, taskTodoWithTag.indexOf('#')).trim();
        String tag = input.contains("#") ? input.substring(input.indexOf("#")).trim() : "";

        if (taskTodo.isEmpty()) {
            throw new AniException("\nPlease state a task\n");
        }
        Todo todoTask = new Todo(Task.getTaskCount(), taskTodo, tag, false);
        tasks.addTask(todoTask);
        Task.countIncrease();
        storage.store(tasks);
        return ui.createAddMessage(todoTask, Task.getTaskCount());
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
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-d");
            String[] parts = input.split("/");
            String date = parts[1].trim().split(" ", 2)[1];
            LocalDate d1 = LocalDate.parse(date, dateFormat);
            String taskDescriptionWithTag = parts[0].trim().split(" ", 2)[1];
            String taskDescription = taskDescriptionWithTag.substring(0, taskDescriptionWithTag.indexOf('#')).trim();
            String tag = taskDescriptionWithTag.substring(taskDescriptionWithTag.indexOf("#")).trim();
            String finalDate = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            Deadlines deadlineTask = new Deadlines(Task.getTaskCount(), taskDescription, tag, false, finalDate);
            tasks.addTask(deadlineTask);
            Task.countIncrease();
            storage.store(tasks);
            return ui.createAddMessage(deadlineTask, Task.getTaskCount());

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
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-d");
            String[] eventParts = input.split("/");
            String start = eventParts[1].trim().split(" ", 2)[1];
            String end = eventParts[2].trim().split(" ", 2)[1];
            String eventDescriptionWithTag = eventParts[0].trim().split(" ", 2)[1];
            String eventDescription = eventDescriptionWithTag.substring(0, eventDescriptionWithTag.indexOf('#')).trim();
            String tag = eventDescriptionWithTag.substring(eventDescriptionWithTag.indexOf("#")).trim();
            LocalDate startDate = LocalDate.parse(start, dateFormat);
            LocalDate endDate = LocalDate.parse(end, dateFormat);
            String finalStart = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String finalEnd = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            Event eventTask = new Event(Task.getTaskCount(), eventDescription, tag, false, finalStart, finalEnd);
            tasks.addTask(eventTask);
            Task.countIncrease();
            storage.store(tasks);
            return ui.createAddMessage(eventTask, Task.getTaskCount());

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
