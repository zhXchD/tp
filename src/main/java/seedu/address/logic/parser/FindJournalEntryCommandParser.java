package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.address.logic.parser.CliSyntax.ALL_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindJournalEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Description;
import seedu.address.model.journal.Entry;
import seedu.address.model.tag.Tag;

//@@author {zhXchD}
/**
 * Parse input arguments and return a FindJournalEntryCommand.
 */
public class FindJournalEntryCommandParser implements Parser<FindJournalEntryCommand> {
    /**
     * Parse input argument string and return a FindJournalEntryCommand.
     * @param args the input argument string
     * @return FindJournalEntryCommand to with a predicate
     * @throws ParseException throws exception if invalid arguments are parsed
     */
    public FindJournalEntryCommand parse(String commandWord, String args)
            throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ALL_PREFIXES);

        // if has preamble or prefixes are empty
        if (!argMultimap.getPreamble().isEmpty()
                || arePrefixesEmpty(argMultimap, PREFIX_NAME, PREFIX_CONTACT,
                PREFIX_DATE_AND_TIME, PREFIX_DESCRIPTION, PREFIX_TAG)) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            FindJournalEntryCommand.getMessageUsage(commandWord)
                    )
            );
        }
        Predicate<Entry> entryPredicate = entry -> true;
        if (!arePrefixesEmpty(argMultimap, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_OF)) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_PREFIX,
                            FindJournalEntryCommand.getMessageUsage(commandWord)
                    )
            );
        }
        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            assert argMultimap.getValue(PREFIX_NAME).isPresent();
            String titleKeyWord = argMultimap.getValue(PREFIX_NAME).get().trim().toLowerCase();
            entryPredicate = entryPredicate.and(parseTitle(titleKeyWord));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            assert argMultimap.getValue(PREFIX_DESCRIPTION).isPresent();
            String descriptionKeyWord = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim().toLowerCase();
            entryPredicate = entryPredicate.and(parseDescription(descriptionKeyWord));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_DATE_AND_TIME)) {
            assert argMultimap.getValue(PREFIX_DATE_AND_TIME).isPresent();
            Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE_AND_TIME).orElse(null));
            entryPredicate = entryPredicate.and(entry -> entry.getDate().equals(date));
        }
        List<String> names = argMultimap.getAllValues(PREFIX_CONTACT);
        entryPredicate = entryPredicate.and(parseContactNames(names));
        Set<Tag> tagList1 = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        entryPredicate = entryPredicate.and(entry -> entry.getTags().containsAll(tagList1));
        return new FindJournalEntryCommand(entryPredicate);
    }

    private Predicate<Entry> parseTitle(String titleKeyWord) {
        return entry -> entry.getTitle().title.toLowerCase().contains(titleKeyWord);
    }

    private Predicate<Entry> parseDescription(String descriptionKeyWord) {
        return entry -> {
            if (entry.getDescription().equals(Description.EMPTY_DESCRIPTION)) {
                return false;
            } else {
                return entry.getDescription().description
                    .toLowerCase()
                    .contains(descriptionKeyWord);
            }
        };
    }

    private Predicate<Entry> parseContactNames(List<String> names) {
        return entry -> names.stream().allMatch(
            name -> entry
                .getContactList().stream()
                .anyMatch(
                    person -> person.getName().fullName
                        .toLowerCase()
                        .contains(name.toLowerCase())));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if none of the prefixes contains present {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesEmpty(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isEmpty());
    }
}
