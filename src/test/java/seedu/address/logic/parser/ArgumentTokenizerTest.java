package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ArgumentTokenizerTest {

    private final Prefix unknownPrefix = new Prefix("--u");
    private final Prefix pSlash = new Prefix("p/");
    private final Prefix dashT = new Prefix("-t");
    private final Prefix hatQ = new Prefix("^Q");

    @Nested
    @DisplayName("tokenize method")
    class Tokenize {

        private void assertPreamblePresent(
                ArgumentMultimap argMultimap,
                String expectedPreamble
        ) {
            assertEquals(expectedPreamble, argMultimap.getPreamble());
        }

        private void assertPreambleEmpty(ArgumentMultimap argMultimap) {
            assertTrue(argMultimap.getPreamble().isEmpty());
        }

        /**
         * Asserts all the arguments in {@code argMultimap} with {@code prefix}
         * match the {@code expectedValues} and only the last value is returned
         * upon calling {@code ArgumentMultimap#getValue(Prefix)}.
         */
        private void assertArgumentPresent(
                ArgumentMultimap argMultimap,
                Prefix prefix,
                String... expectedValues
        ) {

            // Verify the last value is returned
            assertEquals(
                    expectedValues[expectedValues.length - 1],
                    argMultimap.getValue(prefix).get()
            );

            // Verify the number of values returned is as expected
            assertEquals(
                    expectedValues.length,
                    argMultimap.getAllValues(prefix).size()
            );

            // Verify all values returned are as expected and in order
            for (int i = 0; i < expectedValues.length; i++) {
                assertEquals(
                        expectedValues[i],
                        argMultimap.getAllValues(prefix).get(i)
                );
            }
        }

        private void assertArgumentAbsent(
                ArgumentMultimap argMultimap,
                Prefix prefix
        ) {
            assertFalse(argMultimap.getValue(prefix).isPresent());
        }

        @Test
        @DisplayName("should not return any prefixes")
        public void tokenize_emptyArgsString_noValues() {
            String argsString = "  ";
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(argsString, pSlash);

            assertPreambleEmpty(argMultimap);
            assertArgumentAbsent(argMultimap, pSlash);
        }

        @Test
        @DisplayName("should return everything in string as preamble")
        public void tokenize_noPrefixes_allTakenAsPreamble() {
            String argsString =
                    "  some random string /t tag with leading and trailing spaces ";
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(argsString);

            // Same string expected as preamble, but leading/trailing spaces should be trimmed
            assertPreamblePresent(argMultimap, argsString.trim());
        }

        @Test
        @DisplayName("should return one prefix and preamble")
        public void tokenize_oneArgument() {
            // Preamble present
            String argsString = "  Some preamble string p/ Argument value ";
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(argsString, pSlash);
            assertPreamblePresent(argMultimap, "Some preamble string");
            assertArgumentPresent(argMultimap, pSlash, "Argument value");

            // No preamble
            argsString = " p/   Argument value ";
            argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
            assertPreambleEmpty(argMultimap);
            assertArgumentPresent(argMultimap, pSlash, "Argument value");
        }

        @Test
        @DisplayName("should return multiple prefixes")
        public void tokenize_multipleArguments() {
            // Only two arguments are present
            String argsString =
                    "SomePreambleString -t dashT-Value p/pSlash value";
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
            assertPreamblePresent(argMultimap, "SomePreambleString");
            assertArgumentPresent(argMultimap, pSlash, "pSlash value");
            assertArgumentPresent(argMultimap, dashT, "dashT-Value");
            assertArgumentAbsent(argMultimap, hatQ);

            // All three arguments are present
            argsString =
                    "Different Preamble String ^Q111 -t dashT-Value p/pSlash value";
            argMultimap =
                    ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
            assertPreamblePresent(argMultimap, "Different Preamble String");
            assertArgumentPresent(argMultimap, pSlash, "pSlash value");
            assertArgumentPresent(argMultimap, dashT, "dashT-Value");
            assertArgumentPresent(argMultimap, hatQ, "111");

            /* Also covers: Reusing of the tokenizer multiple times */

            // Reuse tokenizer on an empty string to ensure ArgumentMultimap is correctly reset
            // (i.e. no stale values from the previous tokenizing remain)
            argsString = "";
            argMultimap =
                    ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
            assertPreambleEmpty(argMultimap);
            assertArgumentAbsent(argMultimap, pSlash);

            /* Also covers: testing for prefixes not specified as a prefix */

            // Prefixes not previously given to the tokenizer should not return any values
            argsString = unknownPrefix + "some value";
            argMultimap =
                    ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
            assertArgumentAbsent(argMultimap, unknownPrefix);
            assertPreamblePresent(
                    argMultimap,
                    argsString
            ); // Unknown prefix is taken as part of preamble
        }

        @Test
        public void tokenize_multipleArgumentsWithRepeats() {
            // Two arguments repeated, some have empty values
            String argsString =
                    "SomePreambleString -t dashT-Value ^Q ^Q -t another dashT value p/ pSlash value -t";
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
            assertPreamblePresent(argMultimap, "SomePreambleString");
            assertArgumentPresent(argMultimap, pSlash, "pSlash value");
            assertArgumentPresent(
                    argMultimap,
                    dashT,
                    "dashT-Value",
                    "another dashT value",
                    ""
            );
            assertArgumentPresent(argMultimap, hatQ, "", "");
        }

        @Test
        @DisplayName("should not return joined arguments as prefixes")
        public void tokenize_multipleArgumentsJoined() {
            String argsString = "SomePreambleStringp/ pSlash joined-tjoined "
                    + "-t not joined^Qjoined";
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
            assertPreamblePresent(
                    argMultimap,
                    "SomePreambleStringp/ pSlash joined-tjoined"
            );
            assertArgumentAbsent(argMultimap, pSlash);
            assertArgumentPresent(argMultimap, dashT, "not joined^Qjoined");
            assertArgumentAbsent(argMultimap, hatQ);
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        private final Prefix aaa = new Prefix("aaa");

        @Test
        @DisplayName("should return true for same object")
        public void equals_sameObject_true() {
            assertEquals(aaa, aaa);
        }

        @Test
        @DisplayName("should return true for same values")
        public void equals_sameValue_true() {
            assertEquals(aaa, new Prefix("aaa"));
        }

        @Test
        @DisplayName("should return false for different types")
        public void equals_differentType_false() {
            assertNotEquals(aaa, "aaa");
        }

        @Test
        @DisplayName("should return false for different values")
        public void equals_differentValue_false() {
            assertNotEquals(aaa, new Prefix("aab"));
        }
    }
}
