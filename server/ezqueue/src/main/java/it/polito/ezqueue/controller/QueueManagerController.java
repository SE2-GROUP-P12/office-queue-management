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

    /**
     * Controller -> Get method (since @Getter annotation is used there is no explicit reference to that method, by the way it returns list of ALL services
     * encoded in json standard format)
     * @return json package to send to server
     */
    @GetMapping(Constants.LOAD_SERVICES)
    public ResponseEntity loadServices() {
        return ResponseEntity.ok(qmService.getServices());
    }

    /**
     * Controller -> Get method (since @Getter annotation is used there is no explicit reference to that method, by the way it returns list of ALL ACTIVE services
     *      * encoded in json standard format)
     * @return json package to send to server
     */
    @GetMapping(Constants.GET_ACTIVE_SERVICES)
    public ResponseEntity getActiveServices() {
        return ResponseEntity.ok(qmService.activeServs());
    }

    /**
     * Controller -> Post method (check the method documentation into QueueManagerServices for other information)
     * @return json package to send to server
     */
    @PostMapping(Constants.TICKET_REQUEST)
    public ResponseEntity ticketRequest(@RequestBody String serviceRequested) throws JsonProcessingException {
        HashMap<String, String> res = qmService.getEstimatedTimeAndTicketNumber(serviceRequested);
        return ResponseEntity.ok(res);
    }

    /**
     * Controller -> Get method (check the method documentation into QueueManagerServices for other information)
     * @return json package to send to server
     */
    @PostMapping(Constants.GET_NEXT)
    public ResponseEntity queueUpdate(@RequestBody String data) throws JsonProcessingException {
        return ResponseEntity.ok(qmService.getNextNumberAndUpdateQueue(data));
    }

}