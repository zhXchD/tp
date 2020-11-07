package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteJournalEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author {zhXchD}
/**
 * Parses input arguments and creates a new DeleteContactCommand object
 */
public class DeleteJournalEntryCommandParser implements Parser<DeleteJournalEntryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteContactCommand and returns a DeleteContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected
     *                        format
     */
    public DeleteJournalEntryCommand parse(String commandWord, String args)
            throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteJournalEntryCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteJournalEntryCommand
                                    .getMessageUsage(commandWord)
                    ),
                    pe
            );
        }
    }
}
