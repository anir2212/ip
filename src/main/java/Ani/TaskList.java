package ani;

import java.util.ArrayList;

import ani.task.Task;

/**
 * TaskList class to store tasks.
 */
public class TaskList {

    private ArrayList<Task> arr;

    /**
     * TaskList constructor that takes in an ArrayList of tasks from Storage.
     *
     * @param arr ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * TaskList constructor to instantiate an empty TaskList.
     */
    public TaskList() {
        this(new ArrayList<Task>());
    }

    /**
     * Gets number of tasks in taskList.
     *
     * @return Size of array.
     */
    public int len() {
        return this.arr.size();
    }

    public Task getTask(int num) {
        return this.arr.get(num);
    }

    /**
     * Adds task to taskList.
     *
     * @param task
     */
    public void addTask(Task task) {
        this.arr.add(task);
    }

    /**
     * Removes task from taskList.
     *
     * @param num Task number.
     */
    public void removeTask(int num) {
        this.arr.remove(num);
    }
}



