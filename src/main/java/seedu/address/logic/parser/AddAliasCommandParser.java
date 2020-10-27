package seedu.address.logic.parser;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddAliasCommandParser implements Parser<AddAliasCommand> {

    private static final String MESSAGE_WRONG_INPUT_FORMAT = "Wrong input format. Input format:  "
            + "alias [COMMAND] [CUSTOMIZED_ALIAS]";

    @Override
    public AddAliasCommand parse(String userInput) throws ParseException {
        if (userInput.equals("")) {
            throw new ParseException(MESSAGE_WRONG_INPUT_FORMAT);
        }

        String[] commandAliasPair = userInput.substring(1, userInput.length()).split("\\s+");

        if (commandAliasPair.length != 2) {
            throw new ParseException(MESSAGE_WRONG_INPUT_FORMAT);
        }

        String target = commandAliasPair[0];
        String alias = commandAliasPair[1];

        return new AddAliasCommand(target, alias);
    }
}
