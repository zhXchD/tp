package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEntries.ENTRY_DEFAULT;
import static seedu.address.testutil.TypicalEntries.getTypicalEntry;
import static seedu.address.testutil.TypicalEntries.getTypicalJournal;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.journal.Entry;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.Assert;
import seedu.address.testutil.EntryBuilder;


public class JournalTest {

    private final Journal journal = new Journal();

    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("Should create an empty entry list")
        public void constructor_create_emptyList() {
            assertEquals(Collections.emptyList(), new Journal().getEntryList());
        }

        @Test
        @DisplayName("Should create journal according to given entry")
        public void constructor_create_givenData() {
            Journal journal = getTypicalJournal();
            List<Entry> entries = getTypicalEntry();

            for (int i = 0; i < journal.getEntryList().size(); i++) {
                assertEquals(entries.get(i), journal.getEntryList().get(i));
            }
        }
    }

    @Nested
    @DisplayName("hasEntry method")
    class HasEntry {
        @Test
        @DisplayName("Should throw null pointer exception when pass null")
        public void hasEntry_throw_nullPointerException() {
            assertThrows(NullPointerException.class, () -> journal.hasEntry(null));
        }

        @Test
        @DisplayName("Should return false when entry is not in the journal")
        public void hasEntry_false_entryNotInList() {
            assertFalse(journal.hasEntry(ENTRY_DEFAULT));
        }

        @Test
        @DisplayName("Should return true when the entry is in the journal")
        public void hasEntry_true_entryInList() {
            assertTrue(getTypicalJournal().hasEntry(ENTRY_DEFAULT));
        }
    }

    @Nested
    @DisplayName("getEntryList method")
    class GetEntryList {
        @Test
        @DisplayName("should throw UnsupportedOperationException")
        public void getEntryList_modifyList_throwsUnsupportedOperationException() {
            Assert.assertThrows(UnsupportedOperationException.class, () ->
                    journal.getEntryList().remove(0));
        }
    }

    @Nested
    @DisplayName("removeAssociateEntry method")
    class RemoveAssociateEntry {
        @Test
        @DisplayName("should throw NullPointerException if pass in a null")
        public void removeAssociateEntry_null_throwNullPointerException() {
            assertThrows(NullPointerException.class, () -> journal.removeAssociateEntry(null));
        }

        @Test
        @DisplayName("should remove the entry that associate with the person")
        public void removeAssociateEntry_success_removeTheAssociateEntry() {
            UniquePersonList contactList = new UniquePersonList();
            contactList.add(ALICE);
            Entry test = new EntryBuilder()
                    .withContacts(
                            contactList
                                    .asUnmodifiableObservableList()
                                    .toArray(new Person[0])
                    )
                    .build();
            journal.addEntry(test);
            assertTrue(journal.hasEntry(test));
            journal.removeAssociateEntry(ALICE);
            assertFalse(journal.hasEntry(test));
        }
    }
}
