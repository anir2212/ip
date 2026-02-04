package ani;

import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        String logo = "    ___     ___   ___  _______\n"
                + "   /   \\   |   \\  | | |__  __|\n"
                + "  / /_\\ \\  | |\\ \\ | |   |  |\n"
                + " / ----- \\ | | \\ \\| | __|  |__\n"
                + "/__/   \\__\\| |  \\___| |______|";



        System.out.println("Hello from\n" + logo);



        String intro = "___________________________________\n" +
                "Hello! I'm Ani\n"
                + "What can I do for you?\n" + "________________________________";

        System.out.println(intro);

    }

    public String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }


    public void showExit() {
        System.out.println("___________________________________\n" +
                "Bye. Hope to see you again soon!\n" + "___________________________________");

    }




}
