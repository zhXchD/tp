package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class JournalTest {

    @Nested
    @DisplayName("constructor")
    class Constructor {
        @Test
        @DisplayName("Should create an empty entry list")
        public void constructor_create_emptylist() {
            assertEquals(Collections.emptyList(), new Journal().getEntryList());
        }
    }
}
