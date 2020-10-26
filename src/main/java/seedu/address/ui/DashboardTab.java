package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import seedu.address.model.person.Person;


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

    @FXML
    private Label guide;

    public DashboardTab(ObservableList<Person> recent, ObservableList<Person> frequent) {
        super(FXML);
        this.title.setText("Hello there, welcome to IntelliJournal!");
        this.message.setText("IntelliJournal is here to helped you with your contacts and events with them!");
        this.guide.setText("Type some commands to start. Have no idea? Try typing \"help\"!");
        this.recentPersonList = new PersonListPanel(recent);
        this.frequentPersonList = new PersonListPanel(frequent);
        this.recentPersonListPanelPlaceholder.getChildren().add(recentPersonList.getRoot());
        this.frequentPersonListPanelPlaceholder.getChildren().add(frequentPersonList.getRoot());
    }
}
