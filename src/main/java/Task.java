public class Task {
    static int count = 0;
    private String task_name;
    public Task( String s) {
        task_name = s;

    }

    public String getTask_name() {
        return task_name;
    }

    public static void count_increase() {
        count++;
    }
}
