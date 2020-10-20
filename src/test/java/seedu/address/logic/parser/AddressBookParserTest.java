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
import seedu.address.logic.commands.ClearAddressBookCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteContactCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Nested
    @DisplayName("parseCommand method")
    class ParseCommand {
        @Test
        @DisplayName("should generate AddContactCommand object from appropriate add "
                + "person input")
        public void parseCommand_add() throws Exception {
            UUID uuid = UUID.randomUUID();
            AddressBookParser parser = new AddressBookParser(uuid);
            Person person = new PersonBuilder().build(uuid);
            AddContactCommand command = (AddContactCommand) parser
                    .parseCommand(PersonUtil.getAddCommand(person));
            assertEquals(new AddContactCommand(person), command);
        }

        @Test
        @DisplayName("should generate ClearAddressBookCommand object from appropriate "
                + "clear input")
        public void parseCommand_clear() throws Exception {
            assertTrue(parser.parseCommand(ClearAddressBookCommand.COMMAND_WORD)
                    instanceof ClearAddressBookCommand);
            assertTrue(parser.parseCommand(
                    ClearAddressBookCommand.COMMAND_WORD + " 3")
                    instanceof ClearAddressBookCommand);
        }

        @Test
        @DisplayName("should generate DeleteContactCommand object from appropriate "
                + "delete person input")
        public void parseCommand_delete() throws Exception {
            DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
                    DeleteContactCommand.COMMAND_WORD + " "
                            + INDEX_FIRST_PERSON.getOneBased());
            assertEquals(new DeleteContactCommand(INDEX_FIRST_PERSON), command);
        }

        @Test
        @DisplayName("should generate EditCommand object from appropriate "
                + "edit person input")
        public void parseCommand_edit() throws Exception {
            Person person = new PersonBuilder().build();
            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder(person).build();
            EditCommand command = (EditCommand) parser.parseCommand(
                    EditCommand.COMMAND_WORD + " "
                            + INDEX_FIRST_PERSON.getOneBased() + " "
                            + PersonUtil.getEditPersonDescriptorDetails(
                            descriptor));
            assertEquals(
                    new EditCommand(INDEX_FIRST_PERSON, descriptor),
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
        @DisplayName("should generate FindCommand object from appropriate "
                + "find keyword input")
        public void parseCommand_find() throws Exception {
            Command command = parser.parseCommand("find in/c n/test");
            assertTrue(command instanceof FindCommand);
        }

        @Test
        @DisplayName("should generate HelpCommand object from appropriate "
                + "help input")
        public void parseCommand_help() throws Exception {
            assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD)
                    instanceof HelpCommand);
            assertTrue(parser.parseCommand(
                    HelpCommand.COMMAND_WORD + " 3")
                    instanceof HelpCommand);
        }

        @Test
        @DisplayName("should generate ListContactCommand object from appropriate "
                + "list input")
        public void parseCommand_list() throws Exception {
            assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD)
                    instanceof ListContactCommand);
            assertTrue(parser.parseCommand(
                    ListContactCommand.COMMAND_WORD + " 3")
                    instanceof ListContactCommand);
        }

        @Test
        @DisplayName("should throw ParseException if input not recognised")
        public void parseCommand_unrecognisedInput_throwsParseException() {
            assertThrows(
                    ParseException.class,
                    String.format(
                            MESSAGE_INVALID_COMMAND_FORMAT,
                            HelpCommand.MESSAGE_USAGE
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
