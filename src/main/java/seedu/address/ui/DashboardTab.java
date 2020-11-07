package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import seedu.address.model.person.Person;

//@@author {zhXchD}
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

    /**
     * Create the dashboard UI by given recent and frequent person list as {@code ObservableList<Person>}.
     * @param recent the list of the most recent contacts
     * @param frequent the list of the most frequent contacts
     */
    public DashboardTab(ObservableList<Person> recent, ObservableList<Person> frequent) {
        super(FXML);
        this.title.setText("Hello there, welcome to IntelliJournal!");
        this.message.setText("IntelliJournal is here to help you with your contacts and events with them!");
        this.guide.setText("Type some commands to start. Have no idea? Try typing \"help\"!");
        this.recentPersonList = new PersonListPanel(recent);
        this.frequentPersonList = new PersonListPanel(frequent);
        this.recentPersonListPanelPlaceholder.getChildren().add(recentPersonList.getRoot());
        this.frequentPersonListPanelPlaceholder.getChildren().add(frequentPersonList.getRoot());
    }

    /**
     * Returns {@code true} if another {@code Object} is the same as the current {@code DashBoardTab}.
     * @param other the other object to check
     * @return true if the two objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof DashboardTab) {
            DashboardTab otherTab = (DashboardTab) other;
            return otherTab.recentPersonList.equals(this.recentPersonList)
                    && otherTab.frequentPersonList.equals(this.frequentPersonList);
        } else {
            return false;
        }
    }
}
