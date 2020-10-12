package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Journal;
import seedu.address.model.journal.Entry;

public class TypicalEntries {

    public static final Entry ENTRY_DEFAULT = new EntryBuilder().build(); //Default

    public static final Entry TEST_ENTRY_DIFF_DATE = new EntryBuilder().withTitle("Product Design")
            .withDate("2020-12-21 19:00").build();

    public static final Entry TEST_ENTRY_DIFF_DECRIPTION = new EntryBuilder().withTitle("Sample 1")
            .withDescription("Meet with boss").build();

    public static final Entry TEST_ENTRY_DIFFTITLE = new EntryBuilder().withTitle("Board Meeting").build();

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
                ENTRY_DEFAULT, TEST_ENTRY_DIFF_DATE, TEST_ENTRY_DIFF_DECRIPTION, TEST_ENTRY_DIFFTITLE));
    }
}
