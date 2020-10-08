package seedu.address.model.journal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.ENTRY_DEFAULT;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.EntryBuilder;

public class EntryTest {

    @Nested
    @DisplayName("miscellaneous operations")
    class Misc {
        @Test
        @DisplayName("should throw UnsupportedOperationException if tags are "
                + "attempted to be removed")
        public void asObservableList_modifyList_throwsUnsupportedOperationException() {
            Entry entry = new EntryBuilder().build();
            assertThrows(UnsupportedOperationException.class, () ->
                    entry.getContactList().remove(0));
        }
    }

    @Nested
    @DisplayName("isSameEntry")
    class IsSameEntry {
        @Test
        @DisplayName("Should be true if the same entry")
        public void isSameEntry_sameEntry_true() {
            assertTrue(ENTRY_DEFAULT.isSameEntry(ENTRY_DEFAULT));
        }

        @Test
        @DisplayName("Should return false if the entry is null")
        public void isSameEntry_null_false() {
            assertFalse(ENTRY_DEFAULT.isSameEntry(null));
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        @Test
        @DisplayName("Should return true if the same instance of entry")
        public void equals_sameInstance_true() {
            assertTrue(ENTRY_DEFAULT.equals(ENTRY_DEFAULT));
        }

        @Test
        @DisplayName("Should return true if the Entry content is the same")
        public void equals_sameContent_true() {
            Entry testEntry = new EntryBuilder().build();
            assertTrue(testEntry.equals(ENTRY_DEFAULT));
        }

        @Test
        @DisplayName("Should return false if the content is differnet")
        public void equals_diffContent_false() {
            Entry testEntry = new EntryBuilder().withDescription("Decide the product").build();
            assertFalse(testEntry.equals(ENTRY_DEFAULT));
        }
    }
}
