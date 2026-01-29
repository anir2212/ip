import java.util.ArrayList;
public class TaskList {


    private ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    public TaskList() {

        this(new ArrayList<Task>());
    }

    public int len() {
        return this.arr.size();
    }

    public Task getTask(int num) {
        return this.arr.get(num);
    }

    public void addTask(Task task) {
        this.arr.add(task);
    }

    public void removeTask(int num) {
        this.arr.remove(num);
    }









}



