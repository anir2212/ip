
import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Ani {
    public static void main(String[] args) throws IOException {

        String logo = "    ___     ___   ___  _______\n"
                + "   /   \\   |   \\  | | |__  __|\n"
                + "  / /_\\ \\  | |\\ \\ | |   |  |\n"
                + " / ----- \\ | | \\ \\| | __|  |__\n"
                + "/__/   \\__\\| |  \\___| |______|";



        System.out.println("Hello from\n" + logo);



        String intro = "___________________________________\n" +
                "Hello! I'm Ani\n"
                + "What can I do for you?\n" + "________________________________";

        String exit = "___________________________________\n" +
                "Bye. Hope to see you again soon!\n" + "___________________________________";

        System.out.println(intro);
        //Task[] lst = new Task[100];




        Scanner s = new Scanner(System.in);



        File file = new File("./data/Ani.txt");
        file.getParentFile().mkdirs();


        //ArrayList<String> lines = new ArrayList<>();
        Scanner s_read = new Scanner(file);


        ArrayList<Task> lst = new ArrayList<>();

        if (file.length() != 0) {
            while (s_read.hasNextLine()) {
                String line = s_read.nextLine();
                String[] words = line.split("\\|");
                String word = words[0].trim();
                switch(word) {
                    case "T" :


                        Todo a = new Todo(Task.count, words[2].trim(), "1".equals(words[1].trim()));
                        Task.count++;
                        lst.add(a);

                        break;

                    case "D" :

                        Deadlines d = new Deadlines(Task.count, words[2].trim(), "1".equals(words[1].trim()),
                                                    words[3].trim());
                        Task.count++;
                        lst.add(d);
                        break;

                    case "E" :
                        int start_index = words[3].trim().indexOf(":") + 1;
                        int end_index = words[3].trim().indexOf("to");

                        String start_date = words[3].trim().substring(start_index, end_index).trim();
                        String end_date = words[3].trim().substring(end_index + "to: ".length());


                        Event e = new Event(Task.count, words[2].trim(), "1".equals(words[1].trim()), start_date, end_date);
                        lst.add(e);
                        Task.count++;
                        break;




                }


            }


        }

        String input = s.nextLine();
        String[] words = input.split(" ");


        while (!input.equals("bye")) {
            String firstWord = input.split(" ")[0];
            DateTimeFormatter d_format = DateTimeFormatter.ofPattern("yyyy-MM-d");
            switch (firstWord) {
                case "list":
                    System.out.println("____________________________\n" + "Here are the tasks in your list:");
                    for (int i = 1; i < lst.size() + 1; i++) {

                        System.out.println(i + ". " + lst.get(i - 1).toString());
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

                    lst.get(num - 1).change_to_mark();
                    System.out.println("_____________________\n" +
                            "Nice! I have marked this task as done:\n" +
                            lst.get(num - 1).toString() + "\n____________________________");



                    break;

                case "unmark" :
                    int second_num = Integer.parseInt(words[1]);
                    lst.get(second_num - 1).change_to_unmark();
                    System.out.println("_____________________\n" +
                            "OK, I've marked this task as not done yet:\n" +
                            lst.get(second_num - 1).toString() + "\n___________________________");

                    break;

                case "todo" :

                    try {
                        String task_todo = input.trim().split(" ", 2)[1];
                        if (task_todo.isEmpty()) {
                            throw new AniException("_____________________________\n" + "Please state a task\n" +
                                    "_______________________________" );
                        }
                        Todo t = new Todo(Task.count, task_todo, false);
                        lst.add(t);
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
                        lst.add(d);
                        Task.count_increase();
                        System.out.println("__________________________\n" + "Got it. I've added this task:\n"
                                + d + "\nNow you have " + Task.count + " tasks in the list.\n"
                                + "_________________________________");

                    } catch (DateTimeParseException e) {
                        System.out.println("_________________________________________________________\n"
                                            + "Please enter a valid date and follow yyyy-mm-dd format\n"
                                            + "_______________________________________________________");

                        input = s.nextLine();
                        words = input.split(" ");
                        continue;
                    }


                    //pw.println(d.toStringForFile());

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
                        lst.add(e);
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
                    int delete_num = Integer.parseInt(words[1]);
                    Task removed = lst.get(delete_num - 1);
                    lst.remove(delete_num - 1);
                    Task.count--;
                    System.out.println("_____________________________\n" + "Noted. I've removed this task:\n"
                                        + removed.toString() + "\nNow you have " + Task.count + " tasks in the list.\n"
                                        + "____________________________");

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

            try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
                for (Task task : lst) {
                    pw.println(task.toStringForFile());

                }
            } catch (IOException e) {
                System.out.println("Error saving tasks");
            }

            input = s.nextLine();
            words = input.split(" ");
        }

        /*
        for (Task t : lst) {
            pw.println(t.toStringForFile());  // write each task as one line
        }
        pw.close();

         */
        System.out.println(exit);
    }
}


