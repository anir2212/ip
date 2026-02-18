package ani.task;

/**
 * Deadlines class to input the necessary details for deadlines and the toString methods.
 */
public class Deadlines extends Task {
    private String date;

    /**
     * Creates Deadlines object with the respective details.
     *
     * @param num      Task number.
     * @param taskName Description of task.
     * @param isMark   Boolean of whether task is marked or not.
     * @param date     Deadline date.
     */
    public Deadlines(int num, String taskName, String tag, boolean isMark, String date) {
        super(num, taskName, tag, isMark);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    /**
     * Implements the string representation of a task to be stored in the text file.
     *
     * @return String implementation of tasks to be stored in text file.
     */
    public String toStringForFile() {
        return "D " + super.toStringForFile() + " | " + date;
    }
}
