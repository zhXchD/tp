package seedu.address.logic.parser.exceptions;

public class AliasNotFoundException extends AliasException {
    public AliasNotFoundException() {
        super("Cannot delete alias because alias is not in system.");
    }
}
