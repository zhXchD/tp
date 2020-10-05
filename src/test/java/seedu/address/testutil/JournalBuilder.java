package seedu.address.testutil;

import seedu.address.model.Journal;
import seedu.address.model.journal.Entry;

/**
 * A utility class to build the journal.
 */
public class JournalBuilder {

    private Journal journal;

    /**
     * Initializes the builder with a new journal.
     */
    public JournalBuilder() {
        journal = new Journal();
    }

    /**
     * Initializes the builder with a given journal.
     */
    public JournalBuilder(Journal journal) {
        this.journal = journal;
    }

    /**
     * Add new entry to the journal
     */
    public JournalBuilder withEntry(Entry entryToAdd) {
        this.journal.addEntry(entryToAdd);
        return this;
    }

    public Journal build() {
        return journal;
    }
}
