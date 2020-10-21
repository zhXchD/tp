package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OF;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ValidCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

public class HelpCommandParser implements Parser<HelpCommand>{
    @Override
    public HelpCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_OF);

        if(!arePrefixesPresent(argMultimap, PREFIX_OF) && !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        if(!arePrefixesPresent(argMultimap, PREFIX_OF)) {
            return new HelpCommand(true);
        }

        assert argMultimap.getValue(PREFIX_OF).isPresent() : "Help command argument not present, please check.";
        ValidCommand validCommandType = ParserUtil.parseValidCommand(argMultimap.getValue(PREFIX_OF).get());
        return new HelpCommand(validCommandType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
