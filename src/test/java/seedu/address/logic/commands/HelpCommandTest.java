package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ValidCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class HelpCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should execute help command successfully")
        public void execute_help_showingWindow() {
            CommandResult expectedCommandResult = new CommandResult(
                    SHOWING_HELP_MESSAGE, true, false);
            assertCommandSuccess(
                    new HelpCommand(true),
                    model,
                    expectedCommandResult,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should execute help command successfully")
        public void execute_help_withAddCommand() {
            ValidCommand validCommandType = ValidCommand.ADD_CONTACT;
            CommandResult expectedCommandResult = new CommandResult(
                    AddContactCommand.getMessageUsage("addc"));
            assertCommandSuccess(
                    new HelpCommand(validCommandType, "addc"),
                    model,
                    expectedCommandResult,
                    expectedModel
            );
        }
    }
}
