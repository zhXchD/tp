package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {

    @Nested
    @DisplayName("equals method")
    class Equals {
        private final List<String> firstPredicateKeywordList =
                Collections.singletonList("first");
        private final List<String> secondPredicateKeywordList =
                Arrays.asList("first", "second");

        private final NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        private final NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(firstPredicate.equals(firstPredicate));
        }

        @Test
        @DisplayName("should return true if same value")
        public void equals_sameValue_true() {
            NameContainsKeywordsPredicate firstPredicateCopy =
                    new NameContainsKeywordsPredicate(firstPredicateKeywordList);
            assertTrue(firstPredicate.equals(firstPredicateCopy));
        }

        @Test
        @DisplayName("should return false if different type")
        public void equals_differentType_false() {
            assertFalse(firstPredicate.equals(1));
        }

        @Test
        @DisplayName("should return false if null")
        public void equals_null_false() {
            assertFalse(firstPredicate.equals(null));
        }

        @Test
        @DisplayName("should return false if different person")
        public void equals_differentPerson_false() {
            assertFalse(firstPredicate.equals(secondPredicate));
        }
    }

    @Nested
    @DisplayName("test method")
    class TestMethod {
        @Test
        @DisplayName("should return true if name contains keywords")
        public void test_nameContainsKeywords_returnsTrue() {
            // One keyword
            NameContainsKeywordsPredicate predicate =
                    new NameContainsKeywordsPredicate(Collections.singletonList(
                            "Alice"));
            assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob")
                    .build()));

            // Multiple keywords
            predicate = new NameContainsKeywordsPredicate(Arrays.asList(
                    "Alice",
                    "Bob"
            ));
            assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob")
                    .build()));

            // Only one matching keyword
            predicate = new NameContainsKeywordsPredicate(Arrays.asList(
                    "Bob",
                    "Carol"
            ));
            assertTrue(
                    predicate.test(
                            new PersonBuilder().withName("Alice Carol").build()
                    )
            );

            // Mixed-case keywords
            predicate = new NameContainsKeywordsPredicate(Arrays.asList(
                    "aLIce",
                    "bOB"
            ));
            assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob")
                    .build()));
        }

        @Test
        @DisplayName("should return false if name does not contain keywords")
        public void test_nameDoesNotContainKeywords_returnsFalse() {
            // Zero keywords
            NameContainsKeywordsPredicate predicate =
                    new NameContainsKeywordsPredicate(Collections.emptyList());
            assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                    .build()));

            // Non-matching keyword
            predicate =
                    new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
            assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob")
                    .build()));

            // Keywords match phone, email and address, but does not match name
            predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345",
                    "alice@email.com",
                    "Main",
                    "Street"
            ));
            assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                    .withPhone("91234567")
                    .withEmail("alice@email.com")
                    .withAddress("Main Street")
                    .build()));
        }
    }
}
