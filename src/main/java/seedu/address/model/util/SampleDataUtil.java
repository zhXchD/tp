package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.Journal;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyJournal;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Description;
import seedu.address.model.journal.Entry;
import seedu.address.model.journal.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(
                    new Name("Alex Yeoh"),
                    new Phone("87438807"),
                    new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends"),
                    UUID.fromString("e26616c9-c740-4d86-861e-733a4d377a3e")
            ),
            new Person(
                    new Name("Bernice Yu"),
                    new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends"),
                    UUID.fromString("f73eaee6-a320-4005-b09c-98450d1ef661")
            ),
            new Person(new Name("Charlotte Oliveiro"),
                    new Phone("93210283"),
                    new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours"),
                    UUID.fromString("623afedd-5f71-4130-8ed3-7ebc0969d776")
            ),
            new Person(
                    new Name("David Li"),
                    new Phone("91031282"),
                    new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family"),
                    UUID.fromString("5f5df00a-60f6-43ad-aad5-77b17847c20a")
            ),
            new Person(
                    new Name("Irfan Ibrahim"),
                    new Phone("92492021"),
                    new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates"),
                    UUID.fromString("3f40a181-8153-4616-a69a-3362664f5a50")
            ),
            new Person(
                    new Name("Roy Balakrishnan"),
                    new Phone("92624417"),
                    new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"),
                    UUID.fromString("bfac8b18-3103-43a3-bd98-3de9d3375655")
            )
        };
    }

    public static Entry[] getSampleEntries() {
        return new Entry[] {
            new Entry(
                    new Title("Sample Journal 1"),
                    new Date(LocalDateTime.of(2020, 10, 10, 10, 10)),
                    new Description("Sample Journal 1"),
                    new UniquePersonList(),
                    getTagSet("10")
            ),
            new Entry(
                    new Title("Sample Journal 2"),
                    new Date(LocalDateTime.of(1990, 1, 1, 0, 0)),
                    new Description("Sample Journal 2"),
                    new UniquePersonList(),
                    getTagSet("11")
            ),
            new Entry(
                    new Title("A Big Event"),
                    new Date(LocalDateTime.of(2000, 1, 1, 0, 0)),
                    new Description("A Big Event"),
                    new UniquePersonList(),
                    getTagSet("event")
            ),
            new Entry(
                    new Title("Meeting"),
                    new Date(LocalDateTime.of(2500, 12, 31, 23, 59)),
                    new Description("I need to meet someone."),
                    new UniquePersonList(),
                    getTagSet("meeting")
            )
        };
    }

    public static ReadOnlyJournal getSampleJournal() {
        Journal sampleJournal = new Journal();
        for (Entry sampleEntry : getSampleEntries()) {
            sampleJournal.addEntry(sampleEntry);
        }
        return sampleJournal;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
