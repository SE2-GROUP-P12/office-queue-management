package it.polito.ezqueue.objects;

import java.util.ArrayList;

@Entity
public class Desk {
    private Integer deskId;
    private ArrayList<Service> deskServices;
    private Boolean deskOpen;

    public Desk(Integer deskId) {
        this.deskId = deskId;
        this.deskServices=new ArrayList<Service>();
        this.deskOpen=false;
    }

    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public ArrayList<Service> getDeskServices() {
        return deskServices;
    }

    public void setDeskServices(ArrayList<Service> deskServices) {
        this.deskServices = deskServices;
    }

    public boolean addDeskService (Service s)
    {
        return this.deskServices.add(s);
    }

    public boolean isServed (String serviceId)
    {
        for(Service s : this.deskServices )
        {
            if(s.getServiceId().equals(serviceId)) return true;
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