package sma_project;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author hamid
 */
public class GUIController  implements Initializable {

    @FXML
    JFXButton exit, reduire;

    @FXML
    AnchorPane parent;

    @FXML
    FlowPane FlowPane;

    @FXML
    static Label secure1;
   
    @FXML
    private JFXDrawer drawer;

    static ObservableList<Label> ol=FXCollections.observableArrayList();
    
    
    static Rectangle[][] rectangle;
    static int x,y;
    int i,j;
    Stage stage = null;
    static Stage s1,s2;
    
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
           
            move(secure1, 61, 0, 1);
            
            VBox box = FXMLLoader.load(getClass().getResource("/FXMLs/Menu.fxml"));
            for (Node n :box.getChildren()) {
                n.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
                    switch(n.getAccessibleText()){
                    case "Dath_agent":
                        try {
                          AnchorPane ap=FXMLLoader.load(getClass().getResource("/FXMLs/Dath_Agent.fxml"));
                          Scene s=new Scene(ap);
                          s1=new Stage();
                          s1.setScene(s);
                          s1.show();
                        } catch (Exception e) {
                        }
                        break;
                    case "Virus_agent" :
                        
                        break;
                    case "Active_agent":
                         try {
                          AnchorPane ap=FXMLLoader.load(getClass().getResource("/FXMLs/Secure_Agent.fxml"));
                          Scene s=new Scene(ap);
                          s2=new Stage();
                          s2.initStyle(StageStyle.TRANSPARENT);
                          s2.setScene(s);
                          s2.show();
                        } catch (Exception e) {
                        }
                        break;
                }
                });
                
            }
            drawer.setSidePane(box);
            drawer.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
                if (drawer.isHidden()) {
                    drawer.open();
                }
            });
            drawer.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
                if (!drawer.isHidden()) {
                    drawer.close();
                }
            });
            rectangle = createOrganisme();
            

//        rectangle[y][x].setFill(Color.valueOf("#21eeff"));//vaccinee
//        rectangle[1][2].setFill(Color.valueOf("#000000"));//mort
//        rectangle[5][6].setFill(Color.valueOf("#eeb816"));//virus 2
//        rectangle[7][8].setFill(Color.valueOf("#12f221"));//virus 1
//        rectangle[4][5].setFill(Color.valueOf("#ff321f"));//virus 3
            for (int u = 0; u < 10; u++) {
                for (int r = 0; r < 10; r++) {
                    FlowPane.getChildren().add(rectangle[u][r]);
                }
            }

            exit.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                Platform.exit();
            });
            reduire.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setIconified(true);

            });
            makeStageDrageable();
            } catch (IOException ex) {
        }
    }
    
    public void makeStageDrageable() {
        parent.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        parent.setOnMouseDragged((MouseEvent event) -> {
            sma_project.SMA_project.stage.setX(event.getScreenX() - xOffset);
            sma_project.SMA_project.stage.setY(event.getScreenY() - yOffset);
            sma_project.SMA_project.stage.setOpacity(0.7f);
        });
        parent.setOnDragDone((e) -> {
            sma_project.SMA_project.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((e) -> {
            sma_project.SMA_project.stage.setOpacity(1.0f);
        });
    }

    private static Rectangle[][] createOrganisme() {
        Color etat_Color = Color.valueOf("#0746A6");
        final Rectangle[][] rectangle1 = new Rectangle[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final Rectangle rect = new Rectangle(60, 60);
                rect.setFill(etat_Color);
                rectangle1[i][j] = rect;
            }
        }
        return rectangle1;
    }

    public static void move(Label n, int x, int y, int z) {

        TranslateTransition transition = new TranslateTransition(Duration.seconds(z), n);
        transition.setToX(x);
        transition.setToY(y);
        transition.play();

    }

    private double xOffset = 0;
    private double yOffset = 0;
}
