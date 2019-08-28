package duke.parser;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.exception.DukeException;

/**
 * Represents parsing and decomposing string commands into Command types.
 */
public class Parser {

    /**
     * Parses a string, determining what command it is.
     *
     * @param string String to be parsed.
     * @return Relevant command.
     * @throws DukeException If string is badly formatted.
     */
    public static Command parse(String string) throws DukeException {
        if (string == null) {
            throw new DukeException();
        }
        switch (string) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        default:
            String[] tokens = string.split("\\s+");
            if (tokens.length > 0 && tokens[0] != null) {
                switch (tokens[0]) {
                case "done":
                    try {
                        Command c = new DoCommand(Integer.parseInt(tokens[1]));
                        return c;
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Invalid done command.");
                    }
                case "delete":
                    try {
                        Command c = new DeleteCommand(Integer.parseInt(tokens[1]));
                        return c;
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Invalid delete command.");
                    }
                case "find":
                    try {
                        Command c = new FindCommand(tokens[1]);
                        return c;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Invalid find command.");
                    }
                default:
                    return new AddCommand(string);
                }
            }
            return new AddCommand(string);
        }
    }

}
