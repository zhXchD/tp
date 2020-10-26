package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    private StorageManager storageManager;
    private JsonAddressBookStorage addressBookStorage;
    private JsonUserPrefsStorage userPrefsStorage;
    private JsonJournalStorage journalStorage;
    private JsonAliasMapStorage aliasMapStorage;

    @Nested
    @DisplayName("miscellaneous operations")
    class Misc {
        @TempDir
        public Path testFolder;

        @BeforeEach
        public void setUp() {
            addressBookStorage = new JsonAddressBookStorage(
                    getTempFilePath("ab"));
            userPrefsStorage = new JsonUserPrefsStorage(
                    getTempFilePath("prefs"));
            journalStorage = new JsonJournalStorage(
                    getTempFilePath("journals"));
            aliasMapStorage = new JsonAliasMapStorage(getTempFilePath("aliases"));
            storageManager = new StorageManager(
                    addressBookStorage,
                    journalStorage,
                    userPrefsStorage,
                    aliasMapStorage
            );
        }

        private Path getTempFilePath(String fileName) {
            return testFolder.resolve(fileName);
        }

        @Test
        @DisplayName("should save user prefs successfully")
        public void prefsReadSave() throws Exception {
            /*
             * Note: This is an integration test that verifies the
             * StorageManager is
             * properly wired to the {@link JsonUserPrefsStorage} class.
             * More extensive testing of UserPref saving/reading is done in
             * {@link JsonUserPrefsStorageTest} class.
             */
            UserPrefs original = new UserPrefs();
            original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
            storageManager.saveUserPrefs(original);
            UserPrefs retrieved = storageManager.readUserPrefs().get();
            assertEquals(original, retrieved);
        }

        @Test
        @DisplayName("should save address book successfully")
        public void addressBookReadSave() throws Exception {
            /*
             * Note: This is an integration test that verifies the
             * StorageManager is
             * properly wired to the {@link JsonAddressBookStorage} class.
             * More extensive testing of UserPref saving/reading is done in
             * {@link JsonAddressBookStorageTest} class.
             */
            AddressBook original = getTypicalAddressBook();
            storageManager.saveAddressBook(original);
            ReadOnlyAddressBook retrieved =
                    storageManager.readAddressBook().get();
            assertEquals(original, new AddressBook(retrieved));
        }
    }

    @Nested
    @DisplayName("getAddressBookFilePath method")
    class GetAddressBookFilePath {
        @TempDir
        public Path testFolder;

        @BeforeEach
        public void setUp() {
            addressBookStorage = new JsonAddressBookStorage(
                    getTempFilePath("ab"));
            userPrefsStorage = new JsonUserPrefsStorage(
                    getTempFilePath("prefs"));
            journalStorage = new JsonJournalStorage(
                    getTempFilePath("journals"));
            storageManager = new StorageManager(
                    addressBookStorage,
                    journalStorage,
                    userPrefsStorage,
                    aliasMapStorage
            );
        }

        private Path getTempFilePath(String fileName) {
            return testFolder.resolve(fileName);
        }

        @Test
        @DisplayName("should not be null")
        public void getAddressBookFilePath_noArgs_notNull() {
            assertNotNull(storageManager.getAddressBookFilePath());
        }
    }
}
