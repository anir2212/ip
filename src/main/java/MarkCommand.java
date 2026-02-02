public class MarkCommand extends Command{

    private int num;

    public MarkCommand(int num) {
        this.num = num;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

        if (num > Task.count) {
            throw new AniException("_________________________________\n"
                                    + "Please provide a valid task number\n"
                                    +"______________________________");
        }


        tasks.getTask(num - 1).change_to_mark();
        System.out.println("_____________________\n" +
                "Nice! I have marked this task as done:\n" +
                tasks.getTask(num - 1).toString() + "\n____________________________");

    }

    public boolean isExit() {
        return false;
    }


}
