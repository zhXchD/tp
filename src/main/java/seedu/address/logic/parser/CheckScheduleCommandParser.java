package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.CheckScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.journal.Date;

public class CheckScheduleCommandParser
        implements Parser<CheckScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * CheckScheduleCommand and returns a CheckScheduleCommand object for
     * execution.
     * @throws ParseException if the user input does not conform the expected
     *                        format.
     */
    @Override
    public CheckScheduleCommand parse(String commandWord, String userInput)
            throws ParseException {
        try {
            String trimmedInput = userInput.trim();
            Date date;

            if (trimmedInput.length() == 0) {
                date = ParserUtil.parseDate(null);
            } else {
                date = ParserUtil.parseDate(trimmedInput + " 00:00");
            }

            return new CheckScheduleCommand(
                entry -> entry.getDate().isSameDate(date), date);
        } catch (ParseException parseException) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            CheckScheduleCommand.getMessageUsage(commandWord)
                    ),
                    parseException
            );
        }
    }
}
