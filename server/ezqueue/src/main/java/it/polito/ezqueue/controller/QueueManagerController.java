package it.polito.ezqueue.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.ezqueue.entity.Serv;
import it.polito.ezqueue.service.QueueManagerService;
import it.polito.ezqueue.resources.Constants;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(Constants.HOME) //must wait for API
public class QueueManagerController {

    private final QueueManagerService qmService;

    public QueueManagerController(QueueManagerService qmService) {
        this.qmService = qmService;
    }

    @GetMapping(Constants.LOAD_SERVICES)
    public ResponseEntity loadServices() {
        return ResponseEntity.ok(qmService.getServices());
    }

    @GetMapping(Constants.GET_ACTIVE_SERVICES)
    public ResponseEntity getActiveServices() {
        return ResponseEntity.ok(qmService.activeServs());
    }

    @PostMapping(Constants.TICKET_REQUEST)
    public ResponseEntity ticketRequest(@RequestBody String serviceRequested) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(serviceRequested, Map.class);
        Integer clientNumber = qmService.getTicketNumberAndIncrease();
        double waitingTime;
        for (Serv serv : qmService.activeServs())
            if (serv.isActive() && serv.getServId().equals(map.get("serviceRequested"))) {
                serv.addTicket(clientNumber);
                break;
            }
        //Evaluate the waiting time TODO: assuming to not consider the current client
        waitingTime = qmService.getEstimatedWaitingTime(map.get("serviceRequested").toString());
        HashMap<String, String> res = new HashMap<>();
        res.put("estimatedWaitingTime", String.valueOf(waitingTime));
        res.put("clientNumber", String.valueOf(clientNumber));
        return ResponseEntity.ok(res);
    }



    /*TODO
    '/API/customer_newNumber' -> prendi nuovo numero
    "/API/employee_getNext/"+counterNumber -> chiama next
    */

}