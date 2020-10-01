package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(
            getTypicalAddressBook(), new Journal(), new UserPrefs());
    private final Model expectedModel = new ModelManager(
            getTypicalAddressBook(), new Journal(), new UserPrefs());

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(
                Arrays.asList(userInput.split("\\s+")));
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        private final NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(
                        Collections.singletonList("first"));
        private final NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(
                        Collections.singletonList("second"));

        private final FindCommand findFirstCommand =
                new FindCommand(firstPredicate);
        private final FindCommand findSecondCommand =
                new FindCommand(secondPredicate);

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(findFirstCommand.equals(findFirstCommand));
        }

        @Test
        @DisplayName("should return true if same values")
        public void equals_sameValues_true() {
            FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
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
        @DisplayName("should not find any person without keywords")
        public void execute_zeroKeywords_noPersonFound() {
            String expectedMessage =
                    String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
            NameContainsKeywordsPredicate predicate = preparePredicate(" ");
            FindCommand command = new FindCommand(predicate);
            expectedModel.updateFilteredPersonList(predicate);
            assertCommandSuccess(
                    command,
                    model,
                    expectedMessage,
                    expectedModel
            );
            assertEquals(
                    Collections.emptyList(),
                    model.getFilteredPersonList()
            );
        }

        @Test
        @DisplayName("should find multiple persons with multiple keywords")
        public void execute_multipleKeywords_multiplePersonsFound() {
            String expectedMessage =
                    String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
            NameContainsKeywordsPredicate predicate =
                    preparePredicate("Kurz Elle Kunz");
            FindCommand command = new FindCommand(predicate);
            expectedModel.updateFilteredPersonList(predicate);
            assertCommandSuccess(
                    command,
                    model,
                    expectedMessage,
                    expectedModel
            );
            assertEquals(
                    Arrays.asList(CARL, ELLE, FIONA),
                    model.getFilteredPersonList()
            );
        }
    }
}
