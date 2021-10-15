package it.polito.ezqueue.service;

import it.polito.ezqueue.entity.Desk;
import it.polito.ezqueue.entity.Serv;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static it.polito.ezqueue.resources.Constants.*;

@Service
@Getter
@Setter
public class QueueManagerService {
    private List<Desk> desks;
    private HashMap<String, Serv> services;
    private Integer serving;
    private Integer nextNumber;





    public QueueManagerService(ArrayList<Desk> desks, ArrayList<Serv> services) {
        initServices();
        initDesks();
        this.serving=0;
        this.nextNumber=1;
    }

    private void initServices(){
        /*
         * DB access, replace hardcoded data
         */
        this.services = new HashMap<>();
        this.services.put(serv1.getServId(),serv1);
        this.services.put(serv2.getServId(),serv2);
        this.services.put(serv3.getServId(),serv3);
        this.services.put(serv4.getServId(),serv4);
        this.services.put(serv5.getServId(),serv5);

    }

    private void initDesks() {
        /*
         * DB access, replace hardcoded data
         */
        this.desks = new LinkedList<>();
        this.desks.add(desk1);
        this.desks.add(desk2);
        this.desks.add(desk3);
        this.desks.add(desk4);
        this.desks.get(0).addDeskService(serv1);
        this.desks.get(0).addDeskService(serv2);
        this.desks.get(1).addDeskService(serv1);
        this.desks.get(1).addDeskService(serv3);
        this.desks.get(2).addDeskService(serv1);
        this.desks.get(2).addDeskService(serv2);
        this.desks.get(3).addDeskService(serv1);
        this.desks.get(3).addDeskService(serv4);
        this.desks.get(3).addDeskService(serv5);
    }

    public List<Desk> getDesks() {
        return desks;
    }

    public void setDesks(ArrayList<Desk> desks) {
        this.desks = desks;
    }

    public HashMap<String, Serv> getServs() {
        return services;
    }

    public void setServs(HashMap<String, Serv> services) {
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

    public Integer getTicketNumberAndIncrease(){
        return this.nextNumber++;
    }

    public ArrayList<Serv> activeServs ()
    {
        ArrayList<Serv> active = new ArrayList<>();
        for(Desk d : desks)
        {
            if(d.getDeskOpen())
            {
                for(Serv s : d.getDeskServices().values())
                {
                    if(!active.contains(s)) {
                        active.add(s);
                        s.setActive(true);
                    }
                }
            }
        }
        return active;
    }

    public double getEstimatedWaitingTime(String serviceId){
        double tmpSum = 0.00;
        int index = 0;
        int servId = -1;
        double res;
        for (Desk desk : desks) {
            for (Serv serv : desk.getDeskServices().values())
                if (serv.getServId().equals(serviceId))
                    tmpSum += (double) 1 / (desk.getDeskServices().values().size());
        }
        res = (double) (services.get(serviceId).getServTime() * ((services.get(serviceId)).getServiceQueue().size() / tmpSum) + 0.50);
        return res;
    }

}
