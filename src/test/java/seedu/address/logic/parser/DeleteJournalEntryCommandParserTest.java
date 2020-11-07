package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteJournalEntryCommand;

class DeleteJournalEntryCommandParserTest {

    private final DeleteJournalEntryCommandParser parser = new DeleteJournalEntryCommandParser();

    @Nested
    @DisplayName("parse method")
    class Parse {
        @Test
        @DisplayName("should generate DeleteJournalEntryCommand from valid user input")
        public void parse_validArgs_returnsDeleteCommand() {
            assertParseSuccess(
                    parser,
                    "1",
                    new DeleteJournalEntryCommand(INDEX_FIRST_PERSON),
                    "delj"
            );
        }

        @Test
        @DisplayName("should throw ParseException from invalid user input")
        public void parse_invalidArgs_throwsParseException() {
            assertParseFailure(
                    parser,
                    "a",
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteJournalEntryCommand.getMessageUsage("delj")
                    ),
                    "delj"
            );
        }
    }
}
