package seedu.address.logic.parser;

import seedu.address.logic.commands.FindContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

//@@author {zhXchD}
/**
 * Parses input arguments and creates a new FindContactCommand object
 */
public class FindContactCommandParser implements Parser<FindContactCommand> {
    public FindContactCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args,
                PREFIX_NAME,
                PREFIX_ADDRESS,
                PREFIX_PHONE,
                PREFIX_EMAIL,
                PREFIX_TAG,
                PREFIX_DATE_AND_TIME,
                PREFIX_DESCRIPTION,
                PREFIX_CONTACT
        );

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            FindContactCommand.MESSAGE_USAGE)
            );
        }
        Predicate<Person> personPredicate = person -> true;
        if (!arePrefixesEmpty(argMultimap, PREFIX_DATE_AND_TIME, PREFIX_DESCRIPTION, PREFIX_CONTACT)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
        }
        // if all fields are empty
        if (arePrefixesEmpty(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_TAG)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            assert argMultimap.getValue(PREFIX_NAME).isPresent();
            String nameKeyWord =
                    argMultimap.getValue(PREFIX_NAME).get().trim().toLowerCase();
            personPredicate =
                    personPredicate.and(person -> person.getName().fullName
                            .toLowerCase()
                            .contains(nameKeyWord));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
            assert argMultimap.getValue(PREFIX_EMAIL).isPresent();
            String emailKeyWord =
                    argMultimap.getValue(PREFIX_EMAIL).get().trim().toLowerCase();
            personPredicate =
                    personPredicate.and(person -> {
                        if (person.getEmail().equals(Email.EMPTY_EMAIL)) {
                            return false;
                        } else {
                            return person.getEmail().value
                                    .toLowerCase()
                                    .contains(emailKeyWord);
                        }
                    });
        }
        if (arePrefixesPresent(argMultimap, PREFIX_ADDRESS)) {
            assert argMultimap.getValue(PREFIX_ADDRESS).isPresent();
            String addressKeyWord =
                    argMultimap.getValue(PREFIX_ADDRESS).get().trim().toLowerCase();
            personPredicate =
                    personPredicate.and(person -> {
                        if (person.getAddress().equals(Address.EMPTY_ADDRESS)) {
                            return false;
                        } else {
                            return person.getAddress().value
                                    .toLowerCase()
                                    .contains(addressKeyWord);
                        }
                    });
        }
        if (arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
            assert argMultimap.getValue(PREFIX_PHONE).isPresent();
            String phoneKeyWord =
                    argMultimap.getValue(PREFIX_PHONE).get().trim().toLowerCase();
            personPredicate =
                    personPredicate.and(person -> {
                        if (person.getPhone().equals(Phone.EMPTY_PHONE)) {
                            return false;
                        } else {
                            return person.getPhone().value
                                    .toLowerCase()
                                    .contains(phoneKeyWord);
                        }
                    });
        }
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        personPredicate = personPredicate.and(person -> person.getTags().containsAll(tagList));
        return new FindContactCommand(personPredicate);

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
