package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Description;
import seedu.address.model.journal.Entry;
import seedu.address.model.journal.Title;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Jackson-friendly version of {@link Entry}
 */
public class JsonAdaptedEntry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT =
            "Entry's %s field is missing!";

    private final String title;
    private final String date;
    private final String description;
    private final List<String> contactList;

    // TODO: Add representation of UniquePersonList.
    // TODO: Add tags to Entry?
    /**
     * Constructs a {@code JsonAdaptedEntry} with the given entry details.
     */
    @JsonCreator
    public JsonAdaptedEntry(
            @JsonProperty("title") String title,
            @JsonProperty("date") String date,
            @JsonProperty("description") String description,
            @JsonProperty("contactList") List<String> contactList
    ) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.contactList = contactList;
    }

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedEntry(Entry source) {
        title = source.getTitle().title;
        date = source.getDate().value;
        description = source.getDescription().description;
        contactList = new ArrayList<>();

        for (Person person : source.getContactList()) {
            contactList.add(person.getUuid().toString());
        }
    }

    /**
     * Converts this Jackson-friendly adapted entry object into the model's
     * {@code Entry} object.
     *
     * @param addressBook to create contact lists.
     * @throws IllegalValueException if there were any data constraints violated
     * in the adapted person.
     */
    public Entry toModelType(ReadOnlyAddressBook addressBook)
            throws IllegalValueException {

        if (title == null) {
            throw new IllegalValueException(
                    String.format(
                            MISSING_FIELD_MESSAGE_FORMAT,
                            Title.class.getSimpleName()
                    )
            );
        }
        final Title modelTitle = new Title(title);

        if (date == null) {
            throw new IllegalValueException(
                    String.format(
                            MISSING_FIELD_MESSAGE_FORMAT,
                            Date.class.getSimpleName()
                    )
            );
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        Description modelDescription = new Description(
                Objects.requireNonNullElse(description, ""));

        UniquePersonList modelPersonList = new UniquePersonList();
        addressBook.getPersonList()
                .parallelStream()
                .filter(
                        person -> contactList.parallelStream()
                                .map(UUID::fromString)
                                .anyMatch(uuid -> person.getUuid().equals(uuid))
                )
                .forEach(modelPersonList::add);

        return new Entry(
                modelTitle,
                modelDate,
                modelDescription,
                modelPersonList
        );
    }
}
