package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and
 * RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(
            getTypicalAddressBook(),
            new Journal(),
            new UserPrefs()
    );

    @Nested
    @DisplayName("execute method")
    class Execute {
        @Test
        @DisplayName("should edit person successfully in unfiltered list")
        public void execute_allFieldsSpecifiedUnfilteredList_success() {
            Person editedPerson = new PersonBuilder().build();
            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder(editedPerson).build();
            EditCommand editCommand =
                    new EditCommand(INDEX_FIRST_PERSON, descriptor);

            String expectedMessage = String.format(
                    EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                    editedPerson
            );

            Model expectedModel =
                    new ModelManager(new AddressBook(model.getAddressBook()),
                            new Journal(),
                            new UserPrefs()
                    );
            expectedModel.setPerson(
                    model.getFilteredPersonList().get(0),
                    editedPerson
            );

            assertCommandSuccess(
                    editCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should successfully edit person with only certain "
                + "fields specified")
        public void execute_someFieldsSpecifiedUnfilteredList_success() {
            Index indexLastPerson =
                    Index.fromOneBased(model.getFilteredPersonList().size());
            Person lastPerson = model.getFilteredPersonList()
                    .get(indexLastPerson.getZeroBased());

            PersonBuilder personInList = new PersonBuilder(lastPerson);
            Person editedPerson = personInList.withName(VALID_NAME_BOB)
                    .withPhone(VALID_PHONE_BOB)
                    .withTags(VALID_TAG_HUSBAND)
                    .build();

            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                            .withPhone(VALID_PHONE_BOB)
                            .withTags(VALID_TAG_HUSBAND)
                            .build();
            EditCommand editCommand =
                    new EditCommand(indexLastPerson, descriptor);

            String expectedMessage = String.format(
                    EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                    editedPerson
            );

            Model expectedModel =
                    new ModelManager(new AddressBook(model.getAddressBook()),
                            new Journal(),
                            new UserPrefs()
                    );
            expectedModel.setPerson(lastPerson, editedPerson);

            assertCommandSuccess(
                    editCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should successfully edit person with no field specified"
                + " in unfiltered list")
        public void execute_noFieldSpecifiedUnfilteredList_success() {
            EditCommand editCommand = new EditCommand(
                    INDEX_FIRST_PERSON,
                    new EditPersonDescriptor()
            );
            Person editedPerson = model.getFilteredPersonList()
                    .get(INDEX_FIRST_PERSON.getZeroBased());

            String expectedMessage = String.format(
                    EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                    editedPerson
            );

            Model expectedModel =
                    new ModelManager(new AddressBook(model.getAddressBook()),
                            new Journal(),
                            new UserPrefs()
                    );

            assertCommandSuccess(
                    editCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should successfully edit a person in a filtered list")
        public void execute_filteredList_success() {
            showPersonAtIndex(model, INDEX_FIRST_PERSON);

            Person personInFilteredList = model.getFilteredPersonList()
                    .get(INDEX_FIRST_PERSON.getZeroBased());
            Person editedPerson =
                    new PersonBuilder(personInFilteredList).withName(
                            VALID_NAME_BOB).build();
            EditCommand editCommand = new EditCommand(
                    INDEX_FIRST_PERSON,
                    new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                            .build()
            );

            String expectedMessage = String.format(
                    EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                    editedPerson
            );

            Model expectedModel =
                    new ModelManager(new AddressBook(model.getAddressBook()),
                            new Journal(),
                            new UserPrefs()
                    );
            expectedModel.setPerson(
                    model.getFilteredPersonList().get(0),
                    editedPerson
            );

            assertCommandSuccess(
                    editCommand,
                    model,
                    expectedMessage,
                    expectedModel
            );
        }

        @Test
        @DisplayName("should not edit a person if the person is a duplicate")
        public void execute_duplicatePersonUnfilteredList_failure() {
            Person firstPerson = model.getFilteredPersonList()
                    .get(INDEX_FIRST_PERSON.getZeroBased());
            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder(firstPerson).build();
            EditCommand editCommand =
                    new EditCommand(INDEX_SECOND_PERSON, descriptor);

            assertCommandFailure(
                    editCommand,
                    model,
                    EditCommand.MESSAGE_DUPLICATE_PERSON
            );
        }

        @Test
        @DisplayName("should not edit a person if the person is a duplicate "
                + "in a filtered list")
        public void execute_duplicatePersonFilteredList_failure() {
            showPersonAtIndex(model, INDEX_FIRST_PERSON);

            // edit person in filtered list into a duplicate in address book
            Person personInList = model.getAddressBook()
                    .getPersonList()
                    .get(INDEX_SECOND_PERSON.getZeroBased());
            EditCommand editCommand = new EditCommand(
                    INDEX_FIRST_PERSON,
                    new EditPersonDescriptorBuilder(personInList).build()
            );

            assertCommandFailure(
                    editCommand,
                    model,
                    EditCommand.MESSAGE_DUPLICATE_PERSON
            );
        }

        @Test
        @DisplayName("should not edit a person if the index is invalid in the"
                + " unfiltered list")
        public void execute_invalidPersonIndexUnfilteredList_failure() {
            Index outOfBoundIndex = Index.fromOneBased(
                    model.getFilteredPersonList().size() + 1);
            EditPersonDescriptor descriptor =
                    new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                            .build();
            EditCommand editCommand =
                    new EditCommand(outOfBoundIndex, descriptor);

            assertCommandFailure(
                    editCommand,
                    model,
                    Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX
            );
        }

        /**
         * Edit filtered list where index is larger than size of filtered list,
         * but smaller than size of address book
         */
        @Test
        @DisplayName("should not edit a person if the index is invalid in the"
                + " filtered list")
        public void execute_invalidPersonIndexFilteredList_failure() {
            showPersonAtIndex(model, INDEX_FIRST_PERSON);
            Index outOfBoundIndex = INDEX_SECOND_PERSON;
            // ensures that outOfBoundIndex is still in bounds of address book list
            assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook()
                    .getPersonList()
                    .size());

            EditCommand editCommand = new EditCommand(
                    outOfBoundIndex,
                    new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                            .build()
            );

            assertCommandFailure(
                    editCommand,
                    model,
                    Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX
            );
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        private final EditCommand standardCommand =
                new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        @Test
        @DisplayName("should return true if values are equal")
        public void equals_sameValue_true() {
            EditPersonDescriptor copyDescriptor =
                    new EditPersonDescriptor(DESC_AMY);
            EditCommand commandWithSameValues =
                    new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
            assertTrue(standardCommand.equals(commandWithSameValues));
        }

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(standardCommand.equals(standardCommand));
        }

        @Test
        @DisplayName("should return false if null")
        public void equals_null_false() {
            assertFalse(standardCommand.equals(null));
        }

        @Test
        @DisplayName("should return false if different types")
        public void equals_differentTypes_false() {
            assertFalse(standardCommand.equals(new ClearCommand()));
        }

        @Test
        @DisplayName("should return false if index is different")
        public void equals_differentIndex_false() {
            assertFalse(
                    standardCommand.equals(
                            new EditCommand(
                                    INDEX_SECOND_PERSON,
                                    DESC_AMY
                            )
                    )
            );
        }

        @Test
        @DisplayName("should return false if descriptor is different")
        public void equals_differentDescriptor_false() {
            assertFalse(
                    standardCommand.equals(
                            new EditCommand(
                                    INDEX_FIRST_PERSON,
                                    DESC_BOB
                            )
                    )
            );
        }
    }
}
