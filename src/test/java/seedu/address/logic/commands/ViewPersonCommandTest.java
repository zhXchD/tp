package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

class ViewPersonCommandTest {
    @Nested
    @DisplayName("equals method")
    class Equals {
        private final ViewPersonCommand firstCommand = new ViewPersonCommand(Index.fromZeroBased(0));
        private final ViewPersonCommand secondCommand = new ViewPersonCommand(Index.fromZeroBased(1));

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(firstCommand.equals(firstCommand));
        }

        @Test
        @DisplayName("should return true if same values")
        public void equals_sameValues_true() {
            ViewPersonCommand firstCommandCopy = new ViewPersonCommand(Index.fromZeroBased(0));
            assertTrue(firstCommand.equals(firstCommandCopy));
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
        @DisplayName("should return false if different index")
        public void equals_differentPerson_false() {
            assertFalse(firstCommand.equals(secondCommand));
        }
    }
}
