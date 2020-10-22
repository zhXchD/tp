package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.journal.Entry;
import seedu.address.testutil.EntryBuilder;
import seedu.address.testutil.PersonBuilder;

import seedu.address.logic.commands.EditJournalEntryCommand;
import seedu.address.logic.commands.EditJournalEntryCommand.EditJournalEntryDescriptor;

public class EditJournalEntryCommandTest {


    private final Model model = new ModelManager(
            getTypicalAddressBook(),
            new Journal(),
            new UserPrefs()
    );

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should edit contacts successfully in unfiltered list")
        public void execute_contactSpecified_success() {
            Entry originalEntry = model.getFilteredEntryList().get(0);
            Entry editedEntry =
                    new EntryBuilder(originalEntry).withContacts(new PersonBuilder().build()).build();
            EditJournalEntryDescriptor descriptor =
                    new EditJournalEntryDescriptor();
            EditJournalEntryCommand editJournalEntryCommand =
                    new EditJournalEntryCommand(INDEX_FIRST_PERSON, descriptor);
        }
    }
}
