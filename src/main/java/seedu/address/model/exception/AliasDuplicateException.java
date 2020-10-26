package seedu.address.model.exception;

public class AliasDuplicateException extends RuntimeException {
    public AliasDuplicateException() {
        super("The alias has been used");
    }
}

