package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Nested
    @DisplayName("miscellaneous operations")
    class Misc {
        @Test
        @DisplayName("should throw UnsupportedOperationException if tags are "
                + "attempted to be removed")
        public void asObservableList_modifyList_throwsUnsupportedOperationException() {
            Person person = new PersonBuilder().build();
            assertThrows(UnsupportedOperationException.class, () ->
                    person.getTags().remove(0));
        }
    }

    @Nested
    @DisplayName("isSamePerson method")
    class IsSamePerson {
        private Person editedAlice = new PersonBuilder(ALICE)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .build();

        @Test
        @DisplayName("should return true if same object")
        public void isSamePerson_sameObject_true() {
            assertTrue(ALICE.isSamePerson(ALICE));
        }

        @Test
        @DisplayName("should return false if null")
        public void isSamePerson_null_false() {
            assertFalse(ALICE.isSamePerson(null));
        }

        @Test
        @DisplayName("should return false if different phone and email")
        public void isSamePerson_differentPhoneAndEmail_false() {
            assertFalse(ALICE.isSamePerson(editedAlice));
        }

        @Test
        @DisplayName("should return false if different name")
        public void isSamePerson_differentName_false() {
            editedAlice =
                    new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
            assertFalse(ALICE.isSamePerson(editedAlice));
        }

        @Test
        @DisplayName("should return true if same name, phone, email but "
                + "different attributes")
        public void isSamePerson_differentAttributes_true() {
            // same name, same phone, different attributes -> returns true
            editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                    .withAddress(VALID_ADDRESS_BOB)
                    .withTags(VALID_TAG_HUSBAND)
                    .build();
            assertTrue(ALICE.isSamePerson(editedAlice));

            // same name, same email, different attributes -> returns true
            editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                    .withAddress(VALID_ADDRESS_BOB)
                    .withTags(VALID_TAG_HUSBAND)
                    .build();
            assertTrue(ALICE.isSamePerson(editedAlice));

            // same name, same phone, same email, different attributes -> returns true
            editedAlice =
                    new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                            .withTags(VALID_TAG_HUSBAND)
                            .build();
            assertTrue(ALICE.isSamePerson(editedAlice));
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        private final Person aliceCopy =
                new PersonBuilder(ALICE).build(ALICE.getUuid());
        private Person editedAlice = new PersonBuilder(ALICE)
                .withName(VALID_NAME_BOB).build(ALICE.getUuid());

        @Test
        @DisplayName("should return true if same values")
        public void equals_sameValues_true() {
            assertTrue(ALICE.equals(aliceCopy));
        }

        @Test
        @DisplayName("should return true if same object")
        public void equals_sameObject_true() {
            assertTrue(ALICE.equals(ALICE));
        }

        @Test
        @DisplayName("should return false if null")
        public void equals_null_false() {
            assertFalse(ALICE.equals(null));
        }

        @Test
        @DisplayName("should return false if different type")
        public void equals_differentType_false() {
            assertFalse(ALICE.equals(5));
        }

        @Test
        @DisplayName("should return false if different person")
        public void equals_differentPerson_false() {
            assertFalse(ALICE.equals(BOB));
        }

        @Test
        @DisplayName("should return false if different name")
        public void equals_differentName_false() {
            assertFalse(ALICE.equals(editedAlice));
        }

        @Test
        @DisplayName("should return false if different phone")
        public void equals_differentPhone_false() {
            editedAlice =
                    new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
            assertFalse(ALICE.equals(editedAlice));
        }

        @Test
        @DisplayName("should return false if different email")
        public void equals_differentEmail_false() {
            editedAlice =
                    new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
            assertFalse(ALICE.equals(editedAlice));
        }

        @Test
        @DisplayName("should return false if different address")
        public void equals_differentAddress_false() {
            editedAlice =
                    new PersonBuilder(ALICE)
                            .withAddress(VALID_ADDRESS_BOB)
                            .build();
            assertFalse(ALICE.equals(editedAlice));
        }

        @Test
        @DisplayName("should return false if different tags")
        public void equals_differentTags_false() {
            editedAlice = new PersonBuilder(ALICE)
                    .withTags(VALID_TAG_HUSBAND)
                    .build();
            assertFalse(ALICE.equals(editedAlice));
        }
    }
}
