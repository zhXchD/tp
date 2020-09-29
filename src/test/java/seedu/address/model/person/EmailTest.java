package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class EmailTest {

    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("should throw NullPointerException if null is passed "
                + "into the constructor")
        public void constructor_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new Email(null));
        }

        @Test
        @DisplayName("should throw IllegalArgumentException if email is "
                + "invalid")
        public void constructor_invalidEmail_throwsIllegalArgumentException() {
            String invalidEmail = "";
            assertThrows(IllegalArgumentException.class, () ->
                    new Email(invalidEmail));
        }
    }

    @Nested
    @DisplayName("isValidEmail method")
    class IsValidEmail {
        @Test
        @DisplayName("should throw NullPointerException if email is null")
        public void isValidEmail_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    Email.isValidEmail(null));
        }

        @Test
        @DisplayName("should return false if email is blank")
        public void isValidEmail_blank_false() {
            assertFalse(Email.isValidEmail("")); // empty string
            assertFalse(Email.isValidEmail(" ")); // spaces only
        }

        @Test
        @DisplayName("should return false if parts of email missing")
        public void isValidEmail_missingParts_false() {
            assertFalse(Email.isValidEmail("@example.com")); // missing local part
            assertFalse(Email.isValidEmail("peterjackexample.com")); // missing '@' symbol
            assertFalse(Email.isValidEmail("peterjack@")); // missing domain name
        }

        @Test
        @DisplayName("should return false if parts of email invalid")
        public void isValidEmail_invalidParts_false() {
            assertFalse(Email.isValidEmail("peterjack@-")); // invalid domain name
            assertFalse(Email.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
            assertFalse(Email.isValidEmail("peter jack@example.com")); // spaces in local part
            assertFalse(Email.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
            assertFalse(Email.isValidEmail(" peterjack@example.com")); // leading space
            assertFalse(Email.isValidEmail("peterjack@example.com ")); // trailing space
            assertFalse(Email.isValidEmail("peterjack@@example.com")); // double '@' symbol
            assertFalse(Email.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
            assertFalse(Email.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
            assertFalse(Email.isValidEmail("peterjack@.example.com")); // domain name starts with a period
            assertFalse(Email.isValidEmail("peterjack@example.com.")); // domain name ends with a period
            assertFalse(Email.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
            assertFalse(Email.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        }

        @Test
        @DisplayName("should return true for valid input")
        public void isValidEmail_validInput_true() {
            assertTrue(Email.isValidEmail("PeterJack_1190@example.com"));
            assertTrue(Email.isValidEmail("a@bc")); // minimal
            assertTrue(Email.isValidEmail("test@localhost")); // alphabets only
            assertTrue(Email.isValidEmail("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
            assertTrue(Email.isValidEmail("123@145")); // numeric local part and domain name
            assertTrue(Email.isValidEmail("a1+be!@example1.com")); // mixture of alphanumeric and special characters
            assertTrue(Email.isValidEmail(
                    "peter_jack@very-very-very-long-example.com")); // long domain name
            assertTrue(Email.isValidEmail(
                    "if.you.dream.it_you.can.do.it@example.com")); // long local part
        }
    }
}
