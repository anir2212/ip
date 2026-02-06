package ani;


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

        showLine();
        String intro = "Hello! I'm Ani\n"
                + "What can I do for you?";
        System.out.println(intro);
        showLine();

    }


    /**
     * Shows error message.
     *
     * @param message Error message.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Shows exit message.
     */
    public String showExit() {
        return showLine() + "\nBye. Hope to see you again soon!\n" + showLine();
    }

    /**
     * Shows line
     */
    public String showLine() {
        return "_____________________________________";
    }
}
