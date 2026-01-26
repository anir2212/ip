public class Task {
    static int count = 0;
    private String task_name;
    private int num;
    private boolean b;

    public Task(int num,  String s, boolean b) {
        task_name = s;
        this.num = num;
        this.b = b;

    }

    public String getTask_name() {
        return task_name;
    }

    public static void count_increase() {
        count++;
    }

    public void change_to_mark() {

        this.b = true;
    }

    public void change_to_unmark() {
        this.b = false;

    }

    public String toString() {
        if (!this.b) {
            return "[ ] " + task_name;


        } else {
            return "[X] " + task_name;

        }
    }
}
