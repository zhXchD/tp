package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewJournalEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author {zhXchD}
/**
 * Parses input arguments and creates a new ViewPersonCommand object
 */
public class ViewJournalEntryCommandParser implements Parser<ViewJournalEntryCommand> {
    @Override
    public ViewJournalEntryCommand parse(String commandWord, String args)
            throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewJournalEntryCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            ViewJournalEntryCommand.getMessageUsage(commandWord)
                    ),
                    pe
            );
        }
    }
}
