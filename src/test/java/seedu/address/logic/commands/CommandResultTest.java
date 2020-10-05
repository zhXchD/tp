package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CommandResultTest {
    private final CommandResult commandResult = new CommandResult("feedback");

    @Nested
    @DisplayName("equals method")
    class Equals {
        @Test
        @DisplayName("should return true for the same values")
        public void equals_sameValues_true() {
            assertTrue(commandResult.equals(new CommandResult("feedback")));
            assertTrue(
                    commandResult.equals(
                            new CommandResult(
                                    "feedback",
                                    false,
                                    false
                            )
                    )
            );
        }

        @Test
        @DisplayName("should return true for same object")
        public void equals_sameObject_true() {
            assertTrue(commandResult.equals(commandResult));
        }

        @Test
        @DisplayName("should return false for null")
        public void equals_null_false() {
            assertFalse(commandResult.equals(null));
        }

        @Test
        @DisplayName("should return false for different type object")
        public void equals_differentType_false() {
            assertFalse(commandResult.equals(0.5f));
        }

        @Test
        @DisplayName("should return false for command result with different "
                + "values")
        public void equals_differentValues_false() {
            assertFalse(commandResult.equals(new CommandResult("different")));
        }

        @Test
        @DisplayName("should return false for different help value")
        public void equals_differentHelpValue_false() {
            assertFalse(
                    commandResult.equals(
                            new CommandResult(
                                    "feedback",
                                    true,
                                    false
                            )
                    )
            );
        }

        @Test
        @DisplayName("should return false for different exit value")
        public void equals_differentExitValue_false() {
            assertFalse(
                    commandResult.equals(
                            new CommandResult(
                                    "feedback",
                                    false,
                                    true
                            )
                    )
            );
        }
    }

    @Nested
    @DisplayName("hashCode method")
    class HashCode {
        @Test
        @DisplayName("should return same hashcode if values are same")
        public void hashCode_sameValues_sameHashCode() {
            assertEquals(
                    commandResult.hashCode(),
                    new CommandResult("feedback").hashCode()
            );
        }

        @Test
        @DisplayName("should return different hashcode if values are different")
        public void hashCode_differentValues_differentHashCode() {
            assertNotEquals(
                    commandResult.hashCode(),
                    new CommandResult("different").hashCode()
            );
        }

        @Test
        @DisplayName("should return different hashcode if help values are "
                + "different")
        public void hashCode_differentHelpValue_differentHashCode() {
            assertNotEquals(
                    commandResult.hashCode(),
                    new CommandResult("feedback", true, false).hashCode()
            );
        }

        @Test
        @DisplayName("should return different hashcode if exit values are "
                + "different")
        public void hashCode_differentExitValue_differentHashCode() {
            assertNotEquals(
                    commandResult.hashCode(),
                    new CommandResult("feedback", false, true).hashCode()
            );
        }
    }
}
