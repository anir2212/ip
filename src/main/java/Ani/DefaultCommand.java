package ani;

public class DefaultCommand extends Command{

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        throw new AniException("________________________________\n"
                + "Sorry, I don't understand what that means\n"
                + "_____________________________");
    }

    public boolean isExit() {
        return false;
    }


}
