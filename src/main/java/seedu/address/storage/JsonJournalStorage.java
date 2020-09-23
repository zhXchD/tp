package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyJournal;

/**
 * A class to access Journal data stored as a json file on the hard disk.
 */
public class JsonJournalStorage implements JournalStorage {

    @Override
    public Path getJournalFilePath() {
        return null;
    }

    @Override
    public Optional<ReadOnlyJournal> readJournal()
            throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public Optional<ReadOnlyJournal> readJournal(Path filePath)
            throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public void saveJournal(ReadOnlyJournal journal) throws IOException {

    }

    @Override
    public void saveJournal(ReadOnlyJournal journal, Path filePath)
            throws IOException {

    }
}
