package it.polito.ezqueue.controller;

import it.polito.ezqueue.service.QueueManagerService;
import it.polito.ezqueue.resources.Constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.HOME) //must wait for API
public class QueueManagerController {

    private final QueueManagerService qmService;

    public QueueManagerController(QueueManagerService qmService)
    {
        this.qmService=qmService;
    }

    @GetMapping(Constants.LOAD_SERVICES)
    public ResponseEntity loadServices ()
    {
        return ResponseEntity.ok(qmService.activeServs());
    }



    /*TODO
    '/API/customer_newNumber' -> prendi nuovo numero
    "/API/employee_getNext/"+counterNumber -> chiama next
    */

}