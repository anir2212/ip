package ani.task;

/**
 * Todo class for todo type tasks.
 */
public class Todo extends Task {

    /**
     * Todo constructor that takes in the necessary details of a task.
     *
     * @param num      Task number.
     * @param taskName Description of task.
     * @param isMark   Boolean of whether task is marked or not.
     */
    public Todo(int num, String taskName, String tag, boolean isMark) {
        super(num, taskName, tag, isMark);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();

    }

    /**
     * Implements the string representation of a task to be stored in the text file.
     *
     * @return String implementation of tasks to be stored in text file.
     */
    public String toStringForFile() {
        return "T " + super.toStringForFile();
    }
}
