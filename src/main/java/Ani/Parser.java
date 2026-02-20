package ani;

import ani.command.AddCommand;
import ani.command.Command;
import ani.command.DefaultCommand;
import ani.command.DeleteCommand;
import ani.command.ExitCommand;
import ani.command.FindCommand;
import ani.command.ListCommand;
import ani.command.MarkCommand;
import ani.command.UnmarkCommand;
import ani.command.TagCommand;
import ani.exception.AniException;

/**
 * Parser class parses the input from the user and outputs the respective command type.
 */
public class Parser {

    /**
     * Parses the user input and outputs the command type required to execute task.
     *
     * @param input User input.
     * @return Instance of relevant command type.
     */
    public static Command parse(String input) {
        String firstWord = input.split(" ")[0];
        String[] words = input.split(" ");

        switch (firstWord) {
        case "list":
            return new ListCommand();
        case "mark":
            int markNum = Parser.catchMissingTaskNumber(words);
            return new MarkCommand(markNum);
        case "unmark":
            int unmarkNum = Parser.catchMissingTaskNumber(words);
            return new UnmarkCommand(unmarkNum);
        case "todo":
            return new AddCommand("todo", input);
        case "deadline":
            return new AddCommand("deadline", input);
        case "event":
            return new AddCommand("event", input);
        case "delete":
            int deleteNum = Parser.catchMissingTaskNumber(words);
            return new DeleteCommand(deleteNum);
        case "find":
            String keyword = words[1];
            return new FindCommand(keyword);
        case "tag" :
            String tagWord = catchMissingTag(words);
            int taskNumber = catchMissingTaskNumber(words);
            return new TagCommand(taskNumber, tagWord);
        case "bye":
            return new ExitCommand();

        default:
            return new DefaultCommand();
        }

    }

    
    private static int catchMissingTaskNumber(String[] words) {
        try {
            return Integer.parseInt(words[1]);

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new AniException("\nPlease specify a task number with the below format\n"
                                    + "Example: command 5\n");
        }
    }

    private static String catchMissingTag(String[] words) {
        try {
            Integer.parseInt(words[1]);
            if (words[2].charAt(0) != '#') {
                throw new AniException("\nTag using # please! #fun #lol #comeonbro\n");
            }
            return words[2];
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new AniException("\nPlease use the tag command with the below format\n"
                    + "tag 2 #CS2103T\n");
        }
    }
}

