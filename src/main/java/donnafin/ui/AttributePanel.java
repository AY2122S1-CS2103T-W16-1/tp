package donnafin.ui;

import donnafin.model.person.Attribute;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * An UI component that displays information of a {@code Person}.
 */
class AttributePanel extends ListCell<Attribute> {

    private HBox hbox = new HBox();
    private Label fieldName = new Label();
    private TextField fieldValue = new TextField();
    private Pane pane = new Pane();

    public AttributePanel() {
        super();
        fieldName.setMinWidth(100);
        fieldName.setPadding(new Insets(8, 8, 8, 8));
        fieldValue.setMinWidth(600);
        hbox.getChildren().addAll(fieldName, fieldValue);
        hbox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(pane, Priority.ALWAYS);
    }

    @Override
    protected void updateItem(Attribute attribute, boolean empty) {
        super.updateItem(attribute, empty);

        if (empty || attribute == null) {
            setGraphic(null);
            setText(null);
        } else {
            fieldName.setText(attribute.getClass().getSimpleName());
            fieldValue.setText(attribute.toString());
            setGraphic(hbox);
        }
    }
}

