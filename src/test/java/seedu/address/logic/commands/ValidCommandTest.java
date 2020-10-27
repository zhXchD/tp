package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ValidCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class ValidCommandTest {

    @Nested
    @DisplayName("commandTypeOf method")
    class CommandTypeOf {

        @Test
        @DisplayName("Should return a command type when the alias is valid")
        public void commandTypeOf_valid_returnsCommandType() {
            try {
                assertEquals(ValidCommand.SWITCH, ValidCommand.commandTypeOf("swt"));
            } catch (ParseException e) {
                fail();
            }
        }

        @Test
        @DisplayName("Should throw ParseException when the alias is invalid")
        public void commandTypeOf_invalid_throwsParseException() {
            assertThrows(ParseException.class, () -> ValidCommand.commandTypeOf("type"));
        }
    }

    @Nested
    @DisplayName("addAlias method")
    class AddAlias {

        @Test
        @DisplayName("Should add an alias to the alias map")
        public void addAlias_success_addAliasToMap() {
            assertThrows(ParseException.class, () -> ValidCommand.commandTypeOf("change"));
            ValidCommand.addAlias(ValidCommand.SWITCH, "change");
            try {
                assertEquals(ValidCommand.SWITCH, ValidCommand.commandTypeOf("change"));
            } catch (ParseException e) {
                fail();
            }
        }
    }
}
