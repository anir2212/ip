package ani;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import ani.task.Deadlines;
import ani.task.Event;
import ani.task.Task;
import ani.task.Todo;

/**
 * Storage class to store and update the tasks on the storage file.
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> arr;
    private File file;

    /**
     * Storage constructor that takes in the filepath to store
     *
     * @param filePath Filepath to storage file containing all the tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);

    }


    /**
     * Stores the tasks on TaskList into the storage text file.
     *
     * @param arr TaskList array.
     */
    public void store(TaskList arr) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (int i = 0; i < arr.len(); i++) {
                pw.println(arr.getTask(i).toStringForFile());

            }
        } catch (IOException e) {
            System.out.println("Error saving tasks");
        }


    }


    /**
     * Creates todo task to be loaded onto taskList.
     *
     * @param words Array of words on txt file to be read.
     * @return ArrayList of tasks to be loaded onto TaskList.
     */
    public Todo todoCreate(String[] words) {
        //Todo(int num, String taskName, String tag, boolean isMark)
        //words = [T, tag, isMark, taskName]
        String taskName = words[3].trim();
        boolean isMark = "1".equals(words[2].trim());
        String tag = words[1].trim();
        Todo a = new Todo(Task.getTaskCount(), taskName, tag, isMark);
        Task.countIncrease();
        return a;
    }

    /**
     * Creates deadline task to be loaded onto taskList.
     *
     * @param words Array of words on txt file to be read.
     * @return ArrayList of tasks to be loaded onto taskList.
     */
    public Deadlines deadlineCreate(String[] words) {
        //words = [T, tag, isMark, taskName, date]
        //Deadlines(int num, String taskName, String tag, boolean isMark, String date)
        String tag = words[1].trim();
        boolean isMark = "1".equals(words[2].trim());
        String date = words[4].trim();
        String taskName = words[3].trim();
        Deadlines deadline = new Deadlines(Task.getTaskCount(), taskName, tag, isMark, date);
        Task.countIncrease();
        return deadline;

    }

    /**
     * Creates event task to be loaded onto taskList.
     *
     * @param words Array of words on txt file to be read.
     * @return ArrayList of tasks to be loaded onto taskList.
     */
    public Event eventCreate(String[] words) {
        //Event(int num, String taskName, String tag, boolean isMark, String start, String end)
        //words = [E, tag, isMark, taskName,
        int startIndex = words[4].trim().indexOf(":") + 1;
        int endIndex = words[4].trim().indexOf("to");
        String startDate = words[4].trim().substring(startIndex, endIndex).trim();
        String endDate = words[4].trim().substring(endIndex + "to: ".length());
        String taskName = words[3].trim();
        String tag = words[1].trim();
        boolean isMark = "1".equals(words[2].trim());
        Event event = new Event(Task.getTaskCount(), taskName, tag, isMark, startDate, endDate);
        Task.countIncrease();
        return event;
    }


    /**
     * Loads the tasks on the text storage file back into taskList when the app is reopened.
     *
     * @return ArrayList of tasks.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException {

        file.getParentFile().mkdirs();
        Scanner sRead = new Scanner(file);
        ArrayList<Task> lst = new ArrayList<>();

        if (file.length() != 0) {
            while (sRead.hasNextLine()) {
                String line = sRead.nextLine();

                String[] words = line.split("\\|");

                String word = words[0].trim();
                switch (word) {
                case "T":
                    //words = [T, tag, isMark, taskName]
                    lst.add(todoCreate(words));
                    break;

                case "D":
                    lst.add(deadlineCreate(words));
                    break;

                case "E":
                    lst.add(eventCreate(words));
                    break;

                }
            }
        }
        return lst;
    }
}