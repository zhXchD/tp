package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCOPE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ViewJournalEntryCommand;
import seedu.address.logic.commands.ViewPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {
    @Override
    public ViewCommand parse(String args) throws ParseException {
        String scope, index;
        try {
            scope = args.substring(0, args.lastIndexOf(" "));
            index = args.substring(args.lastIndexOf(" ")+1);
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(scope, PREFIX_SCOPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_SCOPE)|| !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        Index targetIndex = parseIndex(index);

        switch (ParserUtil.parseScope(argMultimap.getValue(PREFIX_SCOPE).get())) {
            case "c":
                return new ViewPersonCommand(targetIndex);
            case "j":
                return new ViewJournalEntryCommand(targetIndex);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    private Index parseIndex(String arg) throws ParseException {
        try {
            return ParserUtil.parseIndex(arg);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            ViewCommand.MESSAGE_USAGE
                    ),
                    pe
            );
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
