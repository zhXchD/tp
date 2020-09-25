package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.journal.Entry;

/**
 * Adds a person to the address book.
 */
public class AddJournalEntryCommand extends Command {

    public static final String COMMAND_WORD = "addj";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a journal entry to the journal. "
            + "Parameters: "
            + PREFIX_NAME + "TITLE "
//            + PREFIX_PHONE + "PHONE "
//            + PREFIX_EMAIL + "EMAIL "
//            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_DATE_AND_TIME + "DATE_AND_TIME"
            + PREFIX_DESCRIPTION + "DESCRIPTION"
//            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Meet with client John Doe"
//            + PREFIX_PHONE + "98765432 "
//            + PREFIX_EMAIL + "johnd@example.com "
//            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
//            + PREFIX_TAG + "friends "
//            + PREFIX_TAG + "owesMoney";
            + PREFIX_DATE_AND_TIME + "2020-9-20 1400"
            + PREFIX_DESCRIPTION + "Discussed about his demands.";

    public static final String MESSAGE_SUCCESS = "New journal entry added: %1$s";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the journal";

    private final Entry toAdd;

    /**
     * Creates an AddJournalEntryCommand to add the specified {@code Entry}
     */
    public AddJournalEntryCommand(Entry entry) {
        requireNonNull(entry);
        toAdd = entry;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEntry(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
        }

        model.addEntry(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddJournalEntryCommand // instanceof handles nulls
                && toAdd.equals(((AddJournalEntryCommand) other).toAdd));
    }
}
