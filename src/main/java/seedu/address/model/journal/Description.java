package seedu.address.model.journal;

import static java.util.Objects.requireNonNull;

public class Description {

    public final String description;

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-z]*";

    /**
     * Creates an instance of description for entry.
     *
     * @param description Description of an entry.
     */
    public Description(String description) {
        requireNonNull(description);
        this.description = description;
    }

    public boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
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
