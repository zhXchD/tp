package seedu.address.model.journal;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

public class Entry {

    private final Title title;
    private final Date date;
    private final Description description;

    // Represents a contact list for a certain event
    private final UniquePersonList contactList;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Creates a new entry.
     */
    public Entry(Title title, Date date, Description description,
                 UniquePersonList contactList, Set<Tag> tags) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.contactList = contactList;
        this.tags.addAll(tags);
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
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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

    /**
     * Returns true if the person is related to this entry.
     *
     * @param person Person needs to check.
     * @return True if person is in {@code contactList}
     */
    public boolean isRelatedTo(Person person) {
        return contactList.contains(person);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Entry) {
            return this.isSameEntry((Entry) obj);
        }
        return false;
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
