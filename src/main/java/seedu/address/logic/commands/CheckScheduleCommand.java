package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.journal.Entry;

/**
 * Checks schedule on a given day.
 */
public class CheckScheduleCommand extends Command {

    public static final String COMMAND_WORD = "check";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Checks the schedule of a given date.\n"
            + "Parameters: DATE_AND_TIME\n"
            + "Example: " + COMMAND_WORD + " 2011-12-0";

    public static final String MESSAGE_SUCCESS = "Here is your schedule on %s";

    private final Predicate<Entry> predicate;
    private final LocalDate date;

    /**
     * Creates a CheckScheduleCommand with the predicate for filtering the
     * model.
     * @param predicate to filter the model.
     */
    public CheckScheduleCommand(Predicate<Entry> predicate) {
        this.predicate = requireNonNull(predicate);
        date = LocalDate.now();
    }

    /**
     * Creates a CheckScheduleCommand with the predicate for filtering the
     * model.
     * @param predicate to filter the model.
     * @param date of the schedule to check.
     */
    public CheckScheduleCommand(Predicate<Entry> predicate, LocalDate date) {
        this.predicate = requireNonNull(predicate);
        this.date = requireNonNullElse(date, LocalDate.now());
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEntryList(predicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, date))
                .setJournalTab();
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof CheckScheduleCommand
                && predicate.equals(((CheckScheduleCommand) other).predicate));
    }
}
