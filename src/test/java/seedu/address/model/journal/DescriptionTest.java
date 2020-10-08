package seedu.address.model.journal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Nested
    @DisplayName("constructor")
    public class Constructor {

        private Description sampleDescription = new Description("Team meeting");

        @Test
        @DisplayName("Should throw nullpointerexception for null input")
        public void constructor_null_thrownullpointerexception() {
            assertThrows(NullPointerException.class, () -> new Description(null));
        }

        @Test
        @DisplayName("Should create an instance of description")
        public void constructor_success_createDescription() {
            Description description = new Description("Team meeting");
            assertEquals("Team meeting", description.toString());
            assertEquals(sampleDescription, description);
        }
    }

    @Nested
    @DisplayName("isValidDescription")
    public class IsValidDescription {
        @Test
        @DisplayName("Should throw NullPointerException for null input")
        public void isValidDescription_null_throwNullPointerException() {
            assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));
        }

        @Test
        @DisplayName("Should return false for empty string")
        public void isValidDescription_empty_false() {
            assertFalse(Description.isValidDescription(""));
            assertFalse(Description.isValidDescription(" "));
        }

        @Test
        @DisplayName("Should return false if the input do not start with letter")
        public void isValidDescription_notLetter_false() {
            assertFalse(Description.isValidDescription("1team meeting"));
            assertFalse(Description.isValidDescription(".interview with google"));
        }

        @Test
        @DisplayName("Should return true if the input is valid")
        public void isValidDescription_valid_true() {
            assertTrue(Description.isValidDescription("team meeting"));
            assertTrue(Description.isValidDescription("interview with google"));
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        @Test
        @DisplayName("Should return true for the same object")
        public void equals_sameObj_true() {
            Description testDescription = new Description("interview with google");
            assertTrue(testDescription.equals(testDescription));
        }
    }
}

