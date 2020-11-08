package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddContactCommand;
import seedu.address.logic.commands.AddJournalEntryCommand;
import seedu.address.logic.commands.CheckScheduleCommand;
import seedu.address.logic.commands.ClearAddressBookCommand;
import seedu.address.logic.commands.ClearJournalCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteContactCommand;
import seedu.address.logic.commands.DeleteJournalEntryCommand;
import seedu.address.logic.commands.EditContactCommand;
import seedu.address.logic.commands.EditContactCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindContactCommand;
import seedu.address.logic.commands.FindJournalEntryCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListContactCommand;
import seedu.address.logic.commands.ListJournalEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class IntelliJournalParserTest {

    private final IntelliJournalParser parser = new IntelliJournalParser();

    @Nested
    @DisplayName("parseCommand method")
    class ParseCommand {
        @Test
        @DisplayName("should generate AddContactCommand object from appropriate"
                + " add person input")
        public void parseCommand_addContact() throws Exception {
            UUID uuid = UUID.randomUUID();
            IntelliJournalParser parser = new IntelliJournalParser(uuid);
            Person person = new PersonBuilder().build(uuid);
            AddContactCommand command = (AddContactCommand) parser
                    .parseCommand(PersonUtil.getAddCommand(person));
            assertEquals(new AddContactCommand(person), command);
        }

        @Test
        @DisplayName("should generate AddJournalEntryCommand object from "
                + "appropriate add person input")
        public void parseCommand_addJournalEntry() throws Exception {
            assertTrue(parser.parseCommand(
                    AddJournalEntryCommand.COMMAND_WORD + " n/test"
            ) instanceof AddJournalEntryCommand);
        }

        @Test
        @DisplayName("should generate ClearAddressBookCommand object from "
                + "appropriate clear input")
        public void parseCommand_clearAddressBook() throws Exception {
            assertTrue(parser.parseCommand(ClearAddressBookCommand.COMMAND_WORD)
                    instanceof ClearAddressBookCommand);
            assertTrue(parser.parseCommand(
                    ClearAddressBookCommand.COMMAND_WORD + " 3")
                    instanceof ClearAddressBookCommand);
        }

        @Test
        @DisplayName("should generate ClearJournalCommand object from "
                + "appropriate clear input")
        public void parseCommand_clearJournal() throws Exception {
            assertTrue(parser.parseCommand(ClearJournalCommand.COMMAND_WORD)
                    instanceof ClearJournalCommand);
            assertTrue(parser.parseCommand(
                    ClearJournalCommand.COMMAND_WORD + " 3")
                    instanceof ClearJournalCommand);
        }

        @Test
        @DisplayName("should generate DeleteContactCommand object from "
                + "appropriate delete person input")
        public void parseCommand_deleteContact() throws Exception {
            DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
                    DeleteContactCommand.COMMAND_WORD + " "
                            + INDEX_FIRST_PERSON.getOneBased());
            assertEquals(new DeleteContactCommand(INDEX_FIRST_PERSON), command);
        }

        @Test
        @DisplayName("should generate DeleteJournalEntryCommand object from "
                + "appropriate delete journal entry input")
        public void parseCommand_deleteJournalEntry() throws Exception {
            DeleteJournalEntryCommand command =
                    (DeleteJournalEntryCommand) parser.parseCommand(
                            DeleteJournalEntryCommand.COMMAND_WORD + " "
                                    + INDEX_FIRST_PERSON.getOneBased());
            assertEquals(
                    new DeleteJournalEntryCommand(INDEX_FIRST_PERSON), command);
        }

        @Test
        @DisplayName("should generate EditCommand object from appropriate "
                + "edit person input")
        public void parseCommand_edit() throws Exception {
            Person person = new PersonBuilder().build();
            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder(person).build();
            EditContactCommand command = (EditContactCommand) parser.parseCommand(
                    EditContactCommand.COMMAND_WORD + " "
                            + INDEX_FIRST_PERSON.getOneBased() + " "
                            + PersonUtil.getEditPersonDescriptorDetails(
                            descriptor));
            assertEquals(
                    new EditContactCommand(INDEX_FIRST_PERSON, descriptor),
                    command
            );
        }

        @Test
        @DisplayName("should generate ExitCommand object from appropriate "
                + "exit input")
        public void parseCommand_exit() throws Exception {
            assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD)
                    instanceof ExitCommand);
            assertTrue(parser.parseCommand(
                    ExitCommand.COMMAND_WORD + " 3")
                    instanceof ExitCommand);
        }

        @Test
        @DisplayName("should generate FindContactCommand object from "
                + "appropriate find keyword input")
        public void parseCommand_findContact() throws Exception {
            Command command = parser.parseCommand("findc n/test");
            assertTrue(command instanceof FindContactCommand);
        }

        @Test
        @DisplayName("should generate FindJournalEntryCommand object from "
                + "appropriate find keyword input")
        public void parseCommand_findJournalEntry() throws Exception {
            Command command = parser.parseCommand("findj n/test");
            assertTrue(command instanceof FindJournalEntryCommand);
        }

        @Test
        @DisplayName("should generate HelpCommand object from appropriate "
                + "help input")
        public void parseCommand_help() throws Exception {
            assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD)
                    instanceof HelpCommand);
            assertTrue(parser.parseCommand(
                    HelpCommand.COMMAND_WORD + " of/addc")
                    instanceof HelpCommand);
        }

        @Test
        @DisplayName("should generate ListContactCommand object from "
                + "appropriate list input")
        public void parseCommand_listContact() throws Exception {
            assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD)
                    instanceof ListContactCommand);
            assertTrue(parser.parseCommand(
                    ListContactCommand.COMMAND_WORD + " 3")
                    instanceof ListContactCommand);
        }

        @Test
        @DisplayName("should generate ListJournalEntryCommand object from "
                + "appropriate list input")
        public void parseCommand_listEntry() throws Exception {
            assertTrue(parser.parseCommand(ListJournalEntryCommand.COMMAND_WORD)
                    instanceof ListJournalEntryCommand);
            assertTrue(parser.parseCommand(
                    ListJournalEntryCommand.COMMAND_WORD + " 3")
                    instanceof ListJournalEntryCommand);
        }

        @Test
        @DisplayName("should generate CheckScheduleCommand object from "
                + "appropriate check schedule input")
        public void parseCommand_checkSchedule() throws Exception {
            assertTrue(parser.parseCommand(CheckScheduleCommand.COMMAND_WORD)
                    instanceof CheckScheduleCommand);
            assertTrue(parser.parseCommand(
                    CheckScheduleCommand.COMMAND_WORD + " 2020-03-04"
            ) instanceof CheckScheduleCommand);
        }

        @Test
        @DisplayName("should throw ParseException if input not recognised")
        public void parseCommand_unrecognisedInput_throwsParseException() {
            assertThrows(
                    ParseException.class,
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            HelpCommand.getMessageUsage("help")
                    ), () -> parser.parseCommand("")
            );
        }

        @Test
        @DisplayName("should throw ParseException if input is an unknown "
                + "command")
        public void parseCommand_unknownCommand_throwsParseException() {
            assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                            parser.parseCommand("unknownCommand"));
        }
    }
}
