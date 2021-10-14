package it.polito.ezqueue.service;

import it.polito.ezqueue.entity.Desk;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QueueManagerService {
    private ArrayList<Desk> desks;
    private ArrayList<Service> services;
    private Integer serving;
    private Integer nextNumber;

    public QueueManagerService(ArrayList<Desk> desks, ArrayList<Service> services) {
        this.desks = desks;
        this.services=services;
        this.serving=0;
        this.nextNumber=1;
    }

    public ArrayList<Desk> getDesks() {
        return desks;
    }

    public void setDesks(ArrayList<Desk> desks) {
        this.desks = desks;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public Integer getServing() {
        return serving;
    }

    public void setServing(Integer serving) {
        this.serving = serving;
    }

    public Integer getNextNumber() {
        return nextNumber;
    }

    public void setNextNumber(Integer nextNumber) {
        this.nextNumber = nextNumber;
    }



}
