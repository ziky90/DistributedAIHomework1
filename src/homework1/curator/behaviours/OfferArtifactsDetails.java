package homework1.curator.behaviours;

import homework1.curator.Element;
import homework1.curator.ElementsDatabase;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * behaviour that should response to all the requests for the particular item
 * @author zikesjan
 */
public class OfferArtifactsDetails extends CyclicBehaviour{

    public OfferArtifactsDetails(Agent a){
        super(a);
    }
    
    
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg != null){
            String name = msg.getContent();
            System.out.println("<"+myAgent.getLocalName()+">:: request for element "+name);
            Element e = ElementsDatabase.getElement(name);
            
            ACLMessage reply = msg.createReply();                               
            
            if(e != null){
                try {
                    reply.setContentObject(e);                                  //XXX maybe feasable way how to send objects
                    reply.setPerformative(ACLMessage.PROPOSE);
                    
                } catch (IOException ex) {
                    Logger.getLogger(OfferArtifactsDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                System.out.println("<"+myAgent.getLocalName()+">:: Desired item not found");
                reply.setContent("Desired item not found");      
                reply.setPerformative(ACLMessage.REFUSE);
            }
            myAgent.send(reply);
            System.out.println("<"+myAgent.getLocalName()+">:: elements "+ name +" details sent");
        }
    }
    
}
