package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditJournalEntryCommand.EditEntryDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.journal.Entry;
import seedu.address.model.journal.TitleContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditEntryDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "91111111";
    public static final String VALID_PHONE_BOB = "82222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_DATE_OCTOBER = "2020-10-10 10:00";
    public static final String VALID_DATE_NOVEMBER = "2020-11-11 11:00";
    public static final String VALID_TITLE_MEETING = "Meeting";
    public static final String VALID_TITLE_MOVIE = "Movie";
    public static final String VALID_DESCRIPTION_STORY = "Brainstorm user stories";
    public static final String VALID_DESCRIPTION_MOVIE = "Watch a movie";
    public static final String CONTACT_DEFAULT_UUID = "e26616c9-c740-4d86"
            + "-861e-733a4d377a3e";

    public static final String NAME_DESC_AMY =
            " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB =
            " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY =
            " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB =
            " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY =
            " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB =
            " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY =
            " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB =
            " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND =
            " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND =
            " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String DATE_DESC_OCTOBER =
            " " + PREFIX_DATE_AND_TIME + VALID_DATE_OCTOBER;
    public static final String DATE_DESC_NOVEMBER =
            " " + PREFIX_DATE_AND_TIME + VALID_DATE_NOVEMBER;
    public static final String TITLE_DESC_MEETING =
            " " + PREFIX_NAME + VALID_TITLE_MEETING;
    public static final String DESCRIPTION_DESC_STORY =
            " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_STORY;
    public static final String DESCRIPTION_DESC_MOVIE =
            " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_MOVIE;
    public static final String CONTACTS_DESC_AMY =
            " " + PREFIX_CONTACT + VALID_NAME_AMY;

    public static final String INVALID_NAME_DESC =
            " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC =
            " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC =
            " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC =
            " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC =
            " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TITLE_DESC =
            " " + PREFIX_NAME + "1"; // start with number not allowed
    public static final String INVALID_DATE_DESC =
            " " + PREFIX_DATE_AND_TIME + "2020/10/10 10:00";
    public static final String INVALID_DESCRIPTION_DESC =
            " " + PREFIX_DESCRIPTION + " "; // blank string not allowed
    public static final String INVALID_CONTACT_DESC =
            " " + PREFIX_CONTACT + "James&"; // '&' not allowed in name


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditContactCommand.EditPersonDescriptor DESC_AMY;
    public static final EditContactCommand.EditPersonDescriptor DESC_BOB;

    public static final EditEntryDescriptor DESC_MEETING;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND)
                .build();
        DESC_BOB = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .build();
        DESC_MEETING = new EditEntryDescriptorBuilder()
                .withTitle(VALID_TITLE_MEETING)
                .withDescription(VALID_DESCRIPTION_STORY)
                .withDate(VALID_DATE_OCTOBER)
                .withTags(VALID_TAG_FRIEND)
                .withContacts(ALICE, BENSON)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches
     * {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(
            Command command,
            Model actualModel,
            CommandResult expectedCommandResult,
            Model expectedModel
    ) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError(
                    "Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to
     * {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(
            Command command,
            Model actualModel,
            String expectedMessage,
            Model expectedModel
    ) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in
     * {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(
            Command command,
            Model actualModel,
            String expectedMessage
    ) {
        // we are unable to defensively copy the model for comparison later, so
        // we can only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(
                actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(
                actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () ->
                command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the
     * given {@code targetIndex} in the {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(
                targetIndex.getZeroBased()
                        < model.getFilteredPersonList().size()
        );

        Person person = model.getFilteredPersonList()
                .get(targetIndex.getZeroBased());
        final String[] splitName = person.getName()
                .fullName.split("\\s+");
        model.updateFilteredPersonList(
                new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the entry at the
     * given {@code targetIndex} in the {@code model}'s journal.
     */
    public static void showEntryAtIndex(Model model, Index targetIndex) {
        assertTrue(
                targetIndex.getZeroBased() < model.getFilteredPersonList().size()
        );
        Entry entry = model.getFilteredEntryList()
                .get(targetIndex.getZeroBased());
        final String[] splitTitle = entry.getTitle().title.split("\\s+");
        model.updateFilteredEntryList(
                new TitleContainsKeywordsPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredEntryList().size());

    }

}
