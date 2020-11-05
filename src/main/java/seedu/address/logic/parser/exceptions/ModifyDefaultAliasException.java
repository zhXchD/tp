package seedu.address.logic.parser.exceptions;

public class ModifyDefaultAliasException extends AliasException {
    public ModifyDefaultAliasException() {
        super("You cannot modify the default command alias.");
    }
}
