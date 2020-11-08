package seedu.address.model.journal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Nested
    @DisplayName("constructor")
    public class Constructor {

        private final Description sampleDescription =
                new Description("Team meeting");

        @Test
        @DisplayName("Should create an instance of description")
        public void constructor_success_createDescription() {
            Description description = new Description("Team meeting");
            assertEquals("Team meeting", description.toString());
            assertEquals(sampleDescription, description);
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

