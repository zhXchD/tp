package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AppUtilTest {

    @Nested
    @DisplayName("getImage method")
    class GetImage {
        @Test
        @DisplayName("should generate Image object")
        public void getImage_exitingImage() {
            assertNotNull(AppUtil.getImage("/images/address_book_32.png"));
        }

        @Test
        @DisplayName("should throw NullPointerException if image not found")
        public void getImage_nullGiven_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                            AppUtil.getImage(null));
        }
    }

    @Nested
    @DisplayName("checkArgument method")
    class CheckArgument {
        @Test
        @DisplayName("should not do anything if arguments are valid")
        public void checkArgument_true_nothingHappens() {
            AppUtil.checkArgument(true);
            AppUtil.checkArgument(true, "");
        }

        @Test
        @DisplayName("should throw IllegalArgumentException if arguments are "
                + "invalid")
        public void checkArgument_falseWithoutErrorMessage_throwsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () ->
                    AppUtil.checkArgument(false));
        }

        @Test
        @DisplayName(
                "should throw IllegalArgumentException with error message if"
                        + " arguments are invalid")
        public void checkArgument_falseWithErrorMessage_throwsIllegalArgumentException() {
            String errorMessage = "error message";
            assertThrows(IllegalArgumentException.class, errorMessage, () ->
                            AppUtil.checkArgument(false, errorMessage));
        }
    }
}
