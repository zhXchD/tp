package seedu.address.model.journal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_DEFAULT;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_DIFF_DESCRIPTION;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_DIFF_TITLE;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.journal.exceptions.DuplicateEntryException;
import seedu.address.model.journal.exceptions.EntryNotFoundException;
import seedu.address.testutil.EntryBuilder;

public class UniqueEntryListTest {
    //TODO: Update UniqueEntryTest

    private final UniqueEntryList list = new UniqueEntryList();

    @Nested
    @DisplayName("contains method")
    class Contains {

        @Test
        @DisplayName("Throw NullPointerException when pass in an null entry")
        void contains_nullInput_throwNullPointerException() {
            assertThrows(NullPointerException.class, () -> list.contains(null));
        }

        @Test
        @DisplayName("Return true when the entry is in the list")
        void contains_containsEntry_returnTrue() {
            UniqueEntryList modifiedList = list;
            modifiedList.add(TEST_ENTRY_DEFAULT);
            assertTrue(modifiedList.contains(TEST_ENTRY_DEFAULT));
        }

        @Test
        @DisplayName("Return false when the entry is not in the list")
        void contains_notInList_returnFalse() {
            assertFalse(list.contains(TEST_ENTRY_DIFF_DESCRIPTION));
        }
    }

    @Nested
    @DisplayName("asUnmodifiableList method")
    class AsUnmodifiableList {

        @Test
        @DisplayName("Should throw unsupportedOperationException when modify the list")
        void asUnmodifiableList_modify_throwUnsupportedOpertationException() {
            assertThrows(UnsupportedOperationException.class, () ->
                    list.asUnmodifiableObservableList().add(TEST_ENTRY_DEFAULT));
        }
    }

    @Nested
    @DisplayName("add method")
    class Add {

        @Test
        @DisplayName("Should throw DuplicateEntryException if add a duplicate entry")
        void add_addDuplicate_throwDuplicateEntryException() {
            assertThrows(DuplicateEntryException.class, () -> {
                UniqueEntryList list = new UniqueEntryList();
                list.add(TEST_ENTRY_DEFAULT);
                list.add(TEST_ENTRY_DEFAULT);
            });
        }

        @Test
        @DisplayName("Should throw NullPointerException if try to add a null")
        void add_null_throwNullPointerException() {
            assertThrows(NullPointerException.class, () -> list.add(null));
        }
    }

    @Nested
    @DisplayName("remove method")
    class Remove {
        @Test
        @DisplayName("Should throw NullPointerExeption if try to remove a null")
        void remove_null_throwNullPointerException() {
            assertThrows(NullPointerException.class, () -> list.remove(null));
        }

        @Test
        @DisplayName("Should throw EntryNotFoundException if the entry is not in the list")
        void remove_notInList_throwEntryNotFoundException() {
            assertThrows(EntryNotFoundException.class, () -> list.remove(
                    TEST_ENTRY_DEFAULT));
        }

        @Test
        @DisplayName("Should remove the entry if the entry is in the list")
        void remove_inList_removeEntry() {
            UniqueEntryList expectedList = new UniqueEntryList();
            list.add(TEST_ENTRY_DEFAULT);
            list.remove(TEST_ENTRY_DEFAULT);
            assertEquals(expectedList, list);
        }
    }

    @Nested
    @DisplayName("setEntries method")
    class SetEntries {
        @Test
        @DisplayName("throw nullPointerException if pass in a null list")
        void setEntries_null_throwNullPointerException() {
            assertThrows(NullPointerException.class, () -> list.setEntries(null));
        }

        @Test
        @DisplayName("set entry according to the list given")
        void setEntries_validList_setEntriesSuccess() {
            List<Entry> testList = new LinkedList<>();
            testList.add(TEST_ENTRY_DEFAULT);
            testList.add(TEST_ENTRY_DIFF_TITLE);
            list.setEntries(testList);
            UniqueEntryList expectedList = new UniqueEntryList();
            expectedList.add(TEST_ENTRY_DEFAULT);
            expectedList.add(TEST_ENTRY_DIFF_TITLE);
            assertEquals(expectedList, list);
        }
    }

    @Nested
    @DisplayName("iterator method")
    class Iterator {
        @Test
        @DisplayName("Return a interator contains all element in the lsit")
        void iterator_contains_allElementsInList() {
            list.add(TEST_ENTRY_DEFAULT);
            list.add(TEST_ENTRY_DIFF_DESCRIPTION);
            for (java.util.Iterator<Entry> it = list.iterator(); it.hasNext(); ) {
                Entry e = it.next();
                assertTrue(list.contains(e));
            }
        }
    }

    @Nested
    @DisplayName("Equals method")
    class Equals {

        private final UniqueEntryList list = new UniqueEntryList();

        @Test
        @DisplayName("Should return true when the same object")
        void equals_sameObject_true() {
            assertTrue(list.equals(list));
        }

        @Test
        @DisplayName("Should return true when the content is the same")
        void equals_true_sameContent() {
            list.add(TEST_ENTRY_DEFAULT);
            list.add(TEST_ENTRY_DIFF_DESCRIPTION);
            UniqueEntryList testList = new UniqueEntryList();
            testList.add(TEST_ENTRY_DEFAULT);
            testList.add(TEST_ENTRY_DIFF_DESCRIPTION);
            assertTrue(testList.equals(list));
        }

        @Test
        @DisplayName("SHould return false when the content is not the same")
        void equals_diffContent_false() {
            list.add(TEST_ENTRY_DEFAULT);
            UniqueEntryList testList = new UniqueEntryList();
            assertFalse(testList.equals(list));
        }
    }

    @Nested
    @DisplayName("setEntry method")
    class SetEntry {

        private final UniqueEntryList list = new UniqueEntryList();
        private final Entry sampleEntry1 = new EntryBuilder().build();
        private final Entry sampleEntry2 = new EntryBuilder().withTitle("Dummy title").build();

        @Test
        @DisplayName("Should the replace the entry if the edited entry is valid.")
        void setEntry_success_replaceEntry() {
            list.add(sampleEntry1);
            assertTrue(list.contains(sampleEntry1));
            list.setEntry(sampleEntry1, sampleEntry2);
            assertTrue(list.contains(sampleEntry2));
            assertFalse(list.contains(sampleEntry1));
        }

        @Test
        @DisplayName("Should throw EntryNotFoundException if the target is not in list")
        void setEntry_notInList_throwsEntryNotFoundException() {
            assertThrows(EntryNotFoundException.class, () -> list.setEntry(sampleEntry1, sampleEntry2));
        }

        @Test
        @DisplayName("Should throw DuplicateEntryException if the entry is in the list")
        void setEntry_editedEntryInList_throwsDuplicateEntryException() {
            list.add(sampleEntry1);
            list.add(sampleEntry2);
            assertThrows(DuplicateEntryException.class, () -> list.setEntry(sampleEntry1, sampleEntry2));
        }
    }
}
