package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.AliasMap;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code FindContactCommandTest}.
 */
class FindContactCommandTest {
    private final Model model = new ModelManager(
            getTypicalAddressBook(), new Journal(), new UserPrefs(), new AliasMap());
    private final Model expectedModel = new ModelManager(
            getTypicalAddressBook(), new Journal(), new UserPrefs(), new AliasMap());
    @Nested
    @DisplayName("equals method")
    class Equals {
        private final Predicate<Person> firstPredicate =
            person -> person.getName().fullName.contains("first");
        private final Predicate<Person> secondPredicate =
            person -> person.getName().fullName.contains("second");

        private final FindContactCommand findFirstCommand =
                new FindContactCommand(firstPredicate);
        private final FindContactCommand findSecondCommand =
                new FindContactCommand(secondPredicate);

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(findFirstCommand.equals(findFirstCommand));
        }

        @Test
        @DisplayName("should return true if same values")
        public void equals_sameValues_true() {
            FindContactCommand findFirstCommandCopy = new FindContactCommand(firstPredicate);
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

    @Test
    @DisplayName("should find multiple persons with multiple keywords")
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage =
                String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        Predicate<Person> predicate = person -> person.getName().fullName.contains("Kurz");
        FindContactCommand command = new FindContactCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(
                command,
                model,
                expectedMessage,
                expectedModel
        );
        assertEquals(
                Arrays.asList(CARL),
                model.getFilteredPersonList()
        );
    }
}
