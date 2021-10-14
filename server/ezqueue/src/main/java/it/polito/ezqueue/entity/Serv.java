package it.polito.ezqueue.entity;

import java.util.ArrayList;

public class Serv {
    private String serviceId;
    private Float serviceTime;
    private ArrayList<Integer> serviceQueue;

    public Serv(String serviceId, Float serviceTime) {
        this.serviceId = serviceId;
        this.serviceTime = serviceTime;
        this.serviceQueue= new ArrayList<Integer>();
    }

    public String getServId() {
        return serviceId;
    }

    public void setServId(String serviceID) {
        this.serviceId = serviceID;
    }

    public Float getServTime() {
        return serviceTime;
    }

    public void setServTime(Float serviceTime) {
        this.serviceTime = serviceTime;
    }

    public boolean addTicket (Integer num)
    {return this.serviceQueue.add(num); }

    public boolean removeTicket (Integer num)
    { return this.serviceQueue.remove(num); }

    @Override
    public String toString() {
        return "Serv{" +
                "serviceId='" + serviceId + '\'' +
                ", serviceTime=" + serviceTime +
                ", serviceQueue=" + serviceQueue +
                '}';
    }
}