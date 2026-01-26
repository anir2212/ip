
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


        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

         */
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
        while (!input.equals("bye")) {
            if (input.equals("list")) {

                System.out.println("____________________________");
                for (int i = 1; i < lst.length + 1; i++) {
                    if (lst[i - 1] == null) {
                        break;
                    }
                    System.out.println(i + ". " + lst[i - 1].getTask_name());
                }

                System.out.println("_____________________________");
            }

            else {
                System.out.println("_______________________\n" + "added: " + input + "\n__________________________\n");
                Task a = new Task(input);
                lst[Task.count] = a;
                Task.count_increase();
            }

            input = s.nextLine();

        }

        System.out.println(exit);

    }
}
