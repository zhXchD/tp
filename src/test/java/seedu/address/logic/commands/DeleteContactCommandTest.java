package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AliasMap;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and
 * RedoCommand) and unit tests for {@code DeleteContactCommand}.
 */
public class DeleteContactCommandTest {

    private final Model model = new ModelManager(
            getTypicalAddressBook(), new Journal(), new UserPrefs(), new AliasMap());

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should delete successfully from an unfiltered list if "
                + "index is valid")
        public void execute_validIndexUnfilteredList_success() {
            Person personToDelete = model.getFilteredPersonList()
                    .get(INDEX_FIRST_PERSON.getZeroBased());

            DeleteContactCommand deleteContactCommand =
                    new DeleteContactCommand(INDEX_FIRST_PERSON);

            String expectedMessage = String.format(
                    DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                    personToDelete, "None"
            );

            ModelManager expectedModel =
                    new ModelManager(model.getAddressBook(),
                            new Journal(),
                            new UserPrefs(),
                            new AliasMap()
                    );
            expectedModel.deletePerson(personToDelete);

            assertCommandSuccess(
                    deleteContactCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should throw CommandException if the index given is "
                + "invalid in the unfiltered list")
        public void execute_invalidIndexUnfilteredList_throwsCommandException() {
            Index outOfBoundIndex = Index.fromOneBased(
                    model.getFilteredPersonList().size() + 1);
            DeleteContactCommand deleteContactCommand =
                    new DeleteContactCommand(outOfBoundIndex);

            assertCommandFailure(
                    deleteContactCommand,
                    model,
                    Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX
            );
        }

        @Test
        @DisplayName("should delete successfully from a filtered list if "
                + "index is valid")
        public void execute_validIndexFilteredList_success() {
            showPersonAtIndex(model, INDEX_FIRST_PERSON);

            Person personToDelete = model.getFilteredPersonList()
                    .get(INDEX_FIRST_PERSON.getZeroBased());
            DeleteContactCommand deleteContactCommand = new DeleteContactCommand(INDEX_FIRST_PERSON);

            String expectedMessage = String.format(
                    DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                    personToDelete,
                    "None"
            );

            Model expectedModel = new ModelManager(model.getAddressBook(),
                    new Journal(),
                    new UserPrefs(),
                    new AliasMap()
            );
            expectedModel.deletePerson(personToDelete);
            showNoPerson(expectedModel);

            assertCommandSuccess(
                    deleteContactCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should throw CommandException if index is invalid in a "
                + "filtered list")
        public void execute_invalidIndexFilteredList_throwsCommandException() {
            showPersonAtIndex(model, INDEX_FIRST_PERSON);

            Index outOfBoundIndex = INDEX_SECOND_PERSON;
            // ensures that outOfBoundIndex is still in bounds of address book list
            assertTrue(outOfBoundIndex.getZeroBased()
                    < model.getAddressBook()
                            .getPersonList()
                            .size()
            );

            DeleteContactCommand deleteContactCommand = new DeleteContactCommand(outOfBoundIndex);

            assertCommandFailure(
                    deleteContactCommand,
                    model,
                    Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX
            );
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        private final DeleteContactCommand deleteFirstCommand =
                new DeleteContactCommand(INDEX_FIRST_PERSON);
        private final DeleteContactCommand deleteSecondCommand =
                new DeleteContactCommand(INDEX_SECOND_PERSON);

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
        }

        @Test
        @DisplayName("should return true if same values")
        public void equals_sameValues_true() {
            DeleteContactCommand deleteFirstCommandCopy =
                    new DeleteContactCommand(INDEX_FIRST_PERSON);
            assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
        }

        @Test
        @DisplayName("should return false if different type")
        public void equals_differentType_false() {
            assertFalse(deleteFirstCommand.equals(1));
        }

        @Test
        @DisplayName("should return false if null")
        public void equals_null_false() {
            assertFalse(deleteFirstCommand.equals(null));
        }

        @Test
        @DisplayName("should return false if different person")
        public void equals_differentPerson_false() {
            assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
        }
    }
}
