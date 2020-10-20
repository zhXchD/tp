package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddContactCommand;
import seedu.address.model.journal.Entry;

public class EntryUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddEntryCommand(Entry entry) {
        return AddContactCommand.COMMAND_WORD + " " + getEntryDetails(entry);
    }

    /**
     * Returns the part of command string for the given {@code person}'s
     * details.
     */
    public static String getEntryDetails(Entry entry) {

        //TODO: change when support contact list

        return PREFIX_NAME + entry.getTitle().title + " "
               + PREFIX_DATE_AND_TIME + entry.getDate().value + " "
                + PREFIX_DESCRIPTION + entry.getDescription().description + " ";
    }
}
