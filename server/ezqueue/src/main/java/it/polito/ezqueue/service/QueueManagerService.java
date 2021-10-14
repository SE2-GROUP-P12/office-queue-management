package it.polito.ezqueue.service;

import it.polito.ezqueue.entity.Desk;
import it.polito.ezqueue.entity.Serv;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Getter
@Setter
public class QueueManagerService {
    private List<Desk> desks;
    private ArrayList<Serv> services;
    private Integer serving;
    private Integer nextNumber;

    public QueueManagerService(ArrayList<Desk> desks, ArrayList<Serv> services) {
        initDesks();
        this.services=services;
        this.serving=0;
        this.nextNumber=1;
    }

    private void initDesks() {
        //System.out.println("Ciao");
        List<Desk> list = new LinkedList<>();
        this.desks = list;
    }

    public List<Desk> getDesks() {
        return desks;
    }

    public void setDesks(ArrayList<Desk> desks) {
        this.desks = desks;
    }

    public ArrayList<Serv> getServs() {
        return services;
    }

    public void setServs(ArrayList<Serv> services) {
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

    public ArrayList<Serv> activeServs ()
    {
        ArrayList<Serv> active = new ArrayList<Serv>();
        for(Desk d : desks)
        {
            if(d.getDeskOpen())
            {
                for(Serv s : d.getDeskServices())
                {
                    if(!active.contains(s))
                        active.add(s);
                }
            }
        }
        return active;
    }

}
