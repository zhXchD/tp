package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.EntryBuilder;
import seedu.address.testutil.JournalBuilder;

public class FindCommandParserTest {

    private final Journal journal = new JournalBuilder()
            .withEntry(
                    new EntryBuilder()
                            .withTitle("first entry")
                            .withDescription("first description")
                            .withDate("2020-10-10 10:00")
                            .withContacts(CARL)
                            .withContacts(DANIEL)
                            .build()
            )
            .withEntry(
                    new EntryBuilder()
                            .withTitle("second entry")
                            .withDescription("second description")
                            .withDate("2020-10-11 10:00")
                            .withContacts(ALICE)
                            .withContacts(DANIEL)
                            .build()
            )
            .withEntry(
                    new EntryBuilder()
                            .withTitle("third entry")
                            .withDescription("third description")
                            .withDate("2020-10-12 10:00")
                            .withContacts(FIONA)
                            .withContacts(DANIEL)
                            .build()
            )
            .build();

    private final Model model = new ModelManager(
            getTypicalAddressBook(), new Journal(journal), new UserPrefs());
    private final Model expectedModel = new ModelManager(
            getTypicalAddressBook(), new Journal(journal), new UserPrefs());

    private final FindCommandParser parser = new FindCommandParser();

    @Nested
    @DisplayName("parse method")
    class Parse {
        @Test
        @DisplayName("should throw ParseException if no keywords")
        public void parse_emptyArg_throwsParseException() {
            assertParseFailure(
                    parser,
                    "     ",
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            FindCommand.MESSAGE_USAGE
                    )
            );
        }

        @Test
        @DisplayName("should generate FindCommand object if arguments are "
                + "valid")
        public void parse_validArgs_returnsFindContactCommand()
                throws Exception {
            // no leading and trailing whitespaces
            FindCommand actualFindCommand =
                    parser.parse(" in/c n/Alice e/test a/test p/000 t/tes");
            String expectedMessage = String.format(
                    MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
            expectedModel.updateFilteredPersonList(person -> false);
            assertCommandSuccess(
                    actualFindCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should generate FindCommand object if arguments are "
                + "valid")
        public void parse_validArgs_returnsFindJournalCommand() throws Exception {
            // no leading and trailing whitespaces

            FindCommand actualFindCommand = parser.parse(
                    " in/j n/test d/test at/2020-10-10 10:00 with/test t/tes");

            String expectedMessage =
                    String.format(MESSAGE_ENTRIES_LISTED_OVERVIEW, 0);
            expectedModel.updateFilteredEntryList(entry -> false);
            assertCommandSuccess(
                    actualFindCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }
    }
}
