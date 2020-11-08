package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.journal.Entry;
import seedu.address.model.person.Person;

//@@author {zhXchD}
/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteContactCommand extends Command {

    public static final String COMMAND_WORD = "deletec";

    public static final String MESSAGE_USAGE = "%s: Deletes the contact at the"
            + " index position in the currently "
            + "displayed contact list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: %s 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS =
            "Deleted Person: %1$s\nAssociated with journal entries:\n%2$s";

    private final Index targetIndex;

    public DeleteContactCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public static String getMessageUsage(String commandWord) {
        return String.format(MESSAGE_USAGE, commandWord, commandWord);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        List<Entry> entriesListToDelete = model
                .getJournal()
                .getEntryList()
                .stream()
                .filter(entry -> entry.getContactList().contains(personToDelete))
                .collect(Collectors.toList());

        StringBuilder entriesToDelete = new StringBuilder();
        for (Entry entry : entriesListToDelete) {
            entriesToDelete.append(entry.getTitle()).append("\n");
        }
        if (entriesToDelete.length() == 0) {
            entriesToDelete.append("None");
        }

        model.deletePerson(personToDelete);

        return new CommandResult(
                String.format(
                        MESSAGE_DELETE_PERSON_SUCCESS,
                        personToDelete,
                        entriesToDelete
                )
        ).setAddressBookTab();
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof DeleteContactCommand
                && ((DeleteContactCommand) other).targetIndex.equals(targetIndex));
    }
}
