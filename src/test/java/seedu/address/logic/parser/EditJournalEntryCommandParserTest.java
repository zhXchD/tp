package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CONTACTS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DEFAULT_UUID;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_NOVEMBER;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_OCTOBER;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_STORY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTACT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_NOVEMBER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OCTOBER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MOVIE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_STORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MOVIE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditJournalEntryCommand;
import seedu.address.logic.commands.EditJournalEntryCommand.EditEntryDescriptor;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Title;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditEntryDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditJournalEntryCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String MESSAGE_INVALID_FORMAT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT,
            EditJournalEntryCommand.getMessageUsage("edj")
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
            assertParseFailure(
                    parser, VALID_TITLE_MOVIE, MESSAGE_INVALID_FORMAT, "edj");

            // no field specified
            assertParseFailure(parser, "1",
                    EditJournalEntryCommand.MESSAGE_NOT_EDITED, "edj");

            // no index and no field specified
            assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT, "edj");
        }

        @Test
        @DisplayName("should fail to parse if preamble passed in is invalid")
        public void parse_invalidPreamble_failure() {
            //negative index
            assertParseFailure(
                    parser,
                    "-5" + VALID_TITLE_MOVIE,
                    MESSAGE_INVALID_FORMAT,
                    "edj"
            );

            // zero index
            assertParseFailure(
                    parser,
                    "0" + VALID_TITLE_MOVIE,
                    MESSAGE_INVALID_FORMAT,
                    "edj"
            );

            // invalid arguments as preamble
            assertParseFailure(
                    parser,
                    "1 some random string",
                    MESSAGE_INVALID_FORMAT,
                    "edj"
            );

            // invalid prefix parsed as preamble
            assertParseFailure(
                    parser,
                    "1 i/string",
                    MESSAGE_INVALID_FORMAT,
                    "edj"
            );
        }

        @Test
        @DisplayName("should fail to parse if invalid values passed into "
                + "each field")
        public void parse_invalidValue_failure() {
            // invalid title
            assertParseFailure(
                    parser,
                    "1" + INVALID_TITLE_DESC,
                    Title.MESSAGE_CONSTRAINTS,
                    "edj"
            );

            // invalid date
            assertParseFailure(
                    parser,
                    "1" + INVALID_DATE_DESC,
                    Date.MESSAGE_CONSTRAINTS,
                    "edj"
            );

            // invalid name for a contact
            assertParseFailure(
                    parser,
                    "1" + INVALID_CONTACT_DESC,
                    Name.MESSAGE_CONSTRAINTS,
                    "edj"
            );

            // invalid tag
            assertParseFailure(
                    parser,
                    "1" + INVALID_TAG_DESC,
                    Tag.MESSAGE_CONSTRAINTS,
                    "edj"
            );

            // valid contact followed by invalid contact
            assertParseFailure(
                    parser,
                    "1" + CONTACTS_DESC_AMY + INVALID_CONTACT_DESC,
                    Name.MESSAGE_CONSTRAINTS,
                    "edj"
            );

            // invalid contact followed by valid contact
            assertParseFailure(
                    parser,
                    "1" + INVALID_CONTACT_DESC + CONTACTS_DESC_AMY,
                    Name.MESSAGE_CONSTRAINTS,
                    "edj"
            );

            // valid tag followed by invalid tag
            assertParseFailure(
                    parser,
                    "1" + TAG_DESC_FRIEND + INVALID_TAG_DESC,
                    Tag.MESSAGE_CONSTRAINTS,
                    "edj"
            );


            // invalid tag followed by valid tag
            assertParseFailure(
                    parser,
                    "1" + INVALID_TAG_DESC + TAG_DESC_FRIEND,
                    Tag.MESSAGE_CONSTRAINTS,
                    "edj"
            );

        }


        @Test
        @DisplayName("should generate EditJournalEntry Command if all fields "
                + "are specified in the correct format")
        public void parse_allFieldsSpecified_success() {
            Index targetIndex = INDEX_SECOND_PERSON;
            String userInput = targetIndex.getOneBased()
                    + TITLE_DESC_MEETING
                    + DATE_DESC_OCTOBER
                    + DESCRIPTION_DESC_STORY
                    + CONTACTS_DESC_AMY
                    + TAG_DESC_FRIEND;
            EditEntryDescriptor descriptor =
                    new EditEntryDescriptorBuilder()
                            .withTitle(VALID_TITLE_MEETING)
                            .withDate(VALID_DATE_OCTOBER)
                            .withDescription(VALID_DESCRIPTION_STORY)
                            .withTags(VALID_TAG_FRIEND)
                            .withContacts(new PersonBuilder()
                                    .withName(VALID_NAME_AMY)
                                    .setBlankFields()
                                    .build(UUID.fromString(CONTACT_DEFAULT_UUID)))
                            .build();
            EditJournalEntryCommand expectedCommand =
                    new EditJournalEntryCommand(targetIndex, descriptor);

            assertParseSuccess(parser, userInput, expectedCommand, "edj");
        }

        @Test
        @DisplayName("should generate EditJournalEntryCommand with one field "
                + "specified with a valid value")
        public void parse_oneFieldSpecified_success() {
            // title
            Index targetIndex = INDEX_THIRD_PERSON;
            String userInput = targetIndex.getOneBased() + TITLE_DESC_MEETING;
            EditEntryDescriptor descriptor =
                    new EditEntryDescriptorBuilder()
                            .withTitle(VALID_TITLE_MEETING)
                            .build();
            EditJournalEntryCommand expectedCommand =
                    new EditJournalEntryCommand(targetIndex, descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edj");

            // description
            userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_STORY;
            descriptor =
                    new EditEntryDescriptorBuilder()
                            .withDescription(VALID_DESCRIPTION_STORY)
                            .build();
            expectedCommand = new EditJournalEntryCommand(targetIndex,
                    descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edj");

            // date
            userInput = targetIndex.getOneBased() + DATE_DESC_OCTOBER;
            descriptor =
                    new EditEntryDescriptorBuilder()
                            .withDate(VALID_DATE_OCTOBER)
                            .build();
            expectedCommand = new EditJournalEntryCommand(targetIndex,
                    descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edj");

            // contact
            userInput = targetIndex.getOneBased() + CONTACTS_DESC_AMY;
            descriptor =
                    new EditEntryDescriptorBuilder()
                            .withContacts(new PersonBuilder()
                                    .setBlankFields()
                                    .withName(VALID_NAME_AMY)
                                    .build(UUID.fromString(CONTACT_DEFAULT_UUID)))
                            .build();
            expectedCommand = new EditJournalEntryCommand(targetIndex,
                    descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edj");

            //tag
            userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
            descriptor =
                    new EditEntryDescriptorBuilder()
                            .withTags(VALID_TAG_FRIEND)
                            .build();
            expectedCommand = new EditJournalEntryCommand(targetIndex,
                    descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edj");
        }

        @Test
        @DisplayName("should accept the last field if there are multiple of "
                + "the same repeated fields")
        public void parse_multipleRepeatedFields_acceptsLast() {
            Index targetIndex = INDEX_THIRD_PERSON;
            // tags and contacts not included since those don't care about order
            String userInput = targetIndex.getOneBased()
                    + DESCRIPTION_DESC_STORY
                    + DESCRIPTION_DESC_MOVIE
                    + DATE_DESC_OCTOBER
                    + DATE_DESC_NOVEMBER;
            EditEntryDescriptor descriptor =
                    new EditEntryDescriptorBuilder()
                            .withDescription(VALID_DESCRIPTION_MOVIE)
                            .withDate(VALID_DATE_NOVEMBER)
                            .build();
            EditJournalEntryCommand expectedCommand =
                    new EditJournalEntryCommand(targetIndex, descriptor);

            assertParseSuccess(parser, userInput, expectedCommand, "edj");
        }

        @Test
        @DisplayName("should take the valid last field even if invalid field "
                + "is passed in first")
        public void parse_invalidValueFollowedByValidValue_success() {
            Index targetIndex = INDEX_FIRST_PERSON;
            String userInput =
                    targetIndex.getOneBased()
                            + INVALID_DESCRIPTION_DESC
                            + INVALID_DATE_DESC
                            + DESCRIPTION_DESC_MOVIE
                            + DATE_DESC_OCTOBER;
            EditEntryDescriptor descriptor =
                    new EditEntryDescriptorBuilder()
                            .withDescription(VALID_DESCRIPTION_MOVIE)
                            .withDate(VALID_DATE_OCTOBER)
                            .build();
            EditJournalEntryCommand expectedCommand =
                    new EditJournalEntryCommand(targetIndex, descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edj");
        }

        @Test
        @DisplayName("should reset all tags if tag prefix is called without "
                + "tags")
        public void parse_resetTags_success() {
            Index targetIndex = INDEX_THIRD_PERSON;
            String userInput = targetIndex.getOneBased() + TAG_EMPTY;

            EditEntryDescriptor descriptor =
                    new EditEntryDescriptorBuilder()
                            .withTags()
                            .build();
            EditJournalEntryCommand expectedCommand =
                    new EditJournalEntryCommand(targetIndex, descriptor);

            assertParseSuccess(parser, userInput, expectedCommand, "edj");
        }
    }
}
