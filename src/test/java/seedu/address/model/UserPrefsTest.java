package seedu.address.model;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Nested
    @DisplayName("setGuiSettings method")
    class SetGuiSettings {
        @Test
        @DisplayName("should throw NullPointerException if gui settings is "
                + "null")
        public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
            UserPrefs userPref = new UserPrefs();
            assertThrows(NullPointerException.class, () ->
                    userPref.setGuiSettings(null));
        }
    }

    @Nested
    @DisplayName("setAddressBookFilePath method")
    class SetAddressBookFilePath {
        @Test
        @DisplayName("should throw NullPointerException if path is null")
        public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
            UserPrefs userPrefs = new UserPrefs();
            assertThrows(NullPointerException.class, () ->
                    userPrefs.setAddressBookFilePath(null));
        }
    }
}
