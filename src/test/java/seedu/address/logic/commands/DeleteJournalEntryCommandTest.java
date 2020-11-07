package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AliasMap;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.EntryBuilder;
import seedu.address.testutil.JournalBuilder;

class DeleteJournalEntryCommandTest {
    private final Journal journal = new JournalBuilder()
            .withEntry(
                    new EntryBuilder()
                            .withTitle("first entry")
                            .withDescription("first description")
                            .withDate("2020-10-10 10:00")
                            .withContacts(CARL)
                            .withContacts(DANIEL)
                            .build())
            .withEntry(
                    new EntryBuilder()
                            .withTitle("second entry")
                            .withDescription("second description")
                            .withDate("2020-10-11 10:00")
                            .withContacts(ALICE)
                            .withContacts(DANIEL)
                            .build())
            .withEntry(
                    new EntryBuilder()
                            .withTitle("third entry")
                            .withDescription("third description")
                            .withDate("2020-10-12 10:00")
                            .withContacts(FIONA)
                            .withContacts(DANIEL)
                            .build())
            .build();
    private final Model model = new ModelManager(
            getTypicalAddressBook(), new Journal(journal), new UserPrefs(), new AliasMap());
    private final Model expectedModel = new ModelManager(
            getTypicalAddressBook(), new Journal(journal), new UserPrefs(), new AliasMap());

    @Nested
    @DisplayName("equals method")
    class Equals {
        private Index firstIndex = Index.fromOneBased(1);
        private Index secondIndex = Index.fromOneBased(2);

        private DeleteJournalEntryCommand firstCommand = new DeleteJournalEntryCommand(firstIndex);
        private DeleteJournalEntryCommand secondCommand = new DeleteJournalEntryCommand(secondIndex);

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(firstCommand.equals(firstCommand));
        }

        @Test
        @DisplayName("should return true if same values")
        public void equals_sameValues_true() {
            DeleteJournalEntryCommand findFirstCommandCopy = new DeleteJournalEntryCommand(firstIndex);
            assertTrue(firstCommand.equals(findFirstCommandCopy));
        }

        @Test
        @DisplayName("should return false if different types")
        public void equals_differentTypes_false() {
            assertFalse(firstCommand.equals(1));
        }

        @Test
        @DisplayName("should return false if null")
        public void equals_null_false() {
            assertFalse(firstCommand.equals(null));
        }

        @Test
        @DisplayName("should return false if different person")
        public void equals_differentPerson_false() {
            assertFalse(firstCommand.equals(secondCommand));
        }
    }

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should delete the first entry")
        public void execute_deleteFirst_successfullyDeleted() {
            String expectedMessage = String.format(
                    DeleteJournalEntryCommand.MESSAGE_DELETE_ENTRY_SUCCESS,
                    new EntryBuilder()
                            .withTitle("first entry")
                            .withDescription("first description")
                            .withDate("2020-10-10 10:00")
                            .withContacts(CARL)
                            .withContacts(DANIEL)
                            .build()
                            .toString()
            );
            DeleteJournalEntryCommand command = new DeleteJournalEntryCommand(Index.fromOneBased(1));
            expectedModel.deleteEntry(new EntryBuilder()
                    .withTitle("first entry")
                    .withDescription("first description")
                    .withDate("2020-10-10 10:00")
                    .withContacts(CARL)
                    .withContacts(DANIEL)
                    .build());
            assertCommandSuccess(
                    command,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }
    }
}
