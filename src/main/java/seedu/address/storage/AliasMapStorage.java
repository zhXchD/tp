package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAliasMap;


/**
 * Represents the storage for user alias.
 */
public interface AliasMapStorage {

    /**
     * Returns the file path of the UserPrefs data file.
     */
    Path getAliasmapFilePath();

    /**
     * Returns aliases data as a {@link ReadOnlyAliasMap}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the
     * expected format.
     * @throws IOException if there was any problem when reading from the
     * storage.
     */
    Optional<ReadOnlyAliasMap> readAliasMap()
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAliasMap} to the storage.
     * @param userAlias cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAliasMap(ReadOnlyAliasMap userAlias) throws IOException;
}

