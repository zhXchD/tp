package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AddressTest {

    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("should throw NullPointerException if null is passed in")
        public void constructor_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    new Address(null));
        }

        @Test
        @DisplayName("should throw IllegalArgumentException if address is "
                + "invalid")
        public void constructor_invalidAddress_throwsIllegalArgumentException() {
            String invalidAddress = "";
            assertThrows(IllegalArgumentException.class, () ->
                    new Address(invalidAddress));
        }
    }

    @Nested
    @DisplayName("isValidAddress method")
    class IsValidAddress {
        @Test
        @DisplayName("should throw NullPointerException if value is null")
        public void isValidAddress_null_throwsNullPointerException() {
            // null address
            assertThrows(NullPointerException.class, () ->
                    Address.isValidAddress(null));
        }

        @Test
        @DisplayName("should return false if address is invalid")
        public void isValidAddress_invalidAddress_false() {
            assertFalse(Address.isValidAddress("")); // empty string
            assertFalse(Address.isValidAddress(" ")); // spaces only
        }

        @Test
        @DisplayName("should return true if address is valid")
        public void isValidAddress_validAddress_true() {
            assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
            assertTrue(Address.isValidAddress("-")); // one character
            assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; "
                    + "San Francisco CA 2349879; USA")); // long address
        }
    }
}
