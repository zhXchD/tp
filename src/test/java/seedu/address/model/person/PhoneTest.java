package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("should throw NullPointerException if phone is null")
        public void constructor_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new Phone(null));
        }

        @Test
        @DisplayName("should throw IllegalArgumentException if phone is "
                + "invalid")
        public void constructor_invalidPhone_throwsIllegalArgumentException() {
            String invalidPhone = "";
            assertThrows(IllegalArgumentException.class, () ->
                    new Phone(invalidPhone));
        }
    }

    @Nested
    @DisplayName("isValidPhone method")
    class IsValidPhone {
        @Test
        @DisplayName("should throw NullPointerException if phone is null")
        public void isValidPhone_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    Phone.isValidPhone(null));
        }

        @Test
        @DisplayName("should return false if phone is invalid")
        public void isValidPhone_invalidPhone_false() {
            assertFalse(Phone.isValidPhone("")); // empty string
            assertFalse(Phone.isValidPhone(" ")); // spaces only
            assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
            assertFalse(Phone.isValidPhone("phone")); // non-numeric
            assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
            assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits
            assertFalse(Phone.isValidPhone("124293842033123")); // long phone

        }

        @Test
        @DisplayName("should return true if phone is valid")
        public void isValidPhone_validPhone_true() {
            // valid phone numbers
            assertTrue(Phone.isValidPhone("93121534"));
            assertTrue(Phone.isValidPhone("+6583817459"));
            assertTrue(Phone.isValidPhone("65850030"));
            assertTrue(Phone.isValidPhone("81765147"));
        }
    }
}
