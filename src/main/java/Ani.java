import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Ani {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ani(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());

        } catch (AniException | FileNotFoundException e) {
            System.out.println("No previous tasks listed");
            tasks = new TaskList();

        }

    }

    public void run() throws IOException {


        ui.showWelcome();
        //String input = ui.readCommand();
        Scanner s = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            try {


                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();


            } catch (AniException e) {
                ui.showError(e.getMessage());
            } finally {
                //ui.showLine();
            }
        }
    }







/*
        String input = s.nextLine();
        String[] words = input.split(" ");


        while (!input.equals("bye")) {

            String firstWord = input.split(" ")[0];
            DateTimeFormatter d_format = DateTimeFormatter.ofPattern("yyyy-MM-d");
            switch (firstWord) {
                case "list":
                    System.out.println("____________________________\n" + "Here are the tasks in your list:");
                    for (int i = 1; i < tasks.len() + 1; i++) {

                        System.out.println(i + ". " + tasks.getTask(i - 1).toString());
                    }


                    System.out.println("_____________________________");
                    break;

                case "mark" :
                    int num = Integer.parseInt(words[1]);

                    try {
                        if (num > Task.count) {
                            throw new AniException("_________________________________\n"
                                    + "Please provide a valid task number\n"
                                    +"______________________________");
                        }
                    } catch (AniException e) {
                        System.out.println(e.getMessage());
                        input = s.nextLine();
                        words = input.split(" ");
                        continue;
                    }

                    tasks.getTask(num - 1).change_to_mark();
                    System.out.println("_____________________\n" +
                            "Nice! I have marked this task as done:\n" +
                            tasks.getTask(num - 1).toString() + "\n____________________________");



                    break;

                case "unmark" :
                    int second_num = Integer.parseInt(words[1]);
                    tasks.getTask(second_num - 1).change_to_unmark();
                    System.out.println("_____________________\n" +
                            "OK, I've marked this task as not done yet:\n" +
                            tasks.getTask(second_num - 1).toString() + "\n___________________________");

                    break;

                case "todo" :

                    try {
                        String task_todo = input.trim().split(" ", 2)[1];
                        if (task_todo.isEmpty()) {
                            throw new AniException("_____________________________\n" + "Please state a task\n" +
                                    "_______________________________" );
                        }
                        Todo t = new Todo(Task.count, task_todo, false);
                        tasks.addTask(t);
                        Task.count_increase();
                        System.out.println("__________________________\n" + "Got it. I've added this task:\n"
                                + t + "\nNow you have " + Task.count + " tasks in the list.\n"
                                + "_________________________________");
                        //pw.println(t.toStringForFile());

                        break;
                    } catch (ArrayIndexOutOfBoundsException b) {
                        System.out.println("________________________________\n" + "Please state a task\n"
                                + "____________________________________");
                        input = s.nextLine();
                        words = input.split(" ");
                        continue;
                    } catch (AniException c) {
                        System.out.println(c.getMessage());
                        input = s.nextLine();
                        words = input.split(" ");
                        continue;
                    }


                case "deadline" :
                    String[] parts = input.split("/");
                    String date = parts[1].trim().split(" ", 2)[1];

                    try {
                        LocalDate d1 = LocalDate.parse(date, d_format);
                        String task_name = parts[0].trim().split(" ", 2)[1];


                        String final_date = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                        Deadlines d = new Deadlines(Task.count, task_name, false, final_date);
                        tasks.addTask(d);
                        Task.count_increase();
                        System.out.println("__________________________\n" + "Got it. I've added this task:\n"
                                + d + "\nNow you have " + Task.count + " tasks in the list.\n"
                                + "_________________________________");

                    } catch (DateTimeParseException e) {
                        System.out.println("_________________________________________________________\n"
                                + "Please enter a valid date and follow yyyy-mm-dd format. Thank you.\n"
                                + "_______________________________________________________");

                        input = s.nextLine();
                        words = input.split(" ");
                        continue;
                    }

                    break;

                case "event" :
                    String[] event_parts = input.split("/");
                    String start = event_parts[1].trim().split(" ", 2)[1];
                    String end = event_parts[2].trim().split(" ", 2)[1];
                    String event_task = event_parts[0].trim().split(" ", 2)[1];
                    try {
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
                        //pw.println(e.toStringForFile());

                    } catch (DateTimeParseException e) {
                        System.out.println("_________________________________________________________\n"
                                + "Please enter a valid date and follow yyyy-mm-dd format\n"
                                + "_______________________________________________________");

                        input = s.nextLine();
                        words = input.split(" ");
                        continue;

                    }
                    break;

                case "delete" :
                    try {
                        int delete_num = Integer.parseInt(words[1]);
                        if (delete_num > Task.count) {
                            throw new AniException("___________________________________\n"
                                    + "Please enter a valid task number\n"
                                    + "_________________________________");

                        }


                        Task removed = tasks.getTask(delete_num - 1);
                        tasks.removeTask(delete_num - 1);
                        Task.count--;
                        System.out.println("_____________________________\n" + "Noted. I've removed this task:\n"
                                + removed.toString() + "\nNow you have " + Task.count + " tasks in the list.\n"
                                + "____________________________");


                    } catch (AniException e) {
                        System.out.println(e.getMessage());
                        input = s.nextLine();
                        words = input.split(" ");
                        continue;

                    }

                    break;

                default:

                    try {
                        throw new AniException("________________________________\n"
                                + "Sorry, I don't understand what that means\n"
                                + "_____________________________");
                    }
                    catch (AniException a){
                        System.out.println(a.getMessage());


                    }

            }

            storage.store(tasks);



            input = s.nextLine();
            words = input.split(" ");
        }

        //ui.showExit();
    }



*/

    public static void main(String[] args) throws IOException {
        new Ani("./data/Ani.txt").run();


    }

}


