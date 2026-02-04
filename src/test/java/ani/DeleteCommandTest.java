package ani;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {

    @Test

    public void executeTest_invalidTaskNumber_exceptionThrown() throws IOException {

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/AniTest.txt");


        new AddCommand("deadline"
                    , "deadline read book /by 2025-12-01")
                    .execute(tasks, ui, storage);
        try {
            new DeleteCommand(2).execute(tasks, ui, storage);
            fail();

        } catch (AniException e) {
            assertEquals("___________________________________\n"
                    + "Please enter a valid task number\n"
                    + "_________________________________", e.getMessage());

        }
    }
}
