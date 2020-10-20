package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_DEFAULT;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_DIFF_TITLE;
import static seedu.address.testutil.TypicalEntries.TEST_ENTRY_SEVEN;
import static seedu.address.testutil.TypicalEntries.getTypicalJournal;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Journal;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyJournal;

class JsonJournalStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src",
            "test",
            "data",
            "JsonJournalStorageTest"
    );

    private Optional<ReadOnlyJournal> readJournal(String filePath)
            throws Exception {
        ReadOnlyAddressBook addressBook = getTypicalAddressBook();
        return new JsonJournalStorage(Paths.get(filePath))
                .readJournal(getTypicalAddressBook(),
                        addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Nested
    @DisplayName("readJournal method")
    class ReadJournal {
        @Test
        @DisplayName("should throw NullPointerException if file path is null")
        public void readJournal_nullFilePath_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    readJournal(null));
        }

        @Test
        @DisplayName("should return empty result if file is missing")
        public void readJournal_missingFile_emptyResult() throws Exception {
            assertFalse(readJournal("NonExistingFile.json").isPresent());
        }

        @Test
        @DisplayName("should throw DataConversionException if file is not in "
                + "json format")
        public void readJournal_notJsonFormat_throwsDataConversionException() {
            assertThrows(DataConversionException.class, () ->
                    readJournal("notJsonFormatJournal.json"));
        }

        @Test
        @DisplayName("should throw DataConversionException if journal has "
                + "invalid entries")
        public void readJournal_invalidEntryJournal_throwsDataConversionException() {
            assertThrows(DataConversionException.class, () ->
                    readJournal("invalidEntryJournal.json"));
        }

        @Test
        @DisplayName("should throw DataConversionException if journal has "
                + "both valid and invalid entries")
        public void readJournal_invalidAndValidEntryJournal_throwsDataConversionException() {
            assertThrows(DataConversionException.class, () ->
                    readJournal("invalidAndValidEntryJournal.json"));
        }
    }

    @Nested
    @DisplayName("saveJournal method")
    class SaveJournal {
        private void saveJournal(ReadOnlyJournal journal, String filePath) {
            try {
                JsonJournalStorage journalStorage =
                        new JsonJournalStorage(Paths.get(filePath));
                journalStorage.saveJournal(journal,
                        addToTestDataPathIfNotNull(filePath));
            } catch (IOException ioe) {
                throw new AssertionError(
                        "There should not be an error writing to the file.",
                        ioe
                );
            }
        }

        @Test
        @DisplayName("should throw NullPointerException if journal is null")
        public void saveJournal_nullJournal_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    saveJournal(null, "SomeFile.json"));
        }

        @Test
        @DisplayName("should throw NullPointerException if file path is null")
        public void saveJournal_nullFilePath_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    saveJournal(new Journal(), null));
        }
    }

    @Nested
    @DisplayName("miscellaneous operations")
    class Misc {
        @TempDir
        public Path testFolder;

        @Test
        @DisplayName("should read and then save journal successfully")
        public void readAndSaveJournal_allInOrder_success() throws Exception {
            Path filePath = testFolder.resolve("TempJournal.json");
            Journal original = getTypicalJournal();
            JsonJournalStorage jsonJournalStorage =
                    new JsonJournalStorage(filePath);

            // Save in new file and then read back
            jsonJournalStorage.saveJournal(original, filePath);
            ReadOnlyJournal readBack =
                    jsonJournalStorage.readJournal(
                            getTypicalAddressBook(), filePath).get();
            assertEquals(original, new Journal(readBack));

            // Modifying data, overwrite existing file, and read back
            original.addEntry(TEST_ENTRY_DIFF_TITLE);
            original.removeEntry(TEST_ENTRY_DEFAULT);
            jsonJournalStorage.saveJournal(original, filePath);
            readBack = jsonJournalStorage.readJournal(
                    getTypicalAddressBook(), filePath).get();
            assertEquals(original, new Journal(readBack));

            // Save and read without specifying file path
            original.addEntry(TEST_ENTRY_SEVEN);
            jsonJournalStorage.saveJournal(original);
            readBack = jsonJournalStorage
                    .readJournal(getTypicalAddressBook())
                    .get();
            assertEquals(original, new Journal(readBack));
        }
    }
}
