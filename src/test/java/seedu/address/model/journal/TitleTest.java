package seedu.address.model.journal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TitleTest {

    @Nested
    @DisplayName("constructor")
    public class Constructor {
        @Test
        @DisplayName("Should throw nullpointerexception for null input")
        public void constructor_null_thrownullpointerexception() {
            assertThrows(NullPointerException.class, () -> new Title(null));
        }
    }

    @Nested
    @DisplayName("isValidDescription")
    public class IsValidTitle {
        @Test
        @DisplayName("Should throw NullPointerException for null input")
        public void isValidDescription_null_throwNullPointerException() {
            assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));
        }

        @Test
        @DisplayName("Should return false for empty string")
        public void isValidDescription_empty_false() {
            assertFalse(Title.isValidTitle(""));
            assertFalse(Title.isValidTitle(" "));
        }

        @Test
        @DisplayName("Should return false if the input do not start with letter")
        public void isValidDescription_notLetter_false() {
            assertFalse(Title.isValidTitle("1team meeting"));
            assertFalse(Title.isValidTitle(".interview with google"));
        }

        @Test
        @DisplayName("Should return true if the input is valid")
        public void isValidDescription_valid_true() {
            assertTrue(Title.isValidTitle("team meeting"));
            assertTrue(Title.isValidTitle("interview with google"));
        }
    }

}

