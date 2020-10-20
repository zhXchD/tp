package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Finds and lists all persons in address book whose name contains any of the
 * argument keywords. Keyword matching is case insensitive.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "Usage: find in/c"
            + " [n/NAME] [a/ADDRESS] [e/EMAIL] [p/PHONE] [t/TAG]"
            + " or find in/j [t/TITLE] [at/DATE] [d/DESCRIPTION]"
            + " [with/CONTACT_NAME] [t/TAG]";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
