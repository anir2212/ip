
import java.util.Objects;
import java.util.Scanner;
public class Ani {
    public static void main(String[] args) {

        String logo = "    ___     ___   ___  _______\n"
                + "   /   \\   |   \\  | | |__  __|\n"
                + "  / /_\\ \\  | |\\ \\ | |   |  |\n"
                + " / ----- \\ | | \\ \\| | __|  |__\n"
                + "/__/   \\__\\| |  \\___| |______|";



        System.out.println("Hello from\n" + logo);
        String[] task_list = new String[100];


        String intro = "___________________________________\n" +
                "Hello! I'm Ani\n"
                + "What can I do for you?\n" + "________________________________";

        String exit = "___________________________________\n" +
                "Bye. Hope to see you again soon!\n" + "___________________________________";

        System.out.println(intro);
        Task[] lst = new Task[100];


        Scanner s = new Scanner(System.in);


        String input = s.nextLine();
        String[] words = input.split(" ");


        while (!input.equals("bye")) {
            String firstWord = input.split(" ")[0];
            switch (firstWord) {
                case "list":
                    System.out.println("____________________________\n" + "Here are the tasks in your list:");
                    for (int i = 1; i < lst.length + 1; i++) {
                        if (lst[i - 1] == null) {
                            break;
                        }
                        System.out.println(i + ". " + lst[i - 1].toString());
                    }

                    System.out.println("_____________________________");
                    break;

                case "mark" :
                    int num = Integer.parseInt(words[1]);

                    lst[num - 1].change_to_mark();
                    System.out.println("_____________________\n" +
                            "Nice! I have marked this task as done:\n" +
                            lst[num - 1].toString() + "\n____________________________");
                    break;

                case "unmark" :
                    int second_num = Integer.parseInt(words[1]);
                    lst[second_num - 1].change_to_unmark();
                    System.out.println("_____________________\n" +
                            "OK, I've marked this task as not done yet:\n" +
                            lst[second_num - 1].toString() + "\n___________________________");
                    break;

                case "todo" :

                    String task_todo = input.trim().split(" ", 2)[1];
                    Todo t = new Todo(Task.count, task_todo, false);
                    lst[Task.count] = t;
                    Task.count_increase();
                    System.out.println("__________________________\n" + "Got it. I've added this task:\n"
                            + t + "\nNow you have " + Task.count + " tasks in the list.\n"
                            + "_________________________________");
                    break;
                case "deadline" :
                    String[] parts = input.split("/");
                    String date = parts[1].trim().split(" ", 2)[1];
                    String task_name = parts[0].trim().split(" ", 2)[1];
                    Deadlines d = new Deadlines(Task.count, task_name, false, date);
                    lst[Task.count] = d;
                    Task.count_increase();
                    System.out.println("__________________________\n" + "Got it. I've added this task:\n"
                            + d + "\nNow you have " + Task.count + " tasks in the list.\n"
                            + "_________________________________");
                    break;

                case "event" :
                    String[] event_parts = input.split("/");
                    String start = event_parts[1].trim().split(" ", 2)[1];
                    String end = event_parts[2].trim().split(" ", 2)[1];
                    String event_task = event_parts[0].trim().split(" ", 2)[1];
                    Event e = new Event(Task.count, event_task, false, start, end);
                    lst[Task.count] = e;
                    Task.count_increase();
                    System.out.println("__________________________\n" + "Got it. I've added this task:\n"
                            + e + "\nNow you have " + Task.count + " tasks in the list.\n"
                            + "_________________________________");
                    break;

                default:
                    System.out.println("_______________________\n" + "added: " + input + "\n__________________________\n");
                    Task a = new Task(Task.count, input, false);
                    lst[Task.count] = a;
                    Task.count_increase();

            }

            input = s.nextLine();
            words = input.split(" ");
        }
        System.out.println(exit);
    }
}


