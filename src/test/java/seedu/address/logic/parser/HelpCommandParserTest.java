package seedu.address.logic.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddContactCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteContactCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class HelpCommandParserTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    private final HelpCommandParser parser = new HelpCommandParser();

    @Nested
    @DisplayName("parse method")
    class Parse {
        @Test
        @DisplayName("should throw ParseException if the command is not valid")
        public void parse_notValidCommand_throwsParseException() {
            assertParseFailure(
                parser,
                " of/",
                "This is not a valid command."
            );

            assertParseFailure(
                parser,
                " of/haha",
                "This is not a valid command."
            );
        }

        @Test
        @DisplayName("should throw ParseException if the command is not valid")
        public void parse_isValidCommand_returnsHelpCommand() throws ParseException {
            HelpCommand helpCommandOfAddc = parser.parse(" of/addc");
            HelpCommand helpCommandOfDelc = parser.parse(" of/delc");

            CommandResult expectedCommandResultOfAddc = new CommandResult(
                SHOWING_HELP_MESSAGE + AddContactCommand.MESSAGE_USAGE);

            CommandResult expectedCommandResultOfDelc = new CommandResult(
                SHOWING_HELP_MESSAGE + DeleteContactCommand.MESSAGE_USAGE);

            assertCommandSuccess(
                helpCommandOfAddc,
                model,
                expectedCommandResultOfAddc,
                expectedModel
            );

            assertCommandSuccess(
                helpCommandOfDelc,
                model,
                expectedCommandResultOfDelc,
                expectedModel
            );
        }
    }
}
