package it.polito.ezqueue.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Desk {
    private Integer deskId;
    private HashMap<String, Serv> deskServices;
    private Boolean deskOpen;

    public Desk(Integer deskId) {
        this.deskId = deskId;
        this.deskServices = new HashMap<>();
        this.deskOpen=true;
    }

    public Desk(Integer deskId, HashMap<String, Serv> deskServices) {
        this.deskId = deskId;
        this.deskServices = deskServices;
    }

    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public HashMap<String, Serv> getDeskServices() {
        return deskServices;
    }

    public void setDeskServices(HashMap<String, Serv> deskServices) {
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