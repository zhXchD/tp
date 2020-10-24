package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalEntries.getTypicalJournal;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditJournalEntryCommand.EditEntryDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.journal.Entry;
import seedu.address.testutil.EditEntryDescriptorBuilder;

public class EditJournalEntryCommandTest {

    private final Model model = new ModelManager(
            getTypicalAddressBook(),
            getTypicalJournal(),
            new UserPrefs()
    );

    @Nested
    @DisplayName("execute method")
    class Execute {


        @Test
        @DisplayName("should not edit an entry if the entry is duplicate")
        public void execute_duplicateEntryUnfilteredList_failure() {
            Entry firstEntry = model.getFilteredEntryList()
                    .get(INDEX_FIRST_PERSON.getZeroBased());
            EditEntryDescriptor descriptor =
                    new EditEntryDescriptorBuilder(firstEntry).build();
            EditJournalEntryCommand editJournalEntryCommand =
                    new EditJournalEntryCommand(INDEX_SECOND_PERSON,
                            descriptor);

            assertCommandFailure(
                    editJournalEntryCommand,
                    model,
                    EditJournalEntryCommand.MESSAGE_DUPLICATE_ENTRY
            );
        }

        @Test
        @DisplayName("should not edit an entry if the index is invalid in the"
                + " unfiltered list")
        public void execute_invalidEntryIndexUnfilteredList_failure() {
            Index outofBoundIndex = Index.fromOneBased(
                    model.getFilteredEntryList().size() + 1);
            EditEntryDescriptor descriptor =
                    new EditEntryDescriptorBuilder().withTitle(VALID_TITLE_MOVIE)
                            .build();
            EditJournalEntryCommand editJournalEntryCommand =
                    new EditJournalEntryCommand(outofBoundIndex, descriptor);

            assertCommandFailure(
                    editJournalEntryCommand,
                    model,
                    Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX
            );
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        private final EditJournalEntryCommand standardCommand =
                new EditJournalEntryCommand(INDEX_FIRST_PERSON, DESC_MEETING);

        @Test
        @DisplayName("should return true if values are equal")
        public void equals_sameValue_true() {
            EditEntryDescriptor copyDescriptor =
                    new EditEntryDescriptor(DESC_MEETING);
            EditJournalEntryCommand commandWithSameValues =
                    new EditJournalEntryCommand(INDEX_FIRST_PERSON,
                            copyDescriptor);
            assertTrue(standardCommand.equals(commandWithSameValues));
        }

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(standardCommand.equals(standardCommand));
        }

        @Test
        @DisplayName("should return false if null")
        public void equals_null_false() {
            assertFalse(standardCommand.equals(null));
        }

        @Test
        @DisplayName("should return false if different types")
        public void equals_differentTypes_false() {
            assertFalse(standardCommand.equals(new ClearJournalCommand()));
        }

        @Test
        @DisplayName("should return false if index is different")
        public void equals_differentIndex_false() {
            assertFalse(
                    standardCommand.equals(
                            new EditJournalEntryCommand(
                                    INDEX_SECOND_PERSON,
                                    DESC_MEETING
                            )
                    )
            );
        }

        @Test
        @DisplayName("should return false if descriptor is different")
        public void equals_differentDescriptor_false() {
            EditEntryDescriptor otherDescriptor =
                    new EditEntryDescriptorBuilder()
                            .withTitle(VALID_TITLE_MOVIE)
                            .build();
            assertFalse(
                    standardCommand.equals(
                            new EditJournalEntryCommand(
                                    INDEX_FIRST_PERSON,
                                    otherDescriptor
                            )
                    )
            );
        }

    }

}
