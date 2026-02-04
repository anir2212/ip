package ani;
import java.io.*;
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
    public Storage (String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);

    }


    /**
     * Stores the tasks on TaskList into the storage text file.
     *
     * @param arr TaskList array.
     */
    public void store(TaskList arr){
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (int i = 0; i < arr.len(); i++) {
                pw.println(arr.getTask(i).toStringForFile());

            }
        } catch (IOException e) {
            System.out.println("Error saving tasks");
        }


    }

    /**
     * Loads the tasks on the text storage file back into taskList when the app is reopened.
     *
     * @return ArrayList of tasks.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException {

        file.getParentFile().mkdirs();
        Scanner s_read = new Scanner(file);
        ArrayList<Task> lst = new ArrayList<>();

        if (file.length() != 0) {
            while (s_read.hasNextLine()) {
                String line = s_read.nextLine();
                String[] words = line.split("\\|");
                String word = words[0].trim();
                switch(word) {
                    case "T" :


                        Todo a = new Todo(Task.count, words[2].trim(), "1".equals(words[1].trim()));
                        Task.count++;
                        lst.add(a);

                        break;

                    case "D" :

                        Deadlines d = new Deadlines(Task.count, words[2].trim(), "1".equals(words[1].trim()),
                                words[3].trim());
                        Task.count++;
                        lst.add(d);
                        break;

                    case "E" :
                        int start_index = words[3].trim().indexOf(":") + 1;
                        int end_index = words[3].trim().indexOf("to");

                        String start_date = words[3].trim().substring(start_index, end_index).trim();
                        String end_date = words[3].trim().substring(end_index + "to: ".length());
                        Event e = new Event(Task.count, words[2].trim(), "1".equals(words[1].trim()), start_date, end_date);
                        lst.add(e);
                        Task.count++;
                        break;

                }

            }

        }
        return lst;
    }
}