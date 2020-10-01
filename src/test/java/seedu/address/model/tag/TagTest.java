package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TagTest {

    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("should throw NullPointerException if tag is null")
        public void constructor_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new Tag(null));
        }

        @Test
        @DisplayName("should throw IllegalArgumentException if tag is invalid")
        public void constructor_invalidTagName_throwsIllegalArgumentException() {
            String invalidTagName = "";
            assertThrows(IllegalArgumentException.class, () ->
                    new Tag(invalidTagName));
        }
    }

    @Nested
    @DisplayName("isValidTagName method")
    class IsValidTagName {
        @Test
        @DisplayName("should throw NullPointerException if tag is null")
        public void isValidTagName() {
            assertThrows(NullPointerException.class, () ->
                    Tag.isValidTagName(null));
        }
    }
}
