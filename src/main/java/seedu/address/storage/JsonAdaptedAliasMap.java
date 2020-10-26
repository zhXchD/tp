package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

import seedu.address.logic.ValidCommand;
import seedu.address.model.AliasMap;
import seedu.address.model.ReadOnlyAliasMap;

public class JsonAdaptedAliasMap extends HashMap<String, ValidCommand> {

    /**
     * Construct a JsonAdaptedMap with a given read only map.
     */
    public JsonAdaptedAliasMap(ReadOnlyAliasMap map) {
        requireNonNull(map);
        this.putAll(map.getAliasMap());
    }

    /**
     * Change the JsonAdptedAliasMap to ReadOnlyMap for model.
     */
    public ReadOnlyAliasMap toModelAliasMap() {
        AliasMap map = new AliasMap();
        map.updateMap(this);
        return map;
    }
}
