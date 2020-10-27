package seedu.address.logic.parser.exceptions;

public class AliasExistsException extends RuntimeException {
    public AliasExistsException() {
        super("Alias has been occupied.");
    }
}
