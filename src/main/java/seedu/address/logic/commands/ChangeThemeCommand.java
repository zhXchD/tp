package seedu.address.logic.commands;

import seedu.address.model.Model;

//@@author {Nauw1010}

/**
 * Changes the color theme of IntelliJournal.
 */
public class ChangeThemeCommand extends Command {

    public static final String COMMAND_WORD = "changetheme";

    public static final String MESSAGE_USAGE = "%s: Changes the main color "
            + "theme of IntelliJournal\n"
            + "Example: %s";

    public static final String MESSAGE_THEME_CHANGED = "Theme changed";

    public static String getMessageUsage(String commandWord) {
        return String.format(MESSAGE_USAGE, commandWord, commandWord);
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_THEME_CHANGED)
                .setChangingTheme()
                .setSameTab();
    }
}
