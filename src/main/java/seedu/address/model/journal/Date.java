package seedu.address.model.journal;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

/**
 * Represents date for journal.
 */
public class Date {

    private final LocalDateTime date;
    public final String value;

    /**
     * Creates an instance of date to represent the date of the entry.
     *
     * @param date Event date.
     */
    public Date(LocalDateTime date) {
        requireNonNull(date);
        this.date = date;
        this.value = date.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Date && ((Date) other).date == this.date);
    }
}

