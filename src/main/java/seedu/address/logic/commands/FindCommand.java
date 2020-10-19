package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the
 * argument keywords. Keyword matching is case insensitive.
 */
public abstract class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "find in/c" +
            " [n/NAME] [a/ADDRESS] [e/EMAIL] [p/PHONE] [t/TAG]" +
            " or find in/j [t/TITLE] [at/DATE] [d/DESCRIPTION] " +
            "[with/CONTACT_NAME] [t/TAG]";

//            COMMAND_WORD
//            + ": Finds all persons whose names contain any of "
//            + "the specified keywords (case-insensitive) and displays them as a"
//            + " list with index numbers.\n"
//            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
//            + "Example: " + COMMAND_WORD + " alice bob charlie";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
