package ani;


import ani.task.Task;

/**
 * UI class for all UI related methods and outputs.
 */
public class Ui {

    /**
     * Shows welcome message.
     */
    public String showWelcome() {

        String intro = "\nHello! I'm Ani\nWhat can I do for you?";
        return "WELCOME TO ANI - THE PERFECT CHATBOT!\n"
                + showLine()
                + intro + "\n"
                + showLine();
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

    public String createAddMessage(Task task, int taskNumber) {
        String result = "";
        result += showLine();
        result += "\nGot it. I've added this task:\n"
                + task + "\nNow you have " + taskNumber + " tasks in the list.\n";
        result += showLine();
        return result;
    }
}
