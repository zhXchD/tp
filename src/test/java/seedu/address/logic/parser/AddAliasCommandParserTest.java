package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddAliasCommandParserTest {

    @Nested
    @DisplayName("parse method")
    class Parse {

        private final AddAliasCommandParser parser = new AddAliasCommandParser();
        private final String noArgsInput = " ";
        private final String oneArgInput = " switch";

        @Test
        @DisplayName("Should throw NullPointerException if pass in a null")
        public void parse_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    parser.parse("alias", null));
        }

        @Test
        @DisplayName("Should throw ParseException if the argument is wrong")
        public void parse_invalidArgs_throwsParseException() {
            assertThrows(ParseException.class, () ->
                    parser.parse("alias", noArgsInput));
            assertThrows(ParseException.class, () ->
                    parser.parse("alias", oneArgInput));
        }

        @Test
        @DisplayName("Should return a command if the format is correct")
        public void parse_valid_success() {
            Command expectedCommand = new AddAliasCommand("switch", "si");

            try {
                String validInput = " switch si";
                Command command = parser.parse("alias", validInput);
                assertEquals(expectedCommand, command);
            } catch (Exception e) {
                fail();
            }
        }
    }
}
