package seedu.address.model.journal;

import static java.util.Objects.requireNonNull;

public class Title {

    public final String title;

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    /**
     * Creates an instance of title for entry.
     *
     * @param title Title of the entry.
     */
    public Title(String title) {
        requireNonNull(title);
        this.title = title;
    }

    public boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Title
                && ((Title) other).title.equals(title));
    }

    @Override
    public String toString() {
        return title;
    }
}
