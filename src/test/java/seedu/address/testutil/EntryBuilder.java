package seedu.address.testutil;

import seedu.address.model.journal.Date;
import seedu.address.model.journal.Description;
import seedu.address.model.journal.Entry;
import seedu.address.model.journal.Title;
import seedu.address.model.person.UniquePersonList;

public class EntryBuilder {

    public static final String DEFAULT_TITLE = "";
    public static final String DEFAULT_DESCRIPTION = "";
    public static final String DEFAULT_DATE = "";


    private Title title;
    private Date date;
    private Description description;

    // Represents a contact list for a certain event
    private UniquePersonList contactList;

    /**
     * Initialize the Entry with default value.
     */
    public EntryBuilder() {
        title = new Title(DEFAULT_TITLE);
        date = new Date(DEFAULT_DATE);
        description = new Description(DEFAULT_DESCRIPTION);
        contactList = new UniquePersonList();
    }

    /**
     * Initializes the Entry with a given entry.
     */
    public EntryBuilder(Entry entryToCopy) {
        title = entryToCopy.getTitle();
        date = entryToCopy.getDate();
        description = entryToCopy.getDescription();
        contactList = new UniquePersonList();
    }

    /**
     * Builds an entry with a title.
     *
     * @param title Title of the entry.
     * @return Entry with a new title.
     */
    public EntryBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Builds an entry with description.
     *
     * @param description Description of the entry.
     * @return Entry with a new description.
     */
    public EntryBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Builds an entry with a date.
     *
     * @param date Date of the entry.
     * @return Entry with a new date.
     */
    public EntryBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    public Entry build() {
        return new Entry(title, date, description, contactList);
    }
}
