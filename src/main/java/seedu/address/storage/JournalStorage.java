package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyJournal;

public interface JournalStorage {
    /**
     * Returns the file path of the journal data file.
     */
    Path getJournalFilePath();

    /**
     * Returns Journal data as a {@link ReadOnlyJournal}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @param addressBook the address book of the journal.
     * @throws DataConversionException if the data in storage is not in the
     * expected format.
     * @throws IOException if there was any problem when reading from the
     * storage.
     */
    Optional<ReadOnlyJournal> readJournal(ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException;

    /**
     * @see #getJournalFilePath()
     */
    Optional<ReadOnlyJournal> readJournal(
            ReadOnlyAddressBook addressBook,
            Path filePath
    ) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyJournal} to the storage.
     * @param journal cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveJournal(ReadOnlyJournal journal) throws IOException;

    /**
     * @see #saveJournal(ReadOnlyJournal)
     */
    void saveJournal(ReadOnlyJournal journal, Path filePath) throws IOException;

}
