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
    //CONFIG FILE PATH
    static final String CFG_PATH = "server/ezqueue/src/main/java/it/polito/ezqueue/resources/config.yml";
    //SERVICES:
    static final Serv serv1 = new Serv("SERVICE1", Float.parseFloat("11.00"));
    static final Serv serv2 = new Serv("SERVICE2", Float.parseFloat("12.00"));
    static final Serv serv3 = new Serv("SERVICE3", Float.parseFloat("13.00"));
    static final Serv serv4 = new Serv("SERVICE4", Float.parseFloat("14.00"));
    static final Serv serv5 = new Serv("SERVICE5", Float.parseFloat("15.00"));
    //DESKS DESK_CONFIG  (services dependencies):
    static final Desk desk1 = new Desk(1);
    static final Desk desk2 = new Desk(2);
    static final Desk desk3 = new Desk(3);
    static final Desk desk4 = new Desk(4);
    //ENVIRONMENT
    static final Integer MAX_ITEM_QUEUE = Integer.MAX_VALUE;
}
