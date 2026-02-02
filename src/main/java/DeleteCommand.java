public class DeleteCommand extends Command {

    private int deleteNum;

    public DeleteCommand(int deleteNum) {
        this.deleteNum = deleteNum;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {

        if (deleteNum > Task.count) {
            throw new AniException("___________________________________\n"
                    + "Please enter a valid task number\n"
                    + "_________________________________");

        }


        Task removed = tasks.getTask(deleteNum - 1);
        tasks.removeTask(deleteNum - 1);
        Task.count--;
        System.out.println("_____________________________\n" + "Noted. I've removed this task:\n"
                + removed.toString() + "\nNow you have " + Task.count + " tasks in the list.\n"
                + "____________________________");

    }

    public boolean isExit() {
        return false;
    }


}
