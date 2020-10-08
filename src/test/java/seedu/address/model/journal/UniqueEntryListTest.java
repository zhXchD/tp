package seedu.address.model.journal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.ENTRY_DEFAULT;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_DIFFTITLE;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_DIFF_DECRIPTION;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.journal.exceptions.DuplicateEntryException;
import seedu.address.model.journal.exceptions.EntryNotFoundException;

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
            modifiedList.add(ENTRY_DEFAULT);
            assertTrue(modifiedList.contains(ENTRY_DEFAULT));
        }

        @Test
        @DisplayName("Return false when the entry is not in the list")
        void contains_notInList_returnFalse() {
            assertFalse(list.contains(TEST_ENTRY_DIFF_DECRIPTION));
        }
    }

    @Nested
    @DisplayName("asUnmodifiableList method")
    class AsUnmodifiableList {

        @Test
        @DisplayName("Should throw unsupportedOperationException when modify the list")
        void asUnmodifiableList_modify_throwUnsupportedOpertationException() {
            assertThrows(UnsupportedOperationException.class, () ->
                    list.asUnmodifiableObservableList().add(ENTRY_DEFAULT));
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
                list.add(ENTRY_DEFAULT);
                list.add(ENTRY_DEFAULT);
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
            assertThrows(EntryNotFoundException.class, () -> list.remove(ENTRY_DEFAULT));
        }

        @Test
        @DisplayName("Should remove the entry if the entry is in the list")
        void remove_inList_removeEntry() {
            UniqueEntryList expectedList = new UniqueEntryList();
            list.add(ENTRY_DEFAULT);
            list.remove(ENTRY_DEFAULT);
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
            testList.add(ENTRY_DEFAULT);
            testList.add(TEST_ENTRY_DIFFTITLE);
            list.setEntries(testList);
            UniqueEntryList expectedList = new UniqueEntryList();
            expectedList.add(ENTRY_DEFAULT);
            expectedList.add(TEST_ENTRY_DIFFTITLE);
            assertEquals(expectedList, list);
        }
    }

    @Nested
    @DisplayName("iterator method")
    class Iterator {
        @Test
        @DisplayName("Return a interator contains all element in the lsit")
        void iterator_contains_allElementsInList() {
            list.add(ENTRY_DEFAULT);
            list.add(TEST_ENTRY_DIFF_DECRIPTION);
            for (java.util.Iterator<Entry> it = list.iterator(); it.hasNext(); ) {
                Entry e = it.next();
                assertTrue(list.contains(e));
            }
        }
    }
}
