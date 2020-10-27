package seedu.address.model;

import javafx.collections.ObservableMap;
import seedu.address.logic.parser.ValidCommand;

public interface ReadOnlyAliasMap {

    /**
     * Returns an unmodifiable aliases map.
     */
    ObservableMap<String, ValidCommand> getAliasMap();

}

