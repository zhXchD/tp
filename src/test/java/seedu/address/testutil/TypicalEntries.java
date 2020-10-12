package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Journal;
import seedu.address.model.journal.Entry;

public class TypicalEntries {

    public static final Entry ENTRY_DEFAULT = new EntryBuilder().build(); //Default
    public static final Entry TEST_ENTRY_DIFF_DATE = new EntryBuilder()
            .withTitle("Different title")
            .withDate("2020-12-21 19:00")
            .build();
    public static final Entry TEST_ENTRY_DIFF_DESCRIPTION = new EntryBuilder()
            .withTitle("Another title")
            .withDescription("Meet with boss")
            .build();
    public static final Entry TEST_ENTRY_DIFF_TITLE = new EntryBuilder()
            .withTitle("Board Meeting")
            .build();
    public static final Entry TEST_ENTRY_DIFF_CONTACTS = new EntryBuilder()
            .withTitle("Fish and chips")
            .withContacts(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE)
            .build();

    /**
     * Returns an {@code Journal} with all the typical entries.
     */
    public static Journal getTypicalJournal() {
        Journal journal = new Journal();
        for (Entry entry: getTypicalEntry()) {
            journal.addEntry(entry);
        }
        return journal;
    }

    public static List<Entry> getTypicalEntry() {
        return new ArrayList<>(Arrays.asList(
                ENTRY_DEFAULT, TEST_ENTRY_DIFF_DATE,
                TEST_ENTRY_DIFF_DESCRIPTION,
                TEST_ENTRY_DIFF_TITLE
        ));
    }
}
