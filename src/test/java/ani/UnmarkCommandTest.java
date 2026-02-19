package ani;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import ani.command.AddCommand;
import ani.command.UnmarkCommand;
import ani.exception.AniException;

public class UnmarkCommandTest {

    @Test
    public void executeTest_invalidTaskNumber_exceptionThrown() throws IOException {

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/AniTest.txt");


        new AddCommand("deadline"
                , "deadline read book /by 2025-12-01")
                .execute(tasks, ui, storage);
        try {
            new UnmarkCommand(2).execute(tasks, ui, storage);
            fail();

        } catch (AniException e) {
            assertEquals("\nPlease provide a valid task number\n"
                    , e.getMessage());
        }
    }
}
