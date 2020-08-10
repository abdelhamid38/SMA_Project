package sma_project;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentController;

public class Lanch_Jade {

    //        AnchorPane root=new AnchorPane();
//        FontAwesomeIcon awesomeIcon=new FontAwesomeIconView(FontAwesomeIcon.TIMES).getDefaultGlyph();
//        JFXButton Exit=new JFXButton();
//        JFXButton reduire=new JFXButton();
//        Exit.setPrefWidth(30);
//        Exit.setPrefHeight(30);
//        Exit.setLayoutX(589);
//        Exit.setLayoutY(0);
//        reduire.setLayoutX(559);
//        reduire.setLayoutY(0);
//        Label label=new Label("system multi agent project");
//        label.setFont(new Font("Trebuchet MS",18));
//        label.setTextFill(Color.valueOf("#0746A6"));
//        label.setLayoutY(0);
//        label.setLayoutX(100);
//        reduire.setPrefWidth(30);
//        reduire.setPrefHeight(30);
//        root.getChildren().add(Exit);
//        root.getChildren().add(reduire);
//        root.getChildren().add(label);
//        FlowPane flowPane=new FlowPane();
//        flowPane.setPrefWidth(610);
//        flowPane.setPrefHeight(610);
//        root.getChildren().add(flowPane);
//        flowPane.setLayoutX(1);
//        flowPane.setLayoutY(30);
//        flowPane.setVgap(1);
//        flowPane.setHgap(1);
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                Rectangle rectangle=new Rectangle(60, 60);
//                rectangle.setFill(Color.valueOf("#0746A6"));
//                flowPane.getChildren().add(rectangle);
//            }
//        }
//        root.getStylesheets().add("gui.css");
    
    static AgentContainer mc;
    Runtime rt;

    public Lanch_Jade() {
        try {
            // Get a hold on JADE runtime

            rt = Runtime.instance();
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
                    AgentController Agent_Boilogique = mc.createNewAgent("Boilogique:(" + i + "," + j + ")", les_Organismes_biologiques.class.getName(), new Object[]{this});
                    Agent_Boilogique.start();
                }
            }
        } catch (Exception e) {
        }
    }

}
