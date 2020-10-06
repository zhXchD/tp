package seedu.address.logic.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddJournalEntryCommand;
import seedu.address.model.journal.Entry;
import seedu.address.model.person.Name;
import seedu.address.testutil.EntryBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddJournalEntryCommandParserTest {
    AddJournalEntryCommandParser parser = new AddJournalEntryCommandParser();

    @Nested
    @DisplayName("parse method")
    class Parse {
        @Test
        @DisplayName("should add entry successfully if all required fields "
                + "are present")
        public void parse_allFieldsPresent_success() {
            Entry expectedEntry = new EntryBuilder()
                    .withTitle(VALID_TITLE_MEETING)
                    .withDate(VALID_DATE_OCTOBER)
                    .withDescription(VALID_DESCRIPTION_STORY)
                    .build();

            assertParseSuccess(
                    parser,
                    PREAMBLE_WHITESPACE + TITLE_DESC_MEETING
                            + DATE_DESC_OCTOBER + DESCRIPTION_DESC_STORY,
                    new AddJournalEntryCommand(expectedEntry)
            );
        }

        @Test
        @DisplayName("should fail to add entry if compulsory fields are "
                + "missing")
        public void parse_compulsoryFieldMissing_failure() {
            String expectedMessage = String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    AddJournalEntryCommand.MESSAGE_USAGE
            );

            // missing title prefix
            assertParseFailure(parser,
                    VALID_TITLE_MEETING + DATE_DESC_OCTOBER + DESCRIPTION_DESC_STORY,
                    expectedMessage
            );

            // missing date prefix
            assertParseFailure(parser,
                    TITLE_DESC_MEETING + VALID_DATE_OCTOBER + DESCRIPTION_DESC_STORY,
                    expectedMessage
            );

            // missing description prefix
            assertParseFailure(parser,
                    TITLE_DESC_MEETING + DATE_DESC_OCTOBER + VALID_DESCRIPTION_STORY,
                    expectedMessage
            );

            // all prefixes missing
            assertParseFailure(parser,
                    VALID_TITLE_MEETING + VALID_DATE_OCTOBER + VALID_DESCRIPTION_STORY,
                    expectedMessage
            );
        }

//        @Test
//        @DisplayName("should no add person if there are invalid values in the"
//                + " input")
//        public void parse_invalidValue_failure() {
//            // invalid title
//            assertParseFailure(
//                    parser,
//                    INVALID_NAME_DESC + DATE_DESC_OCTOBER + DESCRIPTION_DESC_STORY,
//                    Name.MESSAGE_CONSTRAINTS
//            );
//        }
//            // invalid name
//            assertParseFailure(
//                    parser,
//                    INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
//                            + ADDRESS_DESC_BOB
//                            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
//                    Name.MESSAGE_CONSTRAINTS
//            );
//
//            // invalid phone
//            assertParseFailure(
//                    parser,
//                    NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
//                            + ADDRESS_DESC_BOB
//                            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
//                    Phone.MESSAGE_CONSTRAINTS
//            );
//
//            // invalid email
//            assertParseFailure(
//                    parser,
//                    NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
//                            + ADDRESS_DESC_BOB
//                            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
//                    Email.MESSAGE_CONSTRAINTS
//            );
//
//            // invalid address
//            assertParseFailure(
//                    parser,
//                    NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
//                            + INVALID_ADDRESS_DESC
//                            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
//                    Address.MESSAGE_CONSTRAINTS
//            );
//
//            // invalid tag
//            assertParseFailure(
//                    parser,
//                    NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
//                            + ADDRESS_DESC_BOB
//                            + INVALID_TAG_DESC + VALID_TAG_FRIEND,
//                    Tag.MESSAGE_CONSTRAINTS
//            );
//
//            // two invalid values, only first invalid value reported
//            assertParseFailure(parser,
//                    INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
//                            + INVALID_ADDRESS_DESC,
//                    Name.MESSAGE_CONSTRAINTS
//            );
//
//            // non-empty preamble
//            assertParseFailure(parser,
//                    PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
//                            + EMAIL_DESC_BOB
//                            + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND
//                            + TAG_DESC_FRIEND,
//                    String.format(
//                            MESSAGE_INVALID_COMMAND_FORMAT,
//                            AddContactCommand.MESSAGE_USAGE
//                    )
//            );
//        }
    }
}