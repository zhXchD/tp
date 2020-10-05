package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ExitCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should execute exit successfully")
        public void execute_exit_success() {
            CommandResult expectedCommandResult = new CommandResult(
                    MESSAGE_EXIT_ACKNOWLEDGEMENT,
                    false,
                    true
            );
            assertCommandSuccess(
                    new ExitCommand(),
                    model,
                    expectedCommandResult,
                    expectedModel
            );
        }
    }
}
