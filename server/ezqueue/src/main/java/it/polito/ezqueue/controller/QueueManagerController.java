package it.polito.ezqueue.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.polito.ezqueue.entity.Serv;
import it.polito.ezqueue.resources.Constants;
import it.polito.ezqueue.service.QueueManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<Serv>> loadServices() {
        return ResponseEntity.ok(new ArrayList<>(qmService.getServices().values()));
    }

    /**
     * Controller -> Get method (since @Getter annotation is used there is no explicit reference to that method, by the way it returns list of ALL ACTIVE services
     *      * encoded in json standard format)
     * @return json package to send to server
     */
    @GetMapping(Constants.GET_ACTIVE_SERVICES)
    public ResponseEntity<List<Serv>> getActiveServices() {
        return ResponseEntity.ok(qmService.activeServs());
    }

    /**
     * Controller -> Post method (check the method documentation into QueueManagerServices for other information)
     * @return json package to send to server
     */
    @PostMapping(Constants.TICKET_REQUEST)
    public ResponseEntity ticketRequest(@RequestBody String serviceRequested){
        Map<String, String> res;
        try {
            res = qmService.getEstimatedTimeAndTicketNumber(serviceRequested);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(res);
    }

    /**
     * Controller -> Get method (check the method documentation into QueueManagerServices for other information)
     * @return json package to send to server
     */
    @PostMapping(Constants.GET_NEXT)
    public ResponseEntity queueUpdate(@RequestBody String data) {
        try {
            return ResponseEntity.ok(qmService.getNextNumberAndUpdateQueue(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    /**
     * Controller -> Post method (check the method documentation into QueueManagerServices for other information)
     * @return json package to send to server
     */
    @PostMapping(Constants.TOGGLE_OPEN)
    public ResponseEntity<Boolean> toggleOpen (@RequestBody String data){
        try{
            return ResponseEntity.ok(qmService.toggleOpenDesk(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    /**
     * Controller -> Post method (check the method documentation into QueueManagerServices for other information)
     * @return json package to send to server
     */
    @PostMapping(Constants.GET_OPEN)
    public ResponseEntity<Boolean> getOpen (@RequestBody String data) {
        try{
            return ResponseEntity.ok(qmService.getOpenDesk(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    public QueueManagerService getQmService() {
        return qmService;
    }
}