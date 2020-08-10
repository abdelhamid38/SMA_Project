package sma_project;

import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
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
 * @author hamid
 */
public class Agent_secouriste extends GuiAgent {

    @Override
    protected void onGuiEvent(GuiEvent Event) {

    }

    @Override
    protected void setup() {

        addBehaviour(new a());

    }

    private class a extends CyclicBehaviour {

        @Override
        public void action() {

            ACLMessage message;
            message = receive();
            if (message != null) {
                if (message.getContent().startsWith("halp me please !!!")) {
                    int i, j;
                    i = Integer.parseInt(message.getSender().getLocalName().charAt(1) + "");
                    j = Integer.parseInt(message.getSender().getLocalName().charAt(2) + "");
                    
                    GUIController.move(GUIController.secure1, i * 61, j * 61, 250 * (i + j));
                    myAgent.doWait(5000);
                    
                    GUIController.rectangle[i][j].setFill(Color.valueOf("#21eeff"));
                    les_Organismes_biologiques.Etat_Cellule[i][j] = true;
                    Label l = new Label(message.getSender().getName());
                    l.setFont(new Font(22));
                    l.setPadding(new Insets(0, 0, 0, 0));
                    l.setPrefWidth(400);
                    l.setPrefHeight(20);
                    l.setBackground(new Background(new BackgroundFill(Color.valueOf("#0746A6"), CornerRadii.EMPTY, Insets.EMPTY)));
                    Secure_controller.ol_s.add(l);
                }
            } else {
                block();
            }
        }
    }
}
