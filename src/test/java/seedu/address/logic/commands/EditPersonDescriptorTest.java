package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditContactCommand.EditPersonDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Nested
    @DisplayName("equals method")
    class Equals {
        private EditPersonDescriptor editedAmy =
                new EditPersonDescriptorBuilder(DESC_AMY)
                        .withName(VALID_NAME_BOB)
                        .build();

        @Test
        @DisplayName("should return true if same values")
        public void equals_sameValues_true() {
            EditPersonDescriptor descriptorWithSameValues =
                    new EditPersonDescriptor(DESC_AMY);
            assertTrue(DESC_AMY.equals(descriptorWithSameValues));
        }

        @Test
        @DisplayName("should return true for same object")
        public void equals_sameObject_true() {
            assertTrue(DESC_AMY.equals(DESC_AMY));
        }

        @Test
        @DisplayName("should return false for null")
        public void equals_null_false() {
            assertFalse(DESC_AMY.equals(null));
        }

        @Test
        @DisplayName("should return false for different types")
        public void equals_differentType_false() {
            assertFalse(DESC_AMY.equals(5));
        }

        @Test
        @DisplayName("should return false for different values")
        public void equals_differentValues_false() {
            assertFalse(DESC_AMY.equals(DESC_BOB));
        }

        @Test
        @DisplayName("should return false for different name")
        public void equals_differentName_false() {
            assertFalse(DESC_AMY.equals(editedAmy));
        }

        @Test
        @DisplayName("should return false for different phone")
        public void equals_differentPhone_false() {
            editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withPhone(
                    VALID_PHONE_BOB).build();
            assertFalse(DESC_AMY.equals(editedAmy));
        }

        @Test
        @DisplayName("should return false for different email")
        public void equals_differentEmail_false() {
            editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(
                    VALID_EMAIL_BOB).build();
            assertFalse(DESC_AMY.equals(editedAmy));
        }

        @Test
        @DisplayName("should return false for different address")
        public void equals_differentAddress_false() {
            editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAddress(
                    VALID_ADDRESS_BOB).build();
            assertFalse(DESC_AMY.equals(editedAmy));
        }

        @Test
        @DisplayName("should return false for different tags")
        public void equals_differentTags_false() {
            editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withTags(
                    VALID_TAG_HUSBAND).build();
            assertFalse(DESC_AMY.equals(editedAmy));
        }
    }
}
