package ani;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Ani {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
                ui.showError(e.getMessage());
            }
        }
    }



    public static void main(String[] args) throws IOException {
        new Ani("./data/Ani.txt").run();


    }

}


