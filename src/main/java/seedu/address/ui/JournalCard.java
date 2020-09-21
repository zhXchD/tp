package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.Journal;
import seedu.address.model.journal.Entry;


public class JournalCard extends UiPart<Region> {

    private static final String FXML = "JournalListCard.fxml";

    public final Entry journal;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label date;

    public JournalCard(Entry journal, int displayedIndex) {
        super(FXML);
        this.journal = journal;
        id.setText(displayedIndex + ". ");
        title.setText(journal.getTitle().title);
        date.setText(journal.getDate().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof JournalCard)) {
            return false;
        }

        // state check
        JournalCard card = (JournalCard) other;
        return id.getText().equals(card.id.getText())
            && journal.equals(card.journal);
    }
}
