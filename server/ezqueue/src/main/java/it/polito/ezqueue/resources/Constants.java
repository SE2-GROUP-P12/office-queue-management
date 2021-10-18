package it.polito.ezqueue.resources;

import it.polito.ezqueue.entity.Desk;
import it.polito.ezqueue.entity.Serv;

import java.util.ArrayList;

public interface Constants {
    //API_PATH:
    static final String HOME = "/API";
    static final String LOAD_SERVICES= "/customer_loadServices";
    static final String GET_NEXT = "/employee_getNext";
    static final String  GET_ACTIVE_SERVICES = "/activeServices";
    static final String TICKET_REQUEST = "/requestTicket";
    static final String TOGGLE_OPEN = "/employee_toggleOpen";
    static final String GET_OPEN ="/employee_getOpen";

    //CONFIG FILE PATH
    static final String CFG_PATH = "server/ezqueue/src/main/java/it/polito/ezqueue/resources/config.yml";

    //ENVIRONMENT
    static final Integer MAX_ITEM_QUEUE = Integer.MAX_VALUE;
}
