package seedu.address.logic.parser.exceptions;

public class AliasExistsException extends AliasException {
    public AliasExistsException() {
        super("Alias has been occupied.");
    }
}
