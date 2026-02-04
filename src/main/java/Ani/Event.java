package ani;

/**
 * Event class to instantiate an event task with the necessary details.
 */
public class Event extends Task{
    private String start;
    private String end;

    /**
     * Event constructor that takes in the necessary details for an event task
     *
     * @param num Task number.
     * @param taskName Description of task.
     * @param isMark Boolean of whether task is marked or not.
     * @param start Start date of event.
     * @param end End date of event.
     */
    Event(int num, String taskName, boolean isMark, String start, String end) {
        super(num, taskName, isMark);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Implements the string representation of a task to be stored in the text file.
     *
     * @return String implementation of tasks to be stored in text file.
     */
    public String toStringForFile() {
        return "E " + super.toStringForFile() + " | from: " + start + " to: " + end;
    }
}
