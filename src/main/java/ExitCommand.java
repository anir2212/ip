public class ExitCommand extends Command {
    private Ui ui;


    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();

    }

    public boolean isExit() {
        return true;
    }
}
