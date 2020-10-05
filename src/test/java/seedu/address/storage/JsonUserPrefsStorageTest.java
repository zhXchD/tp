package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.UserPrefs;

public class JsonUserPrefsStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src",
            "test",
            "data",
            "JsonUserPrefsStorageTest"
    );

    private Path addToTestDataPathIfNotNull(String userPrefsFileInTestDataFolder) {
        return userPrefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(userPrefsFileInTestDataFolder)
                : null;
    }

    private UserPrefs getTypicalUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGuiSettings(new GuiSettings(1000, 500, 300, 100));
        userPrefs.setAddressBookFilePath(Paths.get("addressbook.json"));
        return userPrefs;
    }

    @Nested
    @DisplayName("readUserPrefs method")
    class ReadUserPrefs {
        private Optional<UserPrefs> readUserPrefs(
                String userPrefsFileInTestDataFolder)
                throws DataConversionException {
            Path prefsFilePath =
                    addToTestDataPathIfNotNull(userPrefsFileInTestDataFolder);
            return new JsonUserPrefsStorage(prefsFilePath).readUserPrefs(
                    prefsFilePath);
        }

        @Test
        @DisplayName("should throw NullPointerException if file path is null")
        public void readUserPrefs_nullFilePath_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> readUserPrefs(null));
        }

        @Test
        @DisplayName("should throw NullPointerException if file path is null")
        public void readUserPrefs_missingFile_emptyResult()
                throws DataConversionException {
            assertFalse(readUserPrefs("NonExistentFile.json").isPresent());
        }

        @Test
        @DisplayName("should throw DataConversionException if user prefs not "
                + "in json format")
        public void readUserPrefs_notJsonFormat_throwsDataConversionException() {
            assertThrows(DataConversionException.class, () ->
                    readUserPrefs("NotJsonFormatUserPrefs.json"));
        }

        @Test
        @DisplayName("should read file successfully if file path is valid")
        public void readUserPrefs_fileInOrder_successfullyRead()
                throws DataConversionException {
            UserPrefs expected = getTypicalUserPrefs();
            UserPrefs actual = readUserPrefs("TypicalUserPref.json").get();
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("should use default user prefs if values are missing "
                + "from file")
        public void readUserPrefs_valuesMissingFromFile_defaultValuesUsed()
                throws DataConversionException {
            UserPrefs actual = readUserPrefs("EmptyUserPrefs.json").get();
            assertEquals(new UserPrefs(), actual);
        }

        @Test
        @DisplayName("should ignore extra values in file")
        public void readUserPrefs_extraValuesInFile_extraValuesIgnored()
                throws DataConversionException {
            UserPrefs expected = getTypicalUserPrefs();
            UserPrefs actual = readUserPrefs("ExtraValuesUserPref.json").get();

            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("saveUserPrefs method")
    class SaveUserPrefs {
        @TempDir
        public Path testFolder;

        /**
         * Saves {@code userPrefs} at the specified {@code
         * prefsFileInTestDataFolder} filepath.
         */
        private void saveUserPrefs(
                UserPrefs userPrefs,
                String prefsFileInTestDataFolder
        ) {
            try {
                new JsonUserPrefsStorage(addToTestDataPathIfNotNull(
                        prefsFileInTestDataFolder))
                        .saveUserPrefs(userPrefs);
            } catch (IOException ioe) {
                throw new AssertionError(
                        "There should not be an error writing to the file",
                        ioe
                );
            }
        }

        @Test
        @DisplayName("should throw NullPointerException if user prefs is null")
        public void savePrefs_nullPrefs_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    saveUserPrefs(null, "SomeFile.json"));
        }

        @Test
        @DisplayName("should throw NullPointerException if file path is null")
        public void saveUserPrefs_nullFilePath_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    saveUserPrefs(new UserPrefs(), null));
        }

        @Test
        @DisplayName("should save user prefs successfully")
        public void saveUserPrefs_allInOrder_success()
                throws DataConversionException, IOException {

            UserPrefs original = new UserPrefs();
            original.setGuiSettings(new GuiSettings(1200, 200, 0, 2));

            Path prefsFilePath = testFolder.resolve("TempPrefs.json");
            JsonUserPrefsStorage jsonUserPrefsStorage =
                    new JsonUserPrefsStorage(prefsFilePath);

            //Try writing when the file doesn't exist
            jsonUserPrefsStorage.saveUserPrefs(original);
            UserPrefs readBack = jsonUserPrefsStorage.readUserPrefs().get();
            assertEquals(original, readBack);

            //Try saving when the file exists
            original.setGuiSettings(new GuiSettings(5, 5, 5, 5));
            jsonUserPrefsStorage.saveUserPrefs(original);
            readBack = jsonUserPrefsStorage.readUserPrefs().get();
            assertEquals(original, readBack);
        }
    }
}
