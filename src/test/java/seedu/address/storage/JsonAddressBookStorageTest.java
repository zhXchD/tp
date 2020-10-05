package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
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
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

public class JsonAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src",
            "test",
            "data",
            "JsonAddressBookStorageTest"
    );

    private Optional<ReadOnlyAddressBook> readAddressBook(String filePath)
            throws Exception {
        return new JsonAddressBookStorage(Paths.get(filePath))
                .readAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Nested
    @DisplayName("readAddressBook method")
    class ReadAddressBook {
        @Test
        @DisplayName("should throw NullPointerException if file path is null")
        public void readAddressBook_nullFilePath_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    readAddressBook(null));
        }

        @Test
        @DisplayName("should return empty result if file is missing")
        public void readAddressBook_missingFile_emptyResult() throws Exception {
            assertFalse(readAddressBook("NonExistentFile.json").isPresent());
        }

        @Test
        @DisplayName("should throw DataConversionException if file is not in "
                + "json format")
        public void readAddressBook_notJsonFormat_throwsDataConversionException() {
            assertThrows(DataConversionException.class, () ->
                    readAddressBook("notJsonFormatAddressBook.json"));
        }

        @Test
        @DisplayName("should throw DataConversionException if address book has"
                + " invalid persons")
        public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
            assertThrows(DataConversionException.class, () ->
                    readAddressBook("invalidPersonAddressBook.json"));
        }

        @Test
        @DisplayName("should throw DataConversionException if address book "
                + "has both valid and invalid persons")
        public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
            assertThrows(DataConversionException.class, () ->
                            readAddressBook(
                                    "invalidAndValidPersonAddressBook.json"));
        }
    }

    @Nested
    @DisplayName("saveAddressBook method")
    class SaveAddressBook {
        /**
         * Saves {@code addressBook} at the specified {@code filePath}.
         */
        private void saveAddressBook(
                ReadOnlyAddressBook addressBook,
                String filePath
        ) {
            try {
                JsonAddressBookStorage addressBookStorage =
                        new JsonAddressBookStorage(Paths.get(filePath));
                addressBookStorage.saveAddressBook(
                        addressBook,
                        addToTestDataPathIfNotNull(filePath)
                );
            } catch (IOException ioe) {
                throw new AssertionError(
                        "There should not be an error writing to the file.",
                        ioe
                );
            }
        }

        @Test
        @DisplayName("should throw NullPointerException if address book is "
                + "null")
        public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    saveAddressBook(null, "SomeFile.json"));
        }

        @Test
        @DisplayName("should throw NullPointerException if file path is null")
        public void saveAddressBook_nullFilePath_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    saveAddressBook(new AddressBook(), null));
        }
    }

    @Nested
    @DisplayName("miscellaneous operations")
    class Misc {
        @TempDir
        public Path testFolder;

        @Test
        @DisplayName("should read and then save address book successfully")
        public void readAndSaveAddressBook_allInOrder_success()
                throws Exception {
            Path filePath = testFolder.resolve("TempAddressBook.json");
            AddressBook original = getTypicalAddressBook();
            JsonAddressBookStorage jsonAddressBookStorage =
                    new JsonAddressBookStorage(filePath);

            // Save in new file and read back
            jsonAddressBookStorage.saveAddressBook(original, filePath);
            ReadOnlyAddressBook readBack =
                    jsonAddressBookStorage.readAddressBook(filePath).get();
            assertEquals(original, new AddressBook(readBack));

            // Modify data, overwrite exiting file, and read back
            original.addPerson(HOON);
            original.removePerson(ALICE);
            jsonAddressBookStorage.saveAddressBook(original, filePath);
            readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
            assertEquals(original, new AddressBook(readBack));

            // Save and read without specifying file path
            original.addPerson(IDA);
            jsonAddressBookStorage.saveAddressBook(original); // file path not specified
            readBack = jsonAddressBookStorage
                    .readAddressBook()
                    .get(); // file path not specified
            assertEquals(original, new AddressBook(readBack));
        }
    }
}
