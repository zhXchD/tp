package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Journal;
import seedu.address.model.journal.Entry;

import java.util.logging.Logger;

public class JournalListPanel extends UiPart<Region> {
    private static final String FXML = "JournalListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(JournalListPanel.class);

    @FXML
    private ListView<Entry> journalListView;

    public JournalListPanel(ObservableList<Entry> journalList) {
        super(FXML);
        journalListView.setItems(journalList);
        journalListView.setCellFactory(listView -> new JournalListViewCell());
    }

    class JournalListViewCell extends ListCell<Entry> {
        @Override
        protected void updateItem(Entry journal, boolean empty) {
            super.updateItem(journal, empty);

            if (empty || journal == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new JournalCard(journal, getIndex() + 1).getRoot());
            }
        }
    }
}
