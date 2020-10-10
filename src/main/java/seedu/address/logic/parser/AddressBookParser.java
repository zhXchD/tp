package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddContactCommand;
import seedu.address.logic.commands.AddJournalEntryCommand;
import seedu.address.logic.commands.ClearAddressBookCommand;
import seedu.address.logic.commands.ClearJournalCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ListContactCommand;
import seedu.address.logic.commands.ListJournalEntryCommand;
import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private UUID uuid;

    /**
     * Creates a default instance of AddressBookParser.
     */
    public AddressBookParser() { }

    /**
     * Creates an instance of AddressBookParser with a given uuid.
     * @param uuid used in parsing.
     */
    public AddressBookParser(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddContactCommand.COMMAND_WORD:
            if (uuid == null) {
                return new AddContactCommandParser().parse(arguments);
            } else {
                return new AddContactCommandParser(uuid).parse(arguments);
            }

        case AddJournalEntryCommand.COMMAND_WORD:
            return new AddJournalEntryCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearAddressBookCommand.COMMAND_WORD:
            return new ClearAddressBookCommand();

        case ClearJournalCommand.COMMAND_WORD:
            return new ClearJournalCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListContactCommand.COMMAND_WORD:
            return new ListContactCommand();

        case ListJournalEntryCommand.COMMAND_WORD:
            return new ListJournalEntryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case SwitchCommand.COMMAND_WORD:
            return new SwitchCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
