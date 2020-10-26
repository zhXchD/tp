package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEntryAtIndex;
import static seedu.address.testutil.TypicalEntries.getTypicalJournal;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditJournalEntryCommand.EditEntryDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.AliasMap;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.journal.Entry;
import seedu.address.testutil.EditEntryDescriptorBuilder;
import seedu.address.testutil.EntryBuilder;

public class EditJournalEntryCommandTest {

    private final Model model = new ModelManager(
            getTypicalAddressBook(),
            getTypicalJournal(),
            new UserPrefs(),
            new AliasMap()
    );

    @Nested
    @DisplayName("execute method")
    class Execute {

        @Test
        @DisplayName("should edit person successfully in unfiltered list")
        public void execute_allFieldsSpecifiedUnfilteredList_success() {
            Entry originalEntry = model.getFilteredEntryList().get(0);
            Entry editedEntry = new EntryBuilder().build();

            EditEntryDescriptor descriptor =
                    new EditEntryDescriptorBuilder(editedEntry).build();
            EditJournalEntryCommand editJournalEntryCommand =
                    new EditJournalEntryCommand(INDEX_FIRST_PERSON,
                            descriptor);
            String expectedMessage = String.format(
                    EditJournalEntryCommand.MESSAGE_EDIT_ENTRY_SUCCESS,
                    editedEntry
            );
            Model expectedModel =
                    new ModelManager(
                            new AddressBook(model.getAddressBook()),
                            new Journal(model.getJournal()),
                            new UserPrefs(),
                            new AliasMap()
                    );
            expectedModel.setEntry(originalEntry, editedEntry);
            assertCommandSuccess(
                    editJournalEntryCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should successfully edit entry with only certain fields"
                + " specified")
        public void execute_someFieldsSpecifiedUnfilteredList_success() {
            // using last entry in list
            Index indexLastEntry =
                    Index.fromOneBased(model.getFilteredEntryList().size());
            Entry lastEntry = model.getFilteredEntryList()
                    .get(indexLastEntry.getZeroBased());
            EntryBuilder entryInList = new EntryBuilder(lastEntry);
            Entry editedEntry = entryInList
                    .withTitle(VALID_TITLE_MOVIE)
                    .withContacts(GEORGE)
                    .withTags(VALID_TAG_HUSBAND)
                    .build();

            EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                    .withTitle(VALID_TITLE_MOVIE)
                    .withContacts(GEORGE)
                    .withTags(VALID_TAG_HUSBAND)
                    .build();
            EditJournalEntryCommand editJournalEntryCommand =
                    new EditJournalEntryCommand(indexLastEntry, descriptor);

            String expectedMessage = String.format(
                    EditJournalEntryCommand.MESSAGE_EDIT_ENTRY_SUCCESS,
                    editedEntry
            );

            Model expectedModel =
                    new ModelManager(new AddressBook(model.getAddressBook()),
                            new Journal(model.getJournal()),
                            new UserPrefs(),
                            new AliasMap()
                    );
            expectedModel.setEntry(lastEntry, editedEntry);
            assertCommandSuccess(
                    editJournalEntryCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should successfully edit person with no field specified"
                + " in unfiltered list")
        public void execute_noFieldSpecifiedUnfilteredList_success() {
            EditJournalEntryCommand editJournalEntryCommand =
                    new EditJournalEntryCommand(INDEX_FIRST_PERSON,
                            new EditEntryDescriptor());
            Entry editedEntry = model.getFilteredEntryList()
                    .get(INDEX_FIRST_PERSON.getZeroBased());
            String expectedMessage = String.format(
                    EditJournalEntryCommand.MESSAGE_EDIT_ENTRY_SUCCESS,
                    editedEntry
            );

            Model expectedModel =
                    new ModelManager(new AddressBook(model.getAddressBook()),
                            new Journal(model.getJournal()),
                            new UserPrefs(),
                            new AliasMap());

            assertCommandSuccess(
                    editJournalEntryCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }


        @Test
        @DisplayName("should successfully edit an entry in a filtered list")
        public void execute_filteredList_success() {
            showEntryAtIndex(model, INDEX_FIRST_PERSON);

            Entry entryInFilteredList =
                    model.getFilteredEntryList().get(INDEX_FIRST_PERSON.getZeroBased());
            Entry editedEntry = new EntryBuilder(entryInFilteredList)
                    .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                    .build();
            EditJournalEntryCommand editJournalEntryCommand = new EditJournalEntryCommand(
                    INDEX_FIRST_PERSON,
                    new EditEntryDescriptorBuilder()
                            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                            .build());
            String expectedMessage = String.format(
                    EditJournalEntryCommand.MESSAGE_EDIT_ENTRY_SUCCESS,
                    editedEntry);
            Model expectedModel =
                    new ModelManager(
                            new AddressBook(model.getAddressBook()),
                            new Journal(model.getJournal()),
                            new UserPrefs(),
                            new AliasMap());
            expectedModel.setEntry(model.getFilteredEntryList().get(0), editedEntry);
            assertCommandSuccess(
                    editJournalEntryCommand,
                    model,
                    expectedMessage,
                    expectedModel);

        }

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
        @DisplayName("should not edit an entry if the entry is a duplicate in"
                + " a filtered list")
        public void execute_duplicateEntryFilteredList_failure() {
            showEntryAtIndex(model, INDEX_FIRST_PERSON);
            Entry entryInList =
                    model.getJournal()
                            .getEntryList()
                            .get(INDEX_SECOND_PERSON.getZeroBased());
            EditJournalEntryCommand editJournalEntryCommand =
                    new EditJournalEntryCommand(
                            INDEX_FIRST_PERSON,
                            new EditEntryDescriptorBuilder(entryInList).build());
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
