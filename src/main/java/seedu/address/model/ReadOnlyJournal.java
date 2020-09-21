package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.journal.Journal;

public interface ReadOnlyJournal {

    /**
     * Returns an unmodifiable list of the entries.
     */
    ObservableList<Journal> getEntryList();

}
