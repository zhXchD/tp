package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class IndexTest {

    @Nested
    @DisplayName("one based index")
    class OneBased {
        @Test
        @DisplayName("should throw IndexOutOfBoundsException if index out of "
                + "bounds")
        public void fromOneBased_invalidIndex_throwIndexOutOfBoundsException() {
            assertThrows(IndexOutOfBoundsException.class, () ->
                    Index.fromOneBased(0));
            assertThrows(IndexOutOfBoundsException.class, () ->
                    Index.fromOneBased(-4));
        }

        @Test
        @DisplayName("should be equal if same base used")
        public void fromOneBased_sameBase_sameInteger() {
            assertEquals(1, Index.fromOneBased(1).getOneBased());
            assertEquals(5, Index.fromOneBased(5).getOneBased());
        }

        @Test
        @DisplayName("should convert from one-based index to zero-based index")
        public void fromOneBased_convert_oneLessInteger() {
            assertEquals(0, Index.fromOneBased(1).getZeroBased());
            assertEquals(4, Index.fromOneBased(5).getZeroBased());
        }
    }

    @Nested
    @DisplayName("zero based index")
    class ZeroBased {
        @Test
        @DisplayName("should throw IndexOutOfBoundsException if index out of "
                + "bounds")
        public void fromZeroBased_invalidIndex_throwIndexOutOfBoundsException() {
            assertThrows(IndexOutOfBoundsException.class, () ->
                    Index.fromZeroBased(-1));
            assertThrows(IndexOutOfBoundsException.class, () ->
                    Index.fromOneBased(-4));
        }

        @Test
        @DisplayName("should be equal if same base used")
        public void fromZeroBased_sameBase_sameInteger() {
            assertEquals(1, Index.fromZeroBased(1).getZeroBased());
            assertEquals(5, Index.fromZeroBased(5).getZeroBased());
        }

        @Test
        @DisplayName("should convert from zero-based index to one-based index")
        public void fromZeroBased_convert_oneLessInteger() {
            assertEquals(2, Index.fromZeroBased(1).getOneBased());
            assertEquals(6, Index.fromZeroBased(5).getOneBased());
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        private final Index fifthPersonIndex = Index.fromOneBased(5);
        @Test
        @DisplayName("should return true for same value")
        public void equals_sameValue_true() {
            assertTrue(fifthPersonIndex.equals(Index.fromOneBased(5)));
            assertTrue(fifthPersonIndex.equals(Index.fromZeroBased(4)));
        }

        @Test
        @DisplayName("should return false if object is not Index")
        public void equals_notIndex_false() {
            assertFalse(fifthPersonIndex.equals(5.0f));
        }

        @Test
        @DisplayName("should return false if different index")
        public void equals_differentIndex_false() {
            assertFalse(fifthPersonIndex.equals(Index.fromOneBased(1)));
            assertFalse(fifthPersonIndex.equals(Index.fromOneBased(3)));
        }

        @Test
        @DisplayName("should return false if object is null")
        public void equals_null_false() {
            assertFalse(fifthPersonIndex.equals(null));
        }

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(fifthPersonIndex.equals(fifthPersonIndex));
        }
    }
}
