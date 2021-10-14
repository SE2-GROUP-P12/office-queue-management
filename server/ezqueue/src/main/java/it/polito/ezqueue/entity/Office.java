package it.polito.ezqueue.entity;

import java.util.ArrayList;

public class Office {
    private ArrayList<Desk> desks;
    private ArrayList<Serv> services;
    private Integer serving;
    private Integer newNumber;

    public Office(ArrayList<Desk> desks, ArrayList<Serv> services) {
        this.desks= desks;
        this.services=services;
        this.serving=0;
        this.newNumber=1;
    }

    public ArrayList<Desk> getDesks() {
        return desks;
    }

    public void setDesks(ArrayList<Desk> desks) {
        this.desks = desks;
    }

    public ArrayList<Serv> getServices() {
        return services;
    }

    public void setServices(ArrayList<Serv> services) {
        this.services = services;
    }

    public Integer getServing() {
        return serving;
    }

    public void setServing(Integer serving) {
        this.serving = serving;
    }

    public Integer getNewNumber() {
        return newNumber;
    }

    public void setNewNumber(Integer newNumber) {
        this.newNumber = newNumber;
    }
}