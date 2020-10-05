package seedu.address.model.journal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void isValidDate() {

        //TODO: Modify after changing the definition of the valid date

        //null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));
        //valid date
        assertTrue(Date.isValidDate("2011-12-03T10:15:30"));
        assertTrue(Date.isValidDate("2020-12-29T10:15:30"));

        //invalid date
        assertFalse(Date.isValidDate("2020 12 29 16:00"));
        assertFalse(Date.isValidDate("2020-12-20 19:00"));
    }
}
