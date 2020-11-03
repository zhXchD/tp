package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ValidCommand;
import seedu.address.logic.parser.exceptions.AliasExistsException;
import seedu.address.logic.parser.exceptions.AliasNotFoundException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class DeleteAliasCommand extends Command {

    public static final String COMMAND_WORD = "release";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": delete a shortcut for an existing alias.\n"
            + "Parameters: CUSTOMISED_ALIAS\n"
            + "Example: release sw ";

    private static final String MESSAGE_ALIAS_NOTFOUND =
            "This alias is not in the system.";
    private static final String MESSAGE_ADD_ALIAS_SUCCEED =
            "Delete alias %s.";

    private final String target;

    public DeleteAliasCommand(String target) {
        requireNonNull(target);

        this.target = target;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            ValidCommand.deleteAlias(target);
        } catch (AliasNotFoundException e) {
            throw new CommandException(MESSAGE_ALIAS_NOTFOUND);
        }

        model.updateAlias(ValidCommand.getAliasMap());

        return new CommandResult(String.format(MESSAGE_ADD_ALIAS_SUCCEED, target))
                .setSameTab();
    }
}
