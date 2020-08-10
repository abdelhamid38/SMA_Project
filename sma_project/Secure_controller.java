package sma_project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author hamid
 */
public class Secure_controller implements Initializable {

    @FXML
    private JFXButton exit;

    @FXML
    private JFXButton reduire;

    @FXML
    private JFXListView<Label> list;

    @FXML
    private AnchorPane parent;

    static ObservableList<Label> ol_s = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exit.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            GUIController.s1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
            GUIController.s1.close();
        });
        reduire.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            GUIController.s1 = (Stage) ((Node) e.getSource()).getScene().getWindow();
            GUIController.s1.setIconified(true);
        });
        list.setItems(ol_s);
    }

    public void makeStageDrageable() {
        parent.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        parent.setOnMouseDragged((MouseEvent event) -> {
            GUIController.s2.setX(event.getScreenX() - xOffset);
            GUIController.s2.setY(event.getScreenY() - yOffset);
            GUIController.s2.setOpacity(0.7f);
        });
        parent.setOnDragDone((e) -> {
            GUIController.s2.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((e) -> {
            GUIController.s2.setOpacity(1.0f);
        });
    }
    private double xOffset = 0;
    private double yOffset = 0;
}
