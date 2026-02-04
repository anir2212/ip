package ani;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("____________________________\n" + "Here are the tasks in your list:");
        for (int i = 1; i < tasks.len() + 1; i++) {

            System.out.println(i + ". " + tasks.getTask(i - 1).toString());
        }


        System.out.println("_____________________________");

    }

    public boolean isExit() {
        return false;
    }



}
