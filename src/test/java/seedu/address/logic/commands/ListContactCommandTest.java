package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.AliasMap;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * ListContactCommand.
 */
public class ListContactCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new Journal(), new UserPrefs(), new AliasMap());
        expectedModel = new ModelManager(model.getAddressBook(), new Journal(), new UserPrefs(), new AliasMap());
    }

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should show same list if list is not filtered")
        public void execute_listIsNotFiltered_showsSameList() {
            assertCommandSuccess(
                    new ListContactCommand(),
                    model,
                    ListContactCommand.MESSAGE_SUCCESS,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should show full list if list is filtered")
        public void execute_listIsFiltered_showsEverything() {
            showPersonAtIndex(model, INDEX_FIRST_PERSON);
            assertCommandSuccess(
                    new ListContactCommand(),
                    model,
                    ListContactCommand.MESSAGE_SUCCESS,
                    expectedModel
            );
        }
    }
}
