package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_OCTOBER;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_STORY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OCTOBER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_STORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MOVIE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditJournalEntryCommand;
import seedu.address.logic.commands.EditJournalEntryCommand.EditEntryDescriptor;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Description;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditEntryDescriptorBuilder;

public class EditJournalEntryCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String MESSAGE_INVALID_FORMAT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT,
            EditJournalEntryCommand.MESSAGE_USAGE
    );

    private final EditJournalEntryCommandParser parser =
            new EditJournalEntryCommandParser();

    @Nested
    @DisplayName("parse method")
    class Parse {
        @Test
        @DisplayName("should fail to parse if there are missing fields")
        public void parse_missingParts_failure() {
            // no index specified
            assertParseFailure(parser, VALID_TITLE_MOVIE, MESSAGE_INVALID_FORMAT);

            // no field specified
            assertParseFailure(parser, "1", EditJournalEntryCommand.MESSAGE_NOT_EDITED);

            // no index and no field specified
            assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        }

        @Test
        @DisplayName("should fail to parse if preamble passed in is invalid")
        public void parse_invalidPreamble_failure() {
            //negative index
            assertParseFailure(parser,
                    "-5" + VALID_TITLE_MOVIE,
                    MESSAGE_INVALID_FORMAT);

            // zero index
            assertParseFailure(parser,
                    "0" + VALID_TITLE_MOVIE,
                    MESSAGE_INVALID_FORMAT);

            // invalid arguments as preamble
            assertParseFailure(parser,
                    "1 some random string",
                    MESSAGE_INVALID_FORMAT);

            // invalid prefix parsed as preamble
            assertParseFailure(parser,
                    "1 i/string",
                    MESSAGE_INVALID_FORMAT);

        }

        @Test
        @DisplayName("should fail to parse if invalid values passed into "
                + "each field")
        public void parse_invalidValue_failure() {
            // invalid description
            assertParseFailure(parser,
                    "1" + INVALID_DESCRIPTION_DESC,
                    Description.MESSAGE_CONSTRAINTS);
            // invalid date
            assertParseFailure(parser,
                    "1" + INVALID_DATE_DESC,
                    Date.MESSAGE_CONSTRAINTS);
            // TODO: Invalid contact test

            // invalid tag
            assertParseFailure(parser,
                    "1" + INVALID_TAG_DESC,
                    Tag.MESSAGE_CONSTRAINTS);

            // TODO: the valid X followed by invalid X test cases

        }

        @Test
        @DisplayName("should generate EditJournalEntry Command if all fields "
                + "are specified in the correct format")
        public void parse_allFieldsSpecified_success() {
            // TODO: Add contacts to this test
            Index targetIndex = INDEX_SECOND_PERSON;
            String userInput = targetIndex.getOneBased()
                    + TITLE_DESC_MEETING
                    + DATE_DESC_OCTOBER
                    + DESCRIPTION_DESC_STORY
                    + TAG_DESC_FRIEND;
            EditEntryDescriptor descriptor =
                    new EditEntryDescriptorBuilder()
                            .withTitle(VALID_TITLE_MEETING)
                            .withDate(VALID_DATE_OCTOBER)
                            .withDescription(VALID_DESCRIPTION_STORY)
                            .withTags(VALID_TAG_FRIEND)
                            .withContacts()
                            .build();
            EditJournalEntryCommand expectedCommand =
                    new EditJournalEntryCommand(targetIndex, descriptor);

            assertParseSuccess(parser, userInput, expectedCommand);
        }
    }
}
