package it.polito.ezqueue.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class Desk {
    private Integer deskId;
    private Map<String, Serv> deskServices;
    private Boolean deskOpen;
    private Integer currentTicketServed;

    public void setCurrentTicketServed(Integer currentTicketServed) {
        this.currentTicketServed = currentTicketServed;
    }

    public Desk(Integer deskId) {
        this.deskId = deskId;
        this.deskServices = new HashMap<>();
        this.deskOpen=true;
        this.currentTicketServed = -1;
    }

    public Desk(Integer deskId, Map<String, Serv> deskServices) {
        this.deskId = deskId;
        this.deskServices = deskServices;
        this.deskOpen=true;
        this.currentTicketServed = -1;
    }

    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public Map<String, Serv> getDeskServices() {
        return deskServices;
    }

    public void setDeskServices(Map<String, Serv> deskServices) {
        this.deskServices = deskServices;
    }

    public void  addDeskService (Serv s)
    {
        this.deskServices.put(s.getServId(), s);
    }

    public boolean isServed (String serviceId)
    {
        for(Serv s : this.deskServices.values())
        {
            if(s.getServId().equals(serviceId)) return true;
        }
        return false;
    }

    public Boolean getDeskOpen() {
        return deskOpen;
    }

    public void setDeskOpen(Boolean deskOpen) {
        this.deskOpen = deskOpen;
    }
}