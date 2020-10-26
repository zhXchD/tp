package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_NOVEMBER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MOVIE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditJournalEntryCommand.EditEntryDescriptor;
import seedu.address.testutil.EditEntryDescriptorBuilder;

public class EditEntryDescriptorTest {

    @Nested
    @DisplayName("equals method")
    class Equals {
        private EditEntryDescriptor editedMeeting =
                new EditEntryDescriptorBuilder(DESC_MEETING)
                        .withTitle(VALID_TITLE_MOVIE)
                        .build();

        @Test
        @DisplayName("should return true if same values")
        public void equals_sameValues_true() {
            EditEntryDescriptor descriptorWithSameValues =
                    new EditEntryDescriptor(DESC_MEETING);
            assertTrue(DESC_MEETING.equals(descriptorWithSameValues));
        }

        @Test
        @DisplayName("should return true for same object")
        public void equals_sameObject_true() {
            assertTrue(DESC_MEETING.equals(DESC_MEETING));
        }

        @Test
        @DisplayName("should return false for null")
        public void equals_null_false() {
            assertFalse(DESC_MEETING.equals(null));
        }

        @Test
        @DisplayName("should return false for different types")
        public void equals_differentType_false() {
            assertFalse(DESC_MEETING.equals(5));
        }

        @Test
        @DisplayName("should return false for different title")
        public void equals_differentTitle_false() {
            assertFalse(DESC_MEETING.equals(editedMeeting));
        }


        @Test
        @DisplayName("should return false for different description")
        public void equals_differentDescription_false() {
            editedMeeting = new EditEntryDescriptorBuilder(DESC_MEETING)
                    .withDescription(VALID_DESCRIPTION_MOVIE)
                    .build();
            assertFalse(DESC_MEETING.equals(editedMeeting));
        }

        @Test
        @DisplayName("should return false for different date")
        public void equals_differentDate_false() {
            editedMeeting = new EditEntryDescriptorBuilder(DESC_MEETING)
                    .withDate(VALID_DATE_NOVEMBER)
                    .build();
            assertFalse(DESC_MEETING.equals(editedMeeting));
        }

        @Test
        @DisplayName("should return false for different contacts")
        public void equals_differentContacts_false() {
            editedMeeting = new EditEntryDescriptorBuilder(DESC_MEETING)
                    .withContacts(CARL, DANIEL)
                    .build();
            assertFalse(DESC_MEETING.equals(editedMeeting));
        }

        @Test
        @DisplayName("should return false for different tags")
        public void equals_differentTags_false() {
            editedMeeting = new EditEntryDescriptorBuilder(DESC_MEETING)
                    .withTags(VALID_TAG_HUSBAND)
                    .build();
            assertFalse(DESC_AMY.equals(editedMeeting));
        }

    }
}
