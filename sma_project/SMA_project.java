package sma_project;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author Mounis abd elhamid
 */
public class SMA_project extends Application {

    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        startContainer();
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/GUI.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("SMA project");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        stage = primaryStage;
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(SMA_project.class);
        
    }
    public  void startContainer() {
        
        try {
            // Get a hold on JADE runtime

            AgentContainer mc;
            jade.core.Runtime rt;
            
            rt = jade.core.Runtime.instance();
            Properties p = new ExtendedProperties();
            p.setProperty(Profile.GUI, "true");

            // Exit the JVM when there are no more containers around
            rt.setCloseVM(true);
            Profile pMain = new ProfileImpl(p);
            mc = rt.createMainContainer(pMain);

            AgentController Virus = mc.createNewAgent("Virus-Agent", le_virus.class.getName(), new Object[]{});
            Virus.start();

            AgentController Agent_secouriste = mc.createNewAgent("Agent_secouriste", Agent_secouriste.class.getName(), new Object[]{});
            Agent_secouriste.start();

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    AgentController Agent_Boilogique = mc.createNewAgent("("+i+j+")", les_Organismes_biologiques.class.getName(), new Object[]{this});
                    Agent_Boilogique.start();
                }
            }
        } catch (Exception e) {
        }
        
    }

}