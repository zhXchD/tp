package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.journal.Entry;

//@@author {zhXchD}
/**
 * Finds and lists all persons in address book whose name contains any of the
 * argument keywords. Keyword matching is case insensitive.
 */
public class FindJournalEntryCommand extends Command {
    public static final String COMMAND_WORD = "findc";

    public static final String MESSAGE_USAGE = "%s: Finds all entries in the "
            + "journal that contains the "
            + "fields specified\n"
            + "Parameters: [n/TITLE] [at/DATE_AND_TIME] [d/DESCRIPTION]"
            + " [with/CONTACT_NAME] [t/TAG], "
            + "there must be at least one parameter\n"
            + "Example: %s d/Meeting with/Robert";

    private final Predicate<Entry> predicate;

    /**
     * Create a new FindJournalEntryCommand from a specified predicate.
     * @param predicate predicate to filter the journal entries
     */
    public FindJournalEntryCommand(Predicate<Entry> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    public static String getMessageUsage(String commandWord) {
        return String.format(MESSAGE_USAGE, commandWord, commandWord);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEntryList(predicate);
        return new CommandResult(
                String.format(
                        Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW,
                        model.getFilteredEntryList().size()
                )
        ).setJournalTab();
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof FindJournalEntryCommand
                && predicate.equals(((FindJournalEntryCommand) other).predicate));
    }
}
