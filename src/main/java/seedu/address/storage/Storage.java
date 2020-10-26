package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAliasMap;
import seedu.address.model.ReadOnlyJournal;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends
        AddressBookStorage, JournalStorage, UserPrefsStorage, AliasMapStorage {

    @Override
    Optional<UserPrefs> readUserPrefs()
            throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook()
            throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getJournalFilePath();

    @Override
    Optional<ReadOnlyJournal> readJournal(ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException;

    @Override
    void saveJournal(ReadOnlyJournal journal) throws IOException;

    @Override
    Path getAliasmapFilePath();

    @Override
    void saveAliasMap(ReadOnlyAliasMap map) throws IOException;

    @Override
    Optional<ReadOnlyAliasMap> readAliasMap() throws IOException, DataConversionException;
}
