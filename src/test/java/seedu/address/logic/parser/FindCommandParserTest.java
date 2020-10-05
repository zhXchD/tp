package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Nested
    @DisplayName("parse method")
    class Parse {
        @Test
        @DisplayName("should throw ParseException if no keywords")
        public void parse_emptyArg_throwsParseException() {
            assertParseFailure(
                    parser,
                    "     ",
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            FindCommand.MESSAGE_USAGE
                    )
            );
        }

        @Test
        @DisplayName("should generate FindCommand object if arguments are "
                + "valid")
        public void parse_validArgs_returnsFindCommand() {
            // no leading and trailing whitespaces
            FindCommand expectedFindCommand = new FindCommand(
                    new NameContainsKeywordsPredicate(
                            Arrays.asList("Alice", "Bob")
                    )
            );
            assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

            // multiple whitespaces between keywords
            assertParseSuccess(
                    parser,
                    " \n Alice \n \t Bob  \t",
                    expectedFindCommand
            );
        }
    }
}
