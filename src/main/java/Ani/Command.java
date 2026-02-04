package ani;

import java.io.IOException;

abstract public class Command {

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    abstract boolean isExit();


}
