package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "91234567";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Nested
    @DisplayName("parseIndex method")
    class ParseIndex {
        @Test
        @DisplayName("should throw ParseException if input is invalid")
        public void parseIndex_invalidInput_throwsParseException() {
            assertThrows(ParseException.class, () ->
                    ParserUtil.parseIndex("10 a"));
        }

        @Test
        @DisplayName("should throw ParseException if input is out of range")
        public void parseIndex_outOfRangeInput_throwsParseException() {
            assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                    ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1))
            );
        }

        @Test
        @DisplayName("should successfully generate index if input is valid")
        public void parseIndex_validInput_success() throws Exception {
            // No whitespaces
            assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

            // Leading and trailing whitespaces
            assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
        }
    }

    @Nested
    @DisplayName("parseName method")
    class ParseName {
        @Test
        @DisplayName("should throw NullPointerException if name is null")
        public void parseName_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    ParserUtil.parseName((String) null));
        }

        @Test
        @DisplayName("should throw ParseException if name is invalid")
        public void parseName_invalidValue_throwsParseException() {
            assertThrows(ParseException.class, () ->
                    ParserUtil.parseName(INVALID_NAME));
        }

        @Test
        @DisplayName("should return name if input is valid without whitespace")
        public void parseName_validValueWithoutWhitespace_returnsName()
                throws Exception {
            Name expectedName = new Name(VALID_NAME);
            assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
        }

        @Test
        @DisplayName("should return trimmed name if input is valid with "
                + "whitespace")
        public void parseName_validValueWithWhitespace_returnsTrimmedName()
                throws Exception {
            String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
            Name expectedName = new Name(VALID_NAME);
            assertEquals(
                    expectedName,
                    ParserUtil.parseName(nameWithWhitespace)
            );
        }
    }

    @Nested
    @DisplayName("parsePhone method")
    class ParsePhone {
        @Test
        @DisplayName("should throw NullPointerException if phone is null")
        public void parsePhone_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    ParserUtil.parsePhone((String) null));
        }

        @Test
        @DisplayName("should throw ParseException if phone is invalid")
        public void parsePhone_invalidValue_throwsParseException() {
            assertThrows(ParseException.class, () ->
                    ParserUtil.parsePhone(INVALID_PHONE));
        }

        @Test
        @DisplayName("should return valid phone without whitespace")
        public void parsePhone_validValueWithoutWhitespace_returnsPhone()
                throws Exception {
            Phone expectedPhone = new Phone(VALID_PHONE);
            assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
        }

        @Test
        @DisplayName("should return valid phone with whitespace trimmed")
        public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone()
                throws Exception {
            String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
            Phone expectedPhone = new Phone(VALID_PHONE);
            assertEquals(
                    expectedPhone,
                    ParserUtil.parsePhone(phoneWithWhitespace)
            );
        }
    }

    @Nested
    @DisplayName("parseAddress method")
    class ParseAddress {
        @Test
        @DisplayName("should throw NullPointerException if address is null")
        public void parseAddress_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    ParserUtil.parseAddress((String) null));
        }

        @Test
        @DisplayName("should throw ParseException if value is invalid")
        public void parseAddress_invalidValue_throwsParseException() {
            assertThrows(ParseException.class, () ->
                    ParserUtil.parseAddress(INVALID_ADDRESS));
        }

        @Test
        @DisplayName("should return address if value is valid without "
                + "whitespace")
        public void parseAddress_validValueWithoutWhitespace_returnsAddress()
                throws Exception {
            Address expectedAddress = new Address(VALID_ADDRESS);
            assertEquals(
                    expectedAddress,
                    ParserUtil.parseAddress(VALID_ADDRESS)
            );
        }

        @Test
        @DisplayName("should return trimmed address if value is valid with "
                + "whitespace")
        public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress()
                throws Exception {
            String addressWithWhitespace =
                    WHITESPACE + VALID_ADDRESS + WHITESPACE;
            Address expectedAddress = new Address(VALID_ADDRESS);
            assertEquals(
                    expectedAddress,
                    ParserUtil.parseAddress(addressWithWhitespace)
            );
        }
    }

    @Nested
    @DisplayName("parseEmail method")
    class ParseEmail {
        @Test
        @DisplayName("should throw NullPointerException if email is null")
        public void parseEmail_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    ParserUtil.parseEmail((String) null));
        }

        @Test
        @DisplayName("should throw ParseException if value is invalid")
        public void parseEmail_invalidValue_throwsParseException() {
            assertThrows(ParseException.class, () ->
                    ParserUtil.parseEmail(INVALID_EMAIL));
        }

        @Test
        @DisplayName("should return email if value is valid without whitespace")
        public void parseEmail_validValueWithoutWhitespace_returnsEmail()
                throws Exception {
            Email expectedEmail = new Email(VALID_EMAIL);
            assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
        }

        @Test
        @DisplayName("should return email if value is valid with whitespace")
        public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail()
                throws Exception {
            String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
            Email expectedEmail = new Email(VALID_EMAIL);
            assertEquals(
                    expectedEmail,
                    ParserUtil.parseEmail(emailWithWhitespace)
            );
        }
    }

    @Nested
    @DisplayName("parseTag method")
    class ParseTag {
        @Test
        @DisplayName("should throw NullPointerException if value is null")
        public void parseTag_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    ParserUtil.parseTag(null));
        }

        @Test
        @DisplayName("should throw ParseException if value is invalid")
        public void parseTag_invalidValue_throwsParseException() {
            assertThrows(ParseException.class, () ->
                    ParserUtil.parseTag(INVALID_TAG));
        }

        @Test
        @DisplayName("should return tag if value is valid without whitespace")
        public void parseTag_validValueWithoutWhitespace_returnsTag()
                throws Exception {
            Tag expectedTag = new Tag(VALID_TAG_1);
            assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
        }

        @Test
        @DisplayName("should return trimmed tag if value is valid with "
                + "whitespace")
        public void parseTag_validValueWithWhitespace_returnsTrimmedTag()
                throws Exception {
            String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
            Tag expectedTag = new Tag(VALID_TAG_1);
            assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
        }
    }

    @Nested
    @DisplayName("parseTags method")
    class ParseTags {
        @Test
        @DisplayName("should throw NullPointerException if value is null")
        public void parseTags_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    ParserUtil.parseTags(null));
        }

        @Test
        @DisplayName("should throw ParseException if values are invalid")
        public void parseTags_collectionWithInvalidTags_throwsParseException() {
            assertThrows(ParseException.class, () ->
                    ParserUtil.parseTags(
                            Arrays.asList(VALID_TAG_1, INVALID_TAG)
                    )
            );
        }

        @Test
        @DisplayName("should return empty set of tags if collection is empty")
        public void parseTags_emptyCollection_returnsEmptySet()
                throws Exception {
            assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
        }

        @Test
        @DisplayName("should return tag set if collection has valid tags")
        public void parseTags_collectionWithValidTags_returnsTagSet()
                throws Exception {
            Set<Tag> actualTagSet = ParserUtil.parseTags(
                    Arrays.asList(
                            VALID_TAG_1,
                            VALID_TAG_2
                    )
            );
            Set<Tag> expectedTagSet = new HashSet<Tag>(
                    Arrays.asList(
                            new Tag(VALID_TAG_1),
                            new Tag(VALID_TAG_2)
                    )
            );

            assertEquals(expectedTagSet, actualTagSet);
        }
    }
}
