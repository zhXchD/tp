package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

class JsonAdaptedTagTest {

    @Nested
    @DisplayName("getTagName method")
    class GetTagName {
        @Test
        @DisplayName("should return name of tag")
        public void getTagName_validTag_name() {
            String expectedName = "expectedName";
            JsonAdaptedTag tag = new JsonAdaptedTag(expectedName);
            assertEquals(expectedName, tag.getTagName());
        }
    }

    @Nested
    @DisplayName("toModelType method")
    class ToModelType {
        @Test
        @DisplayName("should return valid tag if name is valid")
        public void toModelType_validName_returnsTag()
                throws IllegalValueException {
            String validName = "abc123";
            Tag expectedTag = new Tag(validName);
            JsonAdaptedTag tag = new JsonAdaptedTag(validName);
            assertEquals(expectedTag, tag.toModelType());
        }

        @Test
        @DisplayName("should throw IllegalValueException if name is invalid")
        public void toModelType_invalidName_throwsIllegalValueException() {
            String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
            JsonAdaptedTag tag = new JsonAdaptedTag("!@*&^%#");
            assertThrows(
                    IllegalValueException.class,
                    tag::toModelType,
                    expectedMessage
            );
        }
    }
}
