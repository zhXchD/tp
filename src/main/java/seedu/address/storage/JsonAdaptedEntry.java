package seedu.address.storage;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Description;
import seedu.address.model.journal.Entry;
import seedu.address.model.journal.Title;
import seedu.address.model.person.UniquePersonList;

/**
 * Jackson-friendly version of {@link Entry}
 */
public class JsonAdaptedEntry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT =
            "Person's %s field is missing!";

    private final String title;
    private final String date;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedEntry} with the given entry details.
     */
    @JsonCreator
    public JsonAdaptedEntry(
            @JsonProperty("title") String title,
            @JsonProperty("date") String date,
            @JsonProperty("description") String description
    ) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedEntry(Entry source) {
        title = source.getTitle().title;
        date = source.getDate().value;
        description = source.getDescription().description;
    }

    /**
     * Converts this Jackson-friendly adapted entry object into the model's
     * {@code Entry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated
     * in the adapted person.
     */
    public Entry toModelType() throws IllegalValueException {

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
        return new Entry(
                modelTitle,
                modelDate,
                modelDescription,
                modelPersonList
        );
    }

}
