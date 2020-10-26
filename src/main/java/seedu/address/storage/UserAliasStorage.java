package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.model.ReadOnlyUserAlias;

/**
 * Represents the storage for user alias.
 */
public interface UserAliasStorage {

    /**
     * Returns the file path of the UserPrefs data file.
     */
    Path getUserAliasFilePath();

    /**
     * Saves the given {@link seedu.address.model.ReadOnlyUserAlias} to the storage.
     * @param userAlias cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserAlias(ReadOnlyUserAlias userAlias) throws IOException;

    /**
     * Saves the {@link seedu.address.model.ReadOnlyUserAlias} to the storage
     * @param userAlias cannot be null.
     * @throws IOException if there was any problem writing to the file
     */
    void saveUserAlias(ReadOnlyUserAlias userAlias, Path aliasPath) throws IOException;
}

