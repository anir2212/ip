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
     * Creates Ani class with the filePath to the task storage file as input.
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



    public String run(String command) throws IOException {
        try {
            Command c = Parser.parse(command);
            return c.execute(tasks, ui, storage);

        } catch (AniException e) {
            return ui.showLine() + ui.showError(e.getMessage()) + ui.showLine();
        }

    }

}


