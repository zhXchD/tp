package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
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

    public DashboardTab(ObservableList<Person> recent, ObservableList<Person> frequent) {
        super(FXML);
        this.recentPersonList = new PersonListPanel(recent);
        this.frequentPersonList = new PersonListPanel(frequent);
        this.recentPersonListPanelPlaceholder.getChildren().add(recentPersonList.getRoot());
        this.frequentPersonListPanelPlaceholder.getChildren().add(frequentPersonList.getRoot());
    }
}
