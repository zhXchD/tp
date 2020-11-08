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

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

//@@author {zhXchD}
/**
 * Parses input arguments and creates a new FindContactCommand object
 */
public class FindContactCommandParser implements Parser<FindContactCommand> {
    /**
     * Parses input arguments and returns a new FindContactCommand object
     * @param args the input argument string
     * @return a FindContactCommand with a predicate
     * @throws ParseException throw exception if taking in an invalid parser
     */
    public FindContactCommand parse(String commandWord, String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ALL_PREFIXES);

        // if has preamble or prefixes are empty
        if (!argMultimap.getPreamble().isEmpty()
                || arePrefixesEmpty(argMultimap, PREFIX_NAME, PREFIX_ADDRESS,
                PREFIX_EMAIL, PREFIX_TAG, PREFIX_PHONE)) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            FindContactCommand.getMessageUsage(commandWord)
                    )
            );
        }
        Predicate<Person> personPredicate = person -> true;
        // if not all invalid fields are empty
        if (!arePrefixesEmpty(argMultimap, PREFIX_DATE_AND_TIME,
                PREFIX_DESCRIPTION, PREFIX_CONTACT, PREFIX_OF)) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_PREFIX,
                            FindContactCommand.getMessageUsage(commandWord)
                    )
            );
        }
        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            assert argMultimap.getValue(PREFIX_NAME).isPresent();
            String nameKeyWord = argMultimap.getValue(PREFIX_NAME).get().trim().toLowerCase();
            personPredicate = personPredicate.and(parseName(nameKeyWord));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
            assert argMultimap.getValue(PREFIX_EMAIL).isPresent();
            String emailKeyWord = argMultimap.getValue(PREFIX_EMAIL).get().trim().toLowerCase();
            personPredicate = personPredicate.and(parseEmail(emailKeyWord));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_ADDRESS)) {
            assert argMultimap.getValue(PREFIX_ADDRESS).isPresent();
            String addressKeyWord =
                    argMultimap.getValue(PREFIX_ADDRESS).get().trim().toLowerCase();
            personPredicate = personPredicate.and(parseAddress(addressKeyWord));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
            assert argMultimap.getValue(PREFIX_PHONE).isPresent();
            String phoneKeyWord = argMultimap.getValue(PREFIX_PHONE).get().trim().toLowerCase();
            personPredicate = personPredicate.and(parsePhone(phoneKeyWord));
        }
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        personPredicate = personPredicate.and(person -> person.getTags().containsAll(tagList));
        return new FindContactCommand(personPredicate);

    }

    private Predicate<Person> parseName(String nameKeyWord) {
        return person -> person.getName().fullName.toLowerCase().contains(nameKeyWord);
    }

    private Predicate<Person> parseEmail(String emailKeyWord) {
        return person -> {
            if (person.getEmail().equals(Email.EMPTY_EMAIL)) {
                return false;
            } else {
                return person.getEmail().value
                        .toLowerCase()
                        .contains(emailKeyWord);
            }
        };
    }

    private Predicate<Person> parseAddress(String addressKeyWord) {
        return person -> {
            if (person.getAddress().equals(Address.EMPTY_ADDRESS)) {
                return false;
            } else {
                return person.getAddress().value
                        .toLowerCase()
                        .contains(addressKeyWord);
            }
        };
    }

    private Predicate<Person> parsePhone(String phoneKeyWord) {
        return person -> {
            if (person.getPhone().equals(Phone.EMPTY_PHONE)) {
                return false;
            } else {
                return person.getPhone().value
                        .toLowerCase()
                        .contains(phoneKeyWord);
            }
        };
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
