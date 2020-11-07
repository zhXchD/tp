package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NameTest {

    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("should throw NullPointerException if null")
        public void constructor_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new Name(null));
        }

        @Test
        @DisplayName("should throw IllegalArgumentException if name is invalid")
        public void constructor_invalidName_throwsIllegalArgumentException() {
            String invalidName = "";
            assertThrows(IllegalArgumentException.class, () ->
                    new Name(invalidName));
        }
    }

    @Nested
    @DisplayName("isValidName method")
    class IsValidName {
        @Test
        @DisplayName("should throw NullPointerException if name is null")
        public void isValidName_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    Name.isValidName(null));
        }

        @Test
        @DisplayName("should return false if name is invalid")
        public void isValidName_invalidName_false() {
            assertFalse(Name.isValidName("")); // empty string
            assertFalse(Name.isValidName(" ")); // spaces only
            assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
            assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        }

        @Test
        @DisplayName("should return true if name is valid")
        public void isValidName_validName_true() {
            assertTrue(Name.isValidName("peter jack")); // alphabets only
            assertTrue(Name.isValidName("12345")); // numbers only
            assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
            assertTrue(Name.isValidName("Capital Tan")); // with capital letters
            assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            Name amy = new Name(VALID_NAME_AMY);
            assertTrue(amy.equals(amy));
        }

        @Test
        @DisplayName("should return true if different case")
        public void equals_differentCase_true() {
            Name amy = new Name(VALID_NAME_AMY);
            Name lowerAmy = new Name(VALID_NAME_AMY.toLowerCase());
            assertTrue(amy.equals(lowerAmy));
        }

        @Test
        @DisplayName("should return false if null")
        public void equals_null_false() {
            Name amy = new Name(VALID_NAME_AMY);
            assertFalse(amy.equals(null));
        }
    }
}
