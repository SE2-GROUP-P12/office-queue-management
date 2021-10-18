package it.polito.ezqueue.service;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlStream;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.polito.ezqueue.entity.Desk;
import it.polito.ezqueue.entity.Serv;
import it.polito.ezqueue.resources.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static it.polito.ezqueue.resources.Constants.*;

@Service
@Getter
@Setter
public class QueueManagerService {
    private Map<Integer, Desk> desks;
    private Map<String, Serv> services;
    private Integer serving;
    private Integer nextNumber;


    public QueueManagerService(List<Desk> desks, List<Serv> services) {
        initConfig();
        this.serving = 0;
        this.nextNumber = 1;
    }

    public void initConfig() {
        initConfig(Constants.CFG_PATH);
    }

    public void initConfig(String path) {
        this.services = new HashMap<>();
        this.desks = new HashMap<>();

        System.out.println("Loading Configuration: ");

        try {
            YamlMapping yaml = Yaml.createYamlInput(new File(path)).readYamlMapping();
            Iterator<YamlNode> s = yaml.yamlSequence("services").iterator();
            while(s.hasNext()) {
                YamlMapping yamlServ = s.next().asMapping().yamlMapping("service");
                String serviceId = yamlServ.string("service-id");
                String serviceDescription = yamlServ.string("service-description");
                Float serviceTime = yamlServ.floatNumber("service-time");
                this.services.put(serviceId, new Serv(serviceId, serviceDescription, serviceTime));
                System.out.println("Added Service: ");
                System.out.println("\t- service-id: " + serviceId);
                System.out.println("\t- service-description: " + serviceDescription);
                System.out.println("\t- service-time: " + serviceTime);
            }

            Iterator<YamlNode> d = yaml.yamlSequence("desks").iterator();
            while(d.hasNext()) {
                YamlMapping yamlDesk = d.next().asMapping().yamlMapping("desk");
                Integer deskId = yamlDesk.integer("desk-id");

                Map<String, Serv> servMap = new HashMap<>();
                Iterator<YamlNode> ds = yamlDesk.yamlSequence("offered-services").iterator();

                while(ds.hasNext())
                {
                    String servIdToAdd = ds.next().asScalar().value();
                    Serv servToAdd = services.get(servIdToAdd);
                    servMap.put(servIdToAdd, servToAdd);
                }
                this.desks.put(deskId, new Desk(deskId, servMap));
                System.out.println("Added Desk: ");
                System.out.println("\t- desk-id: " + deskId);
                System.out.println("\t- offered-services: ");
                for(String os: servMap.keySet())
                    System.out.println("\t\t"+os);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * The following function provides the json package with estimated time and requested ticket number to the client (front-end)
     * It computes the estimated time as illustrated in the specifics providing in the pdf. Since it is a Service instance, its unique aim is to compute
     * json data to pass to the Controller which will proceed with mapping response.
     * Concerning the json structure, it uses all strings to as data type (that means front-end has to take care about that)
     * following structure could be an example:
     * {"estimatedTime" : "12.56", "clientNumber" : "10" }
     *
     * @param serviceRequested which is the json string sent from client which describes the specific service he/she asked for.
     * @return HashMap<String, String> with the two key-values item
     * @throws JsonProcessingException\
     */
    public Map<String, String> getEstimatedTimeAndTicketNumber(String serviceRequested) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(serviceRequested, Map.class);
        Integer clientNumber = this.getTicketNumberAndIncrease();
        double waitingTime;
        for (Serv serv : this.activeServs())
            if (serv.isActive() && serv.getServId().equals(map.get("serviceRequested"))) {
                serv.addTicket(clientNumber);
                break;
            }
        //Evaluate the waiting time TODO: assuming to not consider the current client
        waitingTime = this.getEstimatedWaitingTime(map.get("serviceRequested").toString());
        HashMap<String, String> res = new HashMap<>();
        res.put("estimatedWaitingTime", String.valueOf(waitingTime));
        res.put("clientNumber", String.valueOf(clientNumber));
        return res;
    }

    public Map<Integer, Desk> getDesks() {
        return desks;
    }


    public Map<String, Serv> getServs() {
        return services;
    }


    public Integer getServing() {
        return serving;
    }

    public void setServing(Integer serving) {
        this.serving = serving;
    }

    public Integer getNextNumber() {
        return nextNumber;
    }

    public void setNextNumber(Integer nextNumber) {
        this.nextNumber = nextNumber;
    }

    public Integer getTicketNumberAndIncrease() {
        return this.nextNumber++;
    }

    public List<Serv> activeServs() {
        ArrayList<Serv> active = new ArrayList<>();
        for (Desk d : desks.values()) {
            if (d.getDeskOpen()) {
                for (Serv s : d.getDeskServices().values()) {
                    if (!active.contains(s)) {
                        active.add(s);
                        s.setActive(true);
                    }
                }
            }
        }
        return active;
    }

    public double getEstimatedWaitingTime(String serviceId) {
        double tmpSum = 0.00;

        double res;
        for (Desk desk : desks.values()) {
            for (Serv serv : desk.getDeskServices().values())
                if (serv.getServId().equals(serviceId))
                    tmpSum += (double) 1 / (desk.getDeskServices().values().size());
        }
        res = (services.get(serviceId).getServTime() * ((services.get(serviceId)).getServiceQueue().size() / tmpSum) + 0.50);
        return res;
    }

    /**
     * This function provides information related to the queue status and current ticket number served (with relative service) to the employee at the desk.
     * It computes the next ticket number to be served then it builds a json structure with all this information.
     * json structure uses only string (take care of that in frontend)
     * json structure is quite complex since it is composed by 2 main element:
     *      1   type of message ("messageType" in the structure) which is a string concerning the actual state of the queue in order to inform the employee if
     *          there are other client to serve at that desk ("serve next ticket") or if there are no client to serve which means all services' queue are empty
     *          ("no more ticket to serve")
     *      2   if there are clients to serve ("serve next ticket") there are some data concerning the ticketNumber to serve according to the documentation (pdf) and
     *          the service requested for that ticket
     * json structures could be:
     *      1. (no more clients to serve):
     *          {
     *              "messageType": {
     *                  "action": "no more ticket to serve"
     *              }
     *          }
     *
     *      2. (other clients to serve):
     *          {
     *              "messageType": {
     *                  "action": "serve next ticket"
     *              },
     *              "data": {
     *                  "TicketToServe": "1",
     *                  "serviceId": "SERVICE2"
     *              }
     * }
     *
     * @param requestBody is a string composed by the string version of the deskId which has requested the queue shifting
     * @return ObjectNode which is a Jackson type to compose complex json structure has illustrated before.
     * @throws JsonProcessingException
     */
    public ObjectNode getNextNumberAndUpdateQueue(String requestBody) throws JsonProcessingException {
        ObjectMapper mapperResponse = new ObjectMapper();
        ObjectNode rootNode = mapperResponse.createObjectNode();
        ObjectNode typeOfMessageNode = mapperResponse.createObjectNode();
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(requestBody, Map.class);
        //Get the longest queue among the ones offered by the desk
        Serv longestQueueSizeServ = this.desks.get(Integer.valueOf(map.get("deskId").toString()))
                .getDeskServices().values()
                .stream().max(Comparator.comparing(Serv::getQueueSize)).get();
        //TODO: How can I know when a new ticket will be available to serve? -> there are a scenario to handle in the new ticket routine.
        if (longestQueueSizeServ.getQueueSize() == 0) {
            System.out.println("At the moment all the services offered by the desk are empty.");
            typeOfMessageNode.put("action", "no more ticket to serve");
            rootNode.set("messageType", typeOfMessageNode);
            return rootNode;
        }
        //Add new TicketNumber to serve and removing the old one from the queue
        Integer ticketToServe = longestQueueSizeServ.getServiceQueue().remove(0);
        //Create json encoded data structure -> type of service (serve next ticket (with associated data)/ non more ticket to serve(non associated data))
        //ALL DATA ARE STRING -> PAY ATTENTION DURING CONVERSION IN FRONT END
        typeOfMessageNode.put("action", "serve next ticket");
        rootNode.set("messageType", typeOfMessageNode);
        ObjectNode associatedData = mapper.createObjectNode();
        associatedData.put("TicketToServe", String.valueOf(ticketToServe));
        associatedData.put("serviceId", longestQueueSizeServ.getServId());
        rootNode.set("data", associatedData);
        // set the served number for the desk
        this.desks.get(Integer.valueOf(map.get("deskId").toString())).setCurrentTicketServed(ticketToServe);
        return rootNode;
    }

}
