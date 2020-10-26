package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_DIFF_DATE;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_DIFF_DESCRIPTION;
import static seedu.address.testutil.TypicalEntries.getTypicalJournal;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.AliasMap;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Entry;

public class CheckScheduleCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(
                new AddressBook(),
                getTypicalJournal(),
                new UserPrefs(),
                new AliasMap()
        );
    }

    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("should throw NullPointerException if null predicate is "
                + "passed into constructor")
        public void constructor_nullPredicate_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    new CheckScheduleCommand(null, new Date("")));
        }

        @Test
        @DisplayName("should create CheckScheduleCommand object with date at "
                + "current time if null date passed into constructor")
        public void constructor_nullDate_successful() {
            Predicate<Entry> predicate = entry -> true;
            CheckScheduleCommand checkScheduleCommand =
                    new CheckScheduleCommand(predicate, null);

            assertEquals(checkScheduleCommand,
                    new CheckScheduleCommand(predicate, new Date("")));
        }
    }

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should filter journals successfully if date is valid")
        public void execute_entryAcceptedByModel_addSuccessful() {
            Date date = new Date("2000-12-21 00:00");
            CheckScheduleCommand checkScheduleCommand =
                    new CheckScheduleCommand(entry ->
                            entry.getDate().isSameDate(date), date);

            Model expectedModel = new ModelManager(
                    new AddressBook(),
                    new Journal(),
                    new UserPrefs(),
                    new AliasMap()
            );
            expectedModel.addEntry(TEST_ENTRY_DIFF_DATE);
            expectedModel.addEntry(TEST_ENTRY_DIFF_DESCRIPTION);

            assertCommandSuccess(
                    checkScheduleCommand,
                    model,
                    String.format(
                            CheckScheduleCommand.MESSAGE_SUCCESS,
                            date.getDateString()
                    ),
                    expectedModel
            );
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        private final Predicate<Entry> predicate = entry -> true;
        private final CheckScheduleCommand checkScheduleCommand =
                new CheckScheduleCommand(
                        predicate,
                        new Date("2000-10-12 00:00")
                );

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(checkScheduleCommand.equals(checkScheduleCommand));
        }

        @Test
        @DisplayName("should return true if same values")
        public void equals_sameValues_true() {
            CheckScheduleCommand checkScheduleCommandCopy =
                    new CheckScheduleCommand(
                            predicate,
                            new Date("2000-10-12 00:00")
                    );
            assertTrue(checkScheduleCommand.equals(checkScheduleCommandCopy));
        }

        @Test
        @DisplayName("should return false if different date")
        public void equals_differentDate_false() {
            assertFalse(checkScheduleCommand.equals(
                    new CheckScheduleCommand(predicate,
                            new Date(""))));
        }

        @Test
        @DisplayName("should return false if different predicate")
        public void equals_differentPredicate_false() {
            assertFalse(
                    checkScheduleCommand.equals(
                            new CheckScheduleCommand(entry ->
                                    false, new Date("2020-10-12 00:00"))
                    )
            );
        }

        @Test
        @DisplayName("should return false if null")
        public void equals_null_false() {
            assertFalse(checkScheduleCommand.equals(null));
        }
    }
}
