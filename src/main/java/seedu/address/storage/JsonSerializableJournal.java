package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Journal;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyJournal;
import seedu.address.model.journal.Entry;

/**
 * An Immutable Journal that is serializable to JSON format.
 */
@JsonRootName(value = "journal")
public class JsonSerializableJournal {

    public static final String MESSAGE_DUPLICATE_ENTRY =
            "Entries list contains duplicate entry/entries.";

    private final List<JsonAdaptedEntry> entries = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableJournal} with the given persons.
     */
    @JsonCreator
    public JsonSerializableJournal(
            @JsonProperty("entries") List<JsonAdaptedEntry> entries) {
        assert entries != null;
        this.entries.addAll(entries);
    }

    /**
     * Converts a given {@code ReadOnlyJournal} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created
     * {@code JsonSerializableJournal}.
     */
    public JsonSerializableJournal(ReadOnlyJournal source) {
        entries.addAll(
                source
                        .getEntryList()
                        .stream()
                        .map(JsonAdaptedEntry::new)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Converts this address book into the model's {@code Journal} object.
     *
     * @param addressBook to create contact lists.
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Journal toModelType(ReadOnlyAddressBook addressBook)
            throws IllegalValueException {
        Journal journal = new Journal();
        for (JsonAdaptedEntry jsonAdaptedEntry : entries) {
            Entry entry = jsonAdaptedEntry.toModelType(addressBook);
            if (journal.hasEntry(entry)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ENTRY);
            }
            journal.addEntry(entry);
        }
        return journal;
    }

}
