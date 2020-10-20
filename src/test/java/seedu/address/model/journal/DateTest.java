package seedu.address.model.journal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DateTest {

    @Nested
    @DisplayName("constructor")
    class Constructor {
        private final Date expectedDate =
                new Date(LocalDateTime.of(2020, 12, 20, 12, 0));

        @Test
        @DisplayName("Should create Date instance at current time if date is "
                + "empty string")
        public void constructor_emptyString_dateObjectAtCurrentTime() {
            assertEquals(new Date(LocalDateTime.now()),
                    new Date(""));
        }

        @Test
        @DisplayName("Should create Date instance at current time if date is "
                + "null")
        public void constructor_null_dateObjectAtCurrentTime() {
            assertEquals(new Date(LocalDateTime.now()),
                    new Date((String) null));
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
    @DisplayName("isValidDate method")
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
    @DisplayName("isSameDate method")
    class IsSameDate {
        private final Date date = new Date("2020-12-22 10:14");

        @Test
        @DisplayName("should return true if date is same despite time")
        public void isSameDate_sameDate_true() {
            Date differentDate = new Date("2020-12-22 23:59");
            assertTrue(date.isSameDate(differentDate));
        }

        @Test
        @DisplayName("should return false if date is different")
        public void isSameDate_differentDate_false() {
            Date differentDate = new Date("2020-12-23 10:14");
            assertFalse(date.isSameDate(differentDate));
        }
    }

    @Nested
    @DisplayName("getDateString method")
    class GetDateString {
        @Test
        @DisplayName("should return the string representation of just the date")
        public void getDateString() {
            String expectedString = "2020-04-13";
            assertEquals(
                    expectedString,
                    new Date("2020-04-13 23:22").getDateString()
            );
        }
    }

    @Nested
    @DisplayName("toString method")
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
        private final Date date = new Date("2020-12-22 10:14");

        @Test
        @DisplayName("Should return true for the same object")
        public void equals_sameObj_true() {
            assertTrue(date.equals(date));
        }

        @Test
        @DisplayName("should return false if different date")
        public void equals_sameDate_true() {
            Date differentDate = new Date("2020-12-22 10:14");
            assertTrue(date.equals(differentDate));
        }

        @Test
        @DisplayName("should return false if different date")
        public void equals_differentDate_false() {
            Date differentDate = new Date("2020-11-13 10:10");
            assertFalse(date.equals(differentDate));
        }

        @Test
        @DisplayName("should return false if different object")
        public void equals_differentType_false() {
            assertFalse(date.equals(3));
        }

        @Test
        @DisplayName("should return false if null")
        public void equals_null_false() {
            assertFalse(date.equals(null));
        }
    }
}
