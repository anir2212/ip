package ani;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is the main class for all Ani operations.
 */
public class Ani {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates Ani clas with the filePath to the task storage file as input.
     *
     * @param filePath Filepath to storing of tasks.
     */
    public Ani(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AniException | FileNotFoundException e) {
            System.out.println("No previous tasks listed");
            tasks = new TaskList();
        }
    }

    /**
     * Executes initial UI and the tasks that are continually read.
     * Uses command classes and the execute methods associated with them.
     *
     * @throws IOException  Exception thrown for file reading errors.
     * @throws AniException Exception thrown in execute methods.
     */

    /**
    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AniException e) {
                ui.showLine();
                ui.showError(e.getMessage());
                ui.showLine();
            }
        }
    }
     **/

    public String run(String command) throws IOException {
        Command c = Parser.parse(command);
        return c.execute(tasks, ui, storage);
    }

    /**
     * Runs the Ani application.
     *
     * @param args
     * @throws IOException
     */

    /**
    public static void main(String[] args) throws IOException {
        new Ani("./data/Ani.txt").run();
    }
     **/
}


