package seedu.address.model.journal;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        private final Title title = new Title("Title 1");

        @Test
        @DisplayName("Should throw nullpointerexception for null input")
        public void constructor_null_thrownullpointerexception() {
            assertThrows(NullPointerException.class, () -> new Title(null));
        }

        @Test
        @DisplayName("Should create a title instance given a string")
        public void constructor_success_createsInstance() {
            assertEquals(title, new Title("Title 1"));
            assertEquals("Title 1", new Title("Title 1").toString());
        }
    }

    @Nested
    @DisplayName("isValidDescription")
    public class IsValidTitle {
        @Test
        @DisplayName("Should throw NullPointerException for null input")
        public void isValidTitle_null_throwNullPointerException() {
            assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));
        }

        @Test
        @DisplayName("Should return false for empty string")
        public void isValidTitle_empty_false() {
            assertFalse(Title.isValidTitle(""));
            assertFalse(Title.isValidTitle(" "));
        }

        @Test
        @DisplayName("Should return false if the input do not start with letter")
        public void isValidTitle_notLetter_false() {
            assertFalse(Title.isValidTitle("1team meeting"));
            assertFalse(Title.isValidTitle(".interview with google"));
        }

        @Test
        @DisplayName("Should return true if the input is valid")
        public void isValidTitle_valid_true() {
            assertTrue(Title.isValidTitle("team meeting"));
            assertTrue(Title.isValidTitle("interview with google"));
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        @Test
        @DisplayName("Should return true for the same object")
        public void equals_sameObj_true() {
            Title testTitle = new Title("Title 1");
            assertTrue(testTitle.equals(testTitle));
        }
    }
}

