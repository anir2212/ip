package ani;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import ani.command.AddCommand;
import ani.exception.AniException;

public class AddCommandTest {

    @Test
    public void executeTest_invalidDate_exceptionThrown() throws AniException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/AniTest.txt");
        try {
            new AddCommand("deadline"
                    , "deadline read book /by Sunday")
                    .execute(tasks, ui, storage);
            fail();
        } catch (AniException | IOException e) {
            assertEquals("\nPlease input date in valid format YYYY-MM-DD\n"
                    , e.getMessage());

        }

    }

    @Test
    public void executeTest_validInput_checkFile() throws IOException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        String filePath = "./data/AniTest.txt";
        Storage storage = new Storage(filePath);
        new AddCommand("deadline", "deadline read book /by 2025-01-01")
                .execute(tasks, ui, storage);
        String expectedLine = "D | | 0 | read book | Jan 1 2025";
        String actualLine = Files.readString(Paths.get(filePath)).trim();
        assertEquals(expectedLine, actualLine);


    }


}


