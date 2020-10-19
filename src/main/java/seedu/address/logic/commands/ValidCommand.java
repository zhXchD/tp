package seedu.address.logic.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;


/**
 * ValidCommand enum represents the possible command type.
 */


public enum ValidCommand {

    ADDCONTACT("addc", "addcontact"),
    ADDJOURNALENTRY("addj", "adde"),
    CLEARJOURNAL("clearj"),
    DELETECONTACT("deletec", "delc"),
    DELETEJOURNALENTRY("deletej", "delj"),
    EDIT("edit"),
    FIND("find"),
    EXIT("exit"),
    HELP("help"),
    LISTCONTACT("listc"),
    SWITCH("switch"),
    VIEWJOURNALENTRY("viewj"),
    VIEWPERSON("viewc");
    


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

    public static ValidCommand commandType(String alias) throws CommandException {
        ValidCommand command = aliasMap.get(alias);

        if (command == null) {
            throw new CommandException("Does not support this command");
        }

        return command;
    }

}

