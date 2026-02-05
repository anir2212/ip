package ani;

import java.util.Scanner;

/**
 * UI class for all UI related methods and outputs.
 */
public class Ui {

    /**
     * Shows welcome message.
     */
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

    /**
     * Reads user input.
     *
     * @return User input as String.
     */
    public String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    /**
     * Shows error message.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows exit message.
     */
    public void showExit() {
        System.out.println("___________________________________\n" +
                "Bye. Hope to see you again soon!\n" + "___________________________________");

    }
}
