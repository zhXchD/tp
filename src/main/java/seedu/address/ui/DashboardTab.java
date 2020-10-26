package seedu.address.ui;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import seedu.address.model.journal.Entry;
import seedu.address.model.person.Person;

import java.util.Stack;

public class DashboardTab extends UiPart<Region> {
    private static final String FXML = "DashboardTab.fxml";
    private PersonListPanel recentPersonList;
    private PersonListPanel frequentPersonList;

    @FXML
    private StackPane recentPersonListPanelPlaceholder;

    @FXML
    private StackPane frequentPersonListPanelPlaceholder;

    @FXML
    private Text title;

    @FXML
    private Label message;

    public DashboardTab(ObservableList<Person> recent, ObservableList<Person> frequent) {
        super(FXML);
        this.title.setText("Hello there, welcome to IntelliJournal!");
        this.message.setText("IntelliJournal is here to helped you with your contacts and events with them!");
        this.recentPersonList = new PersonListPanel(recent);
        this.frequentPersonList = new PersonListPanel(frequent);
        this.recentPersonListPanelPlaceholder.getChildren().add(recentPersonList.getRoot());
        this.frequentPersonListPanelPlaceholder.getChildren().add(frequentPersonList.getRoot());
    }
}
