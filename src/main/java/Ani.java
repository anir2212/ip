
import java.util.Objects;
import java.util.Scanner;
public class Ani {
    public static void main(String[] args) {

        String logo = "    ___     ___   ___  _______ \n"
                +
                "   /   \\   |   \\  | | |__  __|\n"
                + "  / /_\\ \\  | |\\ \\ | |   |  |\n"
                + " / ----- \\ | | \\ \\| | __|  |__        \n"
                + "/__/   \\__\\| |  \\___| |______|           ";


        System.out.println("Hello from\n" + logo);
        String[] task_list = new String[100];


        String intro = "___________________________________\n" +
                "Hello! I'm Ani\n"
                + "What can I do for you?\n" + "________________________________\n";

        String exit = "___________________________________\n" +
                "Bye. Hope to see you again soon!\n" + "___________________________________";

        System.out.println(intro);
        Task[] lst = new Task[100];


        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        String[] words = input.split(" ");


        while (!input.equals("bye")) {
            switch (input) {
                case "list":
                    System.out.println("____________________________");
                    for (int i = 1; i < lst.length + 1; i++) {
                        if (lst[i - 1] == null) {
                            break;
                        }
                        System.out.println(i + ". " + lst[i - 1].toString());
                    }

                    System.out.println("_____________________________");
                    break;


                default:
                    if (input.startsWith("mark")) {

                        int num = Integer.parseInt(words[1]);

                        lst[num - 1].change_to_mark();
                        System.out.println("_____________________\n" +
                                "Nice! I have marked this task as done:\n" +
                                lst[num - 1].toString() + "\n____________________________");


                    } else if (input.startsWith("unmark")) {
                        int num = Integer.parseInt(words[1]);
                        lst[num - 1].change_to_unmark();
                        System.out.println("_____________________\n" +
                                "OK, I've marked this task as not done yet:\n" +
                                lst[num - 1].toString() + "\n___________________________");

                    } else {

                        System.out.println("_______________________\n" + "added: " + input + "\n__________________________\n");
                        Task a = new Task(Task.count, input, false);
                        lst[Task.count] = a;
                        Task.count_increase();

                    }
            }
            input = s.nextLine();
            words = input.split(" ");
        }
        System.out.println(exit);
    }
}


