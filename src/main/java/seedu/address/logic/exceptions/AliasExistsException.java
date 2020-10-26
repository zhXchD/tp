package seedu.address.logic.exceptions;

public class AliasExistsException extends RuntimeException {
    public AliasExistsException() {
        super("Alias has been occupied.");
    }
}
