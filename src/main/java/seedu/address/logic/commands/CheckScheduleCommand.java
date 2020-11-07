package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Entry;

/**
 * Checks schedule on a given day.
 */
public class CheckScheduleCommand extends Command {

    public static final String COMMAND_WORD = "check";

    public static final String MESSAGE_USAGE = "%s: Checks the schedule of a "
            + "given date.\n"
            + "Parameters: [DATE_AND_TIME]\n"
            + "Example: %s 2011-12-03";

    public static final String MESSAGE_SUCCESS = "Here is your schedule on %s";

    private final Predicate<Entry> predicate;
    private final Date date;

    /**
     * Creates a CheckScheduleCommand with the predicate for filtering the
     * model.
     * @param predicate to filter the model.
     * @param date of the schedule to check.
     */
    public CheckScheduleCommand(Predicate<Entry> predicate, Date date) {
        this.predicate = requireNonNull(predicate);
        this.date = requireNonNullElse(date, new Date(""));
    }

    public static String getMessageUsage(String commandWord) {
        return String.format(MESSAGE_USAGE, commandWord, commandWord);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEntryList(predicate);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, date.getDateString())
        ).setJournalTab();
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof CheckScheduleCommand
                && predicate.equals(((CheckScheduleCommand) other).predicate)
                && date.equals(((CheckScheduleCommand) other).date));
    }
}
