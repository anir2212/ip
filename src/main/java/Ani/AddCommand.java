package Ani;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command{
    private String taskName;
    private String input;
    public AddCommand(String taskName, String input) {
        this.taskName = taskName;
        this.input = input;

    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        DateTimeFormatter d_format = DateTimeFormatter.ofPattern("yyyy-MM-d");
        switch(taskName) {
            case "todo":


                String task_todo = input.trim().split(" ", 2)[1];
                if (task_todo.isEmpty()) {
                    throw new AniException("_____________________________\n" + "Please state a task\n" +
                            "_______________________________");
                }
                Todo t = new Todo(Task.count, task_todo, false);
                tasks.addTask(t);
                Task.count_increase();
                System.out.println("__________________________\n" + "Got it. I've added this task:\n"
                        + t + "\nNow you have " + Task.count + " tasks in the list.\n"
                        + "_________________________________");


                break;


            case "deadline":
                try {


                    String[] parts = input.split("/");
                    String date = parts[1].trim().split(" ", 2)[1];


                    LocalDate d1 = LocalDate.parse(date, d_format);
                    String task_name = parts[0].trim().split(" ", 2)[1];


                    String final_date = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                    Deadlines d = new Deadlines(Task.count, task_name, false, final_date);
                    tasks.addTask(d);
                    Task.count_increase();
                    System.out.println("__________________________\n" + "Got it. I've added this task:\n"
                            + d + "\nNow you have " + Task.count + " tasks in the list.\n"
                            + "_________________________________");

                    break;
                } catch (DateTimeParseException e) {
                    throw new AniException("_____________________________\n"
                            + "Please input date in valid format YYYY-MM-DD\n"
                            + "__________________________________");
                }


            case "event" :
                try {

                    String[] event_parts = input.split("/");
                    String start = event_parts[1].trim().split(" ", 2)[1];
                    String end = event_parts[2].trim().split(" ", 2)[1];
                    String event_task = event_parts[0].trim().split(" ", 2)[1];

                    LocalDate start_date = LocalDate.parse(start, d_format);
                    LocalDate end_date = LocalDate.parse(end, d_format);
                    String final_start = start_date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    String final_end = end_date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                    Event e = new Event(Task.count, event_task, false, final_start, final_end);
                    tasks.addTask(e);
                    Task.count_increase();
                    System.out.println("__________________________\n" + "Got it. I've added this task:\n"
                            + e + "\nNow you have " + Task.count + " tasks in the list.\n"
                            + "_________________________________");


                    break;
                } catch (DateTimeParseException e) {
                    throw new AniException("_____________________________\n"
                                            + "Please input date in valid format YYYY-MM-DD\n"
                                            + "__________________________________");
                }

        }

    }

    public boolean isExit() {
        return false;
    }
}
