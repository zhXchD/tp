package seedu.address.model;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.logic.ValidCommand;

public class AliasMap implements ReadOnlyAliasMap {

    ObservableMap<String, ValidCommand> internalMap = FXCollections.observableHashMap();
    ObservableMap<String, ValidCommand> internalUnmodifiableMap = FXCollections.unmodifiableObservableMap(internalMap);

    public AliasMap() {
        updateMap(ValidCommand.getAliasMap());
    }

    public void updateMap(Map<String, ValidCommand> map) {
        for (String alias: map.keySet()) {
            internalMap.put(alias, map.get(alias));
        }
    }

    @Override
    public ObservableMap<String, ValidCommand> getAliasMap() {
        return internalUnmodifiableMap;
    }
}
