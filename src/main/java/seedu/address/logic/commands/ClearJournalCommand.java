package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AddressBook;
import seedu.address.model.Journal;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearJournalCommand extends Command {

    public static final String COMMAND_WORD = "clearj";
    public static final String MESSAGE_SUCCESS =
            "Journal has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setJournal(new Journal());
        return new CommandResult(MESSAGE_SUCCESS).setJournalTab();
    }
}
