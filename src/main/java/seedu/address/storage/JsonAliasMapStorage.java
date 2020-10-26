package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AliasMap;
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
        Optional<JsonAdaptedAliasMap> map = JsonUtil.readJsonFile(filePath, JsonAdaptedAliasMap.class);

        if (map.isEmpty()) {
            return Optional.of(new AliasMap());
        }

        return Optional.of(map.get().toModelAliasMap());
    }

    @Override
    public void saveAliasMap(ReadOnlyAliasMap userAlias) throws IOException {
        requireNonNull(userAlias);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonAdaptedAliasMap(userAlias), filePath);
    }
}
