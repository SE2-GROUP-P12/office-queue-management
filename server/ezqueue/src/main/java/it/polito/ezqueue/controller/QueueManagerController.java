package it.polito.ezqueue.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.polito.ezqueue.resources.Constants;
import it.polito.ezqueue.service.QueueManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
        HashMap<String, String> res = qmService.getEstimatedTime(serviceRequested);
        return ResponseEntity.ok(res);
    }






    /*TODO
    '/API/customer_newNumber' -> prendi nuovo numero
    "/API/employee_getNext/"+counterNumber -> chiama next
    */

}