package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class SwitchCommand extends Command {
    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_SUCCESS = "Switch to the other tab.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS).setSwitch();
    }
}
