package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OCTOBER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_STORY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.AliasMap;
import seedu.address.model.Journal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.journal.Entry;
import seedu.address.testutil.EntryBuilder;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code AddJournalEntryCommand}.
 */
public class AddJournalEntryCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(
                getTypicalAddressBook(), new Journal(), new UserPrefs(), new AliasMap());
        model.addEntry(
                new EntryBuilder()
                        .withTitle(VALID_TITLE_MEETING)
                        .withDate(VALID_DATE_OCTOBER)
                        .withDescription(VALID_DESCRIPTION_STORY)
                        .build()
        );
    }

    @Nested
    @DisplayName("execute")
    class Execute {
        @Test
        @DisplayName("should successfully add new entry")
        public void execute_newEntry_success() {
            Entry validEntry = new EntryBuilder().build();

            Model expectedModel = new ModelManager(
                    model.getAddressBook(), model.getJournal(), new UserPrefs(), new AliasMap());
            expectedModel.addEntry(validEntry);

            assertCommandSuccess(
                    new AddJournalEntryCommand(validEntry),
                    model,
                    String.format(AddJournalEntryCommand.MESSAGE_SUCCESS, validEntry),
                    expectedModel
            );
        }

        @Test
        @DisplayName("should throw CommandException if a duplicate entry is "
                + "added")
        public void execute_duplicateEntry_throwsCommandException() {
            Entry entryInList = model.getJournal().getEntryList().get(0);
            assertCommandFailure(
                    new AddJournalEntryCommand(entryInList),
                    model,
                    AddJournalEntryCommand.MESSAGE_DUPLICATE_ENTRY
            );
        }
    }
}
