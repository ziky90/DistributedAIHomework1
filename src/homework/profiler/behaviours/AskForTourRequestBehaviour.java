package homework.profiler.behaviours;

import homework.profiler.ProfilerAgent;
import homework.tourguide.behaviours.TourSenderBehaviour;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * One shot behaviour that request for the tour to the tour guide agent it is
 * ussualy chained with information recieving behaviour
 *
 * @author zikesjan
 */
public class AskForTourRequestBehaviour extends OneShotBehaviour {

    private ProfilerAgent pa;

    public AskForTourRequestBehaviour(ProfilerAgent a) {
        super(a);
        this.pa = a;
    }

    @Override
    public void action() {
        System.out.println("<" + myAgent.getLocalName() + ">: sending the request for the informations about the tour");
        ACLMessage inform = new ACLMessage(ACLMessage.REQUEST);
        inform.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        inform.addReceiver(new AID("tour_guide", AID.ISLOCALNAME));
        try {
            inform.setContentObject(pa.p);
        } catch (IOException ex) {
            Logger.getLogger(TourSenderBehaviour.class.getName()).log(Level.SEVERE, null, ex);
        }
        myAgent.send(inform);
    }
}
