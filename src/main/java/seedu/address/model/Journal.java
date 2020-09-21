package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.journal.Entry;
import seedu.address.model.journal.UniqueEntryList;

public class Journal implements ReadOnlyJournal {

    private final UniqueEntryList entryList;

    public Journal() {
        this.entryList = new UniqueEntryList();
    }

    public Journal(ReadOnlyJournal newData) {
        this();
        resetData(newData);
    }

    // operations
    public void addEntry(Entry e) {
        requireNonNull(e);
        this.entryList.add(e);
    }

    public void removeEntry(Entry e) {
        requireNonNull(e);
        this.entryList.remove(e);
    }

    // util methods

    public boolean hasEntry(Entry toCheck) {
        return this.entryList.contains(toCheck);
    }

    public void resetData(ReadOnlyJournal newData) {
        requireNonNull(newData);

        setEntries(newData.getEntryList());
    }

    public void setEntries(List<Entry> entryList) {
        this.entryList.setEntries(entryList);
    }

    @Override
    public ObservableList<Entry> getEntryList() {
        return entryList.asUnmodifiableObservableList();
    }
}
