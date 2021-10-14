package it.polito.ezqueue.entity;

import java.util.ArrayList;

public class Desk {
    private Integer deskId;
    private ArrayList<Serv> deskServices;
    private Boolean deskOpen;

    public Desk(Integer deskId) {
        this.deskId = deskId;
        this.deskServices=new ArrayList<Serv>();
        this.deskOpen=true;
    }

    public Desk(Integer deskId, ArrayList<Serv> deskServices) {
        this.deskId = deskId;
        this.deskServices = deskServices;
    }

    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public ArrayList<Serv> getDeskServices() {
        return deskServices;
    }

    public void setDeskServices(ArrayList<Serv> deskServices) {
        this.deskServices = deskServices;
    }

    public boolean addDeskService (Serv s)
    {
        return this.deskServices.add(s);
    }

    public boolean isServed (String serviceId)
    {
        for(Serv s : this.deskServices )
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