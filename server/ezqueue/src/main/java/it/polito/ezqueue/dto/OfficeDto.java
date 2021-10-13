package it.polito.ezqueue.dto;

import java.util.ArrayList();

@Entity
public class Office {
    private ArrayList<Desk> desks;
    private Ticket serving;
    private ArrayList<Ticket> queue;

    public Office(Integer nDesks) {
        this.desks=new ArrayList<Desk>();
        for(int i=0; i<nDesks; i++)
            this.desks.add(new Desk(i));
        this.serving=new Ticket();
        this.queue= new ArrayList<Ticket>();
    }

    public ArrayList<Desk> getDesks() {
        return desks;
    }

    public void setDesks(ArrayList<Desk> desks) {
        this.desks = desks;
    }

    public Ticket getServing() {
        return serving;
    }

    public void setServing(Ticket serving) {
        this.serving = serving;
    }

    public ArrayList<Ticket> getQueue() {
        return queue;
    }

    public void setQueue(ArrayList<Ticket> queue) {
        this.queue = queue;
    }
}