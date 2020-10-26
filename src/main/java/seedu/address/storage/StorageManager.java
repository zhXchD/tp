package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAliasMap;
import seedu.address.model.ReadOnlyJournal;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger =
            LogsCenter.getLogger(StorageManager.class);
    private final AddressBookStorage addressBookStorage;
    private final UserPrefsStorage userPrefsStorage;
    private final JournalStorage journalStorage;
    private final AliasMapStorage aliasMapStorage;

    /**
     * Creates a {@code StorageManager} with the given
     * {@code AddressBookStorage}, {@code UserPrefStorage} and
     * {@code JournalStorage}.
     */
    public StorageManager(
            AddressBookStorage addressBookStorage,
            JournalStorage journalStorage,
            UserPrefsStorage userPrefsStorage,
            AliasMapStorage aliasMapStorage
    ) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.journalStorage = journalStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.aliasMapStorage = aliasMapStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs()
            throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook()
            throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read addressBook data from file: "
                + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook)
            throws IOException {
        saveAddressBook(addressBook,
                addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to addressBook data file: "
                + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ Journal methods ==============================

    @Override
    public Path getJournalFilePath() {
        return journalStorage.getJournalFilePath();
    }

    @Override
    public Optional<ReadOnlyJournal> readJournal(
            ReadOnlyAddressBook addressBook
    ) throws DataConversionException, IOException {
        return readJournal(addressBook, journalStorage.getJournalFilePath());
    }

    @Override
    public Optional<ReadOnlyJournal> readJournal(
            ReadOnlyAddressBook addressBook,
            Path filePath
    ) throws DataConversionException, IOException {
        logger.fine("Attempting to read journal data from file: "
                + filePath);
        return journalStorage.readJournal(addressBook, filePath);
    }

    @Override
    public void saveJournal(ReadOnlyJournal journal) throws IOException {
        saveJournal(journal, journalStorage.getJournalFilePath());
    }

    @Override
    public void saveJournal(ReadOnlyJournal journal, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to journal data file: "
                + filePath);
        journalStorage.saveJournal(journal, filePath);
    }

    // ================ AliasMap methods ==============================

    @Override
    public Path getAliasmapFilePath() {
        return aliasMapStorage.getAliasmapFilePath();
    }

    @Override
    public void saveAliasMap(ReadOnlyAliasMap map) throws IOException {
        aliasMapStorage.saveAliasMap(map);
    }

    @Override
    public Optional<ReadOnlyAliasMap> readAliasMap() throws IOException, DataConversionException {
        return aliasMapStorage.readAliasMap();
    }

}
