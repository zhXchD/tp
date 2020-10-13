package seedu.address.model.journal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;

public class DateTest {

    @Nested
    @DisplayName("constructor")

    class Constructor {

        private final Date expectedDate = new Date(LocalDateTime.of(2020, 12, 20, 12, 0));

        @Test
        @DisplayName("Should throw NullPointerException for null input")
        public void constructor_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new Address(null));
        }

        @Test
        @DisplayName("Create Date instance given a local date time")
        public void constructor_create_fromLocalDateTime() {
            LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 20, 12, 0);
            assertEquals(expectedDate, new Date(localDateTime));
        }

        @Test
        @DisplayName("Create Date instance given a string")
        public void constructor_create_fromString() {
            assertEquals(expectedDate, new Date("2020-12-20 12:00"));
        }
    }

    //TODO: Modify after changing the definition of the valid date

    @Nested
    @DisplayName("isValidDate")
    class IsValidDate {
        @Test
        @DisplayName("Should throw NullPointerException for null input")
        public void isValidDate_null_throwNullPointerException() {
            //null date
            assertThrows(NullPointerException.class, () -> Date.isValidDate(null));
        }

        @Test
        @DisplayName("Should return true when enter valid date")
        public void isValidDate_valid_returnTrue() {
            //valid date
            assertTrue(Date.isValidDate("2011-12-03 10:15"));
            assertTrue(Date.isValidDate("2020-12-29 10:15"));
        }

        @Test
        @DisplayName("Should return false when enter invalid date")
        public void isValidDate_invalid_returnFalse() {
            //invalid date
            assertFalse(Date.isValidDate("2020 12 29 16:00"));
            assertFalse(Date.isValidDate("2020 12 20-19:00"));
        }
    }

    @Nested
    @DisplayName("toString")
    class ToString {
        @Test
        @DisplayName("Should return the string representation of a Date")
        public void toString_date_stringRepresentation() {
            Date date = new Date("2020-12-29 10:15");
            assertEquals("2020-12-29 10:15", date.toString());
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        @Test
        @DisplayName("Should return true for the same object")
        public void equals_sameObj_true() {
            Date testDate = new Date("2020-12-29 10:15");
            assertTrue(testDate.equals(testDate));
        }
    }
}
