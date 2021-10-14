package it.polito.ezqueue.controller;

import it.polito.ezqueue.service.QueueManagerService;
import it.polito.ezqueue.resources.Constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = Constants.HOME) //must wait for API
public class QueueManagerController {

    private final QueueManagerService qmService;

    public QueueManagerController(QueueManagerService qmService)
    {
        this.qmService=qmService;
    }

    /*TODO
    "/API/custome_loadServices/" -> lista servizi attivi
    '/API/customer_newNumber' -> prendi nuovo numero
    "/API/employee_getNext/"+counterNumber -> chiama next
    */

}