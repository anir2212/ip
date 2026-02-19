package ani;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import ani.command.AddCommand;
import ani.command.FindCommand;

public class FindCommandTest {
    @Test
    public void executeTest_validInput_checkOutput() throws IOException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        String filePath = "./data/AniTest.txt";
        Storage storage = new Storage(filePath);
        new AddCommand("deadline", "deadline read book #study /by 2025-01-01")
                .execute(tasks, ui, storage);
        new AddCommand("todo", "todo borrow book #read").execute(tasks, ui, storage);
        new AddCommand("todo", "todo boost drink purchase #chill").execute(tasks, ui, storage);
        String actualLine = new FindCommand("book").execute(tasks, ui, storage);
        String expectedLine = ui.showLine() + "\nHere are the matching tasks in your list:\n"
                + "1. [D][#study][ ] read book (by: Jan 1 2025)\n"
                + "2. [T][#read][ ] borrow book\n" + ui.showLine();
        assertEquals(actualLine, expectedLine);





    }
}
