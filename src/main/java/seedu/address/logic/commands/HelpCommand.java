package seedu.address.logic.commands;

import seedu.address.logic.ValidCommand;
import seedu.address.model.Model;


/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows program usage instructions.\n"
            + "[Usage]: " + COMMAND_WORD + " [of/COMMAND]\n"
            + "Example: " + COMMAND_WORD + " of/" + "addj";

    public static final String SHOWING_HELP_MESSAGE = "Showing help.\n";

    protected boolean isShowHelpWindow;
    protected ValidCommand validCommandType;

    /**
     * Constructs a {@code HelpCommand} specified showing the help window
     * or not.
     * @param isShowHelpWindow show the help window or not.
     */
    public HelpCommand(boolean isShowHelpWindow) {
        this.isShowHelpWindow = isShowHelpWindow;
        this.validCommandType = null;
    }

    /**
     * Constructs a {@code HelpCommand} of a specific valid command.
     * @param validCommandType the valid command of type {@code ValidCommand}
     */
    public HelpCommand(ValidCommand validCommandType) {
        this.isShowHelpWindow = false;
        this.validCommandType = validCommandType;
    }

    @Override
    public CommandResult execute(Model model) {
        if (this.isShowHelpWindow) {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false).setSameTab();
        }
        assert this.validCommandType != null : "The valid command type is null. Please check.";

        switch (this.validCommandType) {
        case ADD_CONTACT:
            return new CommandResult(SHOWING_HELP_MESSAGE + AddContactCommand.MESSAGE_USAGE);
        case ADD_JOURNAL_ENTRY:
            return new CommandResult(SHOWING_HELP_MESSAGE + AddJournalEntryCommand.MESSAGE_USAGE);
        case DELETE_CONTACT:
            return new CommandResult(SHOWING_HELP_MESSAGE + DeleteContactCommand.MESSAGE_USAGE);
        case DELETE_JOURNAL_ENTRY:
            return new CommandResult(SHOWING_HELP_MESSAGE + DeleteJournalEntryCommand.MESSAGE_USAGE);
        case EDIT_CONTACT:
            return new CommandResult(SHOWING_HELP_MESSAGE + EditContactCommand.MESSAGE_USAGE);
        case FIND:
            return new CommandResult(SHOWING_HELP_MESSAGE + FindCommand.MESSAGE_USAGE);
        case VIEW:
            return new CommandResult(SHOWING_HELP_MESSAGE + ViewCommand.MESSAGE_USAGE);
        case CHECK_SCHEDULE:
            return new CommandResult(SHOWING_HELP_MESSAGE + CheckScheduleCommand.MESSAGE_USAGE);
        default:
            return new CommandResult(SHOWING_HELP_MESSAGE + "This is a direct command to use.");
        }
    }
}
