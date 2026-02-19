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
        String taskTodoWithTag = input.trim().split(" ", 2)[1].trim();
        String taskTodo = tagAndTaskExtract(taskTodoWithTag)[1];
        String tag = tagAndTaskExtract(taskTodoWithTag)[0];
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
            if (!input.contains("/by")) {
                throw new AniException("\nFormat must be:\n"
                        + "deadline task description /by YYYY-MM-DD\n");
            }
            String[] parts = input.split("/");
            String taskDescriptionWithTag = parts[0].trim().split(" ", 2)[1];
            String taskDescription = tagAndTaskExtract(taskDescriptionWithTag)[1];
            String tag = tagAndTaskExtract(taskDescriptionWithTag)[0];
            String finalDate = deadlineDateParse(parts);
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
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new AniException("\nFormat must be:\n"
                        + "event description /from YYYY-MM-DD /to YYYY-MM-DD\n");
            }

            String[] eventParts = input.split("/");
            String eventDescriptionWithTag = eventParts[0].trim().split(" ", 2)[1];
            String eventDescription = tagAndTaskExtract(eventDescriptionWithTag)[1];
            String tag = tagAndTaskExtract(eventDescriptionWithTag)[0];
            String finalStart = eventDateParse(eventParts)[0];
            String finalEnd = eventDateParse(eventParts)[1];
            Event eventTask = new Event(Task.getTaskCount(), eventDescription,
                    tag, false, finalStart, finalEnd);
            tasks.addTask(eventTask);
            Task.countIncrease();
            storage.store(tasks);
            return ui.createAddMessage(eventTask, Task.getTaskCount());

        } catch (DateTimeParseException e) {
            throw new AniException("\nPlease input date in valid format YYYY-MM-DD\n");
        }
    }

    /**
     * Extracts the tag and task String from a combined String input.
     *
     * @param taskWithTag User input of task along with tag.
     * @return String array output of [task, tag].
     */
    private String[] tagAndTaskExtract(String taskWithTag) {
        /** Used AI for additional exception handling for tag usage */
        int hashIndex = taskWithTag.indexOf("#");
        String taskTodo;
        String tag = " ";
        if (hashIndex == -1) {
            taskTodo = taskWithTag;
        } else {
            taskTodo = taskWithTag.substring(0, hashIndex).trim();
            tag = taskWithTag.substring(hashIndex).trim();

            if (tag.isEmpty()) {
                throw new AniException("\nTag cannot be empty after '#'\n");
            }
        }
        return new String[]{tag, taskTodo};
    }

    /**
     * Parses the date from event task type user input.
     *
     * @param eventParts User input split into an array.
     * @return String array of [eventStartDate, eventEndDate].
     */
    private String[] eventDateParse(String[] eventParts) {
        /** Used AI to extract parsing code from original eventCommand code for better code quality */
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-d");
        String start = eventParts[1].trim().split(" ", 2)[1];
        String end = eventParts[2].trim().split(" ", 2)[1];
        LocalDate startDate = LocalDate.parse(start, dateFormat);
        LocalDate endDate = LocalDate.parse(end, dateFormat);
        String finalStart = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String finalEnd = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return new String[]{finalStart, finalEnd};
    }

    /**
     * Parses the date for deadline task type.
     *
     * @param deadlineParts User input split into an array.
     * @return String output of deadline date.
     */
    private String deadlineDateParse(String[] deadlineParts) {
        /** Used AI to extract parsing of deadline date from deadlineCommand code to improve code quality */
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-d");
        String date = deadlineParts[1].trim().split(" ", 2)[1];
        LocalDate d1 = LocalDate.parse(date, dateFormat);
        return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
