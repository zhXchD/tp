package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditContactCommand;
import seedu.address.logic.commands.EditContactCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditContactCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT,
            EditContactCommand.getMessageUsage("edc")
    );

    private final EditContactCommandParser parser = new EditContactCommandParser();

    @Nested
    @DisplayName("parse method")
    class Parse {
        @Test
        @DisplayName("should fail to parse if there are missing fields")
        public void parse_missingParts_failure() {
            // no index specified
            assertParseFailure(
                    parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT, "edc");

            // no field specified
            assertParseFailure(
                    parser, "1", EditContactCommand.MESSAGE_NOT_EDITED, "edc");

            // no index and no field specified
            assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT, "edc");
        }

        @Test
        @DisplayName("should fail to parse if preamble passed in (the index "
                + "of the person in list) is invalid")
        public void parse_invalidPreamble_failure() {
            // negative index
            assertParseFailure(
                    parser,
                    "-5" + NAME_DESC_AMY,
                    MESSAGE_INVALID_FORMAT,
                    "edc"
            );

            // zero index
            assertParseFailure(
                    parser,
                    "0" + NAME_DESC_AMY,
                    MESSAGE_INVALID_FORMAT,
                    "edc"
            );

            // invalid arguments being parsed as preamble
            assertParseFailure(
                    parser,
                    "1 some random string",
                    MESSAGE_INVALID_FORMAT,
                    "edc"
            );

            // invalid prefix being parsed as preamble
            assertParseFailure(
                    parser, "1 i/ string", MESSAGE_INVALID_FORMAT, "edc");
        }

        @Test
        @DisplayName("should fail to parse if invalid values passed into each "
                + "field ")
        public void parse_invalidValue_failure() {
            assertParseFailure(
                    parser,
                    "1" + INVALID_NAME_DESC,
                    Name.MESSAGE_CONSTRAINTS,
                    "edc"
            ); // invalid name
            assertParseFailure(
                    parser,
                    "1" + INVALID_PHONE_DESC,
                    Phone.MESSAGE_CONSTRAINTS,
                    "edc"
            ); // invalid phone
            assertParseFailure(
                    parser,
                    "1" + INVALID_EMAIL_DESC,
                    Email.MESSAGE_CONSTRAINTS,
                    "edc"
            ); // invalid email
            assertParseFailure(
                    parser,
                    "1" + INVALID_ADDRESS_DESC,
                    Address.MESSAGE_CONSTRAINTS,
                    "edc"
            ); // invalid address
            assertParseFailure(
                    parser,
                    "1" + INVALID_TAG_DESC,
                    Tag.MESSAGE_CONSTRAINTS,
                    "edc"
            ); // invalid tag

            // invalid phone followed by valid email
            assertParseFailure(
                    parser,
                    "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY,
                    Phone.MESSAGE_CONSTRAINTS,
                    "edc"
            );

            // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
            // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
            assertParseFailure(
                    parser,
                    "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC,
                    Phone.MESSAGE_CONSTRAINTS,
                    "edc"
            );

            // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
            // parsing it together with a valid tag results in error
            assertParseFailure(
                    parser,
                    "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                    Tag.MESSAGE_CONSTRAINTS,
                    "edc"
            );
            assertParseFailure(
                    parser,
                    "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                    Tag.MESSAGE_CONSTRAINTS,
                    "edc"
            );
            assertParseFailure(
                    parser,
                    "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                    Tag.MESSAGE_CONSTRAINTS,
                    "edc"
            );

            // multiple invalid values, but only the first invalid value is captured
            assertParseFailure(parser,
                    "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                            + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                    Name.MESSAGE_CONSTRAINTS,
                    "edc"
            );
        }

        @Test
        @DisplayName("should generate EditContactCommand if all fields are specified"
                + " in the correct format")
        public void parse_allFieldsSpecified_success() {
            Index targetIndex = INDEX_SECOND_PERSON;
            String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB
                    + TAG_DESC_HUSBAND
                    + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY
                    + TAG_DESC_FRIEND;

            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                            .withPhone(VALID_PHONE_BOB)
                            .withEmail(VALID_EMAIL_AMY)
                            .withAddress(VALID_ADDRESS_AMY)
                            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                            .build();
            EditContactCommand expectedCommand =
                    new EditContactCommand(targetIndex, descriptor);

            assertParseSuccess(parser, userInput, expectedCommand, "edc");
        }

        @Test
        @DisplayName("should generate EditContactCommand if some fields are "
                + "specified with valid values")
        public void parse_someFieldsSpecified_success() {
            Index targetIndex = INDEX_FIRST_PERSON;
            String userInput =
                    targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                            .withEmail(VALID_EMAIL_AMY).build();
            EditContactCommand expectedCommand =
                    new EditContactCommand(targetIndex, descriptor);

            assertParseSuccess(parser, userInput, expectedCommand, "edc");
        }

        @Test
        @DisplayName("should generate EditContactCommand with one field specified "
                + "with a valid value")
        public void parse_oneFieldSpecified_success() {
            // name
            Index targetIndex = INDEX_THIRD_PERSON;
            String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                            .build();
            EditContactCommand expectedCommand =
                    new EditContactCommand(targetIndex, descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edc");

            // phone
            userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
            descriptor =
                    new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY)
                            .build();
            expectedCommand = new EditContactCommand(targetIndex, descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edc");

            // email
            userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
            descriptor =
                    new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY)
                            .build();
            expectedCommand = new EditContactCommand(targetIndex, descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edc");

            // address
            userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
            descriptor = new EditPersonDescriptorBuilder().withAddress(
                    VALID_ADDRESS_AMY).build();
            expectedCommand = new EditContactCommand(targetIndex, descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edc");

            // tags
            userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
            descriptor =
                    new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND)
                            .build();
            expectedCommand = new EditContactCommand(targetIndex, descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edc");
        }

        @Test
        @DisplayName("should accept the last field if there are multiple of "
                + "the same repeated fields")
        public void parse_multipleRepeatedFields_acceptsLast() {
            Index targetIndex = INDEX_FIRST_PERSON;
            String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY
                    + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                    + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY
                    + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                    + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                    + TAG_DESC_HUSBAND;

            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                            .withEmail(VALID_EMAIL_BOB)
                            .withAddress(VALID_ADDRESS_BOB)
                            .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                            .build();
            EditContactCommand expectedCommand =
                    new EditContactCommand(targetIndex, descriptor);

            assertParseSuccess(parser, userInput, expectedCommand, "edc");
        }

        @Test
        @DisplayName("should take the valid last field even if invalid field "
                + "is passed in first")
        public void parse_invalidValueFollowedByValidValue_success() {
            // no other valid values specified
            Index targetIndex = INDEX_FIRST_PERSON;
            String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC
                    + PHONE_DESC_BOB;
            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                            .build();
            EditContactCommand expectedCommand =
                    new EditContactCommand(targetIndex, descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edc");

            // other valid values specified
            userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB
                    + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                    + PHONE_DESC_BOB;
            descriptor =
                    new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                            .withEmail(VALID_EMAIL_BOB)
                            .withAddress(VALID_ADDRESS_BOB)
                            .build();
            expectedCommand = new EditContactCommand(targetIndex, descriptor);
            assertParseSuccess(parser, userInput, expectedCommand, "edc");
        }

        @Test
        @DisplayName("should reset all tags if just tag prefix is called "
                + "without any tags")
        public void parse_resetTags_success() {
            Index targetIndex = INDEX_THIRD_PERSON;
            String userInput = targetIndex.getOneBased() + TAG_EMPTY;

            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder().withTags().build();
            EditContactCommand expectedCommand =
                    new EditContactCommand(targetIndex, descriptor);

            assertParseSuccess(parser, userInput, expectedCommand, "edc");
        }
    }
}
