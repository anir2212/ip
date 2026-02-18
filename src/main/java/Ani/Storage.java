package ani;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
        Todo a = new Todo(Task.count, words[2].trim(), "1".equals(words[1].trim()));
        Task.count++;
        return a;
    }

    /**
     * Creates deadline task to be loaded onto taskList.
     *
     * @param words Array of words on txt file to be read.
     * @return ArrayList of tasks to be loaded onto taskList.
     */
    public Deadlines deadlineCreate(String[] words) {
        Deadlines deadline = new Deadlines(Task.count, words[2].trim(), "1".equals(words[1].trim()), words[3].trim());
        Task.count++;
        return deadline;

    }

    /**
     * Creates event task to be loaded onto taskList.
     *
     * @param words Array of words on txt file to be read.
     * @return ArrayList of tasks to be loaded onto taskList.
     */
    public Event eventCreate(String[] words) {
        int startIndex = words[3].trim().indexOf(":") + 1;
        int endIndex = words[3].trim().indexOf("to");
        String startDate = words[3].trim().substring(startIndex, endIndex).trim();
        String endDate = words[3].trim().substring(endIndex + "to: ".length());
        Event event = new Event(Task.count, words[2].trim(), "1".equals(words[1].trim()), startDate, endDate);
        Task.count++;
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