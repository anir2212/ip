package ani;

public class FindCommand extends Command {

    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int count = 1;
        System.out.println("___________________________________________\n"
                            + "Here are the matching tasks in your list:");
        for(int i = 1; i < tasks.len() + 1; i++) {
            String nameTask = tasks.getTask(i - 1).getTaskName();
            String[] words = nameTask.split(" ");
            for (String word : words) {
                if (word.equals(keyword)) {
                    System.out.println(count + ". " + tasks.getTask(i - 1).toString());
                    count++;
                    break;
                }
            }
        }
        System.out.println("_________________________________________");
    }

    public boolean isExit() {
        return false;
    }

}
