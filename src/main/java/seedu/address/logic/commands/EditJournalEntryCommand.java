package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Description;
import seedu.address.model.journal.Title;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

public class EditJournalEntryCommand extends Command {

    public static final String COMMAND_WORD = "editj";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "TITLE] "
            + "[" + PREFIX_DATE_AND_TIME + "DATE_AND_TIME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_CONTACT + "CONTACT_NAME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Meet with client Robert "
            + PREFIX_DATE_AND_TIME + "2020-10-10 10:00";

    public static final String MESSAGE_EDIT_ENTRY_SUCCESS = "Edited Journal "
            + "Entry: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to "
            + "edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This person already"
            + " exists in the address book.";
    private final Index index;

    /**
     * @param index
     */
    public EditJournalEntryCommand(Index index) {
        requireNonNull(index);
        // TODO: EditJournalEntryDescriptor

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

    public static class EditJournalEntryDescriptor {
        private Title title;
        private Date date;
        private Description description;
        private UniquePersonList contactList;
        private Set<Tag> tags;

        public EditJournalEntryDescriptor() {
        }

        public EditJournalEntryDescriptor(EditJournalEntryDescriptor toCopy) {

        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public UniquePersonList getContactList() {
            // TODO: Look into the optional for this
            return contactList;
        }

        public void setContactList(UniquePersonList contactList) {
            this.contactList = contactList;
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return tags != null
                    ? Optional.of(Collections.unmodifiableSet(tags))
                    : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditJournalEntryDescriptor)) {
                return false;
            }

            // state check
            EditJournalEntryDescriptor e = (EditJournalEntryDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getContactList().equals(e.getContactList())
                    && getDate().equals(e.getDate())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());


        }
    }
}
