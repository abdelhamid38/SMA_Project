package sma_project;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import java.util.Random;

/**
 *
 * @author Mounis abdelhamid
 */
public class le_virus extends GuiAgent {

    static int x;
    static int y;

    Random randomno = new Random();

    @Override
    protected void setup() {
        addBehaviour(new b());
    }

    @Override
    protected void onGuiEvent(GuiEvent ge) {

    }

    private class b extends OneShotBehaviour {

        @Override
        public void action() {

            x = randomno.nextInt(10);
            y = randomno.nextInt(10);
            myAgent.doWait(3000);
            ACLMessage aCLMessage = new ACLMessage(ACLMessage.INFORM);
            aCLMessage.setContent("virus !!!");
            System.out.println("(" + x + "," + y + ")");
            AID aid = new AID("("+x+y+")", AID.ISLOCALNAME);
            aCLMessage.addReceiver(aid);
            send(aCLMessage);

        }
    }
}
