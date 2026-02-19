package ani;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import ani.command.AddCommand;

public class TagCommandTest {

    @Test
    public void executeTest_validInput_checkFile() throws IOException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        String filePath = "./data/AniTest.txt";
        Storage storage = new Storage(filePath);
        new AddCommand("deadline", "deadline read book #fun /by 2025-01-01")
                .execute(tasks, ui, storage);
        String expectedLine = "D |#fun| 0 | read book | Jan 1 2025";
        String actualLine = Files.readString(Paths.get(filePath)).trim();
        assertEquals(expectedLine, actualLine);
    }
}
