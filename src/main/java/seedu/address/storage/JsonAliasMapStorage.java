package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAliasMap;

public class JsonAliasMapStorage implements AliasMapStorage {

    private final Path filePath;

    public JsonAliasMapStorage(Path path) {
        this.filePath = path;
    }


    @Override
    public Path getAliasmapFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAliasMap> readAliasMap() throws DataConversionException {
        return JsonUtil.readJsonFile(filePath, ReadOnlyAliasMap.class);
    }

    @Override
    public void saveAliasMap(ReadOnlyAliasMap userAlias) throws IOException {
        JsonUtil.saveJsonFile(userAlias, filePath);
    }
}
