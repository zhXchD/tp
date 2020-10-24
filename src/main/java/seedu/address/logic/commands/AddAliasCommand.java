package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class AddAliasCommand extends Command {

    private static final String MESSAGE_ADD_ALIAS_SUCCEED = "Add %s as an alias for %s";
    private static final String MESSAGE_COMMAND_INVALID = "Your target command is invalid.";

    private final String targetCommand;
    private final String alias;

    /**
     * Creates a addAlias command.
     */
    public AddAliasCommand(String targetCommand, String alias) {
        this.targetCommand =targetCommand;
        this.alias = alias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            ValidCommand.addAlias(ValidCommand.commandTypeOf(targetCommand), alias);
        } catch (ParseException e) {
            throw new CommandException(MESSAGE_COMMAND_INVALID);
        }

        return new CommandResult(String.format(MESSAGE_ADD_ALIAS_SUCCEED, alias, targetCommand));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof AddAliasCommand
                && targetCommand.equals(((AddAliasCommand) other).targetCommand)
                && alias.equals(((AddAliasCommand) other).alias));
    }
}
