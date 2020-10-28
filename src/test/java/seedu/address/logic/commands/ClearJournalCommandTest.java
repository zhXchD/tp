package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.getTypicalJournal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.AliasMap;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearJournalCommandTest {

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should successfully clear empty journal")
        public void execute_emptyJournal_success() {
            Model model = new ModelManager();
            Model expectedModel = new ModelManager();

            assertCommandSuccess(
                    new ClearJournalCommand(),
                    model,
                    ClearJournalCommand.MESSAGE_SUCCESS,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should successfully clear non-empty journal")
        public void execute_nonEmptyJournal_success() {
            Model model = new ModelManager(
                    new AddressBook(),
                    getTypicalJournal(),
                    new UserPrefs(),
                    new AliasMap()
            );
            Model expectedModel = new ModelManager(
                    new AddressBook(),
                    new Journal(),
                    new UserPrefs(),
                    new AliasMap()
            );
            expectedModel.setJournal(new Journal());

            assertCommandSuccess(
                    new ClearJournalCommand(),
                    model,
                    ClearJournalCommand.MESSAGE_SUCCESS,
                    expectedModel
            );
        }
    }
}
