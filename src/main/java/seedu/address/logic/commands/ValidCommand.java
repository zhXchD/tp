package seedu.address.logic.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * ValidCommand enum represents the possible command type.
 */


public enum ValidCommand {

    ADDCONTACT("addc", "addcontact"),
    ADDJOURNALENTRY("addj", "adde"),
    CLEARJOURNAL("clearj"),
    CLEARADDRESSBOOK("clearc"),
    DELETECONTACT("deletec", "delc"),
    DELETEJOURNALENTRY("deletej", "delj"),
    EDIT("edit"),
    FIND("find"),
    EXIT("exit"),
    HELP("help"),
    LISTCONTACT("listc"),
    LISTJOURNALENTRY("listj"),
    SWITCH("switch"),
    VIEW("view");
    


    /**
     * Creates command alias from aliases list.
     *
     * @param aliases Aliases list.
     */
    ValidCommand(String... aliases) {
        this.aliases = aliases;
    }

    /**
     * Map that match alias with valid command
     */
    private static final Map<String, ValidCommand> aliasMap = new HashMap<>();

    /**
     * Valid alias for the commands
     */
    private final String[] aliases;


    static {
        Arrays.stream(ValidCommand.values()).forEach(command -> Arrays.stream(command.aliases)
                .forEach(alias -> aliasMap.put(alias, command)));
    }

    public static ValidCommand commandType(String alias) throws ParseException {
        ValidCommand command = aliasMap.get(alias);

        if (command == null) {
            throw new ParseException("Cannot parse command " + alias);
        }

        return command;
    }

}

