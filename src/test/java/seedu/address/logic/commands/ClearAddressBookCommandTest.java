package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.AliasMap;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearAddressBookCommandTest {

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should successfully clear empty address book")
        public void execute_emptyAddressBook_success() {
            Model model = new ModelManager();
            Model expectedModel = new ModelManager();

            assertCommandSuccess(
                    new ClearAddressBookCommand(),
                    model,
                    ClearAddressBookCommand.MESSAGE_SUCCESS,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should successfully clear non-empty address book")
        public void execute_nonEmptyAddressBook_success() {
            Model model = new ModelManager(
                    getTypicalAddressBook(),
                    new Journal(),
                    new UserPrefs(),
                    new AliasMap()
            );
            Model expectedModel = new ModelManager(
                    getTypicalAddressBook(),
                    new Journal(),
                    new UserPrefs(),
                    new AliasMap()
            );
            expectedModel.setAddressBook(new AddressBook());

            assertCommandSuccess(
                    new ClearAddressBookCommand(),
                    model,
                    ClearAddressBookCommand.MESSAGE_SUCCESS,
                    expectedModel
            );
        }
    }
}
