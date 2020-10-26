package seedu.address.model;

import javafx.collections.ObservableMap;
import seedu.address.logic.ValidCommand;

public interface ReadOnlyUserAlias {

    /**
     * Returns an unmodifiable aliases map.
     */
    ObservableMap<String, ValidCommand> getUnmodifiableObservableAliasMap();

}

