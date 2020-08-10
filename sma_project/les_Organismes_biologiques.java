package sma_project;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Mounis abd elhamid
 */
public class les_Organismes_biologiques extends Agent {

    static Boolean[][] Etat_Cellule = new Boolean[10][10];

    @Override
    protected void setup() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Etat_Cellule[i][j] = false;
            }
        }
        addBehaviour(new b());
    }

    private class b extends CyclicBehaviour {

        @Override
        public void action() {

            ACLMessage msg;
            msg = receive();
            if (msg != null) {
                if (msg.getContent().equals("virus !!!")) {
                    Etat_Cellule[Integer.parseInt(myAgent.getAID().getLocalName().charAt(1) + "")][Integer.parseInt(myAgent.getAID().getLocalName().charAt(2) + "")] = true;
                    GUIController.rectangle[Integer.parseInt(myAgent.getAID().getLocalName().charAt(1) + "")][Integer.parseInt(myAgent.getAID().getLocalName().charAt(2) + "")].setFill(Color.valueOf("#12f221"));
                    //send a message for agent secouriste to help this organisme boilogique
                    send("halp me please !!!" + myAgent.getLocalName(), "Agent_secouriste");

                    myAgent.doWait(3000);
                    
                    int i = Integer.parseInt(myAgent.getAID().getLocalName().charAt(1) + "");
                    if(i<9){i=i+1;}else{i=i-1;}
                    int j = Integer.parseInt(myAgent.getAID().getLocalName().charAt(2) + "");
                    if(j>0){j=j-1;}else{j=j+1;}
                    if (!Etat_Cellule[i][j] && i<=9 && i>=0 && j<=9 && j>=0) {
                        send("virus !!!", "(" + i + j + ")");
                    }

                    i = Integer.parseInt(myAgent.getAID().getLocalName().charAt(1) + "") - 1;
                    j = Integer.parseInt(myAgent.getAID().getLocalName().charAt(2) + "") - 1;
                    if (!Etat_Cellule[i][j] && i<=9 && i>=0 && j<=9 && j>=0 ) {
                        send("virus !!!", "(" + i + j + ")");
                    }

                    if (Etat_Cellule[Integer.parseInt(myAgent.getAID().getLocalName().charAt(1) + "")][Integer.parseInt(myAgent.getAID().getLocalName().charAt(2) + "")]) {
                        GUIController.rectangle[Integer.parseInt(myAgent.getAID().getLocalName().charAt(1) + "")][Integer.parseInt(myAgent.getAID().getLocalName().charAt(2) + "")].setFill(Color.valueOf("#eeb816"));
                    }
                    myAgent.doWait(2000);
                    if (Etat_Cellule[Integer.parseInt(myAgent.getAID().getLocalName().charAt(1) + "")][Integer.parseInt(myAgent.getAID().getLocalName().charAt(2) + "")]) {
                        GUIController.rectangle[Integer.parseInt(myAgent.getAID().getLocalName().charAt(1) + "")][Integer.parseInt(myAgent.getAID().getLocalName().charAt(2) + "")].setFill(Color.valueOf("#ff321f"));
                    }
                    myAgent.doWait(1000);
                    if (Etat_Cellule[Integer.parseInt(myAgent.getAID().getLocalName().charAt(1) + "")][Integer.parseInt(myAgent.getAID().getLocalName().charAt(2) + "")]) {
                        GUIController.rectangle[Integer.parseInt(myAgent.getAID().getLocalName().charAt(1) + "")][Integer.parseInt(myAgent.getAID().getLocalName().charAt(2) + "")].setFill(Color.valueOf("#000000"));
                        myAgent.doDelete();
                    }

                }
            } else {
                block();
            }

        }
    }

    @Override
    protected void takeDown() {
        Label l = new Label(this.getAID().getName());
        l.setFont(new Font(22));
        l.setPadding(new Insets(0, 0, 0, 0));
        l.setPrefWidth(400);
        l.setPrefHeight(20);
        l.setBackground(new Background(new BackgroundFill(Color.valueOf("#0746A6"), CornerRadii.EMPTY, Insets.EMPTY)));
        GUIController.ol.add(l);
    }

    public void send(String content, String reciever) {
        ACLMessage msgACLMessage = new ACLMessage(ACLMessage.INFORM);
        msgACLMessage.setContent(content);
        AID aid = new AID(reciever, AID.ISLOCALNAME);
        msgACLMessage.addReceiver(aid);
        send(msgACLMessage);
    }

}
