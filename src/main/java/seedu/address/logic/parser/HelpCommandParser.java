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

import java.util.stream.Stream;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser<HelpCommand> {
    @Override
    public HelpCommand parse(String commandWord, String args)
            throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ALL_PREFIXES);

        // if any of the invalid prefixes shows up, throw an exception
        if (!arePrefixesEmpty(argMultimap, PREFIX_DATE_AND_TIME, PREFIX_DESCRIPTION, PREFIX_CONTACT, PREFIX_NAME,
                PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG)) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_PREFIX,
                            HelpCommand.getMessageUsage(commandWord)
                    )
            );
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_OF) && argMultimap.getPreamble().isEmpty()) {
            return new HelpCommand(true);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_OF) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            HelpCommand.getMessageUsage(commandWord)
                    )
            );
        }

        assert argMultimap.getValue(PREFIX_OF).isPresent() : "Help command argument not present, please check.";
        String commandAlias = argMultimap.getValue(PREFIX_OF).get();
        ValidCommand validCommandType =
                ParserUtil.parseValidCommand(commandAlias);
        return new HelpCommand(validCommandType, commandAlias);
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
