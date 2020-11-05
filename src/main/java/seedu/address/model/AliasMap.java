package seedu.address.model;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.logic.parser.ValidCommand;

//@@author {Lingy12}

public class AliasMap implements ReadOnlyAliasMap {

    private final ObservableMap<String, ValidCommand> internalMap = FXCollections.observableHashMap();
    private final ObservableMap<String, ValidCommand> internalUnmodifiableMap =
            FXCollections.unmodifiableObservableMap(internalMap);

    public AliasMap() {
        updateMap(ValidCommand.getAliasMap());
    }

    /**
     * Constructs an aliasMap using a {@code ReadOnlyAliasMap}
     */
    public AliasMap(ReadOnlyAliasMap map) {
        Map<String, ValidCommand> aliasMap = map.getAliasMap();
        updateMap(aliasMap);
    }

    /**
     * Update the internalMap with a map.
     */
    public void updateMap(Map<String, ValidCommand> map) {
        internalMap.clear();
        for (String alias: map.keySet()) {
            internalMap.put(alias, map.get(alias));
        }
    }

    @Override
    public ObservableMap<String, ValidCommand> getAliasMap() {
        return internalUnmodifiableMap;
    }
}
