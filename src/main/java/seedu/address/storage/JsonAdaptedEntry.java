package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
import seedu.address.model.tag.Tag;

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
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    // TODO: Add representation of UniquePersonList.
    // TODO: Add tags to tagList

    /**
     * Constructs a {@code JsonAdaptedEntry} with the given entry details.
     */
    @JsonCreator
    public JsonAdaptedEntry(
            @JsonProperty("title") String title,
            @JsonProperty("date") String date,
            @JsonProperty("description") String description,
            @JsonProperty("contactList") List<String> contactList,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged
    ) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.contactList = contactList;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedEntry(Entry source) {
        title = source.getTitle().title;
        date = source.getDate().value;
        description = source.getDescription().description;
        contactList = new ArrayList<>();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));

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
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

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

        if (description == null) {
            throw new IllegalValueException(
                    String.format(
                            MISSING_FIELD_MESSAGE_FORMAT,
                            Description.class.getSimpleName()
                    )
            );
        }
        Description modelDescription = new Description(description);

        UniquePersonList modelPersonList = new UniquePersonList();
        addressBook.getPersonList()
                .stream()
                .filter(
                        person -> contactList.stream()
                                .map(UUID::fromString)
                                .anyMatch(uuid -> person.getUuid().equals(uuid))
                )
                .forEach(modelPersonList::add);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Entry(
                modelTitle,
                modelDate,
                modelDescription,
                modelPersonList,
                modelTags
        );
    }
}
