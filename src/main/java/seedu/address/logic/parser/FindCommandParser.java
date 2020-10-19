package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindContactCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_SCOPE,
                PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG,
                PREFIX_TITLE, PREFIX_DATE_AND_TIME, PREFIX_DESCRIPTION, PREFIX_CONTACT);

        if (!arePrefixesPresent(argMultimap, PREFIX_SCOPE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String scope = ParserUtil.parseScope(argMultimap.getValue(PREFIX_SCOPE).get());

        switch (scope) {
        case "c":
            Predicate<Person> personPredicate = person -> true;
            if (!arePrefixesEmpty(argMultimap,
                    PREFIX_TITLE, PREFIX_DATE_AND_TIME, PREFIX_DESCRIPTION, PREFIX_CONTACT)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
            }
            if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
                String nameKeyWord = argMultimap.getValue(PREFIX_NAME).get().trim();
                personPredicate =
                        personPredicate.and(person -> person.getName().fullName.contains(nameKeyWord));
            }
            if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
                String emailKeyWord = argMultimap.getValue(PREFIX_EMAIL).get().trim();
                personPredicate =
                        personPredicate.and(person -> {
                            if (person.getEmail().equals(Email.EMPTY_EMAIL)) {
                                return false;
                            } else {
                                return person.getEmail().value.contains(emailKeyWord);
                            }
                        });
            }
            if (arePrefixesPresent(argMultimap, PREFIX_ADDRESS)) {
                String addressKeyWord = argMultimap.getValue(PREFIX_ADDRESS).get().trim();
                personPredicate =
                        personPredicate.and(person -> {
                            if (person.getAddress().equals(Address.EMPTY_ADDRESS)) {
                                return false;
                            } else {
                                return person.getAddress().value.contains(addressKeyWord);
                            }
                        });
            }
            if (arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
                String phoneKeyWord = argMultimap.getValue(PREFIX_PHONE).get().trim();
                personPredicate =
                        personPredicate.and(person -> {
                            if (person.getPhone().equals(Phone.EMPTY_PHONE)) {
                                return false;
                            } else {
                                return person.getPhone().value.contains(phoneKeyWord);
                            }
                        });
            }
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            personPredicate = personPredicate.and(person -> person.getTags().containsAll(tagList));
            return new FindContactCommand(personPredicate);
        case "j":
            return null;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
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
