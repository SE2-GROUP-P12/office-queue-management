package it.polito.ezqueue.entity;

import lombok.Data;

import java.util.List;

//Note: Office class is not used, the "office" is handled by the queueManagerSystem that is capable to manage only
//      one single office
@Data
public class Office {
    private List<Desk> desks;
    private List<Serv> services;
    private Integer serving;
    private Integer newNumber;

    public Office(List<Desk> desks, List<Serv> services) {
        this.desks= desks;
        this.services=services;
        this.serving=0;
        this.newNumber=1;
    }

}