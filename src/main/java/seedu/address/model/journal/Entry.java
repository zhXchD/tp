package seedu.address.model.journal;

import seedu.address.model.person.UniquePersonList;

public class Entry {

    private final Title title;
    private final Date date;
    private final Description description;

    // Represents a contact list for a certain event
    private final UniquePersonList contactList;

    /**
     * Creates a new entry.
     */
    public Entry(Title title, Date date, Description description, UniquePersonList contactList) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.contactList = contactList;
    }

    public Title getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if the given entry is the same as the current one.
     */
    public boolean isSameEntry(Entry toCheck) {
        if (toCheck == this) {
            return true;
        }

        return toCheck != null && toCheck.title.equals(title)
                && toCheck.date.equals(date)
                && toCheck.description.equals(description);
    }

}
