package donnafin.ui;

import java.util.stream.Collectors;

import donnafin.logic.PersonAdapter;
import donnafin.model.person.Attribute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

public class ClientInfoPanel extends UiPart<Region> {
    private static final String FXML = "ClientInfoPanel.fxml";
    private PersonAdapter personAdapter;

    @FXML
    private ListView<Attribute> clientInfoList;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ClientInfoPanel(PersonAdapter personAdapter) {
        super(FXML);
        this.personAdapter = personAdapter;
        // Make all the attributes into FXML AttributePanel
        ObservableList<Attribute> attributeObservableList =
                personAdapter.getAllAttributesList().stream()
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
        clientInfoList.setItems(attributeObservableList);
        clientInfoList.setCellFactory(listView -> new AttributePanel());
        clientInfoList.getSelectionModel().selectFirst();
    }
}
