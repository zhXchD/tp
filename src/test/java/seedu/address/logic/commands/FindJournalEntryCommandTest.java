package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.AliasMap;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.journal.Entry;
import seedu.address.testutil.EntryBuilder;
import seedu.address.testutil.JournalBuilder;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code FindJournalEntryCommand}.
 */
public class FindJournalEntryCommandTest {

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
        private final Predicate<Entry> firstPredicate = entry -> entry.getTitle().title.contains("first");
        private final Predicate<Entry> secondPredicate = entry -> entry.getTitle().title.contains("second");

        private final FindJournalEntryCommand findFirstCommand =
                new FindJournalEntryCommand(firstPredicate);
        private final FindJournalEntryCommand findSecondCommand =
                new FindJournalEntryCommand(secondPredicate);

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(findFirstCommand.equals(findFirstCommand));
        }

        @Test
        @DisplayName("should return true if same values")
        public void equals_sameValues_true() {
            FindJournalEntryCommand findFirstCommandCopy = new FindJournalEntryCommand(firstPredicate);
            assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        }

        @Test
        @DisplayName("should return false if different types")
        public void equals_differentTypes_false() {
            assertFalse(findFirstCommand.equals(1));
        }

        @Test
        @DisplayName("should return false if null")
        public void equals_null_false() {
            assertFalse(findFirstCommand.equals(null));
        }

        @Test
        @DisplayName("should return false if different person")
        public void equals_differentPerson_false() {
            assertFalse(findFirstCommand.equals(findSecondCommand));
        }
    }

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should correctly filter the entries")
        public void execute_zeroKeywords_noEntryFound() {
            String expectedMessage =
                    String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 0);
            Predicate<Entry> predicate = entry -> false;
            FindJournalEntryCommand command = new FindJournalEntryCommand(predicate);
            expectedModel.updateFilteredEntryList(predicate);
            assertCommandSuccess(
                    command,
                    model,
                    expectedMessage,
                    expectedModel
            );
            assertEquals(
                    Collections.emptyList(),
                    model.getFilteredEntryList()
            );
        }

        @Test
        @DisplayName("should find multiple persons with multiple keywords")
        public void execute_multipleKeywords_multiplePersonsFound() {
            String expectedMessage =
                    String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 3);
            Predicate<Entry> predicate = entry -> entry.getTitle().title.contains("entry");
            FindJournalEntryCommand command = new FindJournalEntryCommand(predicate);
            expectedModel.updateFilteredEntryList(predicate);
            assertCommandSuccess(
                    command,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }
    }
}
