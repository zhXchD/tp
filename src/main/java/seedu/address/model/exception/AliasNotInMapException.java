package seedu.address.model.exception;

public class AliasNotInMapException extends RuntimeException {

    public AliasNotInMapException(String alias) {
        super(alias + " is not a valid alias");
    }
}

