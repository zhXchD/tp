package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the
 * argument keywords. Keyword matching is case insensitive.
 */
public class FindContactCommand extends FindCommand {
    public static final String MESSAGE_USAGE = FindCommand.COMMAND_WORD
            + " in/c: Finds all persons in the address book whose fields contain"
            + " the specified strings or tags contains specified tags.\n"
            + "Parameters: [n/NAME] [a/ADDRESS] [e/EMAIL] [p/PHONE] [t/TAG]";

    private final Predicate<Person> predicate;

    /**
     * Creates a {@code FindContactCommand} command with predicate.
     * @param predicate the predicate to filter the contacts
     */
    public FindContactCommand(Predicate<Person> predicate) {
        assert predicate != null;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(
                        Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
                        model.getFilteredPersonList().size()
                )
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof FindContactCommand
                && predicate.equals(((FindContactCommand) other).predicate));
    }
}
