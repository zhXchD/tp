package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_DIFF_DATE;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_DIFF_DESCRIPTION;
import static seedu.address.testutil.TypicalEntries.getTypicalJournal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CheckScheduleCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.AliasMap;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.journal.Date;
import seedu.address.testutil.JournalBuilder;

class CheckScheduleCommandParserTest {

    private final CheckScheduleCommandParser parser =
            new CheckScheduleCommandParser();
    private final Model model = new ModelManager(
            new AddressBook(),
            getTypicalJournal(),
            new UserPrefs(),
            new AliasMap()
    );

    @Nested
    @DisplayName("parse method")
    class Parse {
        @Test
        @DisplayName("should generate CheckScheduleCommand if user input is "
                + "empty")
        void parse_emptyArg_returnsCheckScheduleCommand() throws Exception {
            Date date = new Date("");
            CheckScheduleCommand checkScheduleCommand = parser.parse(
                    "check", "     ");
            String expectedMessage = String.format(
                    CheckScheduleCommand.MESSAGE_SUCCESS,
                    date.getDateString()
            );
            Model expectedModel = new ModelManager(
                    new AddressBook(),
                    new Journal(),
                    new UserPrefs(),
                    new AliasMap()
            );

            assertCommandSuccess(
                    checkScheduleCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should generate CheckScheduleCommand if date is valid")
        void parse_validDate_returnsCheckScheduleCommand() throws Exception {
            Date date = new Date("2000-12-21 00:00");
            CheckScheduleCommand checkScheduleCommand = parser.parse(
                    "check", "  2000-12-21    ");
            String expectedMessage = String.format(
                    CheckScheduleCommand.MESSAGE_SUCCESS,
                    date.getDateString()
            );
            Model expectedModel = new ModelManager(
                    new AddressBook(),
                    new JournalBuilder()
                            .withEntry(TEST_ENTRY_DIFF_DATE)
                            .withEntry(TEST_ENTRY_DIFF_DESCRIPTION)
                            .build(),
                    new UserPrefs(),
                    new AliasMap()
            );

            assertCommandSuccess(
                    checkScheduleCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should throw ParseException if date is invalid")
        void parse_invalidDate_throwsParseException() {
            assertParseFailure(
                    parser,
                    "   abc123      ",
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            CheckScheduleCommand.getMessageUsage("check")
                    ),
                    "check"
            );
        }
    }
}
