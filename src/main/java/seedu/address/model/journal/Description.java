package seedu.address.model.journal;

import static java.util.Objects.requireNonNullElse;

public class Description {

    /*
     * The first character of the address must not be a whitespace and non-letter
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final Description EMPTY_DESCRIPTION = new Description(null);
    private static final String EMPTY_MESSAGE = "No description found!";

    public final String description;

    /**
     * Creates an instance of description for entry.
     *
     * @param description Description of an entry.
     */
    public Description(String description) {
        description = requireNonNullElse(description, "").trim();
        if (description.equals("")) {
            this.description = EMPTY_MESSAGE;
        } else {
            this.description = description;
        }
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
