package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListJournalEntryCommand extends Command {

    public static final String COMMAND_WORD = "listj";

    public static final String MESSAGE_SUCCESS = "Listed all journal entries";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // TODO: model.updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
        // model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS).setJournalTab();
    }
}
