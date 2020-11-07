package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PREFIX;
import static seedu.address.logic.parser.CliSyntax.ALL_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddJournalEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Description;
import seedu.address.model.journal.Entry;
import seedu.address.model.journal.Title;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

//@@author {zhXchD}
/**
 * Parses input arguments and creates a new AddContactCommand object
 */
public class AddJournalEntryCommandParser implements Parser<AddJournalEntryCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddContactCommand
     * and returns an AddContactCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddJournalEntryCommand parse(String commandWord, String args)
            throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ALL_PREFIXES);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            AddJournalEntryCommand.getMessageUsage(commandWord)
                    )
            );
        }

        // if any of the invalid prefixes shows up, throw an exception
        if (!arePrefixesEmpty(argMultimap, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_OF)) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_PREFIX,
                            AddJournalEntryCommand.getMessageUsage(commandWord)
                    )
            );
        }

        assert argMultimap.getValue(PREFIX_NAME).isPresent();
        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_NAME).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE_AND_TIME).orElse(null));
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).orElse(null));
        UniquePersonList personList = ParserUtil.parseContacts(argMultimap.getAllValues(PREFIX_CONTACT));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Entry entry = new Entry(title, date, description, personList, tagList);

        return new AddJournalEntryCommand(entry);
    }

    /**
     * Returns true if none of the prefixes contains present {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesEmpty(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isEmpty());
    }

}
