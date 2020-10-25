package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditJournalEntryCommand;
import seedu.address.logic.commands.EditJournalEntryCommand.EditEntryDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.journal.Title;
import seedu.address.model.tag.Tag;

public class EditJournalEntryCommandParser implements Parser<EditJournalEntryCommand> {

    @Override
    public EditJournalEntryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME,
                        PREFIX_DATE_AND_TIME,
                        PREFIX_DESCRIPTION,
                        PREFIX_TAG,
                        PREFIX_CONTACT
                );
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    EditJournalEntryCommand.MESSAGE_USAGE),
                    pe);
        }
        EditEntryDescriptor editEntryDescriptor =
                new EditJournalEntryCommand.EditEntryDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEntryDescriptor.setTitle(
                    ParserUtil.parseTitle(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE_AND_TIME).isPresent()) {
            editEntryDescriptor.setDate(
                    ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE_AND_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editEntryDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editEntryDescriptor::setTags);
        editEntryDescriptor.setContactList(ParserUtil.parseContacts(
                argMultimap.getAllValues(PREFIX_CONTACT))
                .asUnmodifiableObservableList());

        if (!editEntryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditJournalEntryCommand.MESSAGE_NOT_EDITED);
        }

        return new EditJournalEntryCommand(index, editEntryDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
