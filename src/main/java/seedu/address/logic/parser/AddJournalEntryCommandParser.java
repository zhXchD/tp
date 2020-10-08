package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
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

/**
 * Parses input arguments and creates a new AddContactCommand object
 */
public class AddJournalEntryCommandParser implements Parser<AddJournalEntryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddContactCommand
     * and returns an AddContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddJournalEntryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE_AND_TIME, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE_AND_TIME, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddJournalEntryCommand.MESSAGE_USAGE));
        }

        Title title = new Title(argMultimap.getValue(PREFIX_NAME).get());
        String dateString = argMultimap.getValue(PREFIX_DATE_AND_TIME).get();
        if (!Date.isValidDate(dateString)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        Date date = new Date(dateString);
        Description description = new Description(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        UniquePersonList personList = new UniquePersonList();
        Set<Tag> taglist =
                ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Entry entry = new Entry(title, date, description, personList, taglist);

        return new AddJournalEntryCommand(entry);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
