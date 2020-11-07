package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.journal.Entry;

//@@author {zhXchD}
/**
 * Views journal entry at specified index of the list currently displayed.
 */
public class ViewJournalEntryCommand extends Command {

    public static final String COMMAND_WORD = "viewj";

    public static final String MESSAGE_VIEW_SUCCESS = "View journal entry: %1$s";

    public static final String MESSAGE_USAGE = "%s: Views the "
            + "journal entry at the index position in the currently "
            + "displayed list.\n"
            + "Parameters: INDEX "
            + "(must be a positive integer)\n"
            + "Example: %s 1";

    protected final Index targetIndex;

    public ViewJournalEntryCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public static String getMessageUsage(String commandWord) {
        return String.format(MESSAGE_USAGE, commandWord, commandWord);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Entry> lastShownList = model.getFilteredEntryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        Entry entryToView = lastShownList.get(targetIndex.getZeroBased());
        // model.updateFilteredEntryList(entry -> entry.isSameEntry(entryToView));

        return new CommandResult(
                String.format(
                        MESSAGE_VIEW_SUCCESS,
                        entryToView.toString()
                )
        ).setJournalTab().setViewingJournal(entryToView);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof ViewJournalEntryCommand
                && ((ViewJournalEntryCommand) other).targetIndex.equals(targetIndex));
    }
}
