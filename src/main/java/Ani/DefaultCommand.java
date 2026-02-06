package ani;

/**
 * DefaultCommand class for commands that do not fall into the main defined task types
 */
public class DefaultCommand extends Command {

    /**
     * Executes throwing of AniException for unknown task types identified
     *
     * @param tasks   Tasks in taskList.
     * @param ui      UI.
     * @param storage Storage in Storage class for tasks present.
     * @throws AniException Exception thrown for invalid task types
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        throw new AniException("\nSorry, I don't understand what that means\n");
    }

    /**
     * Signals whether the command is one that exits.
     *
     * @return Boolean to not exit.
     */
    public boolean isExit() {
        return false;
    }


}
