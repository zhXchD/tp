package seedu.address.ui;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import seedu.address.model.journal.Entry;

/**
 * An UI component that displays information of a {@code Entry} in detail.
 */
public class EntryContent extends UiPart<Region> {

    private static final String FXML = "EntryContent.fxml";

    private Entry entry;

    @FXML
    private VBox entryPane;
    @FXML
    private StackPane titlePane;
    @FXML
    private Text title;
    @FXML
    private JFXTextArea description;

    /**
     * Creates a {@code EntryContent} containing no {@code Entry} temporarily.
     */
    public EntryContent() {
        super(FXML);
        this.entry = null;
        setDefaultContent();
    }

    private void setDefaultContent() {
        title.setText("");
        description.setText("Please select a Journal Entry...");
    }

    private void setContent(Entry entry) {
        title.setText(entry.getTitle().title);
        description.setText(entry.getDescription().description);
    }

    /**
     * Updates the information of a nullable {@code Entry}.
     * If entry is null, the content will be reset to default.
     * @param entry the new entry
     */
    public void setEntryContentToUser(Entry entry) {
        this.entry = entry;
        if (entry == null) {
            setDefaultContent();
        } else {
            setContent(entry);
        }
    }

    public Entry getEntry() {
        return entry;
    }
}
