package seedu.address.model.journal.exceptions;

public class ContactNotInListException extends RuntimeException {

    public ContactNotInListException() {
        super("This contact is not inside the contact list");
    }
}
