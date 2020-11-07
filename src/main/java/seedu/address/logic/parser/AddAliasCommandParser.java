package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddAliasCommandParser implements Parser<AddAliasCommand> {
    @Override
    public AddAliasCommand parse(String commandWord, String userInput)
            throws ParseException {

        String[] commandAliasPair = userInput.trim().split("\\s+");

        if (commandAliasPair.length != 2) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            AddAliasCommand.getMessageUsage(commandWord)
                    )
            );
        }

        String target = commandAliasPair[0];
        String alias = commandAliasPair[1];

        return new AddAliasCommand(target, alias);
    }
}
