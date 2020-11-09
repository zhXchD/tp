package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

//@@author {zhXchD}
/**
 * Views a person at specified index in the current list displayed.
 */
public class ViewPersonCommand extends Command {
    public static final String COMMAND_WORD = "viewc";

    public static final String MESSAGE_VIEW_SUCCESS = "View contact: %1$s";

    public static final String MESSAGE_USAGE = "%s: Views the contact at the "
            + "index position in the currently displayed list.\n"
            + "Parameters: INDEX "
            + "(must be a positive integer)\n"
            + "Example: %s 1";

    protected final Index targetIndex;

    public ViewPersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public static String getMessageUsage(String commandWord) {
        return String.format(MESSAGE_USAGE, commandWord, commandWord);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(targetIndex.getZeroBased());
        // model.updateFilteredPersonList(person -> person.isSamePerson(personToView));

        return new CommandResult(
                String.format(
                        MESSAGE_VIEW_SUCCESS,
                        personToView.toString()
                )
        ).setAddressBookTab().setViewingContact(personToView);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof ViewPersonCommand
                && ((ViewPersonCommand) other).targetIndex.equals(targetIndex));
    }
}
