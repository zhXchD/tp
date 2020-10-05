package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class FileUtilTest {

    @Nested
    @DisplayName("isValidPath method")
    class IsValidPath {
        @Test
        @DisplayName("should return true if path is valid")
        public void isValidPath_validPath_true() {
            assertTrue(FileUtil.isValidPath("valid/file/path"));
        }

        @Test
        @DisplayName("should return false if path is invalid")
        public void isValidPath_invalidPath_false() {
            assertFalse(FileUtil.isValidPath("a\0"));
        }

        @Test
        @DisplayName("should throw NullPointerException if argument is null")
        public void isValidPath_null_throwNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    FileUtil.isValidPath(null));
        }
    }
}
