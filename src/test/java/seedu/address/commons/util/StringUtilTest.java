package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class StringUtilTest {

    @Nested
    @DisplayName("isNonZeroUnsignedInteger method")
    class IsNonZeroUnsignedInteger {
        @Test
        @DisplayName("should return false for non numbers")
        public void isNonZeroUnsignedInteger_nonNumber_false() {
            assertFalse(StringUtil.isNonZeroUnsignedInteger("a"));
            assertFalse(StringUtil.isNonZeroUnsignedInteger("aaa"));
        }

        @Test
        @DisplayName("should return false for 0")
        public void isNonZeroUnsignedInteger_zero_false() {
            assertFalse(StringUtil.isNonZeroUnsignedInteger("0"));
        }

        @Test
        @DisplayName("should return false for signed numbers")
        public void isNonZeroUnsignedInteger_signedNumbers_false() {
            assertFalse(StringUtil.isNonZeroUnsignedInteger("-1"));
            assertFalse(StringUtil.isNonZeroUnsignedInteger("+1"));
        }

        @Test
        @DisplayName("should return false for number larger than "
                + "Integer.MAX_VALUE")
        public void isNonZeroUnsignedInteger_numberLargerThanMaxValue_false() {
            assertFalse(StringUtil.isNonZeroUnsignedInteger(
                    Long.toString(Integer.MAX_VALUE + 1)));
        }

        @Test
        @DisplayName("should return false for numbers with white space")
        public void isNonZeroUnsignedInteger_whiteSpaceNumbers_false() {
            assertFalse(StringUtil.isNonZeroUnsignedInteger(" 10 ")); // Leading/trailing spaces
            assertFalse(StringUtil.isNonZeroUnsignedInteger("1 0")); // Spaces in the middle
        }

        @Test
        @DisplayName("should return false for empty strings")
        public void isNonZeroUnsignedInteger_emptyStrings_false() {
            assertFalse(StringUtil.isNonZeroUnsignedInteger("")); // Boundary value
            assertFalse(StringUtil.isNonZeroUnsignedInteger("  "));
        }

        @Test
        @DisplayName("should return true for valid numbers")
        public void isNonZeroUnsignedInteger_validNumbers_true() {
            assertTrue(StringUtil.isNonZeroUnsignedInteger("1")); // Boundary value
            assertTrue(StringUtil.isNonZeroUnsignedInteger("10"));
        }

        @Test
        @DisplayName("should return true for valid numbers with zero prefix")
        public void isNonZeroUnsignedInteger_zeroPrefixValidNumbers_true() {
            assertTrue(StringUtil.isNonZeroUnsignedInteger("01"));
            assertTrue(StringUtil.isNonZeroUnsignedInteger("09123"));
        }
    }

    @Nested
    @DisplayName("containsWordIgnoreCase method")
    class ContainsWordIgnoreCase {
        /*
         * Invalid equivalence partitions for word: null, empty, multiple words
         * Invalid equivalence partitions for sentence: null
         * The four test cases below test one invalid input at a time.
         */

        @Test
        @DisplayName("should throw NullPointerException if word is null")
        public void containsWordIgnoreCase_nullWord_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    StringUtil.containsWordIgnoreCase("typical sentence", null)
            );
        }

        @Test
        @DisplayName("should throw IllegalArgumentException if no word passed"
                + " in")
        public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
            assertThrows(
                    IllegalArgumentException.class,
                    "Word parameter cannot be empty", () ->
                            StringUtil.containsWordIgnoreCase(
                                    "typical sentence", "  "));
        }

        @Test
        @DisplayName("should throw IllegalArgumentException if multiple words"
                + " are passed in")
        public void containsWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
            assertThrows(
                    IllegalArgumentException.class,
                    "Word parameter should be a single word", () ->
                            StringUtil.containsWordIgnoreCase(
                                    "typical sentence",
                                    "aaa BBB"
                            ));
        }

        @Test
        @DisplayName("should throw NullPointerException if sentence is null")
        public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    StringUtil.containsWordIgnoreCase(null, "abc"));
        }

        /*
         * Valid equivalence partitions for word:
         *   - any word
         *   - word containing symbols/numbers
         *   - word with leading/trailing spaces
         *
         * Valid equivalence partitions for sentence:
         *   - empty string
         *   - one word
         *   - multiple words
         *   - sentence with extra spaces
         *
         * Possible scenarios returning true:
         *   - matches first word in sentence
         *   - last word in sentence
         *   - middle word in sentence
         *   - matches multiple words
         *
         * Possible scenarios returning false:
         *   - query word matches part of a sentence word
         *   - sentence word matches part of the query word
         *
         * The test method below tries to verify all above with a reasonably
         * low number of test cases.
         */

        @Test
        @DisplayName("should return false if empty string")
        public void containsWordIgnoreCase_emptyString_false() {
            assertFalse(StringUtil.containsWordIgnoreCase(
                    "",
                    "abc"
            )); // Boundary case
            assertFalse(StringUtil.containsWordIgnoreCase("    ", "123"));
        }

        @Test
        @DisplayName("should return false if partial word included")
        public void containsWordIgnoreCase_partialWord_false() {
            // Matches a partial word only
            assertFalse(StringUtil.containsWordIgnoreCase(
                    "aaa bbb ccc",
                    "bb"
            )); // Sentence word bigger than query word
            assertFalse(StringUtil.containsWordIgnoreCase(
                    "aaa bbb ccc",
                    "bbbb"
            )); // Query word bigger than sentence word
        }

        @Test
        @DisplayName("should return true if word found regardless of case")
        public void containsWordIgnoreCase_differentCaseWord_true() {
            // Matches word in the sentence, different upper/lower case letters
            assertTrue(StringUtil.containsWordIgnoreCase(
                    "aaa bBb ccc",
                    "Bbb"
            )); // First word (boundary case)
            assertTrue(StringUtil.containsWordIgnoreCase(
                    "aaa bBb ccc@1",
                    "CCc@1"
            )); // Last word (boundary case)
            assertTrue(StringUtil.containsWordIgnoreCase(
                    "  AAA   bBb   ccc  ",
                    "aaa"
            )); // Sentence has extra spaces
            assertTrue(StringUtil.containsWordIgnoreCase(
                    "Aaa",
                    "aaa"
            )); // Only one word in sentence (boundary case)
            assertTrue(StringUtil.containsWordIgnoreCase(
                    "aaa bbb ccc",
                    "  ccc  "
            )); // Leading/trailing spaces

            // Matches multiple words in sentence
            assertTrue(StringUtil.containsWordIgnoreCase(
                    "AAA bBb ccc  bbb",
                    "bbB"
            ));
        }
    }

    @Nested
    @DisplayName("getDetails method")
    class GetDetails {
        /*
         * Equivalence Partitions: null, valid throwable object
         */

        @Test
        @DisplayName("should get string representation of exception")
        public void getDetails_exceptionGiven_stringRepresentation() {
            assertTrue(
                    StringUtil.getDetails(
                            new FileNotFoundException("file not found")
                    )
                            .contains("java.io.FileNotFoundException: "
                                    + "file not found"));
        }

        @Test
        @DisplayName("should throw NullPointerException if argument is null")
        public void getDetails_nullGiven_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    StringUtil.getDetails(null));
        }
    }
}
