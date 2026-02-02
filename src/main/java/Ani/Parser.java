
package Ani;

public class Parser {

    private String command;

    public Parser (String command) {
        this.command = command;
    }

    public static Command parse(String input) {
        String firstWord = input.split(" ")[0];
        String[] words = input.split(" ");
        switch (firstWord) {
            case "list":
                return new ListCommand();
            case "mark" :
                int markNum = Integer.parseInt(words[1]);
                return new MarkCommand(markNum);
            case "unmark" :
                int unmarkNum = Integer.parseInt(words[1]);
                return new UnmarkCommand(unmarkNum);
            case "todo" :
                return new AddCommand("todo", input);
            case "deadline" :
                return new AddCommand("deadline", input);
            case "event" :
                return new AddCommand("event", input);
            case "delete" :
                int deleteNum = Integer.parseInt(words[1]);
                return new DeleteCommand(deleteNum);
            case "bye" :
                return new ExitCommand();
            default:
                return new DefaultCommand();
        }






    }


}
