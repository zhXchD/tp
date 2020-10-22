package seedu.address.testutil;
import seedu.address.logic.commands.EditJournalEntryCommand.EditJournalEntryDescriptor;

/**
 * A utility class to help with building EditJournalEntryDescriptor objects.
 */
public class EditJournalEntryDescriptorBuilder {

    private final EditJournalEntryDescriptor descriptor;

    public EditJournalEntryDescriptorBuilder() {
        descriptor = new EditJournalEntryDescriptor();
    }

}
