package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ViewJournalEntryCommand;
import seedu.address.logic.commands.ViewPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {
    @Override
    public ViewCommand parse(String args) throws ParseException {
        String[] arg = args.trim().split(" ");
        String scope = arg[0];
        String index = arg[1];
        if (scope.startsWith("in/")) {
            if (scope.equals("in/c")) {
                return new ViewPersonCommand(parseIndex(index));
            } else if (scope.equals("in/j")) {
                return new ViewJournalEntryCommand(parseIndex(index));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
    }

    private Index parseIndex(String arg) throws ParseException {
        try {
            return ParserUtil.parseIndex(arg);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            ViewCommand.MESSAGE_USAGE
                    ),
                    pe
            );
        }
    }
}
