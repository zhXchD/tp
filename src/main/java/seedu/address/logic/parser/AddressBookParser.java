package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearAddressBookCommand;
import seedu.address.logic.commands.ClearJournalCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListContactCommand;
import seedu.address.logic.commands.ListJournalEntryCommand;
import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.commands.ValidCommand;
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

        ValidCommand command = ValidCommand.commandType(commandWord);

        switch (command) {
        case ADDCONTACT:
            if (uuid == null) {
                return new AddContactCommandParser().parse(arguments);
            } else {
                return new AddContactCommandParser(uuid).parse(arguments);
            }

        case ADDJOURNALENTRY:
            return new AddJournalEntryCommandParser().parse(arguments);

        case EDIT:
            return new EditCommandParser().parse(arguments);

        case DELETECONTACT:
            return new DeleteContactCommandParser().parse(arguments);

        case DELETEJOURNALENTRY:
            return new DeleteJournalEntryCommandParser().parse(arguments);

        case CLEARADDRESSBOOK:
            return new ClearAddressBookCommand();

        case CLEARJOURNAL:
            return new ClearJournalCommand();

        case FIND:
            return new FindCommandParser().parse(arguments);

        case LISTCONTACT:
            return new ListContactCommand();

        case LISTJOURNALENTRY:
            return new ListJournalEntryCommand();

        case EXIT:
            return new ExitCommand();

        case HELP:
            return new HelpCommand();

        case VIEW:
            return new ViewCommandParser().parse(arguments);

        case SWITCH:
            return new SwitchCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
