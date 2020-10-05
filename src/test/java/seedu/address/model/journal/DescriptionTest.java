package seedu.address.model.journal;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_thrownullpointerexception() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }
}

