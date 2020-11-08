package seedu.address.logic.commands;

import seedu.address.logic.parser.ValidCommand;
import seedu.address.model.Model;

//@@author {Nauw1010}
/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = "%s: Shows program usage "
            + "instructions.\n"
            + "Parameters: [of/COMMAND]\n"
            + "Example: %s of/addj";

    public static final String SHOWING_HELP_MESSAGE = "Showing help.\n";

    protected boolean isShowingHelpWindow;
    protected ValidCommand validCommandType;
    private final String commandAlias;

    /**
     * Constructs a {@code HelpCommand} specified showing the help window
     * or not.
     * @param isShowingHelpWindow show the help window or not.
     */
    public HelpCommand(boolean isShowingHelpWindow) {
        this.isShowingHelpWindow = isShowingHelpWindow;
        validCommandType = null;
        commandAlias = null;
    }

    /**
     * Constructs a {@code HelpCommand} of a specific valid command.
     * @param validCommandType the valid command of type {@code ValidCommand}.
     * @param commandAlias the alias used for the command.
     */
    public HelpCommand(ValidCommand validCommandType, String commandAlias) {
        isShowingHelpWindow = false;
        this.validCommandType = validCommandType;
        this.commandAlias = commandAlias;
    }

    public static String getMessageUsage(String commandWord) {
        return String.format(
                MESSAGE_USAGE,
                commandWord,
                commandWord
        );
    }

    @Override
    public CommandResult execute(Model model) {
        if (isShowingHelpWindow) {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false)
                    .setSameTab();
        }
        assert validCommandType != null : "The valid command type is null.";

        switch (validCommandType) {
        case ADD_ALIAS:
            return new CommandResult(
                    AddAliasCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case ADD_CONTACT:
            return new CommandResult(
                    AddContactCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case ADD_JOURNAL_ENTRY:
            return new CommandResult(
                    AddJournalEntryCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case CHECK_SCHEDULE:
            return new CommandResult(
                    CheckScheduleCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case CLEAR_ADDRESS_BOOK:
            return new CommandResult(
                    ClearAddressBookCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case CLEAR_JOURNAL:
            return new CommandResult(
                    ClearJournalCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case DELETE_CONTACT:
            return new CommandResult(
                    DeleteContactCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case DELETE_JOURNAL_ENTRY:
            return new CommandResult(
                    DeleteJournalEntryCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case EDIT_CONTACT:
            return new CommandResult(
                    EditContactCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case EXIT:
            return new CommandResult(
                    ExitCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case EDIT_JOURNAL_ENTRY:
            return new CommandResult(
                    EditJournalEntryCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case FIND_CONTACT:
            return new CommandResult(
                    FindContactCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case FIND_JOURNAL_ENTRY:
            return new CommandResult(
                    FindJournalEntryCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case HELP:
            return new CommandResult(getMessageUsage(commandAlias))
                    .setSameTab();
        case LIST_CONTACT:
            return new CommandResult(
                    ListContactCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case LIST_JOURNAL_ENTRY:
            return new CommandResult(
                    ListJournalEntryCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case SWITCH:
            return new CommandResult(
                    SwitchCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case VIEW_CONTACT:
            return new CommandResult(
                    ViewPersonCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case VIEW_JOURNAL_ENTRY:
            return new CommandResult(
                    ViewJournalEntryCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case CHANGE_THEME:
            return new CommandResult(
                    ChangeThemeCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        case DELETE_ALIAS:
            return new CommandResult(
                    DeleteAliasCommand.getMessageUsage(commandAlias)
            ).setSameTab();
        default:
            return new CommandResult(
                    "This is a direct command to use."
            ).setSameTab();
        }
    }
}
