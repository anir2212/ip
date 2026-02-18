package ani;

/**
 * Task class to instantiate a task.
 */
public class Task {
    static int count = 0;
    private String taskName;
    private int num;
    private boolean isMark;

    /**
     * Task constructor that takes in necessary task details.
     *
     * @param num      Task number.
     * @param taskName Description of task.
     * @param isMark   Whether task is marked or not.
     */
    public Task(int num, String taskName, boolean isMark) {
        this.taskName = taskName;
        this.num = num;
        this.isMark = isMark;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Increases overall task count.
     */
    public static void countIncrease() {
        count++;
    }

    /**
     * Decreases overall task count.
     */
    public static void countDecrease() {
        count--;
    }

    public static int getTaskCount() {
        return count;
    }

    /**
     * Changes task to be marked if not marked yet.
     */
    public void changeToMark() {
        isMark = true;
    }

    /**
     * Changes task to unmarked if marked.
     */
    public void changeToUnmark() {
        isMark = false;
    }

    @Override
    public String toString() {
        if (!isMark) {
            return "[ ] " + taskName;
        } else {
            return "[X] " + taskName;
        }
    }

    /**
     * Implements string representation of tasks in the storage text file.
     *
     * @return String representation for text file storage.
     */
    public String toStringForFile() {
        if (!isMark) {
            return "| 0 | " + taskName;
        } else {
            return "| 1 | " + taskName;
        }
    }
}
