package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class ChangeThemeCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should execute change scheme successfully")
        public void execute_changetheme_success() {
            assertCommandSuccess(
                    new ChangeThemeCommand(),
                    model,
                    ChangeThemeCommand.MESSAGE_THEME_CHANGED,
                    expectedModel
            );
        }
    }
}
