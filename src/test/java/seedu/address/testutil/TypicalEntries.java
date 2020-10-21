package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Journal;
import seedu.address.model.journal.Entry;
import seedu.address.model.person.Person;

public class TypicalEntries {

    public static final Entry TEST_ENTRY_DEFAULT = new EntryBuilder().build(); //Default
    public static final Entry TEST_ENTRY_DIFF_DATE = new EntryBuilder()
            .withTitle("Product Design")
            .withDate("2000-12-21 19:00")
            .build();
    public static final Entry TEST_ENTRY_DIFF_DESCRIPTION = new EntryBuilder()
            .withTitle("Sample 1")
            .withDescription("Meet with boss")
            .withDate("2000-12-21 14:00")
            .build();
    public static final Entry TEST_ENTRY_DIFF_TITLE = new EntryBuilder()
            .withTitle("Board Meeting")
            .build();
    public static final Entry TEST_ENTRY_DIFF_CONTACTS = new EntryBuilder()
            .withTitle("Fish and chips")
            .withContacts(
                    getTypicalAddressBook()
                            .getPersonList()
                            .toArray(new Person[0])
            )
            .withDate("2000-12-13 14:00")
            .build();
    public static final Entry TEST_ENTRY_DIFF_TAGS = new EntryBuilder()
            .withTitle("Tea and biscuits")
            .withTags("queen", "tea", "biscuits")
            .withDate("2000-12-13 19:00")
            .build();
    public static final Entry TEST_ENTRY_SEVEN = new EntryBuilder()
            .withTitle("Scones and crumpets")
            .build();

    /**
     * Returns an {@code Journal} with all the typical entries.
     */
    public static Journal getTypicalJournal() {
        Journal journal = new Journal();
        for (Entry entry: getTypicalEntries()) {
            journal.addEntry(entry);
        }
        return journal;
    }

    public static List<Entry> getTypicalEntries() {
        return new ArrayList<>(
                Arrays.asList(
                        TEST_ENTRY_DEFAULT,
                        TEST_ENTRY_DIFF_DATE,
                        TEST_ENTRY_DIFF_DESCRIPTION,
                        TEST_ENTRY_DIFF_CONTACTS,
                        TEST_ENTRY_DIFF_TAGS
                )
        );
    }
}
