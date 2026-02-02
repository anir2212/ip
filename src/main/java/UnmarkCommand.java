public class UnmarkCommand extends Command{
    private int num;

    public UnmarkCommand(int num) {
        this.num = num;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.getTask(num - 1).change_to_unmark();
        System.out.println("_____________________\n" +
                "OK, I've marked this task as not done yet:\n" +
                tasks.getTask(num - 1).toString() + "\n___________________________");
    }

    public boolean isExit() {
        return false;
    }


}
