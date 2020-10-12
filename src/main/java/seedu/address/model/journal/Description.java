package seedu.address.model.journal;

public class Description {

    /*
     * The first character of the address must not be a whitespace and non-letter
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-z].*";
    public static final String MESSAGE_CONSTRAINTS = "This is not a valid description!";
    private static final String EMPTY_MESSAGE = "No description found!";

    public final String description;

    /**
     * Creates an instance of description for entry.
     *
     * @param description Description of an entry.
     */
    public Description(String description) {
        if (description == null) {
            this.description = EMPTY_MESSAGE;
        } else {
            this.description = description;
        }
    }

    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals(EMPTY_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Description
            && ((Description) other).description.equals(description));
    }

    @Override
    public String toString() {
        return description;
    }
}
