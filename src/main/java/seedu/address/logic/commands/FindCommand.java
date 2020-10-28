package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

//@@author {zhXchD}
/**
 * Finds and lists all persons in address book whose name contains any of the
 * argument keywords. Keyword matching is case insensitive.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all contacts or the journal entries depending on the "
            + "scope that contains the fields specified.\n"
            + "Parameters: in/SCOPE (\"c\" or \"j\") [n/NAME_OR_TITLE] "
            + "[a/ADDRESS] [e/EMAIL] [p/PHONE] [at/DATE] [d/DESCRIPTION] "
            + "[with/CONTACT_NAME] [t/TAG]...\n"
            + "Example:\n"
            + "- find in/c n/Robert a/Paya Lebar\n"
            + "- find in/j d/Meeting with/Robert";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
