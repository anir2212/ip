
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


        String intro = "___________________________________\n" +
                "Hello! I'm Ani\n"
                + "What can I do for you?\n" + "________________________________\n";

        String exit = "___________________________________\n" +
                "Bye. Hope to see you again soon!\n" + "___________________________________";

        System.out.println(intro);

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (!input.equals("bye")) {
            System.out.println("_______________________\n" + input + "\n__________________________\n");
            input = s.nextLine();
        }

        System.out.println(exit);









    }
}
