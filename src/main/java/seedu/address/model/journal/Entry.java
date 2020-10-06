package seedu.address.model.journal;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
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

    public ObservableList<Person> getContactList() {
        return contactList.asUnmodifiableObservableList();
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
                && toCheck.description.equals(description)
                && toCheck.contactList.equals(contactList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Entry) return this.isSameEntry((Entry) obj);
        else return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Date and time: ")
                .append(getDate())
                .append(" Description: ")
                .append(getDescription())
                .append(" Contacts: ");
        return builder.toString();
    }

}
