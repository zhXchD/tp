package seedu.address.model.journal;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Title {

    /*
     * The first character of the address must not be a whitespace and non-letter
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-z].*";
    public static final String MESSAGE_CONSTRAINTS =
            "Titles should start with a letter, and it should not be blank";

    public final String title;

    /**
     * Creates an instance of title for entry.
     *
     * @param title Title of the entry.
     */
    public Title(String title) {
        requireNonNull(title);
        checkArgument(isValidTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    public static boolean isValidTitle(String test) {
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
