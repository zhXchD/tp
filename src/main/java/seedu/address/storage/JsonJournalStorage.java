package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyJournal;

/**
 * A class to access Journal data stored as a json file on the hard disk.
 */
public class JsonJournalStorage implements JournalStorage {

    private static final Logger logger =
            LogsCenter.getLogger(JsonJournalStorage.class);

    private final Path filePath;

    public JsonJournalStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getJournalFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyJournal> readJournal(
            ReadOnlyAddressBook addressBook) throws DataConversionException {
        return readJournal(addressBook, filePath);
    }

    /**
     * Similar to {@link #readJournal(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyJournal> readJournal(
            ReadOnlyAddressBook addressBook,
            Path filePath
    ) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableJournal> jsonJournal = JsonUtil.readJsonFile(
                filePath, JsonSerializableJournal.class);
        if (jsonJournal.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonJournal.get().toModelType(addressBook));
        } catch (IllegalValueException ive) {
            logger.info(
                    "Illegal values found in "
                            + filePath
                            + ": "
                            + ive.getMessage()
            );
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveJournal(ReadOnlyJournal journal) throws IOException {
        saveJournal(journal, filePath);
    }

    /**
     * Similar to {@link #saveJournal(ReadOnlyJournal)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveJournal(ReadOnlyJournal journal, Path filePath)
            throws IOException {
        requireNonNull(journal);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableJournal(journal), filePath);
    }
}
