package it.polito.ezqueue.service;

import it.polito.ezqueue.entity.Desk;
import it.polito.ezqueue.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QueueManagerService {
    private ArrayList<Desk> desks;
    private Ticket serving;
    private ArrayList<Ticket> queue;

    public QueueManagerService(ArrayList<Desk> desks, Ticket serving, ArrayList<Ticket> queue) {
        this.desks = desks;
        this.serving = serving;
        this.queue = queue;
    }

    public QueueManagerService() {
        this.desks=new ArrayList<Desk>();
        this.serving=new Ticket();
        this.queue=new ArrayList<Ticket>();
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
